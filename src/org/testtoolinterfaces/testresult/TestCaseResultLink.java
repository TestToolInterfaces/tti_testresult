/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.io.File;

import org.testtoolinterfaces.testsuite.TestCaseLink;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestCaseResultLink extends TestResult
{
	private String myId;
	private String myType;
	private int mySequenceNr;
	
	private File myLink;

	/**
	 * @param aTcLink
	 * @param aVerdict
	 * @param aLink
	 */
	public TestCaseResultLink( TestCaseLink aTcLink,
								VERDICT aVerdict,
								File aLink )
	{
		super();
	    Trace.println(Trace.CONSTRUCTOR, "TestGroupResultLink( " + aTcLink.getId() + ", "
	                  											 + aVerdict + ", "
	                  											 + aLink + " )" );

	    myId = aTcLink.getId();
		myType = aTcLink.getScriptType();
		mySequenceNr = aTcLink.getSequenceNr();
		
		myLink = aLink;
		
		this.setResult(aVerdict);
	}

	/**
	 * @return the myTestCaseName
	 */
	public String getId()
	{
	    Trace.println(Trace.LEVEL.GETTER);
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
	    Trace.println(Trace.LEVEL.GETTER);
		return mySequenceNr;
	}

	/**
	 * @return the Link to the Test Group result file 
	 */
	public File getLink()
	{
	    Trace.println(Trace.GETTER);
		return myLink;
	}
}
