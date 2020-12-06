package it.tecnet.crs.audit.service;

import it.tecnet.crs.audit.jpa.dao.AuAuditDao;
import it.tecnet.crs.audit.web.dto.AuAuditDomandaDto;
import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.audit.web.dto.AuAuditorsDto;
import it.tecnet.crs.audit.web.dto.AuditDelegatoDto;
import it.tecnet.crs.audit.web.dto.AuditFasiDto;
import it.tecnet.crs.jpa.model.AuAssUtenteAudit;
import it.tecnet.crs.jpa.model.AuAssUtenteSessione;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuMDomanda;
import it.tecnet.crs.jpa.model.AuMRisposta;
import it.tecnet.crs.jpa.model.AuQuestionario;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.util.ModelToDto;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.web.dto.NonConformitaDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class AuAuditServiceImpl implements AuAuditService {

	protected static Logger log = Logger.getLogger(AuAuditServiceImpl.class);
	private AuAuditDao auAuditDao;


	/*
	 * A U D I T 
	 */

	@Override
	public List<AuAuditDto> getListAuditGenerale(long idUtente,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {
		List<Object[]> auditList= auAuditDao.getListAuditGenerale(idUtente,pageNumber,pageSize, columnNameToOrder, orderType,search);
		if(auditList==null) return null;
		List<AuAuditDto> auAuditList= new ArrayList<AuAuditDto>();
		for(Object o : auditList){			
			Object[] obj=(Object[])o;

			auAuditList.add(ModelToDto.modelToAuditDto(obj));
		}
		return auAuditList;
	}

	public void salvaAudit(AuAudit a){
		AuAudit managed=(AuAudit)auAuditDao.salva(a);
		long idAudit= managed.getIdAudit();

		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		AuAssUtenteAudit associazione=new AuAssUtenteAudit();
		associazione.setIdAudit(idAudit);
		associazione.setIdUtente(user.getIdUtente());
		try{
			AuAssUtenteAudit managedAss=(AuAssUtenteAudit)auAuditDao.salva(associazione);
			long idAss= managedAss.getIdAssUtenteAudit();
			//System.out.println(idAss);
			log.info(idAss);
		}catch (Exception e) {
			//System.out.println(e.getMessage());
			log.error(e);
			e.printStackTrace();
		}
	}

	@Override
	public void salvaModifiche(AuAudit a) {
		auAuditDao.salvaModifiche(a);

	}



	public void deleteAudit(long idAudit) {
		auAuditDao.eliminaAudit(idAudit);

	}

	//conta gli audit(serve a datatable per numero righe ecc)
	public Integer countAudit(long idUtente,String textSearch){
		return auAuditDao.countAudit(idUtente,textSearch);
	}

	public AuAudit getAuditDaModificare(long idAudit) {
		AuAudit auAudit= auAuditDao.getAuditDaModificare(idAudit);
		return auAudit;

	}

//	@Override
//	public int checkIfAuditHasCampagne(long idAudit) {
//		int numeroCampagne=auAuditDao.countCampagneAssociateAudit(idAudit);
//		return numeroCampagne;
//	}
	
	public int isAuditReferenced(long idAudit){
		return auAuditDao.isAuditReferenced(idAudit);
	}

	@Override
	public int checkIfAuditHasQuestionari(long idAudit) {
		int numeroQuestionari=auAuditDao.countQuestionariAssociatiAudit(idAudit);
		return numeroQuestionari;
	}


	/*
	 *  F A S I__________________________________________________________________________________________________________________
	 */



	//1 AUDTI -GENERALE-MODIFICA: TABELLA TAB FASI MODIFICA GENERALE
	public List<AuditFasiDto> getFasiModificaGenerale(long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {

		List<Object[]> list= auAuditDao.getFasiModificaGenerale(idAudit, pageNumber, pageSize, columnNameToOrder, orderType, search);
		List<AuditFasiDto> listDef= new ArrayList<AuditFasiDto>();
		for(Object o : list){			
			Object[] obj=(Object[])o;

			listDef.add(ModelToDto.modelToAuditFasiDto(obj, idAudit));
		}
		return listDef;
	}


	public Integer countFasiAudit(long idAudit, String textSearch) {
		int countRow= auAuditDao.countFasiAudit(idAudit, textSearch);
		return countRow;
	}



	public AuAuditDao getAuAuditDao() {
		return auAuditDao;
	}

	public void setAuAuditDao(AuAuditDao auAuditDao) {
		this.auAuditDao = auAuditDao;
	}


	/*
	 * P R O C E S S I___________________________________________________________________________________________________________________
	 */

	//popola il menu a tendina nell aside delle fasi
	public List<ProcessoDto> getProcessi() {
		List<Object[]> list= auAuditDao.getProcessi();
		List<ProcessoDto> listDef= new ArrayList<ProcessoDto>();

		for(Object o : list){			
			Object[] obj=(Object[])o;
			//(listDef.add(ModelToDto.modelToProcesso(obj));
			ProcessoDto p= new ProcessoDto();
			p.setIdProcesso((Long) obj[0]);
			p.setDescrizione((String) obj[1]);
			listDef.add(p);
		}
		return listDef;
	}


	@Override
	public List<AuditFasiDto>  getFasiInAsideTable(long idProcesso,
			long idAudit, Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {


		List<Object[]> list= auAuditDao.getFasiInAsideTable(idProcesso,idAudit, pageNumber, pageSize, columnNameToOrder, orderType, search);
		List<AuditFasiDto> listDef= new ArrayList<AuditFasiDto>();
		for(Object o : list){			
			Object[] obj=(Object[])o;

			listDef.add(ModelToDto.modelToAuditFasiDtoAside(obj, idProcesso, idAudit));
		}
		return listDef;



	}

	@Override
	public Integer countFasiInAsideTable(long idProcesso, long idAudit,
			String textSearch) {

		int countRow= auAuditDao.countFasiInAsideTable(idProcesso,idAudit, textSearch);
		return countRow;
	}

	//aggiunge la fase all'audit(tabella AU_ASS_AUDIT_FASE)
	public void addFaseToAudit(long idFaseToSave, long idAudit) {
		auAuditDao.addFaseToAudit(idFaseToSave,idAudit);

	}

	//elimina fase assegnata all audit
	public void eliminaFase(long idAudit, long idSottoprocesso) {
		auAuditDao.eliminaFase(idAudit,idSottoprocesso);

	}

	/* 
	 * N O N   C O N F O R M I T A_______________________________________________________________________________________________
	 */

	public List<NonConformitaDto> getListaNonConformita(Long idAudit,long idFase, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,String search) {
		List<Object[]> list=auAuditDao.getListaNonConformita(idAudit,idFase, pageNumber,
				pageSize,  columnNameToOrder,  orderType, search);
		List<NonConformitaDto> listDef= new ArrayList<NonConformitaDto>();

		for(Object o : list){			
			Object[] obj=(Object[])o;
			NonConformitaDto q= new NonConformitaDto();
			q.setIdNonConformita((Long)obj[0]);
			q.setDescrizione((String) obj[1]);
			q.setDescrizioneNonConformita((String) obj[2]);
			q.setRischio((String) obj[3]);
			q.setRischioEconomico((BigDecimal)obj[4]);

			listDef.add(q);
		}

		return listDef;
	}

	@Override
	public Integer countNonConformita(long idAudit, long idFase, String getsSearch ) {
		int i=auAuditDao.countNonConformita(idAudit,idFase,getsSearch);
		return i;
	}


	/*
	 *  D E L E G A T O __________________________________________________________________________
	 */



	@Override
	public long getIdDirigenteFromDelegato(long idDelegato) {
		return auAuditDao.getIdDirigenteFromDelegato(idDelegato);
	}

	@Override
	public List<AuditDelegatoDto> getListaDelegati(long idDirigente, long idAudit,Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {
		List<AuditDelegatoDto> listaDelegati=new ArrayList<AuditDelegatoDto>();
		List<Object[]> resultList=auAuditDao.getListaDelegati(idDirigente, idAudit, pageNumber, pageSize, columnNameToOrder, orderType, search);
		for(Object[] obj:resultList){
			listaDelegati.add(ModelToDto.modelToDelegatoDto(obj));
		}
		return listaDelegati;
	}

	@Override
	public int countListaDelegati(long idDirigente, long idAudit, String textSearch) {
		return auAuditDao.countListaDelegati(idDirigente, idAudit, textSearch);
	}

	@Deprecated
	public void dissociaDelegato(long idAudit, long idUtente) {
		auAuditDao.dissociaDelegato(idAudit, idUtente);
	}


	public AuAssUtenteAudit getAssociazioneDelegato(long idAudit, long idUtente) {

		AuAssUtenteAudit result = auAuditDao.getAssociazioneDelegato(idAudit, idUtente);

		return result;
	}

	/*
	 *  A U D I T O R S________________________________________________________________________________________________
	 */

	@Override
	public List<AuAuditorsDto> getListaAuditors(long idDirigente, long idAccesso,
			Integer pageNumber, Integer pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<AuAuditorsDto> listaAuditors=new ArrayList<AuAuditorsDto>();
		List<Object[]> resultList=auAuditDao.getListaAuditors(idDirigente, idAccesso, pageNumber, pageSize, columnNameToOrder, orderType, search);
		for(Object[] obj:resultList){
			listaAuditors.add(ModelToDto.modelToAuditorsDto(obj));
		}
		return listaAuditors;
	}

	@Override
	public int countListaAuditors(long idDirigente, long idAccesso,
			String textSearch) {
		return auAuditDao.countListaAuditors(idDirigente, idAccesso, textSearch);
	}

	@Deprecated
	public void dissociaAuditors(long idAccesso, long idUtente) {
		auAuditDao.dissociaAuditors(idAccesso, idUtente);

	}

	public AuAssUtenteSessione getAssociazioneAuditors(long idSessione, long idUtente) {

		AuAssUtenteSessione result = auAuditDao.getAssociazioneAuditors(idSessione, idUtente);

		return result;
	}


	/*
	 * Q U E S T I O N A R I O__________________________________________________________________________________
	 */


	@Override
	public List<AuAuditDomandaDto> getDomande(long idAudit,int pageNumber, int pageSize, int columnNameToOrder,String orderType, String search) {


		List<Object[]> listDomande= auAuditDao.getDomande( idAudit,pageNumber,pageSize,columnNameToOrder,orderType,search);

		List<AuAuditDomandaDto> domDtoList= new ArrayList<AuAuditDomandaDto>();
		for(int i=0;i<listDomande.size();i++){
			domDtoList.add(ModelToDto.modelToDomandaDto(listDomande.get(i)));
		}
		return domDtoList;
	}


	@Override
	public int countDomande(long idAudit, int pageNumber,int pageSize, int columnNameToOrder, String orderType,String search)  {
		return auAuditDao.countDomande( idAudit,  pageNumber, pageSize,  columnNameToOrder,  orderType,search) ;
	}


	@Override
	public long getNumDomandeStessoRischio(long idRischio, long idQuestionario) {

		return auAuditDao.getNumDomandeStessoRischio(idRischio, idQuestionario);
	}

	//nuova domanda
	public Double sommaPesiDomandeStessoRischio(long idRischio,
			long idQuestionario) {
		// TODO Auto-generated method stub
		return auAuditDao.sommaPesiDomandeStessoRischio(idRischio, idQuestionario);
	}

	//modifica domanda
	public Double sommaPesiDomandeStessoRischio(long idRischio,long idQuestionario, long idDomanda) {
		// TODO Auto-generated method stub
		return auAuditDao.sommaPesiDomandeStessoRischio(idRischio, idQuestionario, idDomanda);
	}

	@Override
	public AuMDomanda checkRisposteDomanda(AuMDomanda i) {
		// TODO Auto-generated method stub
		return auAuditDao.checkRisposteDomanda(i);
	}

	@Override
	public AuQuestionario cercaQuestionario(long idAudit) {
		// TODO Auto-generated method stub
		return auAuditDao.cercaQuestionario(idAudit);
	}


	//carica risposte in tabella
	public List<AuMRisposta> getRisposteDomanda(long idDomanda, int pageNumber,
			int pageSize, int columnNameToOrder, String orderType, String search) {

		return auAuditDao.getRisposteDomanda( idDomanda,  pageNumber, pageSize,  columnNameToOrder,  orderType,  search);
	}

	@Override
	public int countRisposte(long idDomanda, int pageNumber, int pageSize,
			int columnNameToOrder, String orderType, String search) {
		// TODO Auto-generated method stub
		return auAuditDao.countRisposte( idDomanda,  pageNumber, pageSize,  columnNameToOrder,  orderType,  search);
	}



	@Override
	public void deleteQuestionario(long idAudit) {
		auAuditDao.deleteQuestionario(idAudit);
	}



	/*
	 *  O P   C O M U N I_________________________________________________________________________________________
	 */

	@Override
	public <T> Object salva(T obj) {
		return auAuditDao.salva(obj);

	}


}
