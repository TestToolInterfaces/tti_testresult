/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.TestCaseResult;
import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testresult.observer.TestCaseResultObserver;
import org.testtoolinterfaces.testresult.observer.TestStepResultObserver;
import org.testtoolinterfaces.testsuite.TestCase;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestCaseResultImpl extends TestExecItemResultImpl
	implements TestCaseResult, TestStepResultObserver
{
    private static final Logger LOG = LoggerFactory.getLogger(TestCaseResultImpl.class);

    private TestCase myTestCase;
	private VERDICT myResult = VERDICT.UNKNOWN;

    private Hashtable<Integer, TestStepResultBase> myExecutionResults;
    
    private ArrayList<TestCaseResultObserver> myObserverCollection;

	/**
	 * @param aTestCase
	 */
	public TestCaseResultImpl(TestCase aTestCase)
	{
		super(aTestCase);
		LOG.trace(Mark.CONSTRUCTOR, "{}", aTestCase);

	    myTestCase = aTestCase;
	    myExecutionResults = new Hashtable<Integer, TestStepResultBase>();
		myObserverCollection = new ArrayList<TestCaseResultObserver>();
	}

	public VERDICT getResult()
	{
		LOG.trace(Mark.GETTER, "");
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
		LOG.trace(Mark.SETTER, "{}", aResult);
	    if (myResult.compareTo(aResult) < 0)
	    {
	        myResult = aResult;
		    notifyObservers();
	    }
	}

	/**
	 * @param anExecutionResult
	 */
	public void addExecution(TestStepResultBase anExecutionResult)
	{
		LOG.trace(Mark.SETTER, "{}", anExecutionResult);
		myExecutionResults.put( myExecutionResults.size(), anExecutionResult );
		setResult(anExecutionResult.getResult());

		anExecutionResult.register(this);

	    notifyObservers();
	}

	public ArrayList<String> getRequirements()
	{
		LOG.trace(Mark.GETTER, "");
		return myTestCase.getRequirements();
	}
	
	public Hashtable<Integer, TestStepResultBase> getExecutionResults()
	{
		LOG.trace(Mark.GETTER, "");
		return myExecutionResults;
	}
	
	// Implementation of the Observer Pattern
	
	protected void notifyObservers()
	{
		LOG.trace(Mark.EXEC_PLUS, "");

	    for (TestCaseResultObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register( TestCaseResultObserver anObserver )
	{
		LOG.trace(Mark.SETTER, "{}", anObserver);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver( TestCaseResultObserver anObserver )
	{
		LOG.trace(Mark.SETTER, "{}", anObserver);
	    myObserverCollection.remove( anObserver );
	}

	public void notify(TestStepResultBase aTestStepResult)
	{
		LOG.trace(Mark.EXEC_UTIL, "{}", aTestStepResult);
	    
		setResult(aTestStepResult.getResult());
		notifyObservers();
	}
}
