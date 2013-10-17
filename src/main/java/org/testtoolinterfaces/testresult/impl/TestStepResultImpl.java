/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.ParameterResult;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testresult.TestStepResultList;
import org.testtoolinterfaces.testsuite.ParameterArrayList;
import org.testtoolinterfaces.testsuite.TestStep;
import org.testtoolinterfaces.testsuite.impl.TestStepCommand;
import org.testtoolinterfaces.testsuite.impl.TestStepIteration;
import org.testtoolinterfaces.testsuite.impl.TestStepScript;
import org.testtoolinterfaces.testsuite.impl.TestStepSelection;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author arjan.kranenburg
 *
 */
public abstract class TestStepResultImpl extends TestStepResultBaseImpl implements TestStepResult
{
    private static final Logger LOG = LoggerFactory.getLogger(TestStepResultImpl.class);

    private VERDICT myResult = VERDICT.UNKNOWN;

	private TestStepResultList mySubStepResults;
    private ArrayList<ParameterResult> myParameterResults;

	/**
	 * @param aTestCaseName
	 */
	public TestStepResultImpl(TestStep aTestStep)
	{
		super( aTestStep );

		LOG.trace(Mark.CONSTRUCTOR, "{}", aTestStep);

	    mySubStepResults = new TestStepResultList();
	}

	/**
	 * Sets the result, but only if the new verdict is higher in order.
	 * The order is UNKOWN, PASSED, FAILED, ERROR
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


	public VERDICT getResult()
	{
		LOG.trace(Mark.GETTER, "");
	    return myResult;
	}

	@Override
	public void setExecutionPath(String anExecutionPath)
	{
		super.setExecutionPath(anExecutionPath);

		Iterator<TestStepResultBase> subStepItr = mySubStepResults.iterator();
		while (subStepItr.hasNext())
		{
			subStepItr.next().setExecutionPath(anExecutionPath + "." + this.getDisplayName());
		}
	}

	public ParameterArrayList getParameters()
	{
		LOG.trace(Mark.GETTER, "");
		return ((TestStep) this.getTestEntry()).getParameters();
	}

	public void addSubStep( TestStepResultBase aSubStepResult )
	{
		LOG.trace(Mark.SETTER, "{}", aSubStepResult);
	    mySubStepResults.add(aSubStepResult);
	    
	    this.setResult( aSubStepResult.getResult() );
	}

	public ArrayList<TestStepResultBase> getSubSteps()
	{
		LOG.trace(Mark.GETTER, "");
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

	public static TestStepResult createResult( TestStep aStep ) {
		if ( aStep instanceof TestStepCommand ) {
			return new TestStepCommandResultImpl( (TestStepCommand) aStep );
		} else if ( aStep instanceof TestStepScript ) {
			return new TestStepScriptResultImpl( (TestStepScript) aStep );
		} else if ( aStep instanceof TestStepSelection ) {
			return new TestStepSelectionResultImpl( (TestStepSelection) aStep );
		} else if ( aStep instanceof TestStepIteration ) {
			throw new Error( "Programming error: Do not use createResult for a TestStepIteration" );
		}

		//Unknown
		TestStepScript step = new TestStepScript(aStep.getSequenceNr(), "unknown", "unknown" );
		return new TestStepScriptResultImpl( step );
	}

	protected void setSubEntryResults(TestStepResultList subStepResults) {
		this.mySubStepResults = subStepResults;
		this.myResult = subStepResults.getResult();
	}
}
