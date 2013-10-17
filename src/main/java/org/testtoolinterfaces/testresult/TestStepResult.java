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
public interface TestStepResult extends TestStepResultBase
{
	public ParameterArrayList getParameters();

	public void addSubStep( TestStepResultBase testStepResultBase );

	public ArrayList<TestStepResultBase> getSubSteps();
	
    public ArrayList<ParameterResult> getParameterResults();

	public void setParameterResults(ArrayList<ParameterResult> aParameterResults);

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
	public void setResult(VERDICT aResult);
}
