/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.io.File;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.TestExecItemResultLink;
import org.testtoolinterfaces.testresult.observer.TestExecItemResultLinkObserver;
import org.testtoolinterfaces.testsuite.TestExecItemLink;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestExecItemResultLinkImpl extends TestGroupEntryResultImpl
	implements TestExecItemResultLink
{
    private static final Logger LOG = LoggerFactory.getLogger(TestExecItemResultLinkImpl.class);

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
		LOG.trace(Mark.CONSTRUCTOR, "{}, {}", aTestExecItemLink, aLink );

		myType = aTestExecItemLink.getLinkType();
		myLink = aLink;

		myObserverCollection = new ArrayList<TestExecItemResultLinkObserver>();
	}

	/**
	 * @return the Test Group Type
	 */
	public String getType()
	{
		LOG.trace(Mark.GETTER, "");
		return myType;
	}

	/**
	 * @return the Link to the Test Group result file 
	 */
	public File getLink()
	{
		LOG.trace(Mark.GETTER, "");
		return myLink;
	}

	protected void notifyObservers()
	{
		LOG.trace(Mark.EXEC_PLUS, "");

	    for (TestExecItemResultLinkObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register(TestExecItemResultLinkObserver anObserver) {
		LOG.trace(Mark.SETTER, "{}", anObserver);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver(TestExecItemResultLinkObserver anObserver) {
		LOG.trace(Mark.SETTER, "{}", anObserver);
	    myObserverCollection.remove( anObserver );
	}
}
