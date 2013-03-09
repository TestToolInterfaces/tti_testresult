/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

//import java.util.Calendar;
import java.util.Hashtable;

import org.testtoolinterfaces.testresult.TestResult;
import org.testtoolinterfaces.testsuite.TestEntry;
import org.testtoolinterfaces.utils.Trace;

public abstract class TestResultImpl implements TestResult
{
	private TestEntry myTestEntry;
	
	private Hashtable<String, String> myLogs;
	private String myComment = "";
//    private Calendar myStartDate;
//    private Calendar myEndDate;
	private String myExecutionPath = "";
	
	public TestResultImpl(TestEntry aTestEntry)	{
	    Trace.println(Trace.CONSTRUCTOR);

	    myTestEntry = aTestEntry;
		myLogs = new Hashtable<String, String>();
	}

	protected TestEntry getTestEntry() {
		return myTestEntry;
	}

	public String getDescription() {
		return myTestEntry.getDescription();
	}

	public int getSequenceNr()
	{
	    Trace.println(Trace.GETTER);
		return myTestEntry.getSequenceNr();
	}

	public void addTestLog(String aKey, String aLog) {
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
