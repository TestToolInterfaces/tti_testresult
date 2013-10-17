package org.testtoolinterfaces.testresult.impl;

import org.testtoolinterfaces.testsuite.TestStep;
import org.testtoolinterfaces.testsuite.TestSuiteException;

public class TestStepResultError extends TestStepResultImpl {
	private final TestSuiteException exception;

	public TestStepResultError(TestStep aTestStep, TestSuiteException exception) {
		super(aTestStep);
		this.exception = exception;
		this.setComment( exception.getLocalizedMessage() );
		
		this.setTestFinished();
	}

	public VERDICT getResult() {
		return VERDICT.ERROR;
	}

	public TestSuiteException getException() {
		return exception;
	}
}
