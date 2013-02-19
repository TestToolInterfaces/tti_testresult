/**
 * 
 */
package org.testtoolinterfaces.testresult;

//import java.util.Calendar;
import java.util.Hashtable;

import org.testtoolinterfaces.utils.Trace;

public abstract class TestResult
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

	private VERDICT myResult = VERDICT.UNKNOWN;
	private Hashtable<String, String> myLogs;
	private String myComment = "";
//    private Calendar myStartDate;
//    private Calendar myEndDate;
	private String myExecutionPath = "";
	
	/**
	 * @return the id. It must be unique within a certain scope, e.g. within a file.
	 */
	abstract public String getId();
	
	public TestResult()
	{
	    Trace.println(Trace.CONSTRUCTOR);
		myLogs = new Hashtable<String, String>();
	}

	/**
	 * Sets the result, but only if the new verdict is higher in order.
	 * The order is UNKOWN, PASSED, ERROR, FAILED
	 * E.g. 1) When current value is UNKNOWN, calling setResult( "ERROR" ) will actually
	 *         change the verdict to ERROR. 
	 *      2) When current value is FAILED, calling setResult( "PASSED" ) will leave the
	 *         verdict on FAILED.
	 * 
	 * @param aResult
	 */
	public void setResult(VERDICT aResult)
	{
	    Trace.println(Trace.SETTER, "setResult( " + aResult + " )", true);
	    if (myResult.compareTo(aResult) < 0)
	    {
	        myResult = aResult;
		    notifyObservers();
	    }
	}

	public void addTestLog(String aKey, String aLog)
	{
	    Trace.println(Trace.SETTER, "addTestLog( " + aKey + ", " + aLog + " )", true);
	    myLogs.put(aKey, aLog);
	    notifyObservers();
	}

	public void setComment(String aComment)
	{
	    Trace.println(Trace.SETTER, "setComment( " + aComment + " )", true);
	    myComment = aComment;
	    notifyObservers();
	}

	/**
	 * Adds a comment to the existing comment string
	 * @param aComment the comment to add
	 */
	public void addComment(String aComment)
	{
		myComment += "\n" + aComment;
	    notifyObservers();
	}

	public VERDICT getResult()
	{
	    Trace.println(Trace.GETTER);
	    return myResult;
	}

	public Hashtable<String, String> getLogs()
	{
	    Trace.println(Trace.GETTER);
	    return myLogs;
	}

	public String getComment()
	{
	    Trace.println(Trace.GETTER);
	    return myComment;
	}

	/**
	 * Returns the full execution path of id's each seperated by a '.'.
	 * The id of this result is included and added last. 
	 */
	public String getExecutionIdPath() {
		return this.getExecutionPath() + "." + this.getId();
	}

	/**
	 * Simple getter for the executionPath. This is the execution path up-to this TestResult,
	 * so in effect this is the executionPath of the parent.
	 * @see getExecutionIdPath()
	 * 
	 * @return the myExecutionPath
	 */
	public String getExecutionPath() {
		return myExecutionPath;
	}

	/**
	 * @param anExecutionPath the executionPath to set
	 */
	public void setExecutionPath(String anExecutionPath) {
		this.myExecutionPath = anExecutionPath;
	}

	/*
	 * Can be overriden by childs if they implement the observer pattern
	 */
	protected void notifyObservers()
	{
	}
}
