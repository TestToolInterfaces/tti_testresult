/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.io.File;
import java.util.ArrayList;

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
	private TestCaseResult myLoadedTcResult;

    private ArrayList<TestCaseResultLinkObserver> myObserverCollection;

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
		myType = aTcLink.getLinkType();
		mySequenceNr = aTcLink.getSequenceNr();
		
		myLink = aLink;
		myLoadedTcResult = null;
		myObserverCollection = new ArrayList<TestCaseResultLinkObserver>();
		
		this.setResult(aVerdict);
	}

	/**
	 * @return the myTestCaseName
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

	/**
	 * If the link was used before to read the TestCase Result and it was stored with {@link #setTcResult(TestCaseResult)},
	 * you can get it here so you don't have to read it again.
	 * 
	 * @return The TestCaseResult, null if it was not read before.
	 */
	public TestCaseResult getTcResult()
	{
		return myLoadedTcResult;
	}

	/**
	 * Sets the TC result object. If the link was followed and read, store the result here to
	 * prevent re-reading it in the future.
	 * @param myLoadedTcResult
	 */
	public void setTcResult(TestCaseResult myLoadedTcResult)
	{
		this.myLoadedTcResult = myLoadedTcResult;
	}

	// Implementation of the Observer Pattern
	
	protected void notifyObservers()
	{
	    Trace.println(Trace.EXEC_PLUS);

	    for (TestCaseResultLinkObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register( TestCaseResultLinkObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver( TestCaseResultLinkObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.remove( anObserver );
	}
}
