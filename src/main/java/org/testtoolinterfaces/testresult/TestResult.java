/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.Hashtable;
import java.util.UUID;

public interface TestResult
{
	public String getDescription();
	
	public int getSequenceNr();
	
	public void addTestLog(String aKey, String aLog);

	public Hashtable<String, String> getLogs();

	public void setComment(String aComment);

	/**
	 * Adds a comment to the existing comment string
	 * @param aComment the comment to add
	 */
	public void addComment(String aComment);

	public String getComment();

	/**
	 * Returns the full execution path of id's each seperated by a '.'.
	 * The id of this result is included and added last. 
	 */
	public String getExecutionIdPath();

	/**
	 * Simple getter for the executionPath. This is the execution path up-to this TestResult,
	 * so in effect this is the executionPath of the parent.
	 * @see getExecutionIdPath()
	 * 
	 * @return the myExecutionPath
	 */
	public String getExecutionPath();

	/**
	 * @param anExecutionPath the executionPath to set
	 */
	@Deprecated
	public void setExecutionPath(String anExecutionPath);
	
	/**
	 * Indicates if a test is finished
	 * 
	 * @return true when the test is finished, false otherwise
	 */
	public boolean isTestFinished();
	
	/**
	 * Sets the test to finished, i.e. all sub groups, cases, and/or steps (including restore) are done.
	 */
	public void setTestFinished();
	
	/**
	 * @return a Universal Unique Identifier
	 */
	public UUID getUniqueId();
}
