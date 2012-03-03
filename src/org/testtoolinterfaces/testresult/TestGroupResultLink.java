/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.io.File;
import java.util.ArrayList;

import org.testtoolinterfaces.testsuite.TestGroupLink;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestGroupResultLink extends TestResult implements TestGroupResultObserver
{
	private String myId;
	private String myType;
	private int mySequence;
	
	private ResultSummary myResultSummary;
	private File myLink;
	
	ArrayList<TestGroupResultLinkObserver> myObserverCollection;

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
		myType = aTestGroupLink.getLinkType();
		mySequence = aTestGroupLink.getSequenceNr();
		
		myResultSummary = aResultSummary;
		myLink = aLink;

		myObserverCollection = new ArrayList<TestGroupResultLinkObserver>();
	}

	/**
	 * @param aTestGroupLink
	 * @param aLink
	 */
	public TestGroupResultLink( TestGroupLink aTestGroupLink,
								File aLink )
	{
		this( aTestGroupLink, new ResultSummary( 0, 0, 0, 0 ), aLink );
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
	    // notifyObservers();
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

	public void setSummary( ResultSummary aResultSummary )
	{
		myResultSummary = aResultSummary;
	    notifyObservers();
	}

	public ResultSummary getSummary()
	{
		return myResultSummary;
	}
	
	// Implementation of the Observer Pattern
	
	protected void notifyObservers()
	{
	    Trace.println(Trace.EXEC_PLUS);

	    for (TestGroupResultLinkObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register( TestGroupResultLinkObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver( TestGroupResultLinkObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.remove( anObserver );
	}

	@Override
	public void notify(TestGroupResult aTestGroupResult)
	{
		setSummary( aTestGroupResult.getSummary() );
	}
}
