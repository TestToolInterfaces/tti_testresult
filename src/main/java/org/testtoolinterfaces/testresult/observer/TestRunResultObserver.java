package org.testtoolinterfaces.testresult.observer;

import org.testtoolinterfaces.testresult.TestRunResult;

public interface TestRunResultObserver
{
	public void notify( TestRunResult aTestRunResult );
}
