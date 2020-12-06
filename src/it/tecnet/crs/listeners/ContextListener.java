package it.tecnet.crs.listeners;
//
import it.tecnet.crs.util.ApplicationUtil;

import java.io.FileReader;
import java.util.Enumeration;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class ContextListener  implements ServletContextListener{

	
	private static String rootSettings;



	public void contextDestroyed(ServletContextEvent sce) {

		ServletContext theContext = sce.getServletContext();

	}

	public void contextInitialized(ServletContextEvent sce) {

		try{

			ServletContext theContext = sce.getServletContext();


			//Cartella di configurazione
			String paramRootConfig = sce.getServletContext().getInitParameter("CONFIG_ROOT_PATH");
			Validate.notBlank(paramRootConfig,"Definire nel web.xml il parametro CONFIG_ROOT_PATH");




			String configUrl = getConfigRootPath(paramRootConfig);
			Validate.notBlank(configUrl,"Definire il parametro di ambiente " + paramRootConfig );		

			setRootSettings(configUrl);


			String paramFileConfig = sce.getServletContext().getInitParameter("CONFIG_STATICO");
			Validate.notBlank(paramFileConfig,"Definire nel web.xml il parametro CONFIG_STATICO");

			Properties appProperties = loadPropertiesToSystem(paramFileConfig);

			BasicConfigurator.configure();
			PropertyConfigurator.configure(appProperties);

			//MS SQL
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			ApplicationUtil.setServletContext( theContext );


		}catch(Throwable e){
			e.printStackTrace();
			throw new RuntimeException("Errore avvio applicazione :" + e.getMessage());

		}
		finally{
			
		}

	}

	private String getConfigRootPath(String nomeVar ) throws NamingException {

		String percorso = "";


		Context ctx = new InitialContext();
		ctx = (Context) ctx.lookup("cell/persistent");
		percorso = (String) ctx.lookup(nomeVar);
		System.out.println("percorso file di configurazione: "+percorso);

		return percorso;

	}

	private Properties loadPropertiesToSystem( String file) throws Exception {

		//Load properties for framework and add them to system properties
		Properties systemProperties = System.getProperties();
		Properties applicationProperties = new Properties();

		applicationProperties.load( new FileReader(getRootSettings() + file));

		Enumeration propertyNames = applicationProperties.propertyNames();
		while (propertyNames.hasMoreElements())
		{
			String propertyName = (String)propertyNames.nextElement();
			/*   if("app.datasource".equalsIgnoreCase(propertyName) )
            	 systemProperties.put(propertyName, "java:comp/env/" + applicationProperties.getProperty(propertyName));
            else*/
			systemProperties.put(propertyName, applicationProperties.getProperty(propertyName));
		}
		return applicationProperties;
	}

	public static String getRootSettings() {
		return rootSettings;
	}

	public static void setRootSettings(String rootSettings) {
		ContextListener.rootSettings = rootSettings;
	}

}
