/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.ArrayList;
//import java.util.Hashtable;
import java.util.Iterator;
//import java.util.Map;

import org.testtoolinterfaces.testsuite.ParameterArrayList;
import org.testtoolinterfaces.testsuite.TestStep;
import org.testtoolinterfaces.testsuite.TestStepCommand;
import org.testtoolinterfaces.testsuite.TestStepScript;

import org.testtoolinterfaces.utils.Trace;

/**
 * @author arjan.kranenburg
 *
 */
public class TestStepResult extends TestResult
{
	private TestStep myTestStep;

    private ArrayList<TestStepResultObserver> myObserverCollection;
    private ArrayList<TestStepResult> mySubStepResults;

    /**
	 * @param aTestCaseName
	 */
	public TestStepResult(TestStep aTestStep)
	{
		super();

	    Trace.println(Trace.CONSTRUCTOR, "TestStepResult( " + aTestStep + " )" );
		myTestStep = aTestStep;

		myObserverCollection = new ArrayList<TestStepResultObserver>();
	    mySubStepResults = new ArrayList<TestStepResult>();
	}

	public int getSequenceNr()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getSequenceNr();
	}
	
	public String getDescription()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getDescription();
	}

	public void setDisplayName( String aDisplayName )
	{
	    Trace.println(Trace.SETTER);
		myTestStep.setDisplayName(aDisplayName);
	}
	
	public String getDisplayName()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getDisplayName();
	}
	
	public String getCommand()
	{
	    Trace.println(Trace.GETTER);
	    if ( myTestStep.getClass().equals(TestStepCommand.class) )
	    {
			return ((TestStepCommand) myTestStep).getCommand();	    	
	    }
	    return "";
	}

	public String getScript()
	{
	    Trace.println(Trace.GETTER);
	    if ( myTestStep.getClass().equals(TestStepScript.class) )
	    {
			return ((TestStepScript) myTestStep).getScript();	    	
	    }
	    return "";
	}

	@Override
	public void setExecutionPath(String anExecutionPath)
	{
		super.setExecutionPath(anExecutionPath);

		Iterator<TestStepResult> subStepItr = mySubStepResults.iterator();
		while (subStepItr.hasNext())
		{
			subStepItr.next().setExecutionPath(anExecutionPath + "." + this.getId());
		}
	}

	public ParameterArrayList getParameters()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getParameters();
	}

	public void addSubStep( TestStepResult aSubStepResult )
	{
	    Trace.println(Trace.SETTER);
	    mySubStepResults.add(aSubStepResult);
	    
	    this.setResult( aSubStepResult.getResult() );
	}

	public ArrayList<TestStepResult> getSubSteps()
	{
	    Trace.println(Trace.GETTER);
		return mySubStepResults;
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

	@Override
	public String getId()
	{
		if ( myTestStep instanceof TestStepCommand )
		{
			return ((TestStepCommand) myTestStep).getCommand() + "_" + myTestStep.getSequenceNr();
		} //else
		if ( myTestStep instanceof TestStepScript )
		{
			return ((TestStepScript) myTestStep).getScript() + "_" + myTestStep.getSequenceNr();
		} //else

		return myTestStep.getType().toString() + "_" + myTestStep.getSequenceNr();
	}
}
