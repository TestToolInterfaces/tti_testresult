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
	
	public Hashtable<Integer, TestStepResultBase> getPrepareResults();

	/**
	 * @param aPrepareResult
	 */
	public void addInitialization(TestStepResultBase aPrepareResult);

	public Hashtable<Integer, TestStepResultBase> getRestoreResults();

	/**
	 * @param aRestoreResult
	 */
	public void addRestore(TestStepResultBase aRestoreResult);
}
