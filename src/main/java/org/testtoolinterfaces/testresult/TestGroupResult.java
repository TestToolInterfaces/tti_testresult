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
public class TestGroupResult extends TestResult implements TestGroupResultLinkObserver,
														   TestCaseResultLinkObserver,
														   TestStepResultObserver
{
    private TestGroup myTestGroup;
    private ResultTiming myTiming;

    private Hashtable<Integer, TestStepResult> myPrepareResults;
    private Hashtable<Integer, TestCaseResultLink> myTestCaseResultLinks;
    private Hashtable<Integer, TestGroupResultLink> myTestGroupResultLinks;
    private Hashtable<Integer, TestStepResult> myRestoreResults;
    
    private ArrayList<TestGroupResultObserver> myObserverCollection;

    /**
	 * @param aTestGroupName
	 */
	public TestGroupResult(TestGroup aTestGroup)
	{
		super();

	    Trace.println(Trace.CONSTRUCTOR, "TestGroupResult( " + aTestGroup + " )" );
	    myTestGroup = aTestGroup;

		myPrepareResults = new Hashtable<Integer, TestStepResult>();
		myTestCaseResultLinks = new Hashtable<Integer, TestCaseResultLink>();
		myTestGroupResultLinks = new Hashtable<Integer, TestGroupResultLink>();
		myRestoreResults = new Hashtable<Integer, TestStepResult>();
		
		myObserverCollection = new ArrayList<TestGroupResultObserver>();
	}

	/**
	 * @param aPrepareResult
	 */
	public void addInitialization(TestStepResult aPrepareResult)
	{
	    Trace.println(Trace.SETTER);
	    myPrepareResults.put( myPrepareResults.size(), aPrepareResult );
	    
	    aPrepareResult.register(this);

	    notifyObservers();
	}

	/**
	 * @param anInitializationResult
	 */
	public void addTestCase(TestCaseResultLink aTestCaseResultLink)
	{
	    Trace.println(Trace.SETTER);
	    myTestCaseResultLinks.put( myTestCaseResultLinks.size(), aTestCaseResultLink );
	    
	    aTestCaseResultLink.register(this);

	    notifyObservers();
	}

	public void addTestGroup(TestGroupResultLink aTestGroupResultLink)
	{
	    Trace.println(Trace.SETTER);
	    myTestGroupResultLinks.put( myTestGroupResultLinks.size(), aTestGroupResultLink );

	    aTestGroupResultLink.register(this);

	    notifyObservers();
	}

	/**
	 * @param anInitializationResult
	 */
	public void addRestore(TestStepResult aRestoreResult)
	{
	    Trace.println(Trace.SETTER);
	    myRestoreResults.put( myRestoreResults.size(), aRestoreResult );
	    
	    aRestoreResult.register(this);

	    notifyObservers();
	}

	/**
	 * @return the Test Group ID
	 */
	public String getId()
	{
	    Trace.println(Trace.GETTER);
		return myTestGroup.getId();
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
	
	public Hashtable<Integer, TestStepResult> getPrepareResults()
	{
	    Trace.println(Trace.GETTER);
		return myPrepareResults;
	}
	
	public Hashtable<Integer, TestCaseResultLink> getTestCaseResultLinks()
	{
	    Trace.println(Trace.GETTER);
		return myTestCaseResultLinks;
	}
	
	public Hashtable<Integer, TestGroupResultLink> getTestGroupResultLinks()
	{
	    Trace.println(Trace.GETTER);
		return myTestGroupResultLinks;
	}
	
	public Hashtable<Integer, TestStepResult> getRestoreResults()
	{
	    Trace.println(Trace.GETTER);
		return myRestoreResults;
	}

	public void setResult(VERDICT aResult)
	{
		// NOP
	    
	    // notifyObservers();
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

	/**
	 * @return the myTiming
	 */
	public ResultTiming getTiming()
	{
		return myTiming;
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
	
	// Implementation of the Observer Pattern
	
	protected void notifyObservers()
	{
	    Trace.println(Trace.EXEC_PLUS);

	    for (TestGroupResultObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register( TestGroupResultObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver( TestGroupResultObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.remove( anObserver );
	}

	public void notify(TestGroupResultLink aTestGroupResultLink)
	{
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}

	public void notify(TestCaseResultLink aTestCaseResultLink)
	{
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}

	public void notify(TestStepResult aTestStepResult)
	{
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}
}
