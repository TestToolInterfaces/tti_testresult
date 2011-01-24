package org.testtoolinterfaces.testresult;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.testtoolinterfaces.testresult.TestResult.VERDICT;
import org.testtoolinterfaces.testresult.TestRunResult.TEST_RUN_STATUS;
import org.testtoolinterfaces.testsuite.TestCaseLink;
import org.testtoolinterfaces.testsuite.TestEntryArrayList;
import org.testtoolinterfaces.testsuite.TestGroupImpl;
import org.testtoolinterfaces.testsuite.TestStepArrayList;

public class TestRunResultTester extends TestCase implements TestRunResultObserver
{
	private int notifyCounter = 0;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		notifyCounter = 0;

		System.out.println("==========================================================================");
		System.out.println(this.getName() + ":");
	}

	/**
	 * Test Case
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
	 * Test Case
	 */
	public void testCase_testGroup()
	{
		SutInfo sut = new SutInfo( "Product" );
		TestRunResult testRunResult = new TestRunResult( "Test Suite",
		                                                 "Display Name",
		                                                 "Author",
		                                                 "Machine",
		                                                 sut,
		                                                 Calendar.getInstance(),
		                                                 TEST_RUN_STATUS.STARTED );
		testRunResult.register(this);
		
		Assert.assertEquals("Incorrect Number of TestCases", 0, testRunResult.getNrOfTCs());
		Assert.assertEquals("Incorrect Number of Passed TestCases", 0, testRunResult.getNrOfTCsPassed());
		Assert.assertEquals("Incorrect Number of Failed TestCases", 0, testRunResult.getNrOfTCsFailed());

		TestGroupImpl testGroup = new TestGroupImpl( "ID",
		                                             new Hashtable<String, String>(),
		                                             "A Description",
		                                             new ArrayList<String>(),
		                                             new TestStepArrayList(),
		                                             new TestEntryArrayList(),
		                                             new TestStepArrayList(),
		                                             new Hashtable<String, String>() );
		TestGroupResult testGroupResult = new TestGroupResult(testGroup);
		testRunResult.setTestGroup(testGroupResult);

		Assert.assertEquals("Incorrect Number of Notifies", 1, notifyCounter);
	}
	
	/**
	 * Test Case
	 */
	public void testCase_status()
	{
		SutInfo sut = new SutInfo( "Product" );
		TestRunResult testRunResult = new TestRunResult( "Test Suite",
		                                                 "Display Name",
		                                                 "Author",
		                                                 "Machine",
		                                                 sut,
		                                                 Calendar.getInstance(),
		                                                 TEST_RUN_STATUS.STARTED );
		testRunResult.register(this);
		
		Assert.assertEquals("Incorrect status", TEST_RUN_STATUS.STARTED, testRunResult.getStatus());
		testRunResult.setStatus(TEST_RUN_STATUS.FINISHED);

		Assert.assertEquals("Incorrect status", TEST_RUN_STATUS.FINISHED, testRunResult.getStatus());
		Assert.assertEquals("Incorrect Number of Notifies", 1, notifyCounter);
	}
	
	/**
	 * Test Case
	 */
	public void testCase_addSutLog()
	{
		SutInfo sut = new SutInfo( "Product" );
		TestRunResult testRunResult = new TestRunResult( "Test Suite",
		                                                 "Display Name",
		                                                 "Author",
		                                                 "Machine",
		                                                 sut,
		                                                 Calendar.getInstance(),
		                                                 TEST_RUN_STATUS.STARTED );
		testRunResult.register(this);
		
		Assert.assertEquals("Incorrect number of logs", 0, testRunResult.getSutLogs().size());
		testRunResult.addSutLog("key", "log");

		Assert.assertEquals("Incorrect number of logs", 1, testRunResult.getSutLogs().size());
		Assert.assertEquals("Incorrect Number of Notifies", 1, notifyCounter);
	}
	
	/**
	 * Test Case
	 */
	public void testCase_testGroup_()
	{
		SutInfo sut = new SutInfo( "Product" );
		TestRunResult testRunResult = new TestRunResult( "Test Suite",
		                                                 "Display Name",
		                                                 "Author",
		                                                 "Machine",
		                                                 sut,
		                                                 Calendar.getInstance(),
		                                                 TEST_RUN_STATUS.STARTED );
		testRunResult.register(this);
		
		Assert.assertEquals("Incorrect Number of TestCases", 0, testRunResult.getNrOfTCs());
		Assert.assertEquals("Incorrect Number of Passed TestCases", 0, testRunResult.getNrOfTCsPassed());
		Assert.assertEquals("Incorrect Number of Failed TestCases", 0, testRunResult.getNrOfTCsFailed());

		TestGroupImpl testGroup = new TestGroupImpl( "ID",
		                                             new Hashtable<String, String>(),
		                                             "A Description",
		                                             new ArrayList<String>(),
		                                             new TestStepArrayList(),
		                                             new TestEntryArrayList(),
		                                             new TestStepArrayList(),
		                                             new Hashtable<String, String>() );
		TestGroupResult testGroupResult = new TestGroupResult(testGroup);
		testRunResult.setTestGroup(testGroupResult);
		Assert.assertEquals("Incorrect Number of Notifies", 1, notifyCounter);

		TestCaseLink testCaseLink = new TestCaseLink( "tcId",
		                                              "shell",
		                                              0,
		                                              new File( "Dummy" ),
		                                              new Hashtable<String, String>() );
		
		TestCaseResultLink tcResultLink = new TestCaseResultLink( testCaseLink,
		                                                          VERDICT.PASSED,
		                                                          new File( "DummyResult" ) );
		testGroupResult.addTestCase(tcResultLink);

		Assert.assertEquals("Incorrect Number of Notifies", 2, notifyCounter);
	}
	
	/**
	 * Test Case
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
		testRunResult.register(this);

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
		Assert.assertEquals("Incorrect Number of Notifies", 1, notifyCounter);
	}

	@Override
	public void notify(TestRunResult aTestRunResult)
	{
		notifyCounter++;		
	}
}
