/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

//import java.util.Calendar;
import java.util.Hashtable;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.TestResult;
import org.testtoolinterfaces.testsuite.TestEntry;
import org.testtoolinterfaces.utils.Mark;

public abstract class TestResultImpl implements TestResult
{
    private static final Logger LOG = LoggerFactory.getLogger(TestResultImpl.class);

    private TestEntry myTestEntry;
	
	private Hashtable<String, String> myLogs;
	private String myComment = "";
//    private Calendar myStartDate;
//    private Calendar myEndDate;
	private String myExecutionPath = "";
	private final UUID uniqueId;

	private boolean testFinished = false;
	
	public TestResultImpl(TestEntry aTestEntry)	{
		LOG.trace(Mark.CONSTRUCTOR, "{}", aTestEntry);

	    myTestEntry = aTestEntry;
		myLogs = new Hashtable<String, String>();
		uniqueId = UUID.randomUUID();
	}

	protected TestEntry getTestEntry() {
		return myTestEntry;
	}

	public String getDescription() {
		return myTestEntry.getDescription();
	}

	public int getSequenceNr()
	{
		LOG.trace(Mark.GETTER, "");
		return myTestEntry.getSequenceNr();
	}

	public void addTestLog(String aKey, String aLog) {
		LOG.trace(Mark.SETTER, "{}, {}",  aKey, aLog);

	    myLogs.put(aKey, aLog);
	    notifyObservers();
	}

	public void setComment(String aComment)
	{
		LOG.trace(Mark.SETTER, "{}", aComment);
		if ( aComment != null ) {
		    myComment = aComment;
		    notifyObservers();
		}
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
		LOG.trace(Mark.GETTER, "");
	    return myLogs;
	}

	public String getComment()
	{
		LOG.trace(Mark.GETTER, "");
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

	public boolean isTestFinished() {
		return testFinished ;
	}

	public void setTestFinished() {
		this.testFinished = true;
	}

	public UUID getUniqueId() {
		return uniqueId;
	}


	/*
	 * Can be overriden by childs if they implement the observer pattern
	 */
	protected void notifyObservers()
	{
	}
}
