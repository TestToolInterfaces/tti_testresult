package org.testtoolinterfaces.testresult.impl;

import org.testtoolinterfaces.testresult.SingleResult.VERDICT;
import org.testtoolinterfaces.testresult.TestGroupResultLink;
import org.testtoolinterfaces.testsuite.TestGroup;
import org.testtoolinterfaces.testsuite.TestGroupEntrySequence;
import org.testtoolinterfaces.testsuite.TestStepSequence;
import org.testtoolinterfaces.testsuite.TestSuiteException;
import org.testtoolinterfaces.testsuite.impl.TestGroupImpl;

public class TestGroupResultError extends TestGroupResultImpl {
	private final TestSuiteException exception;
	
	private static final TestStepSequence EMPTY_TS = new TestStepSequence();
	private static final TestGroupEntrySequence EMPTY_TGE = new TestGroupEntrySequence();

	public TestGroupResultError(TestGroup testGroup, TestSuiteException exception) {
		super(testGroup);
		this.exception = exception;

		this.setComment( exception.getLocalizedMessage() );
		this.getSummary().addVerdict(VERDICT.ERROR);

		this.setTestFinished();
	}

	public TestGroupResultError(TestGroupResultLink aTgrLink, TestSuiteException exception) {
		this(new TestGroupImpl(aTgrLink.getId(), aTgrLink.getDescription(),
				aTgrLink.getSequenceNr(), EMPTY_TS, EMPTY_TGE, EMPTY_TS), exception);
	}

	public TestSuiteException getException() {
		return exception;
	}
}
