package org.testtoolinterfaces.testresult;

import java.util.ArrayList;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.utils.Mark;

public class TestStepResultList extends ArrayList<TestStepResultBase>
		implements SingleResult {
    private static final Logger LOG = LoggerFactory.getLogger(TestStepResultList.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -3698536176076570429L;

	public VERDICT getResult() {
		LOG.trace(Mark.GETTER, "");

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
