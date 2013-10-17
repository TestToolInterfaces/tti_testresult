/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.Hashtable;

import org.testtoolinterfaces.testresult.observer.TestCaseResultObserver;

/**
 * @author Arjan Kranenburg
 * 
 */
public interface TestCaseResult extends TestExecItemResult, SingleResult {

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

	/**
	 * @param anExecutionResult
	 */
	public void addExecution(TestStepResultBase anExecutionResult);

	public void setExecutionPath(String anExecutionPath);

	public Hashtable<Integer, TestStepResultBase> getExecutionResults();

	// Implementation of the Observer Pattern
	public void register(TestCaseResultObserver anObserver);

	public void unRegisterObserver(TestCaseResultObserver anObserver);
}
