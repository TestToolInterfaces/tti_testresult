/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testresult.TestStepSelectionResult;
import org.testtoolinterfaces.testsuite.TestStep;
import org.testtoolinterfaces.testsuite.TestStepCommand;
import org.testtoolinterfaces.testsuite.TestStepScript;
import org.testtoolinterfaces.testsuite.TestStepSelection;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author arjan.kranenburg
 *
 */
public class TestStepSelectionResultImpl extends TestStepResultImpl implements TestStepSelectionResult
{
	private TestStepResult ifStepResult;
	private TestStep ifStep;

	/**
	 * @param aTestCaseName
	 */
	public TestStepSelectionResultImpl(TestStepSelection aTestStepSelection)
	{
		super( aTestStepSelection );

	    Trace.println(Trace.CONSTRUCTOR, "TestStepSelectionResult( " + aTestStepSelection + " )" );

	    this.ifStep = aTestStepSelection.getIfStep();
	}

	public String getCommand()
	{
	    Trace.println(Trace.GETTER);
	    if ( this.ifStep instanceof TestStepCommand )
	    {
			return ((TestStepCommand) this.ifStep).getCommand();	    	
	    }
	    return "";
	}

	public String getScript()
	{
	    Trace.println(Trace.GETTER);
	    if ( this.ifStep instanceof TestStepScript )
	    {
			return ((TestStepScript) this.ifStep).getScript();	    	
	    }
	    return "";
	}

	public void setIfStepResult( TestStepResult anIfStepResult )
	{
	    Trace.println(Trace.SETTER);
	    this.ifStepResult = anIfStepResult;
	}

	public TestStepResult getIfStepResult()
	{
	    Trace.println(Trace.GETTER);
		return this.ifStepResult;
	}
	
	@Override
	public VERDICT getResult()
	{
	    Trace.println(Trace.GETTER);
		if ( this.ifStepResult != null ) {
			if ( this.ifStepResult.getResult().equals(VERDICT.ERROR)
				 || this.ifStepResult.getResult().equals(VERDICT.UNKNOWN) ) {
				return this.ifStepResult.getResult();
			} //else
		} //else
		
		return super.getResult();
	}
}
