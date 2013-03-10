/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.testtoolinterfaces.testresult.TestEntryIterationResult;
import org.testtoolinterfaces.testresult.TestResult;
import org.testtoolinterfaces.testresult.TestStepResult;
import org.testtoolinterfaces.testsuite.TestEntryIteration;

/**
 * @author Arjan Kranenburg
 *
 */
public abstract class TestEntryIterationResultImpl<E extends TestResult> //extends TestResultImpl
	implements TestEntryIterationResult<E>
{
	private TestEntryIteration<?> testEntryIteration;
	
	private Hashtable<Integer, List<E>> execResults;
	private Hashtable<Integer, Object> iterationValues;
	private Hashtable<Integer, TestStepResult> untilResults;

	public TestEntryIterationResultImpl(TestEntryIteration<?> aTestEntryIteration) {
		this.testEntryIteration = aTestEntryIteration;
		
		execResults = new Hashtable<Integer, List<E>>();
		iterationValues = new Hashtable<Integer, Object>();
		untilResults = new Hashtable<Integer, TestStepResult>();
	}
	
	public String getItemName() {
		return this.testEntryIteration.getItemName();
	}
	
	public String getListName() {
		return this.testEntryIteration.getListName();
	}

	public Iterator<List<E>> getIterator() {
		return execResults.values().iterator();
	}

	public int getSize() {
		return execResults.size();
	}

	public void addExecResult(List<E> aTestResultSequence) {
		execResults.put(execResults.size(), aTestResultSequence);
	}

	public Hashtable<Integer, List<E>> getTestResultSequenceTable() {
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
}
