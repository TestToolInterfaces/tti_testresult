/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testtoolinterfaces.testresult.TestExecItemResult;
import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testresult.observer.TestStepResultObserver;
import org.testtoolinterfaces.testsuite.TestExecItem;
import org.testtoolinterfaces.testsuite.TestGroupEntry;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public abstract class TestExecItemResultImpl extends TestGroupEntryResultImpl
		implements TestExecItemResult, TestStepResultObserver
{
    private Hashtable<Integer, TestStepResultBase> myPrepareResults;
    private Hashtable<Integer, TestStepResultBase> myRestoreResults;
    
    /**
	 * @param aTestGroupName
	 */
	public TestExecItemResultImpl(TestGroupEntry aTestGroupEntry)
	{
		super( aTestGroupEntry );

	    Trace.println(Trace.CONSTRUCTOR, "TestExecItemResultImpl( " + aTestGroupEntry + " )" );

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
	    Trace.println(Trace.SETTER);
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
	    Trace.println(Trace.SETTER);
	    myRestoreResults.put( myRestoreResults.size(), aRestoreResult );
	    
	    aRestoreResult.register(this);

	    notifyObservers();
	}

	public ArrayList<String> getRequirements()
	{
	    Trace.println(Trace.GETTER);
		return ((TestExecItem) this.getTestEntry()).getRequirements();
	}
	
	public Hashtable<Integer, TestStepResultBase> getPrepareResults()
	{
	    Trace.println(Trace.GETTER);
		return myPrepareResults;
	}
	
	public Hashtable<Integer, TestStepResultBase> getRestoreResults()
	{
	    Trace.println(Trace.GETTER);
		return myRestoreResults;
	}

//	@Override
//	public void setExecutionPath(String anExecutionPath)
//	{
//		super.setExecutionPath(anExecutionPath);
//		
//	    for (TestStepResultBase result : myPrepareResults.values())
//	    {
//	    	result.setExecutionPath(anExecutionPath + "." + this.getId());
//	    }
//
//	    for (TestStepResultBase result : myRestoreResults.values())
//	    {
//	    	result.setExecutionPath(anExecutionPath + "." + this.getId());
//	    }
//	}

	public void notify(TestStepResultBase aTestStepResult)
	{
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}
}
