/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.TestStepIterationResult;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testresult.observer.TestStepResultObserver;
import org.testtoolinterfaces.testsuite.impl.TestStepIteration;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestStepIterationResultImpl extends TestStepResultBaseImpl
	implements TestStepIterationResult, TestStepResultObserver
{
    private static final Logger LOG = LoggerFactory.getLogger(TestStepIterationResultImpl.class);

    private Hashtable<Integer, List<TestStepResultBase>> execResults;
	private Hashtable<Integer, Object> iterationValues;
	private Hashtable<Integer, TestStepResult> untilResults;

	public TestStepIterationResultImpl(TestStepIteration aTestStepIteration) {
		super(aTestStepIteration);
		
		execResults = new Hashtable<Integer, List<TestStepResultBase>>();
		iterationValues = new Hashtable<Integer, Object>();
		untilResults = new Hashtable<Integer, TestStepResult>();
	}
	
	public VERDICT getResult() {
		LOG.trace(Mark.GETTER, "");

		VERDICT finalResult = VERDICT.UNKNOWN;

		Iterator<List<TestStepResultBase>> testStepResultListItr = this.getIterator(); 
		while ( testStepResultListItr.hasNext() )
		{
			List<TestStepResultBase> testStepResultList = testStepResultListItr.next();
			Iterator<TestStepResultBase> testStepResultItr = testStepResultList.iterator();
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

	public Iterator<List<TestStepResultBase>> getIterator() {
		return execResults.values().iterator();
	}

	public int getSize() {
		return execResults.size();
	}

	public void addExecResult(List<TestStepResultBase> aTestResultSequence) {
		execResults.put(execResults.size(), aTestResultSequence);
	}

	public Hashtable<Integer, List<TestStepResultBase>> getTestResultSequenceTable() {
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
		LOG.trace(Mark.EXEC_UTIL, "{}", aTestStepResult);
		notifyObservers();
	}
}
