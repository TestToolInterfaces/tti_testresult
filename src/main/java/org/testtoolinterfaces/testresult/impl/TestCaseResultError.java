package org.testtoolinterfaces.testresult.impl;

import org.testtoolinterfaces.testresult.TestCaseResultLink;
import org.testtoolinterfaces.testsuite.TestCase;
import org.testtoolinterfaces.testsuite.TestStepSequence;
import org.testtoolinterfaces.testsuite.TestSuiteException;
import org.testtoolinterfaces.testsuite.impl.TestCaseImpl;

public class TestCaseResultError extends TestCaseResultImpl {
	private final TestSuiteException exception;
	
	private static final TestStepSequence EMPTY = new TestStepSequence();

	public TestCaseResultError(TestCase aTestCase, TestSuiteException exception) {
		super(aTestCase);
		this.exception = exception;

		super.setResult(ERROR);
		this.setComment( exception.getLocalizedMessage() );	
		this.setTestFinished();
	}

	public TestCaseResultError(TestCaseResultLink aTCRLink, TestSuiteException exception) {
		this(new TestCaseImpl(aTCRLink.getId(), aTCRLink.getDescription(),
				aTCRLink.getSequenceNr(), EMPTY, EMPTY, EMPTY), exception);
	}

	public final void setResult( VERDICT ignored ) {
		// NOP (the result must always remain ERROR
	}

	public TestSuiteException getException() {
		return exception;
	}
}
