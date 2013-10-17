/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Iterator;

import org.testtoolinterfaces.testresult.ResultSummary;
import org.testtoolinterfaces.testresult.TestExecItemResultLink;
import org.testtoolinterfaces.testresult.TestGroupEntryIterationResult;
import org.testtoolinterfaces.testresult.TestGroupEntryResult;
import org.testtoolinterfaces.testresult.TestGroupEntryResultList;
import org.testtoolinterfaces.testresult.TestGroupEntrySelectionResult;
import org.testtoolinterfaces.testresult.TestGroupResult;
import org.testtoolinterfaces.testresult.observer.TestExecItemResultLinkObserver;
import org.testtoolinterfaces.testresult.observer.TestGroupResultObserver;
import org.testtoolinterfaces.testsuite.TestGroupEntry;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public abstract class TestGroupResultAbstract extends TestExecItemResultImpl
		implements TestGroupResult, TestExecItemResultLinkObserver, TestGroupResultObserver {

	private TestGroupEntryResultList testGroupEntryResults;
    
    private ArrayList<TestGroupResultObserver> myObserverCollection;

    /**
	 * @param aTestGroupName
	 */
	public TestGroupResultAbstract(TestGroupEntry aTestGroupEntry)
	{
		super( aTestGroupEntry );

	    Trace.println(Trace.CONSTRUCTOR, "TestGroupResult( " + aTestGroupEntry + " )" );

		testGroupEntryResults = new TestGroupEntryResultList();
		
		myObserverCollection = new ArrayList<TestGroupResultObserver>();
	}

	public void addTestExecItemResultLink(TestExecItemResultLink aTestExecItemResultLink) {

		testGroupEntryResults.add(aTestExecItemResultLink);
		aTestExecItemResultLink.register(this);

	    notifyObservers();
	}

	public void addTgEntryResult(TestGroupEntryResult aTestGroupEntryResult) {

		testGroupEntryResults.add(aTestGroupEntryResult);
		if ( aTestGroupEntryResult instanceof TestExecItemResultLink ) {
			((TestExecItemResultLink) aTestGroupEntryResult).register(this);
		} else if ( aTestGroupEntryResult instanceof TestGroupEntryIterationResult ) {
			((TestGroupEntryIterationResult) aTestGroupEntryResult).register(this);
		} else if ( aTestGroupEntryResult instanceof TestGroupEntrySelectionResult ) {
			((TestGroupEntrySelectionResult) aTestGroupEntryResult).register(this);
		}

	    notifyObservers();
	}

	public TestGroupEntryResultList getTestGroupEntryResults() {

		return testGroupEntryResults;
	}

	public ResultSummary getSummary()
	{
	    Trace.println(Trace.GETTER);

		ResultSummary summary = new ResultSummary( 0, 0, 0, 0 );

		Iterator<TestGroupEntryResult> tgEntryResultItr = testGroupEntryResults.iterator();
		while ( tgEntryResultItr.hasNext() ) {
			TestGroupEntryResult tgEntryResult = tgEntryResultItr.next();
			summary.addResult(tgEntryResult);
		}

	    return summary;
	}

	protected void setSubEntryResults(TestGroupEntryResultList subEntryResults) {
		this.testGroupEntryResults = subEntryResults;
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

	public void notify(TestExecItemResultLink notUsed)
	{
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}

	public void notify(TestGroupResult aTestGroupResult) {
		this.notifyObservers();		
	}
}
