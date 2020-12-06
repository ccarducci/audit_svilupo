package it.tecnet.crs.audit.jpa.dao;

import it.tecnet.crs.audit.web.beans.tassonomie.ambito.ModelAmbitoDataTableDto;
import it.tecnet.crs.audit.web.beans.tassonomie.entita.ModelEntitaDataTableDto;
import it.tecnet.crs.audit.web.beans.tassonomie.glossario.ModelGlossarioDataTableDto;
import it.tecnet.crs.audit.web.beans.tassonomie.sinonimo.ModelSinonimoDataTableDto;
import it.tecnet.crs.jpa.model.CrsAmbito;
import it.tecnet.crs.jpa.model.CrsSinonimo;
import it.tecnet.crs.util.datatables.DataTablesModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class CrsTassonomiaDaoImpl implements CrsTassonomiaDao {

	protected static Logger log = Logger.getLogger(CrsTassonomiaDaoImpl.class);

	@PersistenceContext()
	private EntityManager em;

	@Transactional
	public <T> T salva(T entity) {
		try {
			entity = em.merge(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return entity;
	}

	@Transactional
	public void persist(Object entity) {
		try {
			em.persist(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Transactional
	public void delete(Object entity) {
		em.remove(entity);
	}

	@Override
	public <T> T findById(Class<T> entityClass, Object primaryKey) {
		try {
			return em.find(entityClass, primaryKey);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	// ******************************************************************************************************
	// ------------------------------------------------------------------------------------------------------
	// DATATABLES INIZIO
	// ------------------------------------------------------------------------------------------------------
	// ******************************************************************************************************

	// --------------------------------------------------------------------------
	// GLOSSARIO
	// --------------------------------------------------------------------------
	private String getSearchGlossario(String queryStr, DataTablesModel model) {
		if (model.getSSearch() != null && model.getSSearch().length() > 0)
			queryStr += " where PAROLA like '%" + model.getSSearch()
					+ "%' or DESCR_PAROLA like '%" + model.getSSearch() + "%' ";
		return queryStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getGlossarioDataTable(DataTablesModel model) {
		List<Object> ret = new ArrayList<Object>();
		List<Object[]> retQuery = new ArrayList<Object[]>();
		// ModelGlossarioRow
		String queryStr = "select ID_GLOSSARIO as idGlossario "
				+ ", PAROLA as parola" + ", DESCR_PAROLA as descrParola"
				+ ", DATA_INIZIO as dataInizio" + ", DATA_FINE as dataFine"
				+ " from CRS_GLOSSARIO r";

		queryStr = getSearchGlossario(queryStr, model);

		if (model.getISortColArray().size() > 0) {
			int colOrder = Integer.parseInt(model.getISortColArray().get(0)) + 1;
			queryStr += " ORDER BY " + (colOrder) + " "
					+ model.getSSortDirArray().get(0);
		} else
			queryStr += " ORDER BY ID_GLOSSARIO asc ";

		queryStr += " OFFSET " + (model.getiDisplayStart())
				+ " ROWS FETCH NEXT " + (model.getiDisplayLength() /*
																	 * +model.
																	 * getiDisplayStart
																	 * ()
																	 */)
				+ " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			retQuery = querySel.getResultList();
			for (Object[] object : retQuery) {
				ModelGlossarioDataTableDto item = new ModelGlossarioDataTableDto();
				item.setIdGlossario((Long) object[0]);
				item.setParola((String) object[1]);
				item.setDescParola((String) object[2]);
				item.setDataInizio((Date) object[3]);
				item.setDataFine((Date) object[4]);
				ret.add(item);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return ret;
	}

	@Override
	public Long getGlossarioCountDataTable(DataTablesModel model) {
		Integer total = 0;
		String queryStr = "select count(*) total" + " from CRS_GLOSSARIO r";

		queryStr = getSearchGlossario(queryStr, model);

		try {

			Query querySel = em.createNativeQuery(queryStr);
			total = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return total.longValue();
	}

	// --------------------------------------------------------------------------
	// SINONIMO
	// --------------------------------------------------------------------------
	private String getSearchSinonimo(String queryStr, DataTablesModel model) {
		if (model.getSSearch() != null && model.getSSearch().length() > 0)
			queryStr += " where SINONIMO like '%" + model.getSSearch()
					+ "%' or DESCR_SINONIMO like '%" + model.getSSearch()
					+ "%' ";
		return queryStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getSinonimoDataTable(DataTablesModel model) {
		List<Object> ret = new ArrayList<Object>();
		List<Object[]> retQuery = new ArrayList<Object[]>();
		// ModelGlossarioRow
		String queryStr = "select ID_SINONIMO as idSinonimo, ID_GLOSSARIO as idGlossario "
				+ ", SINONIMO as sinonimo"
				+ ", DESCR_SINONIMO as descrSinonimo"
				+ ", DATA_INIZIO as dataInizio"
				+ ", DATA_FINE as dataFine"
				+ " from CRS_SINONIMO r";

		queryStr = getSearchSinonimo(queryStr, model);

		if (model.getISortColArray().size() > 0) {
			int colOrder = Integer.parseInt(model.getISortColArray().get(0)) + 1;
			queryStr += " ORDER BY " + (colOrder) + " "
					+ model.getSSortDirArray().get(0);
		} else
			queryStr += " ORDER BY ID_SINONIMO asc ";

		queryStr += " OFFSET " + (model.getiDisplayStart())
				+ " ROWS FETCH NEXT " + (model.getiDisplayLength())
				+ " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			retQuery = querySel.getResultList();
			for (Object[] object : retQuery) {
				ModelSinonimoDataTableDto item = new ModelSinonimoDataTableDto();
				item.setIdSinonimo((Long) object[0]);
				item.setIdGlossario((Long) object[1]);
				item.setSinonimo((String) object[2]);
				item.setDescSinonimo((String) object[3]);
				item.setDataInizio((Date) object[4]);
				item.setDataFine((Date) object[5]);
				ret.add(item);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return ret;
	}

	@Override
	public Long getSinonimoCountDataTable(DataTablesModel model) {
		Integer total = 0;
		String queryStr = "select count(*) total" + " from CRS_SINONIMO r";

		queryStr = getSearchSinonimo(queryStr, model);

		try {

			Query querySel = em.createNativeQuery(queryStr);
			total = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return total.longValue();
	}

	// --------------------------------------------------------------------------
	// AMBITO
	// --------------------------------------------------------------------------
	private String getSearchAmbito(String queryStr, DataTablesModel model) {
		if (model.getSSearch() != null && model.getSSearch().length() > 0)
			queryStr += " where AMBITO like '%" + model.getSSearch()
					+ "%' or DESCR_AMBITO like '%" + model.getSSearch() + "%' ";
		return queryStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAmbitoDataTable(DataTablesModel model) {
		List<Object> ret = new ArrayList<Object>();
		List<Object[]> retQuery = new ArrayList<Object[]>();
		// ModelGlossarioRow
		String queryStr = "select r.ID_AMBITO as idAmbito "
				+ ", r.AMBITO as ambito"
				+ ", r.DESCR_AMBITO as descrAmbito"
				+ ", r.ID_GLOSSARIO as idGlossario, r.ID_ENTITA as idEntita "
				+ " , r.TIPO_AMBITO as tipoAmbito , r.DATA_INIZIO as dataInizio, r.DATA_FINE as dataFine"
				+ " from CRS_AMBITO r, CRS_ENTITA e, CRS_GLOSSARIO g"
				+ " WHERE r.ID_GLOSSARIO = g.ID_GLOSSARIO"
				+ " AND r.ID_ENTITA = e.ID_ENTITA";

		queryStr = getSearchGlossario(queryStr, model);

		if (model.getISortColArray().size() > 0) {
			int colOrder = Integer.parseInt(model.getISortColArray().get(0)) + 1;
			queryStr += " ORDER BY " + (colOrder) + " "
					+ model.getSSortDirArray().get(0);
		} else
			queryStr += " ORDER BY ID_AMBITO asc ";

		queryStr += " OFFSET " + (model.getiDisplayStart())
				+ " ROWS FETCH NEXT " + (model.getiDisplayLength() /*
																	 * +model.
																	 * getiDisplayStart
																	 * ()
																	 */)
				+ " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			retQuery = querySel.getResultList();
			for (Object[] object : retQuery) {
				ModelAmbitoDataTableDto item = new ModelAmbitoDataTableDto();
				item.setIdAmbito((Long) object[0]);
				item.setAmbito((String) object[1]);
				item.setDescAmbito((String) object[2]);
				item.setIdGlossario((Long) object[3]);
				item.setIdEntita((Long) object[4]);
				item.setTipoAmbito((String) object[5]);
				item.setDataInizio((Date) object[6]);
				item.setDataFine((Date) object[7]);
				ret.add(item);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return ret;
	}

	@Override
	public Long getAmbitoCountDataTable(DataTablesModel model) {
		Integer total = 0;
		String queryStr = "select count(*) total" + " from CRS_AMBITO r";

		queryStr = getSearchAmbito(queryStr, model);

		try {

			Query querySel = em.createNativeQuery(queryStr);
			total = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return total.longValue();
	}

	// --------------------------------------------------------------------------
	// ENTITA
	// --------------------------------------------------------------------------
	private String getSearchEntita(String queryStr, DataTablesModel model) {
		if (model.getSSearch() != null && model.getSSearch().length() > 0)
			queryStr += " where ENTITA like '%" + model.getSSearch()
					+ "%' or DESCR_ENTITA like '%" + model.getSSearch() + "%' ";
		return queryStr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getEntitaDataTable(DataTablesModel model) {
		List<Object> ret = new ArrayList<Object>();
		List<Object[]> retQuery = new ArrayList<Object[]>();
		// ModelGlossarioRow
		String queryStr = "select ID_ENTITA as idEntita "
				+ ", ENTITA as entita"
				+ ", DESCR_ENTITA as descrEntita, TIPO as tipoEntita"
				+ ", DATA_INIZIO as dataInizio, DATA_FINE as dataFine "
				+ " from CRS_ENTITA r";

		queryStr = getSearchEntita(queryStr, model);

		if (model.getISortColArray().size() > 0) {
			int colOrder = Integer.parseInt(model.getISortColArray().get(0)) + 1;
			queryStr += " ORDER BY " + (colOrder) + " "
					+ model.getSSortDirArray().get(0);
		} else
			queryStr += " ORDER BY ID_ENTITA asc ";

		queryStr += " OFFSET " + (model.getiDisplayStart())
				+ " ROWS FETCH NEXT " + (model.getiDisplayLength() /*
																	 * +model.
																	 * getiDisplayStart
																	 * ()
																	 */)
				+ " ROWS ONLY";

		try {
			Query querySel = em.createNativeQuery(queryStr);
			retQuery = querySel.getResultList();
			for (Object[] object : retQuery) {
				ModelEntitaDataTableDto item = new ModelEntitaDataTableDto();
				item.setIdEntita((Long) object[0]);
				item.setEntita((String) object[1]);
				item.setDescEntita((String) object[2]);
				item.setTipoEntita((String) object[3]);
				item.setDataInizio((Date) object[4]);
				item.setDataFine((Date) object[5]);
				ret.add(item);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return ret;
	}

	@Override
	public Long getEntitaCountDataTable(DataTablesModel model) {
		Integer total = 0;
		String queryStr = "select count(*) total" + " from CRS_ENTITA r";

		queryStr = getSearchEntita(queryStr, model);

		try {

			Query querySel = em.createNativeQuery(queryStr);
			total = (Integer) querySel.getSingleResult();

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
		return total.longValue();
	}

	@Override
	public List<CrsAmbito> getAmbitoFromIdEntita(long idEntita) {
		List<CrsAmbito> ret = null;
		try {
			Query q = em.createNamedQuery(CrsAmbito.QUERY_AMBIO_BY_IDENTITA);
			q.setParameter(CrsAmbito.PARAM_IDENTITA, idEntita);
			ret = q.getResultList();
		} catch (Exception ex) {
			log.error(ex);
		}
		return ret;
	}

	@Override
	public List<CrsAmbito> getAmbitoFromIdGlossario(long idGlossario) {
		List<CrsAmbito> ret = null;
		try {
			Query q = em.createNamedQuery(CrsAmbito.QUERY_AMBIO_BY_IDGLOSSARIO);
			q.setParameter(CrsAmbito.PARAM_IDGLOSSARIO, idGlossario);
			ret = q.getResultList();
		} catch (Exception ex) {
			log.error(ex);
		}
		return ret;
	}

	@Override
	public List<CrsSinonimo> getSinonimoFromIdGlossario(long idGlossario) {
		List<CrsSinonimo> ret = null;
		try {
			Query q = em
					.createNamedQuery(CrsSinonimo.QUERY_SINONIMO_BY_IDGLOSSARIO_);
			q.setParameter(CrsSinonimo.PARAM_IDGLOSSARIO, idGlossario);
			ret = q.getResultList();
		} catch (Exception ex) {
			log.error(ex);
		}
		return ret;
	}

	// ******************************************************************************************************
	// ------------------------------------------------------------------------------------------------------
	// DATATABLES FINE
	// ------------------------------------------------------------------------------------------------------
	// ******************************************************************************************************
}
