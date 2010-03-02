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
	
	public TestResult()
	{
	    Trace.println(Trace.LEVEL.CONSTRUCTOR);
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
	    Trace.println(Trace.LEVEL.SETTER, "setResult( " + aResult + " )", true);
	    if (myResult.compareTo(aResult) < 0)
	    {
	        myResult = aResult;
	    }
	}

	public void addTestLog(String aKey, String aLog)
	{
	    Trace.println(Trace.LEVEL.SETTER, "addTestLog( " + aKey + ", " + aLog + " )", true);
	    myLogs.put(aKey, aLog);
	}

	public void setComment(String aComment)
	{
	    Trace.println(Trace.LEVEL.SETTER, "setComment( " + aComment + " )", true);
	    myComment = aComment;
	}

	/**
	 * Adds a comment to the existing comment string
	 * @param aComment the comment to add
	 */
	public void addComment(String aComment)
	{
		myComment += "\n" + aComment;
	}


	public VERDICT getResult()
	{
	    Trace.println(Trace.LEVEL.GETTER);
	    return myResult;
	}

	public Hashtable<String, String> getLogs()
	{
	    Trace.println(Trace.LEVEL.GETTER);
	    return myLogs;
	}

	public String getComment()
	{
	    Trace.println(Trace.LEVEL.GETTER);
	    return myComment;
	}
}
