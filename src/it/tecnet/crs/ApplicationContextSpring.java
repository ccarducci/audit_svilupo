package it.tecnet.crs;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextSpring implements ApplicationContextAware{

	
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
		
	}
	
	public static ApplicationContext getApplicationContext()
	{
		return ApplicationContextSpring.applicationContext;
	}

}
