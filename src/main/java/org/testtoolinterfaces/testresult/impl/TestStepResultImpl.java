/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Iterator;

import org.testtoolinterfaces.testresult.ParameterResult;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testresult.TestStepResultObserver;
import org.testtoolinterfaces.testsuite.ParameterArrayList;
import org.testtoolinterfaces.testsuite.TestStep;
import org.testtoolinterfaces.testsuite.TestStepCommand;
import org.testtoolinterfaces.testsuite.TestStepScript;
import org.testtoolinterfaces.testsuite.TestStepSelection;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author arjan.kranenburg
 *
 */
public abstract class TestStepResultImpl extends TestResultImpl implements TestStepResult
{
	private VERDICT myResult = VERDICT.UNKNOWN;

    private ArrayList<TestStepResultObserver> myObserverCollection;
    private ArrayList<TestStepResult> mySubStepResults;

    private ArrayList<ParameterResult> myParameterResults;

	/**
	 * @param aTestCaseName
	 */
	public TestStepResultImpl(TestStep aTestStep)
	{
		super( aTestStep );

	    Trace.println(Trace.CONSTRUCTOR, "TestStepResult( " + aTestStep + " )" );

		myObserverCollection = new ArrayList<TestStepResultObserver>();
	    mySubStepResults = new ArrayList<TestStepResult>();
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

	
	public void setDisplayName( String aDisplayName )
	{
	    Trace.println(Trace.SETTER);
		((TestStep) this.getTestEntry()).setDisplayName(aDisplayName);
	}
	
	public String getDisplayName()
	{
	    Trace.println(Trace.GETTER);
		return ((TestStep) this.getTestEntry()).getDisplayName();
	}
	
	/**
	 * Returns the full execution path of id's each seperated by a '.'.
	 * The id of this result is included and added last. 
	 */
	public String getExecutionIdPath() {
		return this.getExecutionPath() + "." + this.getDisplayName();
	}

	@Override
	public void setExecutionPath(String anExecutionPath)
	{
		super.setExecutionPath(anExecutionPath);

		Iterator<TestStepResult> subStepItr = mySubStepResults.iterator();
		while (subStepItr.hasNext())
		{
			subStepItr.next().setExecutionPath(anExecutionPath + "." + this.getDisplayName());
		}
	}

	public ParameterArrayList getParameters()
	{
	    Trace.println(Trace.GETTER);
		return ((TestStep) this.getTestEntry()).getParameters();
	}

	public void addSubStep( TestStepResult aSubStepResult )
	{
	    Trace.println(Trace.SETTER);
	    mySubStepResults.add(aSubStepResult);
	    
	    this.setResult( aSubStepResult.getResult() );
	}

	public ArrayList<TestStepResult> getSubSteps()
	{
	    Trace.println(Trace.GETTER);
		return mySubStepResults;
	}
	
    public ArrayList<ParameterResult> getParameterResults()
    {
		return myParameterResults;
	}

	public void setParameterResults(ArrayList<ParameterResult> aParameterResults)
	{
		myParameterResults = aParameterResults;
	}

	// Implementation of the Observer Pattern
	protected void notifyObservers()
	{
	    Trace.println(Trace.EXEC_PLUS);

	    for (TestStepResultObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register( TestStepResultObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver( TestStepResultObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.remove( anObserver );
	}
	
	public static TestStepResult createResult( TestStep aStep ) {
		if ( aStep instanceof TestStepCommand ) {
			return new TestStepCommandResultImpl( (TestStepCommand) aStep );
		} else if ( aStep instanceof TestStepScript ) {
			return new TestStepScriptResultImpl( (TestStepScript) aStep );
		} else if ( aStep instanceof TestStepSelection ) {
			return new TestStepSelectionResultImpl( (TestStepSelection) aStep );
		}

		//Unknown
		TestStepScript step = new TestStepScript(aStep.getSequenceNr(), "unknown", "unknown" );
		return new TestStepScriptResultImpl( step );
	}
}
