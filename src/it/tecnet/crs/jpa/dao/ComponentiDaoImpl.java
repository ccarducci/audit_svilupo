package it.tecnet.crs.jpa.dao;

import it.tecnet.crs.componenti.jpa.model.CrsClasse;
import it.tecnet.crs.componenti.jpa.model.CrsTipo;
import it.tecnet.crs.util.ApplicationUtil;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

public class ComponentiDaoImpl implements ComponentiDao {
	
	protected static Logger log = Logger.getLogger(ComponentiDaoImpl.class);
	
	
	

	public ComponentiDaoImpl() {
	
	}
	
	/*
	 * 		CLASSE
	 * */

	public void saveClasse(CrsClasse classe){
		
	}
	
	public void deleteClasse(long idClasse){
		
	}
	
	public List<CrsClasse> getClasse (String ricerca, Integer pageNumber, Integer pageSize, String columnNameToOrder, String orderType, String textSearch, char stato){
		List<CrsClasse> listaClasse=null;
		return listaClasse;
	}
	
	public void disableTipo(Long idTipo){
		
	}
	
	public void enableTipo(Long idTipo){
		
	}
	
	
	/*
	 * 		TIPO
	 * */
	public void saveTipo(CrsTipo tipo){
		
	}
	
	public void deleteTipo(long idTipo){
		
	}
	
	public List<CrsTipo> getTipo (String ricerca, Integer pageNumber, Integer pageSize, String columnNameToOrder, String orderType, String textSearch, char stato){
		List<CrsTipo> listaTipo=null;
		return listaTipo;
	}
	
	public void disableClasse(Long idClasse){
		
	}
	
	public void enableClasse(Long idClasse){
		
	}
}
