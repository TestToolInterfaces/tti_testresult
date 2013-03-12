/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.testtoolinterfaces.testresult.ResultSummary;
import org.testtoolinterfaces.testresult.TestCaseResultLink;
import org.testtoolinterfaces.testresult.TestCaseResultLinkObserver;
import org.testtoolinterfaces.testresult.TestExecItemIterationResult;
import org.testtoolinterfaces.testresult.TestGroupEntryResult;
import org.testtoolinterfaces.testresult.TestGroupResultLink;
import org.testtoolinterfaces.testresult.TestGroupResultLinkObserver;
import org.testtoolinterfaces.testresult.TestGroupResultObserver;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testsuite.TestGroupEntryIteration;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestExecItemIterationResultImpl extends TestGroupEntryResultImpl
	implements TestExecItemIterationResult, TestGroupResultLinkObserver, TestCaseResultLinkObserver
{
	private Hashtable<Integer, List<TestGroupEntryResult>> execResults;
	private Hashtable<Integer, Object> iterationValues;
	private Hashtable<Integer, TestStepResult> untilResults;

	private ArrayList<TestGroupResultObserver> myObserverCollection;
	
	public TestExecItemIterationResultImpl(TestGroupEntryIteration aTestGroupEntryIteration) {
		super(aTestGroupEntryIteration);

		execResults = new Hashtable<Integer, List<TestGroupEntryResult>>();
		iterationValues = new Hashtable<Integer, Object>();
		untilResults = new Hashtable<Integer, TestStepResult>();

		myObserverCollection = new ArrayList<TestGroupResultObserver>();
	}

	public String getItemName() {
		return ((TestGroupEntryIteration) this.getTestEntry()).getItemName();
	}
	
	public String getListName() {
		return ((TestGroupEntryIteration) this.getTestEntry()).getListName();
	}

	public Iterator<List<TestGroupEntryResult>> getIterator() {
		return execResults.values().iterator();
	}

	public int getSize() {
		return execResults.size();
	}

	public void addExecResult(List<TestGroupEntryResult> aTestResultSequence) {
		execResults.put(execResults.size(), aTestResultSequence);

		notifyObservers();
	}

	public Hashtable<Integer, List<TestGroupEntryResult>> getTestResultSequenceTable() {
		return this.execResults;
	}

	public void addIterationValue(Object aValue) {
		iterationValues.put(execResults.size(), aValue);
	}

	public Hashtable<Integer, Object> getIterationValues() {
		return this.iterationValues;
	}

	/**
	 * @param anUntilResult
	 */
	public void addUntilResult(TestStepResult anUntilResult) {
		untilResults.put(untilResults.size(), anUntilResult);
	}

	public Hashtable<Integer, TestStepResult> getUntilResults() {
		return this.untilResults;
	}

	
	public ResultSummary getSummary() {
	    Trace.println(Trace.GETTER);

		ResultSummary summary = new ResultSummary( 0, 0, 0, 0 );

		Iterator<List<TestGroupEntryResult>> testGroupEntryListItr = this.getIterator(); 
		while ( testGroupEntryListItr.hasNext() )
		{
			List<TestGroupEntryResult> tgEntryResultList = testGroupEntryListItr.next();
			Iterator<TestGroupEntryResult> tgEntryItr = tgEntryResultList.iterator();
			while ( tgEntryItr.hasNext() ) {
				TestGroupEntryResult tgEntryResult = tgEntryItr.next();
				summary.addResult(tgEntryResult);
			}
		}

	    return summary;
	}

	public void register(TestGroupResultObserver anObserver) {
	    Trace.println(Trace.SETTER);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver(TestGroupResultObserver anObserver) {
	    Trace.println(Trace.SETTER);
	    myObserverCollection.remove( anObserver );
	}

	public void notify(TestGroupResultLink aTestGroupResultLink) {
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}

	public void notify(TestCaseResultLink aTestCaseResultLink) {
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}
}
