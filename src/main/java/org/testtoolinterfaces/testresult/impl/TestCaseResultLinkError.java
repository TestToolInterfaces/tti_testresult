package org.testtoolinterfaces.testresult.impl;

import org.testtoolinterfaces.testsuite.TestCaseLink;

public class TestCaseResultLinkError extends TestCaseResultLinkImpl {
	private final Throwable exception;

	public TestCaseResultLinkError(TestCaseLink aTcLink, Throwable t) {
		super(aTcLink, VERDICT.ERROR, null);
		this.exception = t;
		this.setComment( t.getLocalizedMessage() );
		
		this.setTestFinished();
	}

	public TestCaseResultLinkError(TestCaseLink aTcLink, String message) {
		this( aTcLink, new Exception(message) );
	}

	public VERDICT getResult() {
		return VERDICT.ERROR;
	}

	public Throwable getException() {
		return exception;
	}
}
