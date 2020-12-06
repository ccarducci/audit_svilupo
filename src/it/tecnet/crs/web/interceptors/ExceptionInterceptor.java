package it.tecnet.crs.web.interceptors;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class ExceptionInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9091715118922750169L;
	private static final Logger log = Logger.getLogger(ExceptionInterceptor.class);
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		try{
		    result = invocation.invoke();
		}catch(Throwable e){
			e.printStackTrace();
			log.error("Errore ActionClass:'"+invocation.getAction().getClass().getCanonicalName()+"' ActionMethod:'"+invocation.getInvocationContext().getName()+"'",e);
			
			ValueStack vs = invocation.getStack();
			Collection<String> error =  (Collection<String>) vs.findValue("actionErrors", Collection.class);	   
		    if(error == null)
		    	error = new ArrayList<String>();

			error.add(e.getMessage() == null? "Internal system faliure.Please contact system administrator.":e.getMessage());

		    vs.setValue("actionErrors", error);
		    result = ActionSupport.ERROR;
		}
	        return result;
	      
	}
	
	
	
}
