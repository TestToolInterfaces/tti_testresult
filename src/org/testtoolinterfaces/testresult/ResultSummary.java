/**
 * 
 */
package org.testtoolinterfaces.testresult;

import org.testtoolinterfaces.testresult.TestResult.VERDICT;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class ResultSummary
{
	private int myNrOfTCsPassed;
	private int myNrOfTCsFailed;
	private int myNrOfTCsUnknown;
	private int myNrOfTCsError;
	
    /**
	 * @param aNrOfTCsPassed
	 * @param aNrOfTCsFailed
	 */
	public ResultSummary(int aNrOfTCsPassed, int aNrOfTCsFailed)
	{
		this( aNrOfTCsPassed, aNrOfTCsFailed, 0, 0 );
	}

	/**
	 * @param aNrOfTCsPassed
	 * @param aNrOfTCsFailed
	 * @param aNrOfTCsUnknown
	 * @param aNrOfTCsError
	 */
	public ResultSummary(int aNrOfTCsPassed, int aNrOfTCsFailed, int aNrOfTCsUnknown, int aNrOfTCsError)
	{
	    Trace.println( Trace.CONSTRUCTOR, "ResultSummary( " + aNrOfTCsPassed + ", "
	                  										+ aNrOfTCsFailed + ", "
	                  										+ aNrOfTCsUnknown + ", "
	                  										+ aNrOfTCsError + " )" );
	    
	    myNrOfTCsPassed = aNrOfTCsPassed;
	    myNrOfTCsFailed = aNrOfTCsFailed;
	    myNrOfTCsUnknown = aNrOfTCsUnknown;
	    myNrOfTCsError = aNrOfTCsError;
	}

	/**
	 * @return
	 */
	public int getNrOfTCs()
	{
	    Trace.println(Trace.GETTER);

	    return myNrOfTCsPassed + myNrOfTCsFailed + myNrOfTCsUnknown + myNrOfTCsError;
	}

	/**
	 * @return the number of passed test cases
	 */
	public int getNrOfTCsPassed()
	{
	    Trace.println(Trace.GETTER);
	    return myNrOfTCsPassed;
	}


	/**
	 * @return the number of failed test cases
	 */
	public int getNrOfTCsFailed()
	{
	    Trace.println(Trace.GETTER);
	    return myNrOfTCsFailed;
	}
	
	/**
	 * @return the number of passed test cases
	 */
	public int getNrOfTCsUnknown()
	{
	    Trace.println(Trace.GETTER);
	    return myNrOfTCsUnknown;
	}


	/**
	 * @return the number of failed test cases
	 */
	public int getNrOfTCsError()
	{
	    Trace.println(Trace.GETTER);
	    return myNrOfTCsError;
	}

	public void setResult(VERDICT aResult)
	{
		// NOP
	}
	
	/**
	 * @return VERDICT.UNKNOWN (Always)
	 * Note: There is no total verdict.
	 *       Use the getNrOf...() methods to get statistics of this Test Group.
	 */
	public VERDICT getResult()
	{
	    Trace.println(Trace.GETTER);
		return VERDICT.UNKNOWN;
	}
}
