package org.testtoolinterfaces.testresult.observer;

import org.testtoolinterfaces.testresult.TestGroupResult;

public interface TestGroupResultObserver
{
	public void notify( TestGroupResult aTestGroupResult );
}
