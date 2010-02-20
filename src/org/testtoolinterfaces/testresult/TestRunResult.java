/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.Hashtable;
import java.util.Calendar;

import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class TestRunResult
{
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
	    Trace.println(Trace.LEVEL.CONSTRUCTOR);

		myTestSuite = aTestSuite;
		myDisplayName = aDisplayName;
		myAuthor = aAuthor;
		myMachine = aMachine;
		myStartDate = aStartDate;
		myStatus = aStatus;
		mySut = aSut;
        myRunLogs = new Hashtable<String, String>();
        myTestGroup = null;
	}

	/**
	 * @param aStatus the status to set
	 */
	public void setStatus(TEST_RUN_STATUS aStatus)
	{
	    Trace.println(Trace.LEVEL.SETTER);
		myStatus = aStatus;
	}

	/**
	 * @param aStatus the status to set
	 */
	public void setEndDate(Calendar anEndDate)
	{
	    Trace.println(Trace.LEVEL.SETTER);
	    myEndDate = anEndDate;
	}

	/**
	 * @param aTestGroup the TestGroupResult to set
	 */
	public void setTestGroup(TestGroupResult aTestGroup)
	{
	    Trace.println(Trace.LEVEL.SETTER);
	    myTestGroup = aTestGroup;
	}

	/**
	 * @param aKey: the key to the log-file
	 * @param aLog: the location of the log-file
	 */
	public void addSutLog(String aKey, String aLog)
	{
	    Trace.println(Trace.LEVEL.SETTER);
		mySut.addSutLog(aKey, aLog);
	}

	/**
	 * @return
	 */
	public int getNrOfTCs()
	{
	    Trace.println(Trace.LEVEL.GETTER);
	    return myTestGroup.getNrOfTCs();
	}

	/**
	 * @return
	 */
	public int getNrOfTCsPassed()
	{
	    Trace.println(Trace.LEVEL.GETTER);
	    return myTestGroup.getNrOfTCsPassed();
	}

	/**
	 * @return
	 */
	public int getNrOfTCsFailed()
	{
	    Trace.println(Trace.LEVEL.GETTER);
	    return myTestGroup.getNrOfTCsFailed();
	}

	/**
	 * @return the Author
	 */
	public String getAuthor()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myAuthor;
	}

	/**
	 * @return the Display Name
	 */
	public String getDisplayName()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myDisplayName;
	}

	/**
	 * @return the Machine
	 */
	public String getMachine()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myMachine;
	}

	/**
	 * @return the Start Date of the test run
	 */
	public Calendar getStartDate()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myStartDate;
	}

	/**
	 * @return the StartDate in YY-MM-DD
	 */
	public String getStartDateString()
	{
	    Trace.println(Trace.LEVEL.GETTER);
	    return convertDateString( myStartDate );
	}

	/**
	 * @return the StartTime in HH:MM:SS
	 */
	public String getStartTimeString()
	{
	    Trace.println(Trace.LEVEL.GETTER);
	    return convertTimeString( myStartDate );
	}

	/**
	 * @return the Start Date of the test run
	 */
	public Calendar getEndDate()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myEndDate;
	}

	/**
	 * @return the EndDate in YY-MM-DD
	 */
	public String getEndDateString()
	{
	    Trace.println(Trace.LEVEL.GETTER);
	    return convertDateString( myStartDate );
	}

	/**
	 * @return the EndTime in HH:MM:SS
	 */
	public String getEndTimeString()
	{
	    Trace.println(Trace.LEVEL.GETTER);
	    return convertTimeString( myStartDate );
	}

	/**
	 * @return the RunLogs
	 */
	public Hashtable<String, String> getRunLogs()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myRunLogs;
	}

	/**
	 * @return the Status
	 */
	public TEST_RUN_STATUS getStatus()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myStatus;
	}

	/**
	 * @return the SutDescription
	 */
	public String getSutDescription()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return mySut.getDescription();
	}

	/**
	 * @return the SutLogs
	 */
	public Hashtable<String, String> getSutLogs()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return mySut.getLogs();
	}

	/**
	 * @return the SutProduct
	 */
	public String getSutProduct()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return mySut.getName();
	}

	/**
	 * @return the SutVersionMainLevel
	 */
	public String getSutVersionMainLevel()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return mySut.getVersionMainLevel();
	}

	/**
	 * @return the SutVersionSubLevel
	 */
	public String getSutVersionSubLevel()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return mySut.getVersionSubLevel();
	}

	/**
	 * @return the SutVersionPatchLevel
	 */
	public String getSutVersionPatchLevel()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return mySut.getVersionPatchLevel();
	}

	/**
	 * @return the TestSuite
	 */
	public String getTestSuite()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myTestSuite;
	}

	public TestGroupResult getTestGroup()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myTestGroup;
	}

	/**
	 * @return the string representation of the Date in YY-MM-DD
	 */
	private static String convertDateString( Calendar aDate )
	{
	    Trace.println(Trace.ALL);
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
	    Trace.println(Trace.ALL);
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
}
