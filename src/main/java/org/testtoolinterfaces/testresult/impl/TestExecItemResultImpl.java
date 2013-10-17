/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.TestExecItemResult;
import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testresult.observer.TestStepResultObserver;
import org.testtoolinterfaces.testsuite.TestExecItem;
import org.testtoolinterfaces.testsuite.TestGroupEntry;
import org.testtoolinterfaces.testsuite.impl.TestCaseImpl;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author Arjan Kranenburg
 *
 */
public abstract class TestExecItemResultImpl extends TestGroupEntryResultImpl
		implements TestExecItemResult, TestStepResultObserver
{
    private static final Logger LOG = LoggerFactory.getLogger(TestCaseImpl.class);

    private Hashtable<Integer, TestStepResultBase> myPrepareResults;
    private Hashtable<Integer, TestStepResultBase> myRestoreResults;
    
    /**
	 * @param aTestGroupName
	 */
	public TestExecItemResultImpl(TestGroupEntry aTestGroupEntry)
	{
		super( aTestGroupEntry );
		LOG.trace(Mark.CONSTRUCTOR, "{}",  aTestGroupEntry );

	    myPrepareResults = new Hashtable<Integer, TestStepResultBase>();
		myRestoreResults = new Hashtable<Integer, TestStepResultBase>();
	}

	/**
	 * Adds the result as a preparation result.
	 * This result is also registered with the added result in order to be notified of updates.
	 * 
	 * @param aPrepareResult
	 */
	public void addInitialization(TestStepResultBase aPrepareResult)
	{
		LOG.trace(Mark.SETTER, "{}", aPrepareResult);
	    myPrepareResults.put( myPrepareResults.size(), aPrepareResult );
	    
	    aPrepareResult.register(this);

	    notifyObservers();
	}

	/**
	 * Adds the result as a restore result.
	 * This result is also registered with the added result in order to be notified of updates.
	 * 
	 * @param anInitializationResult
	 */
	public void addRestore(TestStepResultBase aRestoreResult)
	{
		LOG.trace(Mark.SETTER, "{}", aRestoreResult);
	    myRestoreResults.put( myRestoreResults.size(), aRestoreResult );
	    
	    aRestoreResult.register(this);

	    notifyObservers();
	}

	public ArrayList<String> getRequirements()
	{
		LOG.trace(Mark.GETTER, "");
		return ((TestExecItem) this.getTestEntry()).getRequirements();
	}
	
	public Hashtable<Integer, TestStepResultBase> getPrepareResults()
	{
		LOG.trace(Mark.GETTER, "");
		return myPrepareResults;
	}
	
	public Hashtable<Integer, TestStepResultBase> getRestoreResults()
	{
		LOG.trace(Mark.GETTER, "");
		return myRestoreResults;
	}

	public void notify(TestStepResultBase aTestStepResult)
	{
		LOG.trace(Mark.EXEC_UTIL, "");
		notifyObservers();
	}
}
