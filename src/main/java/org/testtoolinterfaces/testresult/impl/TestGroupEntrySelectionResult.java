/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.Enumeration;
import java.util.Hashtable;

import org.testtoolinterfaces.testresult.ResultSummary;
import org.testtoolinterfaces.testresult.TestCaseResultLink;
import org.testtoolinterfaces.testresult.TestGroupResultLink;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testsuite.TestGroupEntrySelection;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author arjan.kranenburg
 *
 */
public class TestGroupEntrySelectionResult extends TestGroupEntryResultImpl
{
	private TestStepResult ifStepResult;

    private Hashtable<Integer, TestCaseResultLink> myTestCaseResultLinks;
    private Hashtable<Integer, TestGroupResultLink> myTestGroupResultLinks;

	/**
	 * @param aTestCaseName
	 */
	public TestGroupEntrySelectionResult(TestGroupEntrySelection aTestGroupEntrySelection)
	{
		super( aTestGroupEntrySelection );

	    Trace.println(Trace.CONSTRUCTOR, "TestGroupEntrySelectionResult( " + aTestGroupEntrySelection + " )" );
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

	public ResultSummary getSummary()
	{
	    Trace.println(Trace.GETTER);

	    int nrOfTCsPassed = 0;
		int nrOfTCsFailed = 0;
		int nrOfTCsUnknown = 0;
		int nrOfTCsError = 0;

	    for (Enumeration<Integer> keys = myTestCaseResultLinks.keys(); keys.hasMoreElements();)
	    {
	    	VERDICT verdict = myTestCaseResultLinks.get(keys.nextElement()).getResult();
	    	if ( verdict == VERDICT.PASSED )
	    	{
	    		nrOfTCsPassed += 1;
	    	}
	    	else if ( verdict == VERDICT.FAILED )
	    	{
	    		nrOfTCsFailed += 1;
	    	}
	    	else if ( verdict == VERDICT.UNKNOWN )
	    	{
	    		nrOfTCsUnknown += 1;
	    	}
	    	else if ( verdict == VERDICT.ERROR )
	    	{
	    		nrOfTCsError += 1;
	    	}
	    }
	    
	    for (Enumeration<Integer> keys = myTestGroupResultLinks.keys(); keys.hasMoreElements();)
	    {
	    	ResultSummary summary = myTestGroupResultLinks.get(keys.nextElement()).getSummary();
    		nrOfTCsPassed += summary.getNrOfTCsPassed();
	    	nrOfTCsFailed += summary.getNrOfTCsFailed();
	    	nrOfTCsUnknown += summary.getNrOfTCsUnknown();
	    	nrOfTCsError += summary.getNrOfTCsError();
	    }

	    return new ResultSummary( nrOfTCsPassed, nrOfTCsFailed, nrOfTCsUnknown, nrOfTCsError );
	}
	
	@Override
	public String getId()
	{
		String firstPart = super.getId();

		return  firstPart + "_" + this.getSequenceNr();
	}
}
