/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import java.util.ArrayList;

import org.testtoolinterfaces.testresult.TestStepResultBase;
import org.testtoolinterfaces.testresult.observer.TestStepResultObserver;
import org.testtoolinterfaces.testsuite.ParameterArrayList;
import org.testtoolinterfaces.testsuite.TestStep;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author arjan.kranenburg
 *
 */
public abstract class TestStepResultBaseImpl extends TestResultImpl
	implements TestStepResultBase
{
    private ArrayList<TestStepResultObserver> myObserverCollection;

	private String myDisplayName = "";

	/**
	 * @param aTestCaseName
	 */
	public TestStepResultBaseImpl(TestStep aTestStep)
	{
		super( aTestStep );

	    Trace.println(Trace.CONSTRUCTOR, "TestStepBaseResult( " + aTestStep + " )" );

		myObserverCollection = new ArrayList<TestStepResultObserver>();
		myDisplayName = aTestStep.getDisplayName();
	}

	private TestStep getTestStep() {
		return (TestStep) getTestEntry();
	}

	public void setDisplayName( String aDisplayName )
	{
	    Trace.println(Trace.SETTER);
	    myDisplayName = aDisplayName;
	}
	
	public String getDisplayName()
	{
	    Trace.println(Trace.GETTER);
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
	    Trace.println(Trace.GETTER);
		return this.getTestStep().getParameters();
	}

	// Implementation of the Observer Pattern
	protected void notifyObservers()
	{
	    Trace.println(Trace.EXEC_PLUS);

	    for (TestStepResultObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register( TestStepResultObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver( TestStepResultObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.remove( anObserver );
	}
}
