package it.tecnet.crs.web.interceptors;

import it.tecnet.crs.jpa.model.CrsAssUtenteRuolo;
import it.tecnet.crs.jpa.model.CrsRuolo;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;
import it.tecnet.crs.service.UtenzeService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.util.ApplicationUtil;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.action.IndexAction;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.UserRole;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RolesInterceptor;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthorizationInterceptor extends RolesInterceptor 
implements Interceptor {
	private static final long serialVersionUID = 9091715118922750169L;

	protected List<String> allowedRoles = Collections.emptyList();
	protected List<String> disallowedRoles = Collections.emptyList();
	protected static Logger log = Logger.getLogger(AuthorizationInterceptor.class);

	private String exception;
	private String exceptionStack;

	private UtenzeService utenzeService;
	//private ApplicationContext ac;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Action action = (Action) invocation.getAction();
		//ApplicationContext ac = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");
	
		AppUser appUser = getUserInHeader(action);
		if( appUser == null){
			((BaseAction) action).setException(exception);
			((BaseAction) action).setExceptionStack(exceptionStack);
			return "loginError";
		}
			

		if(! isAllowed(appUser) )
			return handleRejection(invocation, ServletActionContext.getResponse());
		
		return invocation.invoke();

	}


	private AppUser getUserInHeader(Action action){

		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
		if(user==null){
			String nome = request.getHeader(ApplicationUtil.INPS_nome);
			String cognome = request.getHeader(ApplicationUtil.INPS_cognome);
			String username = request.getHeader(ApplicationUtil.INPS_account_windows);
			String email =  request.getHeader(ApplicationUtil.INPS_email);
			Enumeration roles =  request.getHeaders(ApplicationUtil.INPS_Ruoli); 
			
			log.info("nome:" +nome);
			log.info("cognome:" +cognome);
			log.info("username:" +username);
			log.info("email" +email);
			
			Enumeration headerNames = request.getHeaderNames();
	        while (headerNames.hasMoreElements()) {
	            String key = (String) headerNames.nextElement();
	            String value = request.getHeader(key);
	            
	            log.info("key:" +key);
	            log.info("value:" +value);
	        }

			if(StringUtils.isEmpty(username)){
				log.info("User in header is empty.");
				username = request.getParameter(ApplicationUtil.INPS_account_windows);
				if( !StringUtils.isEmpty(username) ){
					nome = request.getParameter(ApplicationUtil.INPS_nome);
					cognome = request.getParameter(ApplicationUtil.INPS_cognome);
					email = request.getParameter(ApplicationUtil.INPS_email);
					try{
						roles = Collections.enumeration(Arrays.asList(request.getParameterValues(ApplicationUtil.INPS_Ruoli)));
					}catch (Exception e) {
						exception="nessun ruolo assegnato all'utente - access denied";
						return null;
					}

				}else{
					exception="Credenziali assenti.";
					return null;
				}				
			}

			log.debug("account: "+username);
			log.debug("nominativo: "+nome+" "+cognome);

			AppUser appUser = new AppUser();
			appUser.setUsername(username);
			appUser.setNominativo(nome+" "+cognome);
			appUser.setActionInRole(ApplicationUtil.userRolesAction(appUser));

			utenzeService =  ((IndexAction) action).getUtenzeService();
			long idUtente=utenzeService.manageProfile(nome, cognome, username, email);
			if(idUtente==-1){
				exception="permission denied";
				exceptionStack="";
				return null;
			}else{
				List<CrsRuolo> listaRuoliValidi=(List<CrsRuolo>)utenzeService.getValidRolesList();
				List<String> listaRuoliAssociatiUtente=(List<String>)utenzeService.getRuoliAssociatiUtente(idUtente);
				while (roles.hasMoreElements()){
					String value = (String)roles.nextElement();
					String[] ruolo = value.split(",|:|=");
					log.debug("codiceApplicazione: "+ruolo[1]);
					log.debug("codiceRuolo: "+ruolo[2]);
					if(ruolo[1].equals(ApplicationUtil.CODICE_APPLICAZIONE)){
						UserRole role = new UserRole();
						for(CrsRuolo crsRuolo:listaRuoliValidi){
							if(ruolo[2].equals(crsRuolo.getCodice().trim())){
								if(listaRuoliAssociatiUtente.contains(ruolo[2])){
									role.setRoleName(crsRuolo.getDescrizione());
									appUser.getRoles().add(role);
								}else{
									CrsAssUtenteRuolo ass=new CrsAssUtenteRuolo();
									ass.setIdRuolo(crsRuolo.getIdRuolo());
									ass.setIdUtente(idUtente);
									utenzeService.salva(ass);
									role.setRoleName(crsRuolo.getDescrizione());
									appUser.getRoles().add(role);
								}
							}
						}
					}
				}
				for(CrsRuolo crsRuolo:listaRuoliValidi){
					UserRole role = new UserRole();
					CrsAssUtenteRuolo ass=new CrsAssUtenteRuolo();
					ass.setIdRuolo(crsRuolo.getIdRuolo());
					ass.setIdUtente(idUtente);
					utenzeService.salva(ass);
					role.setRoleName(crsRuolo.getDescrizione());
					appUser.getRoles().add(role);
				}
				
				
				if(appUser.getRoles().size()==0){
					log.info("nessun ruolo assegnato all'utente "+nome+" "+cognome);
					exception="Nessun ruolo assegnato all'utente "+nome+" "+cognome;
					return null;
				}
			}
			appUser.setIdUtente(idUtente);
			appUser.setRuoloAttivo(appUser.getRoles().get(0).getRoleName());
			updateUltimoAccesso(idUtente);
			request.getSession().setAttribute("AppUser", appUser);
			request.getSession().setAttribute("DatiUtente", new DatiUtente());
			return appUser;
		}else{
			return user;
		}

	}


	public boolean isAllowed(AppUser appUser) {

		boolean result = false;

		if (allowedRoles.size() > 0) {
			if(appUser==null){
				return result;
			}
			if( allowedRoles.contains("*") )
				return true;
			for (String role : allowedRoles) {
				if (appUser.getRolesStringList().contains(role)) {
					result = true;
				}
			}
			return result;
		} else if (disallowedRoles.size() > 0) {
			if( disallowedRoles.contains("*") )
				return false;
			if( appUser != null){
				for (String role : disallowedRoles) {
					if (appUser.getRolesStringList().contains(role)) {
						return false;
					}
				}
			}
		}
		return true;
	}



	protected String handleRejection( ActionInvocation invocation, HttpServletResponse response ) throws Exception
	{

		response.sendError(403);

		return Action.ERROR;
	}

	public List<String> getAllowedRoles(){
		return allowedRoles;
	}

	public List<String> getDisallowedRoles(){
		return disallowedRoles;
	}

	public void setAllowedRoles(String roles) {
		allowedRoles = stringToList(roles);

	}

	public void setDisallowedRoles(String roles) {
		disallowedRoles = stringToList(roles);
	}

	protected List<String> stringToList(String val)
	{
		if (val != null) 
		{
			String[] list = val.split("[ ]*,[ ]*");
			return Arrays.asList(list);
		}
		return Collections.emptyList();
	}

	public String getExceptionStack() {
		return exceptionStack;
	}

	public void setExceptionStack(String exceptionStack) {
		this.exceptionStack = exceptionStack;
	}

	
	public void updateUltimoAccesso(long id){
		try{
			CrsUtenteAdv currentUser=(CrsUtenteAdv)utenzeService.trova(CrsUtenteAdv.class,id);			
			Date currentDate=new Date(System.currentTimeMillis());
			currentUser.setDataUltimoAccesso(currentDate);
			utenzeService.salva(currentUser);
		}catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

}
