/**
 * 
 */
package org.testtoolinterfaces.testresult;

import org.testtoolinterfaces.testresult.observer.TestStepResultObserver;

/**
 * @author arjan.kranenburg
 *
 */
public interface TestStepResultBase extends TestResult, SingleResult
{
	public void setDisplayName( String aDisplayName );
	
	public String getDisplayName();
	
	public void register( TestStepResultObserver anObserver );

	public void unRegisterObserver( TestStepResultObserver anObserver );
}
