package it.tecnet.crs.jpa.dao;

import java.util.List;


import it.tecnet.crs.componenti.jpa.model.CrsClasse;
import it.tecnet.crs.componenti.jpa.model.CrsTipo;


public interface ComponentiDao {
	
	/*
	 * 		CLASSE
	 * */
	public void saveClasse(CrsClasse classe);
	
	public void deleteClasse(long idClasse);
	
	public List<CrsClasse> getClasse (String ricerca, Integer pageNumber, Integer pageSize, String columnNameToOrder, String orderType, String textSearch, char stato);
	
	public void disableClasse(Long idClasse);
	
	public void enableClasse(Long idClasse);
	
	/*
	 * 		TIPO
	 * */
	public void saveTipo(CrsTipo tipo);
	
	public void deleteTipo(long idTipo);
	
	public List<CrsTipo> getTipo (String ricerca, Integer pageNumber, Integer pageSize, String columnNameToOrder, String orderType, String textSearch, char stato);
	
	public void disableTipo(Long Tipo);
	
	public void enableTipo(Long idTipo);

}
