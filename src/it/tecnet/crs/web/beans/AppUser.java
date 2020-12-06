package it.tecnet.crs.web.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AppUser implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private long idAudit;
	
	private Long idUtente;
	
	private String username;

	private String nominativo;
	
	private String ruoloAttivo;
	
	private List<UserRole> roles = new ArrayList<UserRole>();
	
	private Set<String> actionInRole = new HashSet<String>();

	public String getRuoloAttivo() {
		return ruoloAttivo;
	}

	public void setRuoloAttivo(String ruoloAttivo) {
		this.ruoloAttivo = ruoloAttivo;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public String getNominativo() {
		return nominativo;
	}

	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	
	public boolean isAdministrator(){
		
		for(UserRole role : getRoles() )
		{
			if( role.isAdministrator() )
				return true;
		}
		
		return false;
	}

	
	
	public List<String> getRolesStringList() {
		if( roles == null )
			return null;
		List<String> ret = new ArrayList<String>();
		
		for ( UserRole role : roles )
		{
			ret.add(  role.getRoleName() );
		}
		return ret;
	}

	
	public boolean haveAction( String actionName )
	{
		return  getActionInRole().contains(actionName);
		//ApplicationUtil.userRolesInAction( actionName, this );
	}
	
	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public Set<String> getActionInRole() {
		return actionInRole;
	}

	public void setActionInRole(Set<String> actionInRole) {
		this.actionInRole = actionInRole;
	}

	public long getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}
	
	
}
