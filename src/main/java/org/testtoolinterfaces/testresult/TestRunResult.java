/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.testresult.observer.TestGroupResultObserver;
import org.testtoolinterfaces.testresult.observer.TestRunResultObserver;
import org.testtoolinterfaces.utils.Mark;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestRunResult implements TestGroupResultObserver, AggregatedResult
{
    private static final Logger LOG = LoggerFactory.getLogger(TestRunResult.class);

    public enum TEST_RUN_STATUS { STARTED, FINISHED };

	public final static TEST_RUN_STATUS STARTED  = TEST_RUN_STATUS.STARTED;
	public final static TEST_RUN_STATUS FINISHED = TEST_RUN_STATUS.FINISHED;

    private String myTestSuite;
    private String myDisplayName;
    private String myAuthor;
    private String myMachine;
    private Calendar myStartDate;
    private Calendar myEndDate;
    private TEST_RUN_STATUS myStatus;
    private SutInfo mySut;
	private Hashtable<String, String> myRunLogs;
    private TestGroupResult myTestGroup;

    private ArrayList<TestRunResultObserver> myObserverCollection;

    /**
	 * @param aTestSuite
	 * @param aDisplayName
	 * @param aAuthor
	 * @param aMachine
	 * @param System Under Test
	 * @param aStartDate the start date of the test run
	 * @param aStatus
	 */
	public TestRunResult( String aTestSuite,
						  String aDisplayName,
						  String aAuthor,
						  String aMachine,
						  SutInfo aSut,
						  Calendar aStartDate,
						  TEST_RUN_STATUS aStatus)
	{
		LOG.trace(Mark.CONSTRUCTOR, "{}, {}, {}, {}, {}, {}, {}", aTestSuite,
				aDisplayName, aAuthor, aMachine, aSut, aStartDate, aStatus);

		myTestSuite = aTestSuite;
		myDisplayName = aDisplayName;
		myAuthor = aAuthor;
		myMachine = aMachine;
		myStartDate = aStartDate;
		myStatus = aStatus;
		mySut = aSut;
        myRunLogs = new Hashtable<String, String>();
        myTestGroup = null;

		myObserverCollection = new ArrayList<TestRunResultObserver>();
	}

	/**
	 * @param aStatus the status to set
	 */
	public void setStatus(TEST_RUN_STATUS aStatus)
	{
		LOG.trace(Mark.SETTER, "{}", aStatus);
		myStatus = aStatus;
	    
	    notifyObservers();
	}

	/**
	 * @param aStatus the status to set
	 */
	public void setEndDate(Calendar anEndDate)
	{
		LOG.trace(Mark.SETTER, "{}", anEndDate);
	    myEndDate = anEndDate;
	    
	    notifyObservers();
	}

	/**
	 * @param aTestGroup the TestGroupResult to set
	 */
	public void setTestGroup(TestGroupResult aTestGroup)
	{
		LOG.trace(Mark.SETTER, "{}", aTestGroup);
	    myTestGroup = aTestGroup;
	    
	    aTestGroup.register(this);

	    notifyObservers();
	}

	/**
	 * @param aKey: the key to the log-file
	 * @param aLog: the location of the log-file
	 */
	public void addSutLog(String aKey, String aLog)
	{
		LOG.trace(Mark.SETTER, "{}, {}", aKey, aLog);
		mySut.addSutLog(aKey, aLog);

		notifyObservers();
	}

	public ResultSummary getSummary()
	{
		LOG.trace(Mark.GETTER, "");
	    return myTestGroup.getSummary();
	}

	/**
	 * @return
	 */
	public int getNrOfTCs()
	{
		LOG.trace(Mark.GETTER, "");
	    if (myTestGroup == null) { return 0; }
	    return myTestGroup.getSummary().getNrOfTCs();
	}

	/**
	 * @return
	 */
	public int getNrOfTCsPassed()
	{
		LOG.trace(Mark.GETTER, "");
	    if (myTestGroup == null) { return 0; }
	    return myTestGroup.getSummary().getNrOfTCsPassed();
	}

	/**
	 * @return
	 */
	public int getNrOfTCsFailed()
	{
		LOG.trace(Mark.GETTER, "");
	    if (myTestGroup == null) { return 0; }
	    return myTestGroup.getSummary().getNrOfTCsFailed();
	}

	/**
	 * @return
	 */
	public int getNrOfTCsUnknown()
	{
		LOG.trace(Mark.GETTER, "");
	    if (myTestGroup == null) { return 0; }
	    return myTestGroup.getSummary().getNrOfTCsUnknown();
	}

	/**
	 * @return
	 */
	public int getNrOfTCsError()
	{
		LOG.trace(Mark.GETTER, "");
	    if (myTestGroup == null) { return 0; }
	    return myTestGroup.getSummary().getNrOfTCsError();
	}

	/**
	 * @return the Author
	 */
	public String getAuthor()
	{
		LOG.trace(Mark.GETTER, "");
		return myAuthor;
	}

	/**
	 * @return the Display Name
	 */
	public String getDisplayName()
	{
		LOG.trace(Mark.GETTER, "");
		return myDisplayName;
	}

	/**
	 * @return the Machine
	 */
	public String getMachine()
	{
		LOG.trace(Mark.GETTER, "");
		return myMachine;
	}

	/**
	 * @return the Start Date of the test run
	 */
	public Calendar getStartDate()
	{
		LOG.trace(Mark.GETTER, "");
		return myStartDate;
	}

	/**
	 * @return the StartDate in YY-MM-DD
	 */
	public String getStartDateString()
	{
		LOG.trace(Mark.GETTER, "");
	    return convertDateString( myStartDate );
	}

	/**
	 * @return the StartTime in HH:MM:SS
	 */
	public String getStartTimeString()
	{
		LOG.trace(Mark.GETTER, "");
	    return convertTimeString( myStartDate );
	}

	/**
	 * @return the Start Date of the test run
	 */
	public Calendar getEndDate()
	{
		LOG.trace(Mark.GETTER, "");
		return myEndDate;
	}

	/**
	 * @return the EndDate in YY-MM-DD
	 */
	public String getEndDateString()
	{
		LOG.trace(Mark.GETTER, "");
	    return convertDateString( myEndDate );
	}

	/**
	 * @return the EndTime in HH:MM:SS
	 */
	public String getEndTimeString()
	{
		LOG.trace(Mark.GETTER, "");
	    return convertTimeString( myEndDate );
	}

	/**
	 * @return the RunLogs
	 */
	public Hashtable<String, String> getRunLogs()
	{
		LOG.trace(Mark.GETTER, "");
		return myRunLogs;
	}

	/**
	 * @return the Status
	 */
	public TEST_RUN_STATUS getStatus()
	{
		LOG.trace(Mark.GETTER, "");
		return myStatus;
	}

	/**
	 * @return the SutDescription
	 */
	public String getSutDescription()
	{
		LOG.trace(Mark.GETTER, "");
		return mySut.getDescription();
	}

	/**
	 * @return the SutLogs
	 */
	public Hashtable<String, String> getSutLogs()
	{
		LOG.trace(Mark.GETTER, "");
		return mySut.getLogs();
	}

	/**
	 * @return the SutProduct
	 */
	public String getSutProduct()
	{
		LOG.trace(Mark.GETTER, "");
		return mySut.getName();
	}

	/**
	 * @return the SutVersionMainLevel
	 */
	public String getSutVersionMainLevel()
	{
		LOG.trace(Mark.GETTER, "");
		return mySut.getVersionMainLevel();
	}

	/**
	 * @return the SutVersionSubLevel
	 */
	public String getSutVersionSubLevel()
	{
		LOG.trace(Mark.GETTER, "");
		return mySut.getVersionSubLevel();
	}

	/**
	 * @return the SutVersionPatchLevel
	 */
	public String getSutVersionPatchLevel()
	{
		LOG.trace(Mark.GETTER, "");
		return mySut.getVersionPatchLevel();
	}

	/**
	 * @return the TestSuite
	 */
	public String getTestSuite()
	{
		LOG.trace(Mark.GETTER, "");
		return myTestSuite;
	}

	public TestGroupResult getTestGroup()
	{
		LOG.trace(Mark.GETTER, "");
		return myTestGroup;
	}

	/**
	 * @return the string representation of the Date in YY-MM-DD
	 */
	private static String convertDateString( Calendar aDate )
	{
		LOG.trace(Mark.ALL, "{}", aDate);
	    if ( aDate == null ) { return "Unknown"; }

	    String dateString = aDate.get(Calendar.YEAR) + "-";
	    int month = aDate.get(Calendar.MONTH)+1;
	    if ( month < 10 )
	    {
	    	dateString += "0" + month;
	    }
	    else
	    {
	    	dateString += month;
	    }
	    dateString += "-";

	    int day = aDate.get(Calendar.DAY_OF_MONTH);
	    if ( day < 10 )
	    {
	    	dateString += "0" + day;
	    }
	    else
	    {
	    	dateString += day;
	    }

	    return dateString;
	}

	/**
	 * @return the String representation of the Date in HH:MM:SS
	 */
	private static String convertTimeString( Calendar aDate )
	{
		LOG.trace(Mark.GETTER, "{}", aDate);
	    if ( aDate == null ) { return "Unknown"; }

	    String timeString = "";
	    int hours = aDate.get(Calendar.HOUR_OF_DAY);
	    if ( hours < 10 )
	    {
	    	timeString += "0" + hours;
	    }
	    else
	    {
	    	timeString += hours;
	    }
	    timeString += ":";

	    int minutes = aDate.get(Calendar.MINUTE);
	    if ( minutes < 10 )
	    {
	    	timeString += "0" + minutes;
	    }
	    else
	    {
	    	timeString += minutes;
	    }
	    timeString += ":";

	    int seconds = aDate.get(Calendar.SECOND);
	    if ( seconds < 10 )
	    {
	    	timeString += "0" + seconds;
	    }
	    else
	    {
	    	timeString += seconds;
	    }

	    return timeString;
	}

	// Implementation of the Observer Pattern
	
	private void notifyObservers()
	{
		LOG.trace(Mark.EXEC_PLUS, "");

	    for (TestRunResultObserver observer : myObserverCollection)
	    {
	    	observer.notify(this);
	    }
	}
	
	public void register( TestRunResultObserver anObserver )
	{
		LOG.trace(Mark.SETTER, "{}", anObserver);
	    myObserverCollection.add(anObserver);
	}

	public void unRegisterObserver( TestRunResultObserver anObserver )
	{
		LOG.trace(Mark.SETTER, "{}", anObserver);
	    myObserverCollection.remove( anObserver );
	}

	public void notify(TestGroupResult aTestGroupResult)
	{
		LOG.trace(Mark.EXEC_UTIL, "{}", aTestGroupResult);
		notifyObservers();
	}
}
