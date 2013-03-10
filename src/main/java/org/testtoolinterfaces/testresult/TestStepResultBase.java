/**
 * 
 */
package org.testtoolinterfaces.testresult;

/**
 * @author arjan.kranenburg
 *
 */
public interface TestStepResultBase extends TestResult
{
	public VERDICT getResult();

	public void setDisplayName( String aDisplayName );
	
	public String getDisplayName();
	
	public void register( TestStepResultObserver anObserver );

	public void unRegisterObserver( TestStepResultObserver anObserver );
}
