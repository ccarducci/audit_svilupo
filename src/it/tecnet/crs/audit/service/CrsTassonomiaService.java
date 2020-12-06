package it.tecnet.crs.audit.service;

import it.tecnet.crs.jpa.model.CrsAmbito;
import it.tecnet.crs.jpa.model.CrsSinonimo;
import it.tecnet.crs.util.datatables.DataTablesModel;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface CrsTassonomiaService {

	@Transactional
	public <T> Object salva(T entity);

	@Transactional
	public void persist(Object entity);

	@Transactional
	public <T> void delete(T entityClass);

	@Transactional
	public <T> void deleteById(Class<T> entityClass, Long primaryKey);

	public <T> T findById(Class<T> entityClass, Object primaryKey);
	
	public void getGlossarioDataTable(DataTablesModel model);

	public void getSinonimoDataTable(DataTablesModel model);

	public void getAmbitoDataTable(DataTablesModel model);

	public void getEntitaDataTable(DataTablesModel model);

	public List<CrsAmbito> getAmbitoFromIdEntita(long idEntita);
	
	public List<CrsAmbito> getAmbitoFromIdGlossario(long idGlossario);
	
	public List<CrsSinonimo> getSinonimoFromIdGlossario(long idGlossario);
}
