package it.polito.ai.gas.beans;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class FlyweightLogger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7698174902982154195L;
	private Logger logger;
	private boolean printOut; // quando non ci si fida del logger
	
	public FlyweightLogger()
	{
		//logger = org.apache.log4j.Logger.getRootLogger();
		logger = org.apache.log4j.Logger.getLogger("");
		printOut = true;
	}
	
	public void addInfo(String info)
	{
		logger.info(info);
		if (printOut)
			System.out.println(info);
	}
	
	public void addError(String error)
	{
		logger.error(error);
		if (printOut)
			System.out.println(error);
	}
}
