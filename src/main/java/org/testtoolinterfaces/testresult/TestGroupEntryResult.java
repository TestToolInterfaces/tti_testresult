/**
 * 
 */
package org.testtoolinterfaces.testresult;


/**
 * @author Arjan Kranenburg
 *
 */
public interface TestGroupEntryResult extends TestResult
{

	/**
	 * @return the id. It must be unique within a certain scope, e.g. within a file.
	 */
	public String getId();
	
	/**
	 * @return the myTiming
	 */
	public ResultTiming getTiming();
}
