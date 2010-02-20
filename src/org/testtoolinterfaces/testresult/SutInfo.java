package org.testtoolinterfaces.testresult;

import java.util.Hashtable;

import org.testtoolinterfaces.utils.Trace;

public class SutInfo
{
    private String myName;
    private String myVersionMainLevel = "";
    private String myVersionSubLevel = "";
    private String myVersionPatchLevel = "";
    private String myDescription = "";
	private Hashtable<String, String> myLogs;

	/**
	 * @param aName
	 */
	public SutInfo( String aProduct )
	{
	    Trace.println(Trace.LEVEL.CONSTRUCTOR);
		myName = aProduct;
		myLogs = new Hashtable<String, String>();
	}

	/**
	 * 
	 */
	public void setVersion( String aVersionMainLevel,
							String aVersionSubLevel,
							String aVersionPatchLevel )
	{
	    Trace.println(Trace.LEVEL.SETTER);
		myVersionMainLevel = aVersionMainLevel;
		myVersionSubLevel = aVersionSubLevel;
		myVersionPatchLevel = aVersionPatchLevel;
	}
	
	/**
	 * 
	 */
	public void setDescription( String aDescription )
	{
	    Trace.println(Trace.LEVEL.SETTER);
		myDescription = aDescription;
	}

	/**
	 * @param aKey: the key to the log-file
	 * @param aLog: the location of the log-file
	 */
	public void addSutLog(String aKey, String aLog)
	{
	    Trace.println(Trace.LEVEL.SETTER);
		myLogs.put(aKey, aLog);
	}

	/**
	 * @return the SUT Description
	 */
	public String getDescription()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myDescription;
	}

	/**
	 * @return the logs
	 */
	public Hashtable<String, String> getLogs()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myLogs;
	}

	/**
	 * @return the Name
	 */
	public String getName()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myName;
	}

	/**
	 * @return the SUT Version MainLevel
	 */
	public String getVersionMainLevel()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myVersionMainLevel;
	}

	/**
	 * @return the SUT Version SubLevel
	 */
	public String getVersionSubLevel()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myVersionSubLevel;
	}

	/**
	 * @return the SUT Version PatchLevel
	 */
	public String getVersionPatchLevel()
	{
	    Trace.println(Trace.LEVEL.GETTER);
		return myVersionPatchLevel;
	}
}
