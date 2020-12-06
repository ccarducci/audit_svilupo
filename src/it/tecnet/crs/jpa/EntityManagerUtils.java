package it.tecnet.crs.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {
	
	
	
	public static EntityManagerFactory initEntityManagerFactory(String persistenceUniteName){
		EntityManagerFactory  entityManagerFactory = Persistence.createEntityManagerFactory( persistenceUniteName);
    	return entityManagerFactory;
	}
	
	
	public static void destroyEntityManagerFactory(EntityManagerFactory entityManagerFactory){
		entityManagerFactory.close();
	}

}
