/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testtoolinterfaces.testresult.TestCaseResult;
import org.testtoolinterfaces.testresult.TestCaseResultObserver;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testresult.TestStepResultObserver;
import org.testtoolinterfaces.testsuite.TestCase;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestCaseResultImpl extends TestExecItemResultImpl
	implements TestCaseResult, TestStepResultObserver
{
	private TestCase myTestCase;
	private VERDICT myResult = VERDICT.UNKNOWN;

    private Hashtable<Integer, TestStepResult> myExecutionResults;
    
    private ArrayList<TestCaseResultObserver> myObserverCollection;

	/**
	 * @param aTestCase
	 */
	public TestCaseResultImpl(TestCase aTestCase)
	{
		super(aTestCase);

	    Trace.println(Trace.CONSTRUCTOR, "TestCaseResult( " + aTestCase + " )" );
	    myTestCase = aTestCase;

	    myExecutionResults = new Hashtable<Integer, TestStepResult>();

		myObserverCollection = new ArrayList<TestCaseResultObserver>();
	}

	public VERDICT getResult()
	{
	    Trace.println(Trace.GETTER);
	    return myResult;
	}

	/**
	 * Sets the result, but only if the new verdict is higher in order.
	 * The order is UNKOWN, PASSED, ERROR, FAILED
	 * E.g. 1) When current value is UNKNOWN, calling setResult( "ERROR" ) will actually
	 *         change the verdict to ERROR. 
	 *      2) When current value is FAILED, calling setResult( "PASSED" ) will leave the
	 *         verdict on FAILED.
	 * 
	 * @param aResult
	 */
	public void setResult(VERDICT aResult)
	{
	    Trace.println(Trace.SETTER, "setResult( " + aResult + " )", true);
	    if (myResult.compareTo(aResult) < 0)
	    {
	        myResult = aResult;
		    notifyObservers();
	    }
	}

	/**
	 * @param anExecutionResult
	 */
	public void addExecution(TestStepResult anExecutionResult)
	{
	    Trace.println(Trace.SETTER);
		myExecutionResults.put( myExecutionResults.size(), anExecutionResult );
		setResult(anExecutionResult.getResult());

		anExecutionResult.register(this);

	    notifyObservers();
	}

	@Override
	public void setExecutionPath(String anExecutionPath)
	{
		super.setExecutionPath(anExecutionPath);
		
	    for (TestStepResult result : myExecutionResults.values())
	    {
	    	result.setExecutionPath(anExecutionPath + "." + this.getId());
	    }
	}

	public ArrayList<String> getRequirements()
	{
	    Trace.println(Trace.GETTER);
		return myTestCase.getRequirements();
	}
	
	public Hashtable<Integer, TestStepResult> getExecutionResults()
	{
	    Trace.println(Trace.GETTER);
		return myExecutionResults;
	}
	
	// Implementation of the Observer Pattern
	
	protected void notifyObservers()
	{
	    Trace.println(Trace.EXEC_PLUS);

	    for (TestCaseResultObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register( TestCaseResultObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver( TestCaseResultObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.remove( anObserver );
	}

	public void notify(TestStepResult aTestStepResult)
	{
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}
}
