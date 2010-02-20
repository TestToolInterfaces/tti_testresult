/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Enumeration;

import org.testtoolinterfaces.testsuite.TestGroup;

import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestGroupResult extends TestResult
{
    private TestGroup myTestGroup;

    private Hashtable<Integer, TestStepResult> myInitializationResults;
    private Hashtable<Integer, TestCaseResult> myTestCaseResults;
    private Hashtable<Integer, TestGroupResult> myTestGroupResults;
    private Hashtable<Integer, TestStepResult> myRestoreResults;

    /**
	 * @param aTestGroupName
	 */
	public TestGroupResult(TestGroup aTestGroup)
	{
		super();

	    Trace.println(Trace.CONSTRUCTOR, "TestGroupResult( " + aTestGroup + " )" );
	    myTestGroup = aTestGroup;

		myInitializationResults = new Hashtable<Integer, TestStepResult>();
		myTestCaseResults = new Hashtable<Integer, TestCaseResult>();
		myTestGroupResults = new Hashtable<Integer, TestGroupResult>();
		myRestoreResults = new Hashtable<Integer, TestStepResult>();
	}

	/**
	 * @param anInitializationResult
	 */
	public void addInitialization(TestStepResult anInitializationResult)
	{
	    Trace.println(Trace.SETTER);
	    myInitializationResults.put( myInitializationResults.size(), anInitializationResult );
	}

	/**
	 * @param anInitializationResult
	 */
	public void addTestCase(TestCaseResult aTestCaseResult)
	{
	    Trace.println(Trace.SETTER);
	    myTestCaseResults.put( myTestCaseResults.size(), aTestCaseResult );
	}

	public void addTestGroup(TestGroupResult aTestGroupResult)
	{
	    Trace.println(Trace.SETTER);
	    myTestGroupResults.put( myTestGroupResults.size(), aTestGroupResult );
	}

	/**
	 * @param anInitializationResult
	 */
	public void addRestore(TestStepResult aRestoreResult)
	{
	    Trace.println(Trace.SETTER);
	    myRestoreResults.put( myRestoreResults.size(), aRestoreResult );
	}

	/**
	 * @return the Test Group ID
	 */
	public String getId()
	{
	    Trace.println(Trace.GETTER);
		return myTestGroup.getId();
	}

	public int getSequenceNr()
	{
	    Trace.println(Trace.GETTER);
		return myTestGroup.getSequenceNr();
	}
	
	public String getDescription()
	{
	    Trace.println(Trace.GETTER);
		return myTestGroup.getDescription();
	}
	
	public ArrayList<String> getRequirements()
	{
	    Trace.println(Trace.GETTER);
		return myTestGroup.getRequirements();
	}
	
	public Hashtable<Integer, TestStepResult> getInitializationResults()
	{
	    Trace.println(Trace.GETTER);
		return myInitializationResults;
	}
	
	public Hashtable<Integer, TestCaseResult> getTestCaseResults()
	{
	    Trace.println(Trace.GETTER);
		return myTestCaseResults;
	}
	
	public Hashtable<Integer, TestGroupResult> getTestGroupResults()
	{
	    Trace.println(Trace.GETTER);
		return myTestGroupResults;
	}
	
	public Hashtable<Integer, TestStepResult> getRestoreResults()
	{
	    Trace.println(Trace.GETTER);
		return myRestoreResults;
	}

	/**
	 * @return
	 */
	public int getNrOfTCs()
	{
	    Trace.println(Trace.GETTER);
		int nrOfTCs = myTestCaseResults.size();
	    for (Enumeration<Integer> keys = myTestGroupResults.keys(); keys.hasMoreElements();)
	    {
	    	nrOfTCs += myTestGroupResults.get(keys.nextElement()).getNrOfTCs();
	    }

	    return nrOfTCs;
	}

	/**
	 * @return
	 */
	public int getNrOfTCsPassed()
	{
	    Trace.println(Trace.GETTER);
		int nrOfTCsPassed = 0;
	    for (Enumeration<Integer> keys = myTestCaseResults.keys(); keys.hasMoreElements();)
	    {
	    	if ( myTestCaseResults.get(keys.nextElement()).getResult() == VERDICT.PASSED )
	    	{
	    		nrOfTCsPassed += 1;
	    	}
	    }
	    
	    for (Enumeration<Integer> keys = myTestGroupResults.keys(); keys.hasMoreElements();)
	    {
    		nrOfTCsPassed += myTestGroupResults.get(keys.nextElement()).getNrOfTCsPassed();
	    }

	    return nrOfTCsPassed;
	}


	/**
	 * @return the number of failed test cases
	 * Note: in this number any test case that is not passed is failed, also the UNKNOWN and ERROR results
	 */
	public int getNrOfTCsFailed()
	{
	    Trace.println(Trace.GETTER);
		int nrOfTCsFailed = 0;
	    for (Enumeration<Integer> keys = myTestCaseResults.keys(); keys.hasMoreElements();)
	    {
	    	if ( myTestCaseResults.get(keys.nextElement()).getResult() != VERDICT.PASSED )
	    	{
	    		nrOfTCsFailed += 1;
	    	}
	    }

	    for (Enumeration<Integer> keys = myTestGroupResults.keys(); keys.hasMoreElements();)
	    {
	    	nrOfTCsFailed += myTestGroupResults.get(keys.nextElement()).getNrOfTCsFailed();
	    }

	    return nrOfTCsFailed;
	}
	
	public void setResult(VERDICT aResult)
	{
		// NOP
	}
	
	/**
	 * @return VERDICT.UNKNOWN (Always)
	 * Note: There is no total verdict.
	 *       Use the getNrOf...() methods to get statistics of this Test Group.
	 */
	public VERDICT getResult()
	{
	    Trace.println(Trace.GETTER);
		return VERDICT.UNKNOWN;
	}
}
