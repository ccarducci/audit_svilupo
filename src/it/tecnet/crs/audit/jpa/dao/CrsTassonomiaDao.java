package it.tecnet.crs.audit.jpa.dao;

import it.tecnet.crs.jpa.model.CrsAmbito;
import it.tecnet.crs.jpa.model.CrsSinonimo;
import it.tecnet.crs.util.datatables.DataTablesModel;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface CrsTassonomiaDao {

	@Transactional
	public <T> Object salva(T entity);

	@Transactional
	public void persist(Object entity);

	@Transactional
	public void delete(Object entity);

	public <T> T findById(Class<T> entityClass, Object primaryKey);

	// GLOSSARIO
	public List<Object> getGlossarioDataTable(DataTablesModel model);

	public Long getGlossarioCountDataTable(DataTablesModel model);

	// SINONIMO
	public List<Object> getSinonimoDataTable(DataTablesModel model);

	public Long getSinonimoCountDataTable(DataTablesModel model);

	// AMBITO
	public List<Object> getAmbitoDataTable(DataTablesModel model);

	public Long getAmbitoCountDataTable(DataTablesModel model);

	// ENTITA
	public List<Object> getEntitaDataTable(DataTablesModel model);

	public Long getEntitaCountDataTable(DataTablesModel model);

	public List<CrsAmbito> getAmbitoFromIdEntita(long idEntita);

	public List<CrsAmbito> getAmbitoFromIdGlossario(long idGlossario);

	public List<CrsSinonimo> getSinonimoFromIdGlossario(long idGlossario);
}
