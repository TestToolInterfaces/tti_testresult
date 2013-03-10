/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import org.testtoolinterfaces.testresult.TestExecItemSelectionResult;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testsuite.TestGroupEntrySelection;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestExecItemSelectionResultImpl extends TestGroupResultAbstract
		implements TestExecItemSelectionResult
{
	private TestStepResult ifStepResult;
	
	public TestExecItemSelectionResultImpl(TestGroupEntrySelection aTestGroupEntrySelection) {
		super(aTestGroupEntrySelection);
	}

	public void setIfResult(TestStepResult aResult) {
		ifStepResult = aResult;
	}

	public TestStepResult getIfResult() {
		return ifStepResult;
	}

	public void notify(TestStepResultBase aTestStepResult) {
		// TODO Auto-generated method stub
		
	}
}
