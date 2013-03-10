/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testtoolinterfaces.testresult.TestExecItemResult;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testresult.TestStepResultObserver;
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
    private Hashtable<Integer, TestStepResult> myPrepareResults;
    private Hashtable<Integer, TestStepResult> myRestoreResults;
    
    /**
	 * @param aTestGroupName
	 */
	public TestExecItemResultImpl(TestGroupEntry aTestGroupEntry)
	{
		super( aTestGroupEntry );

	    Trace.println(Trace.CONSTRUCTOR, "TestExecItemResultImpl( " + aTestGroupEntry + " )" );

	    myPrepareResults = new Hashtable<Integer, TestStepResult>();
		myRestoreResults = new Hashtable<Integer, TestStepResult>();
	}

	/**
	 * @param aPrepareResult
	 */
	public void addInitialization(TestStepResult aPrepareResult)
	{
	    Trace.println(Trace.SETTER);
	    myPrepareResults.put( myPrepareResults.size(), aPrepareResult );
	    
	    aPrepareResult.register(this);

	    notifyObservers();
	}

	/**
	 * @param anInitializationResult
	 */
	public void addRestore(TestStepResult aRestoreResult)
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
	
	public Hashtable<Integer, TestStepResult> getPrepareResults()
	{
	    Trace.println(Trace.GETTER);
		return myPrepareResults;
	}
	
	public Hashtable<Integer, TestStepResult> getRestoreResults()
	{
	    Trace.println(Trace.GETTER);
		return myRestoreResults;
	}

	@Override
	public void setExecutionPath(String anExecutionPath)
	{
		super.setExecutionPath(anExecutionPath);
		
	    for (TestStepResult result : myPrepareResults.values())
	    {
	    	result.setExecutionPath(anExecutionPath + "." + this.getId());
	    }

	    for (TestStepResult result : myRestoreResults.values())
	    {
	    	result.setExecutionPath(anExecutionPath + "." + this.getId());
	    }
	}

	public void notify(TestStepResultBase aTestStepResult)
	{
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}
}
