/**
 * 
 */
package org.testtoolinterfaces.testresult;

/**
 * @author Arjan Kranenburg
 *
 */
public interface TestExecItemSelectionResult extends TestGroupResult
{
	public void setIfResult( TestStepResult  aResult );

	public TestStepResult getIfResult();
}
