/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.ArrayList;
import java.util.Hashtable;


/**
 * @author Arjan Kranenburg
 *
 */
public interface TestExecItemResult extends TestGroupEntryResult
{

	public ArrayList<String> getRequirements();
	
	public Hashtable<Integer, TestStepResult> getPrepareResults();

	/**
	 * @param aPrepareResult
	 */
	public void addInitialization(TestStepResult aPrepareResult);

	public Hashtable<Integer, TestStepResult> getRestoreResults();

	/**
	 * @param aRestoreResult
	 */
	public void addRestore(TestStepResult aRestoreResult);
}
