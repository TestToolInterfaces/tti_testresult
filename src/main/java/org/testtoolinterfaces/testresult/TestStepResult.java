/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.ArrayList;

import org.testtoolinterfaces.testsuite.ParameterArrayList;

/**
 * @author arjan.kranenburg
 *
 */
public interface TestStepResult extends TestStepResultBase
{
	public void setResult(VERDICT aResult);

	public ParameterArrayList getParameters();

	public void addSubStep( TestStepResultBase testStepResultBase );

	public ArrayList<TestStepResultBase> getSubSteps();
	
    public ArrayList<ParameterResult> getParameterResults();

	public void setParameterResults(ArrayList<ParameterResult> aParameterResults);
}
