package org.testtoolinterfaces.testresult.observer;

import org.testtoolinterfaces.testresult.TestStepResultBase;

public interface TestStepResultObserver
{
	public void notify( TestStepResultBase aTestStepResult );
}
