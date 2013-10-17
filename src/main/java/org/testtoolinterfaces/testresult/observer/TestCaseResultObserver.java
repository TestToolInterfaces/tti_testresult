package org.testtoolinterfaces.testresult.observer;

import org.testtoolinterfaces.testresult.TestCaseResult;

public interface TestCaseResultObserver
{
	public void notify( TestCaseResult aTestCaseResult );
}
