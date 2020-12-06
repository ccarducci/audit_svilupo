package it.tecnet.crs.web.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserRole {

	private String roleName;
	
	private boolean isAdministrator;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isAdministrator() {
		return isAdministrator;
	}
	
	public boolean getIsAdministrator() {
		return isAdministrator;
	}

	public void setIsAdministrator(boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}
	
}
