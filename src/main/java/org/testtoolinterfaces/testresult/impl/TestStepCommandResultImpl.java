/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import org.testtoolinterfaces.testresult.TestStepCommandResult;
import org.testtoolinterfaces.testsuite.impl.TestStepCommand;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author arjan.kranenburg
 *
 */
public class TestStepCommandResultImpl extends TestStepResultImpl implements TestStepCommandResult
{

	/**
	 * @param aTestCaseName
	 */
	public TestStepCommandResultImpl(TestStepCommand aTestStepCommand)
	{
		super( aTestStepCommand );

	    Trace.println(Trace.CONSTRUCTOR, "TestStepCommandResultImpl( " + aTestStepCommand + " )" );
	}

	public String getCommand()
	{
		return ((TestStepCommand) this.getTestEntry()).getCommand();
	}
	
}
