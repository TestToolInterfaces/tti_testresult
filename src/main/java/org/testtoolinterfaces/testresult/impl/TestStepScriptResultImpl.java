/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.TestStepScriptResult;
import org.testtoolinterfaces.testsuite.impl.TestStepScript;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author arjan.kranenburg
 *
 */
public class TestStepScriptResultImpl extends TestStepResultImpl implements TestStepScriptResult
{
    private static final Logger LOG = LoggerFactory.getLogger(TestStepScriptResultImpl.class);

    /**
	 * @param aTestCaseName
	 */
	public TestStepScriptResultImpl(TestStepScript aTestStepScript)
	{
		super( aTestStepScript );
		LOG.trace(Mark.CONSTRUCTOR, "{}", aTestStepScript);
	}

	public String getScript()
	{
		return ((TestStepScript) this.getTestEntry()).getScript();
	}
}
