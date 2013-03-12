/**
 * 
 */
package org.testtoolinterfaces.testresult;

/**
 * @author Arjan Kranenburg
 *
 */
public interface TestGroupResultLink extends TestExecItemResultLink
{
	public void setSummary( ResultSummary aResultSummary );

	public ResultSummary getSummary();
	
	// Implementation of the Observer Pattern
	
	public void notify(TestGroupResult aTestGroupResult);
}
