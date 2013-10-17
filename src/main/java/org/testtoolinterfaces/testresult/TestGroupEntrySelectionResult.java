/**
 * 
 */
package org.testtoolinterfaces.testresult;

/**
 * @author Arjan Kranenburg
 *
 */
public interface TestGroupEntrySelectionResult extends TestGroupResult
{
	public void setIfResult( TestStepResult  aResult );

	public TestStepResult getIfResult();

	public void setSubEntryResults(TestGroupEntryResultList subEntryResults);
}
