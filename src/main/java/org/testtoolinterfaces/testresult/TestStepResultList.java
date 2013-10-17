package org.testtoolinterfaces.testresult;

import java.util.ArrayList;
import java.util.Iterator;

import org.testtoolinterfaces.utils.Trace;

public class TestStepResultList extends ArrayList<TestStepResultBase>
		implements SingleResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3698536176076570429L;

	public VERDICT getResult() {
	    Trace.println(Trace.GETTER);

		VERDICT finalResult = VERDICT.UNKNOWN;

		Iterator<TestStepResultBase> testStepResultItr = this.iterator(); 
		while ( testStepResultItr.hasNext() )
		{
			TestStepResultBase testStepResultList = testStepResultItr.next();
			VERDICT result = testStepResultList.getResult();
		    if (finalResult.compareTo(result) < 0)
		    {
		    	finalResult = result;
		    }
		}			

		return finalResult;
	}

}
