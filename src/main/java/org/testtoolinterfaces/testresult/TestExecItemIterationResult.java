/**
 * 
 */
package org.testtoolinterfaces.testresult;


/**
 * @author Arjan Kranenburg
 *
 */
public interface TestExecItemIterationResult 
	extends TestEntryIterationResult<TestGroupEntryResult>, TestGroupEntryResult
{
	public ResultSummary getSummary();

	public void register(TestGroupResultObserver aTestGroupResultObserver);
}
