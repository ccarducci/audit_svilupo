package it.tecnet.crs.web.interceptors;

import it.tecnet.crs.web.beans.AppUser;

public interface UserAware {
	 
    public void setUser(AppUser user);
}