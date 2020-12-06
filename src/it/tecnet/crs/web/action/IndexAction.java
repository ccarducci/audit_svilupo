package it.tecnet.crs.web.action;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ModelDriven;
import it.tecnet.crs.audit.service.AuAuditService;
import it.tecnet.crs.service.UtenzeService;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.beans.UserRole;

public class IndexAction extends BaseAction implements ModelDriven<AppUser> {

	private static final long serialVersionUID = -2879655035580628498L;

	private UtenzeService utenzeService;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	private AppUser appUser= (AppUser)request.getSession().getAttribute("AppUser");
	


	public IndexAction(UtenzeService utenzeService,AuAuditService auAuditService){
		this.utenzeService = utenzeService;
	}
	/*
	 * In base all'auditor distingue tra template AuditorV e template AuditorATPO
	 */
	public String execute(){
		return SUCCESS;
	}


	public UtenzeService getUtenzeService() {
		return utenzeService;
	}

	public void setUtenzeService(UtenzeService utenzeService) {
		this.utenzeService = utenzeService;
	}
	
	public String getRuoli(){
		getModel();
		return SUCCESS;
	}
	
	public String switchActiveRole(){
		List<UserRole> rolesList=getModel().getRoles();
		String selectedRole=getModel().getRuoloAttivo();
		for(UserRole role:rolesList){
			if(role.getRoleName().equalsIgnoreCase(selectedRole)){
				int selectedRolePosition=rolesList.indexOf(role);
				Collections.swap(rolesList, 0, selectedRolePosition);
				break;
			}
		}
		return SUCCESS;
	}
	
	
	@Override
	public AppUser getModel() {
		return this.appUser;
	}

	
	

}
