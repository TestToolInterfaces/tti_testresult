/**
 * 
 */
package org.testtoolinterfaces.testresult;

public interface SingleResult
{
	public static enum VERDICT
	{
	    UNKNOWN,
	    PASSED,
	    FAILED,
	    ERROR;
	}

	public final static VERDICT UNKNOWN	= VERDICT.UNKNOWN;
	public final static VERDICT PASSED	= VERDICT.PASSED;
	public final static VERDICT FAILED	= VERDICT.FAILED;
	public final static VERDICT ERROR	= VERDICT.ERROR;

	public VERDICT getResult();

}
