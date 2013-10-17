package org.testtoolinterfaces.testresult.impl;

import org.testtoolinterfaces.testresult.SingleResult.VERDICT;
import org.testtoolinterfaces.testsuite.TestGroupLink;
import org.testtoolinterfaces.testsuite.TestSuiteException;

public class TestGroupResultLinkError extends TestGroupResultLinkImpl {
	private final TestSuiteException exception;

	public TestGroupResultLinkError(TestGroupLink aTgLink, TestSuiteException exception) {
		super(aTgLink, null);
		this.exception = exception;

		this.setComment( exception.getLocalizedMessage() );		
		this.getSummary().addVerdict(VERDICT.ERROR);

		this.setTestFinished();
	}

	public TestSuiteException getException() {
		return exception;
	}
}
