/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.Hashtable;

public interface TestResult
{
	public static enum VERDICT
	{
	    UNKNOWN,
	    PASSED,
	    ERROR,
	    FAILED;
	}

	public final static VERDICT UNKNOWN	= VERDICT.UNKNOWN;
	public final static VERDICT PASSED	= VERDICT.PASSED;
	public final static VERDICT ERROR	= VERDICT.ERROR;
	public final static VERDICT FAILED	= VERDICT.FAILED;


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
	public void setExecutionPath(String anExecutionPath);
}
