/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.ArrayList;

import org.testtoolinterfaces.testsuite.ParameterArrayList;

/**
 * @author arjan.kranenburg
 *
 */
public interface TestStepResult extends TestResult
{
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
	public void setResult(VERDICT aResult);

	public VERDICT getResult();

	public void setDisplayName( String aDisplayName );
	
	public String getDisplayName();
	
	public ParameterArrayList getParameters();

	public void addSubStep( TestStepResult aSubStepResult );

	public ArrayList<TestStepResult> getSubSteps();
	
    public ArrayList<ParameterResult> getParameterResults();

	public void setParameterResults(ArrayList<ParameterResult> aParameterResults);

	public void register( TestStepResultObserver anObserver );

	public void unRegisterObserver( TestStepResultObserver anObserver );
}
