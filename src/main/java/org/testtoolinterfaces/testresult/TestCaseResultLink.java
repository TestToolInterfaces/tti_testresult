/**
 * 
 */
package org.testtoolinterfaces.testresult;

/**
 * @author Arjan Kranenburg
 *
 */
public interface TestCaseResultLink extends TestExecItemResultLink
{
	/**
	 * If the link was used before to read the TestCase Result and it was stored with {@link #setTcResult(TestCaseResult)},
	 * you can get it here so you don't have to read it again.
	 * 
	 * @return The TestCaseResult, null if it was not read before.
	 */
	public TestCaseResult getTcResult();

	public VERDICT getResult();

	/**
	 * Sets the TC result object. If the link was followed and read, store the result here to
	 * prevent re-reading it in the future.
	 * @param myLoadedTcResult
	 */
	public void setTcResult(TestCaseResult myLoadedTcResult);

}
