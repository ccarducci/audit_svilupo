package it.tecnet.crs.audit.service;

import it.tecnet.crs.audit.jpa.dao.CrsTassonomiaDao;
import it.tecnet.crs.jpa.model.CrsAmbito;
import it.tecnet.crs.jpa.model.CrsSinonimo;
import it.tecnet.crs.util.datatables.DataTablesModel;

import java.util.List;

import org.apache.log4j.Logger;

public class CrsTassonomiaServiceImpl implements CrsTassonomiaService {

	protected static Logger log = Logger
			.getLogger(CrsTassonomiaServiceImpl.class);

	private CrsTassonomiaDao crsTassonomiaDao;

	public CrsTassonomiaDao getCrsTassonomiaDao() {
		return crsTassonomiaDao;
	}

	public void setCrsTassonomiaDao(CrsTassonomiaDao crsTassonomiaDao) {
		this.crsTassonomiaDao = crsTassonomiaDao;
	}

	@Override
	public void persist(Object entity) {
		crsTassonomiaDao.persist(entity);
	}

	@Override
	public <T> Object salva(T entity) {
		return crsTassonomiaDao.salva(entity);
	}

	@Override
	public <T> void deleteById(Class<T> entityClass, Long primaryKey) {
		Object obj = crsTassonomiaDao.findById(entityClass, primaryKey);
		crsTassonomiaDao.delete(obj);
	}

	@Override
	public <T> void delete(T entityClass) {
		crsTassonomiaDao.delete(entityClass);
	}

	@Override
	public <T> T findById(Class<T> entityClass, Object primaryKey) {
		return crsTassonomiaDao.findById(entityClass, primaryKey);
	}

	@Override
	public void getGlossarioDataTable(DataTablesModel model) {
		List<Object> aaDataList = crsTassonomiaDao.getGlossarioDataTable(model);
		Long total = crsTassonomiaDao.getGlossarioCountDataTable(model);
		model.setiTotalDisplayRecords(total.intValue());
		model.setiTotalRecords(total.intValue());
		model.setaaData(aaDataList);
	}

	@Override
	public void getSinonimoDataTable(DataTablesModel model) {
		List<Object> aaDataList = crsTassonomiaDao.getSinonimoDataTable(model);
		Long total = crsTassonomiaDao.getSinonimoCountDataTable(model);
		model.setiTotalDisplayRecords(total.intValue());
		model.setiTotalRecords(total.intValue());
		model.setaaData(aaDataList);
	}

	@Override
	public void getAmbitoDataTable(DataTablesModel model) {
		List<Object> aaDataList = crsTassonomiaDao.getAmbitoDataTable(model);
		Long total = crsTassonomiaDao.getAmbitoCountDataTable(model);
		model.setiTotalDisplayRecords(total.intValue());
		model.setiTotalRecords(total.intValue());
		model.setaaData(aaDataList);
	}

	@Override
	public void getEntitaDataTable(DataTablesModel model) {
		List<Object> aaDataList = crsTassonomiaDao.getEntitaDataTable(model);
		Long total = crsTassonomiaDao.getEntitaCountDataTable(model);
		model.setiTotalDisplayRecords(total.intValue());
		model.setiTotalRecords(total.intValue());
		model.setaaData(aaDataList);
	}

	@Override
	public List<CrsAmbito> getAmbitoFromIdEntita(long idEntita) {
		return crsTassonomiaDao.getAmbitoFromIdEntita(idEntita);
	}

	@Override
	public List<CrsAmbito> getAmbitoFromIdGlossario(long idGlossario) {
		return crsTassonomiaDao.getAmbitoFromIdGlossario(idGlossario);
	}

	@Override
	public List<CrsSinonimo> getSinonimoFromIdGlossario(long idGlossario) {
		return crsTassonomiaDao.getSinonimoFromIdGlossario(idGlossario);
	}

}
