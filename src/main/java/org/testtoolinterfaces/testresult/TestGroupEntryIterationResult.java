/**
 * 
 */
package org.testtoolinterfaces.testresult;

import org.testtoolinterfaces.testresult.observer.TestGroupResultObserver;


/**
 * @author Arjan Kranenburg
 *
 */
public interface TestGroupEntryIterationResult 
	extends TestEntryIterationResult<TestGroupEntryResult>,
		TestGroupEntryResult, AggregatedResult {
	public void register(TestGroupResultObserver aTestGroupResultObserver);

}
