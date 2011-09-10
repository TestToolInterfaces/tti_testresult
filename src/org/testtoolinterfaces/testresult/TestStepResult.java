/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testtoolinterfaces.testsuite.ParameterArrayList;
import org.testtoolinterfaces.testsuite.TestStepSimple;
import org.testtoolinterfaces.testsuite.TestStepCommand;
import org.testtoolinterfaces.testsuite.TestStepScript;
import org.testtoolinterfaces.testsuite.TestStep.StepType;

import org.testtoolinterfaces.utils.Trace;

/**
 * @author arjan.kranenburg
 *
 */
public class TestStepResult extends TestResult
{
	private TestStepSimple myTestStep;

    private ArrayList<TestStepResultObserver> myObserverCollection;
    private Hashtable<Integer, TestStepResult> mySubStepResults;

    /**
	 * @param aTestCaseName
	 */
	public TestStepResult(TestStepSimple aTestStep)
	{
		super();

	    Trace.println(Trace.CONSTRUCTOR, "TestStepResult( " + aTestStep + " )" );
		myTestStep = aTestStep;

		myObserverCollection = new ArrayList<TestStepResultObserver>();
	    mySubStepResults = new Hashtable<Integer, TestStepResult>();
	}

	public StepType getType()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getStepType();
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

	public ParameterArrayList getParameters()
	{
	    Trace.println(Trace.GETTER);
		return myTestStep.getParameters();
	}

	public void addSubStep( TestStepResult aSubStepResult )
	{
	    Trace.println(Trace.SETTER);
	    mySubStepResults.put(aSubStepResult.getSequenceNr(), aSubStepResult);
	    
	    this.setResult( aSubStepResult.getResult() );
	}

	public Hashtable<Integer, TestStepResult> getSubSteps()
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
}
