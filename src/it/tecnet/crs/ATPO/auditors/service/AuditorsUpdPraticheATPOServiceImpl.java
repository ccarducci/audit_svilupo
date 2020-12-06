package it.tecnet.crs.ATPO.auditors.service;

import it.tecnet.crs.ATPO.auditors.jpa.dao.AuditorsAtpoUpdPraticheDao;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.NonConformitaPraticheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.PraticheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.RischipraticheAtpoDto;
import it.tecnet.crs.ATPO.util.ObjectToDto;
import it.tecnet.crs.util.ModelToBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AuditorsUpdPraticheATPOServiceImpl implements
		AuditrosUpdPraticheATPOService {

	private AuditorsAtpoUpdPraticheDao auditorsAtpoUpdPraticheDao;

	public AtpoFaseDati getFaseDati(String fascicolo, String codSede) {

		AtpoFaseDati fd=auditorsAtpoUpdPraticheDao.getFaseDati(fascicolo,codSede);
		return fd;

	}

	public PraticheAtpoDto getDatiPratica(long idPraticaAtpoDto,
			String fascicolo) {
		PraticheAtpoDto p = new PraticheAtpoDto();
		Object[] o = (Object[]) auditorsAtpoUpdPraticheDao.getDatiPratica(
				idPraticaAtpoDto, fascicolo);
		if (o != null) {
			try {
				p = ModelToBean.modelToPraticheAtpoDto(o);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return p;
	}

	public void setAuditorsAtpoUpdPraticheDao(
			AuditorsAtpoUpdPraticheDao auditorsAtpoUpdPraticheDao) {
		this.auditorsAtpoUpdPraticheDao = auditorsAtpoUpdPraticheDao;
	}

	public AuditorsAtpoUpdPraticheDao getAuditorsAtpoUpdPraticheDao() {
		return auditorsAtpoUpdPraticheDao;
	}

	/*
	 * tabella rischi
	 * pratiche----------------------------------------------------
	 * ------------------
	 */

	public List<RischipraticheAtpoDto> getTabRischiPratAtpo(long idAudit,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search, long idPratica) {
		List<RischipraticheAtpoDto> r = new ArrayList<RischipraticheAtpoDto>();

		List<Object[]> l = auditorsAtpoUpdPraticheDao.getTabRischiPratAtpo(
				idAudit, pageNumber, pageSize, columnNameToOrder, orderType,
				search, idPratica);
		if (l != null) {

			for (int y = 0; y < l.size(); y++) {
				Object[] o = l.get(y);
				RischipraticheAtpoDto ris = ObjectToDto
						.objectToRischiPraticheDto(o);
				r.add(ris);
			}
		}

		return r;
	}

	@Override
	public int countTabRischiPratAtpo(long idAudit, String search,
			long idPratica) {
		// TODO Auto-generated method stub
		return auditorsAtpoUpdPraticheDao.countTabRischiPratAtpo(idAudit,
				search, idPratica);
	}

	/*
	 * tabella NON CONFORMITA
	 * pratiche--------------------------------------------
	 * --------------------------
	 */
	@Override
	public List<NonConformitaPraticheAtpoDto> getNonConfPratAtpo(long idAudit,
			long idPratica, String filtro, Integer pageNumber, Integer pageSize,
			int columnNameToOrder, String orderType, String search) {
		List<NonConformitaPraticheAtpoDto> lnc = new ArrayList<NonConformitaPraticheAtpoDto>();

		List<Object[]> l = auditorsAtpoUpdPraticheDao.getNonConfPratAtpo(
				idAudit, idPratica, filtro, pageNumber, pageSize, columnNameToOrder,
				orderType, search);
		if (l != null) {

			for (int y = 0; y < l.size(); y++) {
				Object[] o = l.get(y);
				NonConformitaPraticheAtpoDto nc = ObjectToDto
						.objectToNonConformitaPraticheDto(o);
				lnc.add(nc);
			}
			return lnc;
		}
		return null;
	}

	@Override
	public int countNonConfPratAtpo(long idAudit, long idPratica, String filtro,
			String getsSearch) {
		// TODO Auto-generated method stub
		return auditorsAtpoUpdPraticheDao.countNonConfPratAtpo(idAudit,
				idPratica, filtro, getsSearch);
	}
	
	@Override
	public List<String> getDescrizioneFaseAssociate(long idAudit) {
		List<String> lnc = new ArrayList<String>();

		List<Object[]> l = auditorsAtpoUpdPraticheDao.getDescrizioneFaseAssociate(idAudit);
		if (l != null) {

			for (int y = 0; y < l.size(); y++) {
				Object o = l.get(y);
				String descr=(String)o;
				
				lnc.add(descr);
			}
			return lnc;
		}
		return null;
	}

	/*
	 * OP COMUNI
	 */

	@Override
	public <T> T save(T entity) {

		return auditorsAtpoUpdPraticheDao.salva(entity);
	}

	public <T> T cerca(Class<T> obj, Object pk) {

		return auditorsAtpoUpdPraticheDao.cerca(obj, pk);
	}

	@Override
	public List<AuTplTipologicheAtpoDto> getTipologicaAtpo(String tipo) {
		List<Object[]> list = auditorsAtpoUpdPraticheDao
				.getTipologicaAtpo(tipo);
		List<AuTplTipologicheAtpoDto> dto = new ArrayList<AuTplTipologicheAtpoDto>();
		for (Object[] obj : list) {

			dto.add(new AuTplTipologicheAtpoDto((Long) obj[0], (String) obj[1],
					(String) obj[2], (String) obj[3]));
		}
		return dto;
	}

	@Override
	public AuTplTipologicheAtpoDto getDescrTipologicaByCodifica(String codifica) {
		Object[] o = auditorsAtpoUpdPraticheDao
				.getDescrTipologicaByCodifica(codifica);
		AuTplTipologicheAtpoDto t = null;
		if (o != null) {
			t = new AuTplTipologicheAtpoDto((Long) o[0], (String) o[1],
					(String) o[2], (String) o[3]);
		}

		return t;
	}

	@Override
	public Long getAuSPraticaByIdPratica(Long idPratica) {
		// TODO Auto-generated method stub
		return auditorsAtpoUpdPraticheDao.getAuSPraticaByIdPratica(idPratica);
	}

	

}
