/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.io.File;
import java.util.ArrayList;

import org.testtoolinterfaces.testresult.TestExecItemResultLink;
import org.testtoolinterfaces.testresult.observer.TestExecItemResultLinkObserver;
import org.testtoolinterfaces.testsuite.TestExecItemLink;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestExecItemResultLinkImpl extends TestGroupEntryResultImpl
	implements TestExecItemResultLink
{
	private String myType;
	private File myLink;

    private ArrayList<TestExecItemResultLinkObserver> myObserverCollection;

	/**
	 * @param aTcLink
	 * @param aLink
	 */
	public TestExecItemResultLinkImpl( TestExecItemLink aTestExecItemLink,
								File aLink )
	{
		super(aTestExecItemLink);
	    Trace.println(Trace.CONSTRUCTOR, "TestGroupResultLink( " + aTestExecItemLink.getId() + ", "
	                  											 + aLink + " )" );

		myType = aTestExecItemLink.getLinkType();
		myLink = aLink;

		myObserverCollection = new ArrayList<TestExecItemResultLinkObserver>();
	}

	/**
	 * @return the Test Group Type
	 */
	public String getType()
	{
	    Trace.println(Trace.GETTER);
		return myType;
	}

	/**
	 * @return the Link to the Test Group result file 
	 */
	public File getLink()
	{
	    Trace.println(Trace.GETTER);
		return myLink;
	}

	protected void notifyObservers()
	{
	    Trace.println(Trace.EXEC_PLUS);

	    for (TestExecItemResultLinkObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register(TestExecItemResultLinkObserver anObserver) {
	    Trace.println(Trace.SETTER);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver(TestExecItemResultLinkObserver anObserver) {
	    Trace.println(Trace.SETTER);
	    myObserverCollection.remove( anObserver );
	}
}
