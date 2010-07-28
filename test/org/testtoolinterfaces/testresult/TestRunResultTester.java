package org.testtoolinterfaces.testresult;


import java.util.Calendar;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.testtoolinterfaces.testresult.TestRunResult.TEST_RUN_STATUS;

public class TestRunResultTester extends TestCase
{
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		System.out.println("==========================================================================");
		System.out.println(this.getName() + ":");
	}

	/**
	 * Test Cases
	 */
	public void testCase_constructor()
	{
		SutInfo sut = new SutInfo( "Product" );
		TestRunResult testRunResult = new TestRunResult( "Test Suite",
		                                                 "Display Name",
		                                                 "Author",
		                                                 "Machine",
		                                                 sut,
		                                                 Calendar.getInstance(),
		                                                 TEST_RUN_STATUS.STARTED );
		Assert.assertEquals("Incorrect Number of TestCases", 0, testRunResult.getNrOfTCs());
		Assert.assertEquals("Incorrect Number of Passed TestCases", 0, testRunResult.getNrOfTCsPassed());
		Assert.assertEquals("Incorrect Number of Failed TestCases", 0, testRunResult.getNrOfTCsFailed());
	}
	
	/**
	 * Test Cases
	 */
	public void testCase_endDate()
	{
		SutInfo sut = new SutInfo( "Product" );
		TestRunResult testRunResult = new TestRunResult( "Test Suite",
		                                                 "Display Name",
		                                                 "Author",
		                                                 "Machine",
		                                                 sut,
		                                                 Calendar.getInstance(),
		                                                 TEST_RUN_STATUS.STARTED );

		try
		{
			Thread.sleep( 2000 );
		}
		catch (InterruptedException e)
		{
			throw new Error( e );
		}

		testRunResult.setEndDate( Calendar.getInstance() );
		System.out.println( "Before: " + testRunResult.getStartTimeString() );
		
		System.out.println( "After: " + testRunResult.getEndTimeString() );
	}
}
