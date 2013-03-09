/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.Hashtable;

/**
 * @author Arjan Kranenburg
 * 
 */
public interface TestCaseResult extends TestExecItemResult {

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

	/**
	 * @param anExecutionResult
	 */
	public void addExecution(TestStepResult anExecutionResult);

	public void setExecutionPath(String anExecutionPath);

	public Hashtable<Integer, TestStepResult> getExecutionResults();

	// Implementation of the Observer Pattern
	public void register(TestCaseResultObserver anObserver);

	public void unRegisterObserver(TestCaseResultObserver anObserver);

	public void notify(TestStepResult aTestStepResult);
}
