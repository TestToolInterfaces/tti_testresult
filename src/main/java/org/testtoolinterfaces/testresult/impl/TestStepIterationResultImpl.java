/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.testtoolinterfaces.testresult.TestStepIterationResult;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testresult.TestStepResultObserver;
import org.testtoolinterfaces.testsuite.TestStepIteration;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestStepIterationResultImpl extends TestStepResultBaseImpl
//TestEntryIterationResultImpl<TestStepResult>
	implements TestStepIterationResult, TestStepResultObserver
{
	private Hashtable<Integer, List<TestStepResult>> execResults;
	private Hashtable<Integer, Object> iterationValues;
	private Hashtable<Integer, TestStepResult> untilResults;

	private ArrayList<TestStepResultObserver> myObserverCollection;

	public TestStepIterationResultImpl(TestStepIteration aTestStepIteration) {
		super(aTestStepIteration);
		
		execResults = new Hashtable<Integer, List<TestStepResult>>();
		iterationValues = new Hashtable<Integer, Object>();
		untilResults = new Hashtable<Integer, TestStepResult>();

		myObserverCollection = new ArrayList<TestStepResultObserver>();
	}
	
	public VERDICT getResult() {
	    Trace.println(Trace.GETTER);

		VERDICT finalResult = VERDICT.UNKNOWN;

		Iterator<List<TestStepResult>> testStepResultListItr = this.getIterator(); 
		while ( testStepResultListItr.hasNext() )
		{
			List<TestStepResult> testStepResultList = testStepResultListItr.next();
			Iterator<TestStepResult> testStepResultItr = testStepResultList.iterator();
			while ( testStepResultItr.hasNext() ) {
				VERDICT result = testStepResultItr.next().getResult();
			    if (finalResult.compareTo(result) < 0)
			    {
			    	finalResult = result;
			    }
			}			
		}

		return finalResult;
	}

	public String getItemName() {
		return ((TestStepIteration) this.getTestEntry()).getItemName();
	}

	public String getListName() {
		return ((TestStepIteration) this.getTestEntry()).getListName();
	}

	public Iterator<List<TestStepResult>> getIterator() {
		return execResults.values().iterator();
	}

	public int getSize() {
		return execResults.size();
	}

	public void addExecResult(List<TestStepResult> aTestResultSequence) {
		execResults.put(execResults.size(), aTestResultSequence);
	}

	public Hashtable<Integer, List<TestStepResult>> getTestResultSequenceTable() {
		return this.execResults;
	}

	public void addIterationValue(Object aValue) {
		iterationValues.put(execResults.size(), aValue);
	}

	public Hashtable<Integer, Object> getIterationValues() {
		return this.iterationValues;
	}

	public void addUntilResult(TestStepResult anUntilResult) {
		untilResults.put(untilResults.size(), anUntilResult);
	}

	public Hashtable<Integer, TestStepResult> getUntilResults() {
		return this.untilResults;
	}

	public void notify(TestStepResultBase aTestStepResult) {
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}
}
