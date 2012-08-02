/**
 * 
 */
package org.testtoolinterfaces.testresult;

import java.util.ArrayList;

import org.testtoolinterfaces.testsuite.Parameter;
import org.testtoolinterfaces.testsuite.ParameterHash;
import org.testtoolinterfaces.testsuite.ParameterImpl;
import org.testtoolinterfaces.testsuite.ParameterVariable;
import org.testtoolinterfaces.utils.Trace;

/**
 * @author Arjan Kranenburg
 *
 */
public class ParameterResult
{
	private final Parameter myParameter;

	private Object myValue;
	private Object myActualValue;
    private ArrayList<ParameterResult> mySubParameterResults;
    
	/**
	 * @param aParameter
	 */
	public ParameterResult(Parameter aParameter)
	{
	    Trace.println(Trace.CONSTRUCTOR, "ParameterResult( " + aParameter + " )" );
	    myParameter = aParameter;
	    
		if ( myParameter instanceof ParameterImpl )
		{
			myValue = ((ParameterImpl) myParameter).getValue();
		}
		else if ( myParameter instanceof ParameterVariable )
		{
			myValue = ((ParameterVariable) myParameter).getVariableName();
		}
		else if ( myParameter instanceof ParameterHash )
		{
			myValue = ((ParameterHash) myParameter).getList();
		}
		
		mySubParameterResults = new ArrayList<ParameterResult>();
	}

	public Parameter getParameter()
	{
		return myParameter;
	}

	public Object getValue()
	{
		return myValue;
	}
	
	public void setValue( Object aValue )
	{
		myValue = aValue;
	}

	public Object getActualValue()
	{
		return myActualValue;
	}

	public void setActualValue( Object anActualValue )
	{
		myActualValue = anActualValue;
	}

	public ArrayList<ParameterResult> getSubParameterResults()
	{
		return mySubParameterResults;
	}

	public void setSubParameterResults(	ArrayList<ParameterResult> aSubParameterResults)
	{
		mySubParameterResults = aSubParameterResults;
	}
	
	public void addSubParameterResult( ParameterResult aParameterResult )
	{
		mySubParameterResults.add(aParameterResult);
	}
}
