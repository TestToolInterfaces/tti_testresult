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
	    Trace.println(Trace.CONSTRUCTOR);
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
	    Trace.println(Trace.SETTER);
		myVersionMainLevel = aVersionMainLevel;
		myVersionSubLevel = aVersionSubLevel;
		myVersionPatchLevel = aVersionPatchLevel;
	}
	
	/**
	 * 
	 */
	public void setDescription( String aDescription )
	{
	    Trace.println(Trace.SETTER);
		myDescription = aDescription;
	}

	/**
	 * @param aKey: the key to the log-file
	 * @param aLog: the location of the log-file
	 */
	public void addSutLog(String aKey, String aLog)
	{
	    Trace.println(Trace.SETTER);
		myLogs.put(aKey, aLog);
	}

	/**
	 * @return the SUT Description
	 */
	public String getDescription()
	{
	    Trace.println(Trace.GETTER);
		return myDescription;
	}

	/**
	 * @return the logs
	 */
	public Hashtable<String, String> getLogs()
	{
	    Trace.println(Trace.GETTER);
		return myLogs;
	}

	/**
	 * @return the Name
	 */
	public String getName()
	{
	    Trace.println(Trace.GETTER);
		return myName;
	}

	/**
	 * @return the SUT Version MainLevel
	 */
	public String getVersionMainLevel()
	{
	    Trace.println(Trace.GETTER);
		return myVersionMainLevel;
	}

	/**
	 * @return the SUT Version SubLevel
	 */
	public String getVersionSubLevel()
	{
	    Trace.println(Trace.GETTER);
		return myVersionSubLevel;
	}

	/**
	 * @return the SUT Version PatchLevel
	 */
	public String getVersionPatchLevel()
	{
	    Trace.println(Trace.GETTER);
		return myVersionPatchLevel;
	}
}
