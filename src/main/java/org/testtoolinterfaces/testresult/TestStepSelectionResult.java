/**
 * 
 */
package org.testtoolinterfaces.testresult;

/**
 * @author arjan.kranenburg
 *
 */
public interface TestStepSelectionResult extends TestStepResult
{
	public String getCommand();

	public String getScript();

	public void setIfStepResult( TestStepResult anIfStepResult );

	public TestStepResult getIfStepResult();
}
