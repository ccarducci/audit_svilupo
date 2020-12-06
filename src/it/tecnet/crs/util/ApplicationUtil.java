package it.tecnet.crs.util;

import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.interceptors.AuthorizationInterceptor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.Dispatcher;

import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.config.entities.InterceptorMapping;
import com.opensymphony.xwork2.config.entities.PackageConfig;

public class ApplicationUtil {
	
	public static String INPS_matricola= "INPS-matricola";
	public static String INPS_codice_fiscale= "INPS-codice-fiscale";
	public static String INPS_email= "INPS-email";
	
	public static String INPS_account_windows= "INPS-account-windows";
	public static String INPS_nominativo= "INPS-nominativo";
	public static String INPS_nome= "INPS-nome";
	
	public static String INPS_cognome= "INPS-cognome";
	public static String INPS_codice_sede= "INPS-codice-sede";
	public static String INPS_codice_sede_SAP= "INPS-codice-sede-SAP";
	
	public static String INPS_profilo_VEGA= "INPS-profilo-VEGA";
	public static String INPS_qualifica = "INPS-qualifica";
	public static String INPS_Ruoli= "INPS-Ruoli";
	
	public static String CODICE_APPLICAZIONE="A7776";
	
//	public static String CODICE_RUOLO_DIR_GENERALE="P11611";
//	
//	public static String CODICE_RUOLO_DIRIGENTE_01="P11612";
//	
//	public static String CODICE_RUOLO_DIRIGENTE_02="P11613";
//
//	public static String CODICE_RUOLO_DELEGATO_01="P11614";
//	
//	public static String CODICE_RUOLO_DELEGATO_02="P11615";
//	
//	public static String CODICE_RUOLO_AUDITORS_01="P11616";
//	
//	public static String CODICE_RUOLO_AUDITORS_02="P11617";
//	
//	public static String CODICE_RUOLO_MASTER_DCSIT="P11618";
	

	private static ServletContext context;

	protected static Logger log = Logger.getLogger(ApplicationUtil.class);

	/* Called by Listener */
	public static void setServletContext(ServletContext context){
		ApplicationUtil.context = context;
	}
	/* Use this method to access context from any location */
	public static ServletContext getServletContext(){
		return ApplicationUtil.context;
	}
	
	public static Set<String> userRolesAction(AppUser user)
	{
		Set<String> userRoleAction = new HashSet<String>();

		Map<String, PackageConfig> packageConfMap = Dispatcher.getInstance().getConfigurationManager().getConfiguration().getPackageConfigs();

		Collection<PackageConfig> packageConfColl = packageConfMap.values();

		for ( Iterator iterator = packageConfColl.iterator(); iterator.hasNext(); )
		{
			PackageConfig packConfig = (PackageConfig) iterator.next();


			Map<String, ActionConfig> actionConfigMap = packConfig.getActionConfigs();

			for (Iterator iterator2 = actionConfigMap.values().iterator(); iterator2.hasNext();) 
			{
				ActionConfig actionConfig = (ActionConfig) iterator2.next();


				List<InterceptorMapping> interceptorList = actionConfig.getInterceptors();

				if( interceptorList != null )
				{
					for (InterceptorMapping interceptorMapping : interceptorList) 
					{
						if( interceptorMapping.getInterceptor() instanceof AuthorizationInterceptor )
						{
							AuthorizationInterceptor interceptor = (AuthorizationInterceptor) interceptorMapping.getInterceptor();

							if( interceptor.isAllowed(user) )
							{
								String nameSpace = StringUtils.defaultString(packConfig.getNamespace(),"/").equalsIgnoreCase("/") ? "" : packConfig.getNamespace()  + "/";
								nameSpace = nameSpace.startsWith("/") ? nameSpace.substring(1) : nameSpace;
								userRoleAction.add( nameSpace + actionConfig.getName());

							}
						}
					}
				}

			}	

		}

		return userRoleAction;

	}
}