
package it.tecnet.crs.web.action;



import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport  {
	private static final long serialVersionUID = 1L;

	protected static Logger log = Logger.getLogger(BaseAction.class);
	
	protected String exception;

	protected String exceptionStack;


	public String getExceptionStack() {
		return exceptionStack;
	}

	public void setExceptionStack(String exceptionStack) {
		this.exceptionStack = exceptionStack;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
