/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.Date;

import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class ResultTiming
{
	private Date myStart;
	private Date myEnd;
	
    /**
	 * @param aStart
	 * @param anEnd
	 */
	public ResultTiming(Date aStart, Date anEnd)
	{
	    Trace.println( Trace.CONSTRUCTOR, "ResultSummary( " + aStart + ", "
	                  										+ anEnd + " )" );
	    
	    myStart = aStart;
	    myEnd = anEnd;
	}

	/**
	 * @return the start date/time
	 */
	public Date getStart()
	{
	    Trace.println(Trace.GETTER);
		return myStart;
	}

	/**
	 * @return the end date/time
	 */
	public Date getEnd()
	{
	    Trace.println(Trace.GETTER);
		return myEnd;
	}
}
