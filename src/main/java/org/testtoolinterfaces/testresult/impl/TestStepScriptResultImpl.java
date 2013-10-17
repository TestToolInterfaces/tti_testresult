/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import org.testtoolinterfaces.testresult.TestStepScriptResult;
import org.testtoolinterfaces.testsuite.impl.TestStepScript;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author arjan.kranenburg
 *
 */
public class TestStepScriptResultImpl extends TestStepResultImpl implements TestStepScriptResult
{
	/**
	 * @param aTestCaseName
	 */
	public TestStepScriptResultImpl(TestStepScript aTestStepScript)
	{
		super( aTestStepScript );

	    Trace.println(Trace.CONSTRUCTOR, "TestStepResult( " + aTestStepScript + " )" );
	}

	public String getScript()
	{
		return ((TestStepScript) this.getTestEntry()).getScript();
	}
}
