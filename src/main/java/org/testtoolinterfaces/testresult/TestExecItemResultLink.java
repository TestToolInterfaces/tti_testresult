/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.io.File;


/**
 * @author Arjan Kranenburg
 *
 */
public interface TestExecItemResultLink extends TestGroupEntryResult
{

	/**
	 * @return the Link to the LinkedItem 
	 */
	public File getLink();

	/**
	 * @return the Test Link Type
	 */
	public String getType();
}
