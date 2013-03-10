/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.testtoolinterfaces.testresult.ResultSummary;
import org.testtoolinterfaces.testresult.TestCaseResultLink;
import org.testtoolinterfaces.testresult.TestCaseResultLinkObserver;
import org.testtoolinterfaces.testresult.TestGroupResult;
import org.testtoolinterfaces.testresult.TestGroupResultLink;
import org.testtoolinterfaces.testresult.TestGroupResultLinkObserver;
import org.testtoolinterfaces.testresult.TestGroupResultObserver;
import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testsuite.TestGroupEntry;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public abstract class TestGroupResultAbstract extends TestExecItemResultImpl
		implements TestGroupResult, TestGroupResultLinkObserver, TestCaseResultLinkObserver
{
    private Hashtable<Integer, TestCaseResultLink> myTestCaseResultLinks;
    private Hashtable<Integer, TestGroupResultLink> myTestGroupResultLinks;
    
    private ArrayList<TestGroupResultObserver> myObserverCollection;

    /**
	 * @param aTestGroupName
	 */
	public TestGroupResultAbstract(TestGroupEntry aTestGroupEntry)
	{
		super( aTestGroupEntry );

	    Trace.println(Trace.CONSTRUCTOR, "TestGroupResult( " + aTestGroupEntry + " )" );

		myTestCaseResultLinks = new Hashtable<Integer, TestCaseResultLink>();
		myTestGroupResultLinks = new Hashtable<Integer, TestGroupResultLink>();
		
		myObserverCollection = new ArrayList<TestGroupResultObserver>();
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

	public void notify(TestStepResultBase aTestStepResult) {
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}
}
