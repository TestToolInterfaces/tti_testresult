/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testtoolinterfaces.testsuite.TestCase;

import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestCaseResult extends TestResult implements TestStepResultObserver
{
	private TestCase myTestCase;

    private Hashtable<Integer, TestStepResult> myInitializationResults;
    private Hashtable<Integer, TestStepResult> myExecutionResults;
    private Hashtable<Integer, TestStepResult> myRestoreResults;
    
    private ArrayList<TestCaseResultObserver> myObserverCollection;

	/**
	 * @param aTestCase
	 */
	public TestCaseResult(TestCase aTestCase)
	{
		super();

	    Trace.println(Trace.CONSTRUCTOR, "TestCaseResultXmlWriter( " + aTestCase + " )" );
	    myTestCase = aTestCase;

	    myInitializationResults = new Hashtable<Integer, TestStepResult>();
	    myExecutionResults = new Hashtable<Integer, TestStepResult>();
	    myRestoreResults = new Hashtable<Integer, TestStepResult>();

		myObserverCollection = new ArrayList<TestCaseResultObserver>();
	}

	/**
	 * @param anInitializationResult
	 */
	public void addInitialization(TestStepResult anInitializationResult)
	{
	    Trace.println(Trace.SETTER);
		myInitializationResults.put( myInitializationResults.size(), anInitializationResult );

		anInitializationResult.register(this);

	    notifyObservers();
	}

	/**
	 * @param anInitializationResult
	 */
	public void addExecution(TestStepResult anExecutionResult)
	{
	    Trace.println(Trace.SETTER);
		myExecutionResults.put( myExecutionResults.size(), anExecutionResult );
		setResult(anExecutionResult.getResult());

		anExecutionResult.register(this);

	    notifyObservers();
	}

	/**
	 * @param anInitializationResult
	 */
	public void addRestore(TestStepResult aRestoreResult)
	{
	    Trace.println(Trace.SETTER);
	    myRestoreResults.put( myRestoreResults.size(), aRestoreResult );
	
	    aRestoreResult.register(this);

	    notifyObservers();
	}

	/**
	 * @return the id of myTestCase
	 */
	public String getId()
	{
	    Trace.println(Trace.GETTER);
		return myTestCase.getId();
	}

	public int getSequenceNr()
	{
	    Trace.println(Trace.GETTER);
		return myTestCase.getSequenceNr();
	}
	
	public String getDescription()
	{
	    Trace.println(Trace.GETTER);
		return myTestCase.getDescription();
	}
	
	public ArrayList<String> getRequirements()
	{
	    Trace.println(Trace.GETTER);
		return myTestCase.getRequirements();
	}
	
	public Hashtable<Integer, TestStepResult> getPrepareResults()
	{
	    Trace.println(Trace.GETTER);
		return myInitializationResults;
	}
	
	public Hashtable<Integer, TestStepResult> getExecutionResults()
	{
	    Trace.println(Trace.GETTER);
		return myExecutionResults;
	}
	
	public Hashtable<Integer, TestStepResult> getRestoreResults()
	{
	    Trace.println(Trace.GETTER);
		return myRestoreResults;
	}

	// Implementation of the Observer Pattern
	
	protected void notifyObservers()
	{
	    Trace.println(Trace.EXEC_PLUS);

	    for (TestCaseResultObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register( TestCaseResultObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver( TestCaseResultObserver anObserver )
	{
	    Trace.println(Trace.SETTER);
	    myObserverCollection.remove( anObserver );
	}

	@Override
	public void notify(TestStepResult aTestStepResult)
	{
	    Trace.println(Trace.EXEC_UTIL);
		notifyObservers();
	}
}
