package org.testtoolinterfaces.testresult;

import java.util.ArrayList;
import java.util.Iterator;

import org.testtoolinterfaces.testresult.SingleResult.VERDICT;
import org.testtoolinterfaces.utils.Warning;

public class TestGroupEntryResultList extends ArrayList<TestGroupEntryResult>
		implements AggregatedResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3698536176076570429L;

	public ResultSummary getSummary() {
		ResultSummary summary = new ResultSummary(0, 0, 0, 0);
		
		Iterator<TestGroupEntryResult> tgEntryResultItr = this.iterator(); 
		while ( tgEntryResultItr.hasNext() )
		{
			TestGroupEntryResult tgEntryResult = tgEntryResultItr.next();
			
			if ( tgEntryResult instanceof SingleResult ) {
				summary.addVerdict( ((SingleResult) tgEntryResult).getResult() );
			} else if ( tgEntryResult instanceof AggregatedResult ) {
				summary.addSummary( ((AggregatedResult) tgEntryResult).getSummary() );
			} else {
				Warning.println( "Cannot add results of " + tgEntryResult.getId() + " to summary." );
				summary.addVerdict( VERDICT.ERROR );
			}
		}
		
		return summary;
	}
}
