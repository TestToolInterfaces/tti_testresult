/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author Arjan Kranenburg
 *
 */
public class ResultTiming
{
    private static final Logger LOG = LoggerFactory.getLogger(ResultTiming.class);

    private Date myStart;
	private Date myEnd;
	
    /**
	 * @param aStart
	 * @param anEnd
	 */
	public ResultTiming(Date aStart, Date anEnd)
	{
		LOG.trace(Mark.CONSTRUCTOR, "{}, {}", aStart, anEnd );
	    
	    myStart = aStart;
	    myEnd = anEnd;
	}

	/**
	 * @return the start date/time
	 */
	public Date getStart()
	{
		LOG.trace(Mark.GETTER, "");
		return myStart;
	}

	/**
	 * @return the end date/time
	 */
	public Date getEnd()
	{
		LOG.trace(Mark.GETTER, "");
		return myEnd;
	}
}
