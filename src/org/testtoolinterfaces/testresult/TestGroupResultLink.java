/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.io.File;

import org.testtoolinterfaces.testsuite.TestGroupLink;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestGroupResultLink extends TestResult
{
	private String myId;
	private String myType;
	private int mySequence;
	
	private ResultSummary myResultSummary;
	private File myLink;

	/**
	 * @param aTestGroupLink
	 * @param aResultSummary
	 * @param aLink
	 */
	public TestGroupResultLink( TestGroupLink aTestGroupLink,
								ResultSummary aResultSummary,
								File aLink )
	{
	    Trace.println(Trace.CONSTRUCTOR, "TestGroupResultLink( " + aTestGroupLink.getId() + ", "
	                  											 + aResultSummary + ", "
	                  											 + aLink + " )" );

	    myId = aTestGroupLink.getId();
		myType = aTestGroupLink.getGroupType();
		mySequence = aTestGroupLink.getSequenceNr();
		
		myResultSummary = aResultSummary;
		myLink = aLink;
	}

	/**
	 * @return the Test Group ID
	 */
	public String getId()
	{
	    Trace.println(Trace.GETTER);
		return myId;
	}

	/**
	 * @return the Test Group Type
	 */
	public String getType()
	{
	    Trace.println(Trace.GETTER);
		return myType;
	}

	public int getSequenceNr()
	{
	    Trace.println(Trace.GETTER);
		return mySequence;
	}

	/**
	 * @return the Link to the Test Group result file 
	 */
	public File getLink()
	{
	    Trace.println(Trace.GETTER);
		return myLink;
	}

	public void setResult(VERDICT aResult)
	{
		// NOP
	}
	
	/**
	 * @return VERDICT.UNKNOWN (Always)
	 * Note: There is no total verdict.
	 *       Use the getNrOf...() methods to get statistics of this Test Group.
	 */
	public VERDICT getResult()
	{
	    Trace.println(Trace.GETTER);
		return VERDICT.UNKNOWN;
	}

	public ResultSummary getSummary()
	{
		return myResultSummary;
	}
}
