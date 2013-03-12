/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.testtoolinterfaces.testresult.ResultSummary;
import org.testtoolinterfaces.testresult.TestExecItemResultLink;
import org.testtoolinterfaces.testresult.TestExecItemResultLinkObserver;
import org.testtoolinterfaces.testresult.TestGroupEntryResult;
import org.testtoolinterfaces.testresult.TestGroupResult;
import org.testtoolinterfaces.testresult.TestGroupResultObserver;
import org.testtoolinterfaces.testsuite.TestGroupEntry;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public abstract class TestGroupResultAbstract extends TestExecItemResultImpl
		implements TestGroupResult, TestExecItemResultLinkObserver
{
    private Hashtable<Integer, TestGroupEntryResult> myTestGroupEntryResults;
    
    private ArrayList<TestGroupResultObserver> myObserverCollection;

    /**
	 * @param aTestGroupName
	 */
	public TestGroupResultAbstract(TestGroupEntry aTestGroupEntry)
	{
		super( aTestGroupEntry );

	    Trace.println(Trace.CONSTRUCTOR, "TestGroupResult( " + aTestGroupEntry + " )" );

		myTestGroupEntryResults = new Hashtable<Integer, TestGroupEntryResult>();
		myObserverCollection = new ArrayList<TestGroupResultObserver>();
	}

	public void addTestExecItemResultLink(TestExecItemResultLink aTestExecItemResultLink) {
		myTestGroupEntryResults.put(myTestGroupEntryResults.size(), aTestExecItemResultLink);

		aTestExecItemResultLink.register(this);

	    notifyObservers();
	}

	public void addTgEntryResult(TestGroupEntryResult aTestGroupEntryResult) {
		myTestGroupEntryResults.put(myTestGroupEntryResults.size(), aTestGroupEntryResult);

//		if( aTestGroupEntryResult instanceof TestExecItemIterationResult ) {
//			((TestExecItemIterationResult) aTestGroupEntryResult).register(this);
//		}
//
	    notifyObservers();
	}

	public Hashtable<Integer, TestGroupEntryResult> getTestGroupEntryResultsTable() {

		return myTestGroupEntryResults;
	}

	public List<TestGroupEntryResult> getTestGroupEntryResults() {

		// Note:
		// myTestGroupEntryResults.values() somehow reverses the list.

		List<TestGroupEntryResult> tgEntryResults
			= new ArrayList<TestGroupEntryResult>();

		for ( int i=0; i<myTestGroupEntryResults.size(); i++ ) {
			tgEntryResults.add(myTestGroupEntryResults.get(i));
		}

		return tgEntryResults;
	}

	public ResultSummary getSummary()
	{
	    Trace.println(Trace.GETTER);

		ResultSummary summary = new ResultSummary( 0, 0, 0, 0 );

		Iterator<TestGroupEntryResult> tgEntryResultItr = this.myTestGroupEntryResults.values().iterator();
		while ( tgEntryResultItr.hasNext() ) {
			TestGroupEntryResult tgEntryResult = tgEntryResultItr.next();
			summary.addResult(tgEntryResult);
		}

	    return summary;
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
}
