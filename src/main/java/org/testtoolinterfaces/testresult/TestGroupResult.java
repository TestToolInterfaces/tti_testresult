/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.Hashtable;

/**
 * @author Arjan Kranenburg
 *
 */
public interface TestGroupResult extends TestExecItemResult
{
	public void addTestCase(TestCaseResultLink aTestCaseResultLink);

	public void addTestGroup(TestGroupResultLink aTestGroupResultLink);

	public Hashtable<Integer, TestCaseResultLink> getTestCaseResultLinks();
	
	public Hashtable<Integer, TestGroupResultLink> getTestGroupResultLinks();

	public ResultSummary getSummary();
	
	public void register( TestGroupResultObserver anObserver );

	public void unRegisterObserver( TestGroupResultObserver anObserver );

	public void notify(TestGroupResultLink aTestGroupResultLink);

	public void notify(TestCaseResultLink aTestCaseResultLink);
}
