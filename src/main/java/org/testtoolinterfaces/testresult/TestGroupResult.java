/**
 * 
 */
package org.testtoolinterfaces.testresult;

import org.testtoolinterfaces.testresult.observer.TestExecItemResultLinkObserver;
import org.testtoolinterfaces.testresult.observer.TestGroupResultObserver;

/**
 * @author Arjan Kranenburg
 *
 */
public interface TestGroupResult extends TestExecItemResult, AggregatedResult, TestExecItemResultLinkObserver
{
	public void addTestExecItemResultLink(TestExecItemResultLink aTestExecItemResultLink);

	public void addTgEntryResult(TestGroupEntryResult aTestGroupEntryResult);

	public TestGroupEntryResultList getTestGroupEntryResults();

	public void register( TestGroupResultObserver anObserver );

	public void unRegisterObserver( TestGroupResultObserver anObserver );

//	public void notify(TestGroupResultLink aTestGroupResultLink);
//
//	public void notify(TestCaseResultLink aTestCaseResultLink);
}
