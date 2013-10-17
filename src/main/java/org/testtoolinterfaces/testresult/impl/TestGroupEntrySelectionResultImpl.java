/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import org.testtoolinterfaces.testresult.TestGroupEntryResultList;
import org.testtoolinterfaces.testresult.TestGroupEntrySelectionResult;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testsuite.TestGroupEntrySelection;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestGroupEntrySelectionResultImpl extends TestGroupResultAbstract
		implements TestGroupEntrySelectionResult
{
	private TestStepResult ifStepResult;
	
	public TestGroupEntrySelectionResultImpl(TestGroupEntrySelection aTestGroupEntrySelection) {
		super(aTestGroupEntrySelection);
	}

	public void setIfResult(TestStepResult aResult) {
		ifStepResult = aResult;
	}

	public TestStepResult getIfResult() {
		return ifStepResult;
	}

	public void setSubEntryResults(TestGroupEntryResultList subEntryResults) {
		super.setSubEntryResults(subEntryResults);
		
		this.notifyObservers();
	}
}
