/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testresult.observer.TestStepResultObserver;
import org.testtoolinterfaces.testsuite.ParameterArrayList;
import org.testtoolinterfaces.testsuite.TestStep;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author arjan.kranenburg
 *
 */
public abstract class TestStepResultBaseImpl extends TestResultImpl
	implements TestStepResultBase
{
    private static final Logger LOG = LoggerFactory.getLogger(TestStepResultBaseImpl.class);

    private ArrayList<TestStepResultObserver> myObserverCollection;

	private String myDisplayName = "";

	/**
	 * @param aTestCaseName
	 */
	public TestStepResultBaseImpl(TestStep aTestStep)
	{
		super( aTestStep );

		LOG.trace(Mark.CONSTRUCTOR, "{}", aTestStep);

		myObserverCollection = new ArrayList<TestStepResultObserver>();
		myDisplayName = aTestStep.getDisplayName();
	}

	private TestStep getTestStep() {
		return (TestStep) getTestEntry();
	}

	public void setDisplayName( String aDisplayName )
	{
		LOG.trace(Mark.SETTER, "{}", aDisplayName);
	    myDisplayName = aDisplayName;
	}
	
	public String getDisplayName()
	{
		LOG.trace(Mark.GETTER, "");
		return myDisplayName;
	}
	
	/**
	 * Returns the full execution path of id's each seperated by a '.'.
	 * The id of this result is included and added last. 
	 */
	public String getExecutionIdPath() {
		return this.getExecutionPath() + "." + this.getDisplayName();
	}

	public ParameterArrayList getParameters()
	{
		LOG.trace(Mark.GETTER, "");
		return this.getTestStep().getParameters();
	}

	// Implementation of the Observer Pattern
	protected void notifyObservers()
	{
		LOG.trace(Mark.EXEC_PLUS, "");

	    for (TestStepResultObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register( TestStepResultObserver anObserver )
	{
		LOG.trace(Mark.SETTER, "{}", anObserver);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver( TestStepResultObserver anObserver )
	{
		LOG.trace(Mark.SETTER, "{}", anObserver);
	    myObserverCollection.remove( anObserver );
	}
}
