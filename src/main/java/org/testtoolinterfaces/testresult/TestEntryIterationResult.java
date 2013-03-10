/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 * @author Arjan Kranenburg
 *
 */
public interface TestEntryIterationResult<E extends TestResult>
{
	/**
	 * @return the itemName
	 */
	public String getItemName();
	
	/**
	 * @return the list name
	 */
	public String getListName();
	
	public Iterator<List<E>> getIterator();

	public int getSize();
	
	/**
	 * Adds an resultSequence to the end of the resultSequences list.
	 * 
	 * @param aTestResultSequence
	 */
	public void addExecResult(List<E> aTestResultSequence);

	public Hashtable<Integer, List<E>> getTestResultSequenceTable();

	/**
	 * Adds a Value to the end of the iteration values list.
	 * 
	 * @param aValue
	 */
	public void addIterationValue(Object aValue);

	public Hashtable<Integer, Object> getIterationValues();

	/**
	 * @param anUntilResult
	 */
	public void addUntilResult(TestStepResult anUntilResult);

	public Hashtable<Integer, TestStepResult> getUntilResults();
}
