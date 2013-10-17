/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.io.File;

import org.testtoolinterfaces.testresult.TestCaseResult;
import org.testtoolinterfaces.testresult.TestCaseResultLink;
import org.testtoolinterfaces.testresult.observer.TestCaseResultObserver;
import org.testtoolinterfaces.testsuite.TestCaseLink;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestCaseResultLinkImpl extends TestExecItemResultLinkImpl
	implements TestCaseResultLink, TestCaseResultObserver
{
	private VERDICT verdict;

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
	    verdict = aVerdict;
	}

	public VERDICT getResult()
	{
	    Trace.println(Trace.GETTER);
	    	return verdict;
	}

	public void setResult(VERDICT verdict) {
		this.verdict = verdict;
	    notifyObservers();		
	}

	public void notify(TestCaseResult aTestCaseResult) {
	    Trace.println(Trace.EXEC_UTIL);
	    this.setResult(aTestCaseResult.getResult()); // also does notifyObservers()
	}
}
