package org.testtoolinterfaces.testresult;

import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testtoolinterfaces.utils.Mark;

public class SutInfo
{
    private static final Logger LOG = LoggerFactory.getLogger(SutInfo.class);

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
		LOG.trace(Mark.CONSTRUCTOR, "{}", aProduct);
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
		LOG.trace(Mark.SETTER, "{}, {}, {}", 
				aVersionMainLevel, aVersionSubLevel, aVersionPatchLevel);
		myVersionMainLevel = aVersionMainLevel;
		myVersionSubLevel = aVersionSubLevel;
		myVersionPatchLevel = aVersionPatchLevel;
	}
	
	/**
	 * 
	 */
	public void setDescription( String aDescription )
	{
		LOG.trace(Mark.SETTER, "{}", aDescription);
		myDescription = aDescription;
	}

	/**
	 * @param aKey: the key to the log-file
	 * @param aLog: the location of the log-file
	 */
	public void addSutLog(String aKey, String aLog)
	{
		LOG.trace(Mark.SETTER, "{}, {}", aKey, aLog);
		myLogs.put(aKey, aLog);
	}

	/**
	 * @return the SUT Description
	 */
	public String getDescription()
	{
		LOG.trace(Mark.GETTER, "");
		return myDescription;
	}

	/**
	 * @return the logs
	 */
	public Hashtable<String, String> getLogs()
	{
		LOG.trace(Mark.GETTER, "");
		return myLogs;
	}

	/**
	 * @return the Name
	 */
	public String getName()
	{
		LOG.trace(Mark.GETTER, "");
		return myName;
	}

	/**
	 * @return the SUT Version MainLevel
	 */
	public String getVersionMainLevel()
	{
		LOG.trace(Mark.GETTER, "");
		return myVersionMainLevel;
	}

	/**
	 * @return the SUT Version SubLevel
	 */
	public String getVersionSubLevel()
	{
		LOG.trace(Mark.GETTER, "");
		return myVersionSubLevel;
	}

	/**
	 * @return the SUT Version PatchLevel
	 */
	public String getVersionPatchLevel()
	{
		LOG.trace(Mark.GETTER, "");
		return myVersionPatchLevel;
	}
}
