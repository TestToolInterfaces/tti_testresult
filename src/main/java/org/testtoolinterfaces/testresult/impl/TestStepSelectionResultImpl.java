/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testresult.TestStepResultList;
import org.testtoolinterfaces.testresult.TestStepSelectionResult;
import org.testtoolinterfaces.testsuite.TestStep;
import org.testtoolinterfaces.testsuite.impl.TestStepCommand;
import org.testtoolinterfaces.testsuite.impl.TestStepScript;
import org.testtoolinterfaces.testsuite.impl.TestStepSelection;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author arjan.kranenburg
 *
 */
public class TestStepSelectionResultImpl extends TestStepResultImpl implements TestStepSelectionResult
{
    private static final Logger LOG = LoggerFactory.getLogger(TestStepSelectionResultImpl.class);

    private TestStep ifStep;
	private TestStepResult ifStepResult;

	/**
	 * @param aTestCaseName
	 */
	public TestStepSelectionResultImpl(TestStepSelection aTestStepSelection)
	{
		super( aTestStepSelection );
		LOG.trace(Mark.CONSTRUCTOR, "{}", aTestStepSelection);

	    this.ifStep = aTestStepSelection.getIfStep();
	}

	public String getCommand()
	{
		LOG.trace(Mark.GETTER, "");
	    if ( this.ifStep instanceof TestStepCommand )
	    {
			return ((TestStepCommand) this.ifStep).getCommand();	    	
	    }
	    return "";
	}

	public String getScript()
	{
		LOG.trace(Mark.GETTER, "");
	    if ( this.ifStep instanceof TestStepScript )
	    {
			return ((TestStepScript) this.ifStep).getScript();	    	
	    }
	    return "";
	}

	public void setIfStepResult( TestStepResult anIfStepResult )
	{
		LOG.trace(Mark.SETTER, "{}", anIfStepResult);
	    this.ifStepResult = anIfStepResult;
	}

	public TestStepResult getIfStepResult()
	{
		LOG.trace(Mark.GETTER, "");
		return this.ifStepResult;
	}
	
	@Override
	public VERDICT getResult()
	{
		LOG.trace(Mark.GETTER, "");
		if ( this.ifStepResult != null ) {
			if ( this.ifStepResult.getResult().equals(VERDICT.ERROR)
				 || this.ifStepResult.getResult().equals(VERDICT.UNKNOWN) ) {
				return this.ifStepResult.getResult();
			} //else
		} //else
		
		return super.getResult();
	}

	public void setSubStepResults(TestStepResultList subStepResults) {
		super.setSubEntryResults(subStepResults);
		
		this.notifyObservers();		
	}
}
