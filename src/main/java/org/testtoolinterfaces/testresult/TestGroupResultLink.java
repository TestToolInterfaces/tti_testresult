/**
 * 
 */
package org.testtoolinterfaces.testresult;

import org.testtoolinterfaces.testresult.observer.TestGroupResultObserver;

/**
 * @author Arjan Kranenburg
 *
 */
public interface TestGroupResultLink extends TestExecItemResultLink, 
		AggregatedResult, TestGroupResultObserver
{
	public void setSummary( ResultSummary aResultSummary );

	// Implementation of the Observer Pattern
	
	public void notify(TestGroupResult aTestGroupResult);
}
