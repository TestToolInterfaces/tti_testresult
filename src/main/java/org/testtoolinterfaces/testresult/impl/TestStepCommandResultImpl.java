/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.TestStepCommandResult;
import org.testtoolinterfaces.testsuite.impl.TestStepCommand;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author arjan.kranenburg
 *
 */
public class TestStepCommandResultImpl extends TestStepResultImpl implements TestStepCommandResult
{
    private static final Logger LOG = LoggerFactory.getLogger(TestStepCommandResultImpl.class);

	/**
	 * @param aTestCaseName
	 */
	public TestStepCommandResultImpl(TestStepCommand aTestStepCommand)
	{
		super( aTestStepCommand );
		LOG.trace(Mark.CONSTRUCTOR, "{}", aTestStepCommand);
	}

	public String getCommand()
	{
		return ((TestStepCommand) this.getTestEntry()).getCommand();
	}
	
}
