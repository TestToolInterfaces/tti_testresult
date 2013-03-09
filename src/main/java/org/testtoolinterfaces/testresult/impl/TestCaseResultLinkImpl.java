/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.io.File;
import java.util.ArrayList;

import org.testtoolinterfaces.testresult.TestCaseResult;
import org.testtoolinterfaces.testresult.TestCaseResultLink;
import org.testtoolinterfaces.testresult.TestCaseResultLinkObserver;
import org.testtoolinterfaces.testresult.TestCaseResultObserver;
import org.testtoolinterfaces.testsuite.TestCaseLink;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestCaseResultLinkImpl extends TestExecItemResultLinkImpl
	implements TestCaseResultLink, TestCaseResultObserver
{
	private VERDICT myVerdict;
	private TestCaseResult myLoadedTcResult;
    private ArrayList<TestCaseResultLinkObserver> myObserverCollection;

	/**
	 * @param aTcLink
	 * @param aVerdict
	 * @param aLink
	 */
	public TestCaseResultLinkImpl( TestCaseLink aTcLink,
								VERDICT aVerdict, File aLink )
	{
		super(aTcLink, aLink);
	    Trace.println(Trace.CONSTRUCTOR, "TestGroupResultLink( " + aTcLink.getId() + ", "
	                  											 + aLink + " )" );
	    myVerdict = aVerdict;
		myLoadedTcResult = null;
		myObserverCollection = new ArrayList<TestCaseResultLinkObserver>();
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

	public VERDICT getResult()
	{
	    Trace.println(Trace.GETTER);
	    if ( myLoadedTcResult == null ) {
	    	return myVerdict;
	    }
	    return myLoadedTcResult.getResult();
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

	public void notify(TestCaseResult aTestCaseResult) {
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}
}
