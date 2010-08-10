/**
 * 
 */
package org.testtoolinterfaces.testresult;

import org.testtoolinterfaces.testsuite.ParameterArrayList;
import org.testtoolinterfaces.testsuite.TestStep;
import org.testtoolinterfaces.testsuite.TestStep.ActionType;

import org.testtoolinterfaces.utils.Trace;

/**
 * @author arjan.kranenburg
 *
 */
public class TestStepResult extends TestResult
{
	private TestStep myTestStep;

	/**
	 * @param aTestCaseName
	 */
	public TestStepResult(TestStep aTestStep)
	{
		super();

	    Trace.println(Trace.CONSTRUCTOR, "TestStepResult( " + aTestStep + " )" );
		myTestStep = aTestStep;
	}

	public ActionType getType()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getActionType();
	}
	
	public int getSequenceNr()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getSequenceNr();
	}
	
	public String getDescription()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getDescription();
	}
	
	public String getDisplayName()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getDisplayName();
	}
	
	public String getCommand()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getCommand();
	}

	public String getScript()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getScript();
	}

	public ParameterArrayList getParameters()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getParameters();
	}
}
