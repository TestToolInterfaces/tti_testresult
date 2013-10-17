/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.TestCaseResult;
import org.testtoolinterfaces.testresult.TestCaseResultLink;
import org.testtoolinterfaces.testresult.observer.TestCaseResultObserver;
import org.testtoolinterfaces.testsuite.TestCaseLink;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestCaseResultLinkImpl extends TestExecItemResultLinkImpl
	implements TestCaseResultLink, TestCaseResultObserver
{
    private static final Logger LOG = LoggerFactory.getLogger(TestCaseResultLinkImpl.class);
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
		LOG.trace(Mark.CONSTRUCTOR, "{}, {}, {}", aTcLink, aVerdict, aLink );
	    verdict = aVerdict;
	}

	public VERDICT getResult()
	{
		LOG.trace(Mark.GETTER, "");
	    return verdict;
	}

	public void setResult(VERDICT verdict) {
		this.verdict = verdict;
	    notifyObservers();		
	}

	public void notify(TestCaseResult aTestCaseResult) {
		LOG.trace(Mark.EXEC_UTIL, "{}", aTestCaseResult);
	    this.setResult(aTestCaseResult.getResult()); // also does notifyObservers()
	}
}
