/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.io.File;

import org.testtoolinterfaces.testresult.ResultSummary;
import org.testtoolinterfaces.testresult.TestGroupResult;
import org.testtoolinterfaces.testresult.TestGroupResultLink;
import org.testtoolinterfaces.testresult.observer.TestGroupResultObserver;
import org.testtoolinterfaces.testsuite.TestGroupLink;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestGroupResultLinkImpl extends TestExecItemResultLinkImpl
	implements TestGroupResultLink, TestGroupResultObserver
{
	private ResultSummary myResultSummary;
	
	/**
	 * @param aTestGroupLink
	 * @param aResultSummary
	 * @param aLink
	 */
	public TestGroupResultLinkImpl( TestGroupLink aTestGroupLink,
								ResultSummary aResultSummary,
								File aLink )
	{
		super(aTestGroupLink, aLink);
	    Trace.println(Trace.CONSTRUCTOR, "TestGroupResultLink( " + aTestGroupLink.getId() + ", "
	                  											 + aResultSummary + ", "
	                  											 + aLink + " )" );

		myResultSummary = aResultSummary;
	}

	/**
	 * @param aTestGroupLink
	 * @param aLink
	 */
	public TestGroupResultLinkImpl( TestGroupLink aTestGroupLink,
								File aLink )
	{
		this( aTestGroupLink, new ResultSummary( 0, 0, 0, 0 ), aLink );
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
	
	public void notify(TestGroupResult aTestGroupResult)
	{
		setSummary( aTestGroupResult.getSummary() );
	}
}
