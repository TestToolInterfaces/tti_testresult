/**
 * 
 */
package org.testtoolinterfaces.testresult.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.ResultTiming;
import org.testtoolinterfaces.testresult.TestGroupEntryResult;
import org.testtoolinterfaces.testsuite.TestGroupEntry;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestGroupEntryResultImpl extends TestResultImpl
		implements TestGroupEntryResult {
    private static final Logger LOG = LoggerFactory.getLogger(TestGroupEntryResultImpl.class);

	private ResultTiming myTiming;

    /**
	 */
	public TestGroupEntryResultImpl( TestGroupEntry aTestGroupEntry ) {
		super(aTestGroupEntry);
		LOG.trace(Mark.CONSTRUCTOR, "{}", aTestGroupEntry);
	}

	/**
	 * @return the id. It must be unique within a certain scope, e.g. within a file.
	 */
	public String getId() {
		return ((TestGroupEntry) this.getTestEntry()).getId();
	}
	
	/**
	 * @return the myTiming
	 */
	public ResultTiming getTiming()	{
		return myTiming;
	}

	public String getExecutionIdPath() {
		return this.getExecutionPath() + "." + this.getId();
	}
}
