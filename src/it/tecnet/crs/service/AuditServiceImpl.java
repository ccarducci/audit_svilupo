package it.tecnet.crs.service;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoRiepilogoFascicolo;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoTipologicheDto;
import it.tecnet.crs.ATPO.util.ObjectToDto;
import it.tecnet.crs.jpa.dao.AuditDao;
import it.tecnet.crs.jpa.model.AuSPraCalIndLog;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.util.ModelToDto;
import it.tecnet.crs.web.dto.AuMVarCompDto;
import it.tecnet.crs.web.dto.AuTplTipologicheDto;
import it.tecnet.crs.web.dto.NonConformitaAccessiDto;
import it.tecnet.crs.web.dto.NonConformitaPraticheDto;
import it.tecnet.crs.web.dto.PraticaConRischioDto;
import it.tecnet.crs.web.dto.RischiAccessiDto;
import it.tecnet.crs.web.dto.RischiPraticheDto;
import it.tecnet.crs.web.dto.SessioneDto;
import it.tecnet.crs.web.dto.VerbaleDto;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditServiceImpl implements AuditService {


	private AuditDao auditDao;

	public AuditDao getAuditDao() {
		return auditDao;
	}

	public void setAuditDao(AuditDao auditDao) {
		this.auditDao = auditDao;
	}



	public Long getIdAuditBySessione(Long idSessione){
		return auditDao.getIdAuditBySessione(idSessione);
	}



	public List<SessioneDto> getListaSessioniUtente(long idUtente,Integer pageNumber, Integer pageSize, String columnNameToOrder, String orderType, String textSearch){
		List<Object[]> listaSessioni = auditDao.getListaSessioniUtente(idUtente, pageNumber,  pageSize,  columnNameToOrder,  orderType,  textSearch);
		List<SessioneDto> listaSessioneDto= new ArrayList<SessioneDto>();
		for(Object sessioni : listaSessioni){			
			Object[] obj=(Object[])sessioni;
			listaSessioneDto.add(ModelToDto.modelToDto(obj));
		}

		return listaSessioneDto;
	}

	public Integer countAllSessioniUtente(long idUtente,String textSearch){
		return auditDao.countAllSessioniUtente(idUtente, textSearch);
	}

	@Override
	public String getNotaSessione(long idSessione) {
		return auditDao.getNotaSessione(idSessione);

	}






	@Override
	public List<RischiPraticheDto> getTabRischiPrat(long idAudit, Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,
			String search){
		List<RischiPraticheDto> r= new ArrayList<RischiPraticheDto>();
		List<Object[]> l=auditDao.getTabRischiPrat( idAudit,  pageNumber, pageSize,  columnNameToOrder,  orderType,search) ;
		try{
			for(int y=0; y<l.size();y++){
				Object[] o=l.get(y);
				int i=0;
				RischiPraticheDto rpd = new RischiPraticheDto();
				if(o[i] != null){
					rpd.setDescrizione((String)o[i].toString().trim());
				}
				i++;
				if(o[i] != null){
					rpd.setImportoContrNonIncass((BigDecimal)o[i]);
				}i++;
				if(o[i] != null){
					rpd.setImportoIndebSospeso((BigDecimal)o[i]);
				}i++;
				if(o[i] != null){
					rpd.setImportoPrescritto((BigDecimal)o[i]);
				}i++;
				if(o[i] != null){
					rpd.setManifestazioneRischio((Integer)o[i] );
				}i++;
				if(o[i] != null){
					rpd.setRm((Integer)o[i] );
				}i++;
				if(o[i] != null){
					rpd.setEdu((Integer)o[i] );
				}i++;
				if(o[i] != null){
					String pattern = "MM/dd/yyyy";
					DateFormat df = new SimpleDateFormat(pattern);
					Date d = (Date)o[i];     

					String sd = df.format(d);
					rpd.setDataAttribuzione(sd);
				}


				r.add(rpd);

			}

			return r;

		}catch(Exception e){
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public int countTabRischiPrat(long idAudit, Integer pageNumber,Integer pageSize, int columnNameToOrder, String orderType,String search) {
		return auditDao.countTabRischiPrat( idAudit,  pageNumber, pageSize,  columnNameToOrder,  orderType,search) ;
	}

	@Override
	public List<NonConformitaPraticheDto> getTabNonConfPrat(long idAudit,Integer pageNumber, Integer pageSize, int columnNameToOrder,String orderType, String search) {
		List<NonConformitaPraticheDto> r= new ArrayList<NonConformitaPraticheDto>();
		List<Object[]> l=auditDao.getListaNonConformitaPratiche( idAudit,  pageNumber, pageSize,  columnNameToOrder,  orderType,search) ;
		try{
			for(int y=0; y<l.size();y++){
				Object[] o=l.get(y);
				int i=0;
				NonConformitaPraticheDto ncp = new NonConformitaPraticheDto();
				if(o[i] != null){
					ncp.setDescFase((String)o[i].toString().trim());
				}
				i++;
				if(o[i] != null){
					ncp.setCodNonConf((String)o[i].toString().trim());
				}i++;
				if(o[i] != null){
					ncp.setDescNonConf((String)o[i].toString().trim());
				}i++;
				if(o[i] != null){
					ncp.setCodVarComp((String)o[i].toString().trim());
				}i++;
				if(o[i] != null){
					ncp.setDescVarComp((String)o[i].toString().trim());
				}i++;
				if(o[i] != null){
					ncp.setPesoVarComp((String)o[i].toString().trim());
				}i++;
				if(o[i] != null){
					ncp.setColore((String)o[i].toString().trim());
				}i++;
				if(o[i] != null){

					ncp.setDataAttribuzione((String)o[i].toString().trim());
				}


				r.add(ncp);

			}

			return r;

		}catch(Exception e){
			e.printStackTrace();

			return null;
		}
	}
	@Override
	public int countTabConfPrat(long idAudit, Integer pageNumber,
			Integer pageSize, int columnNameToOrder, String orderType,
			String search) {

		return auditDao.countAllNonConformitaPratiche(idAudit,search );
	}

	@Override
	public List<NonConformitaAccessiDto> getTabNonConfAccess(long idAudit,long idSSessione,String filtro, Integer pageNumber, Integer pageSize, int columnNameToOrder, String orderType, String search) {

		List<NonConformitaAccessiDto> r = new ArrayList<NonConformitaAccessiDto>();
		List<Object[]> l = auditDao.getListaNonConformitaAccessi( idAudit, idSSessione,filtro,  pageNumber, pageSize,  columnNameToOrder,  orderType,search) ;

		try{
			for(Object[] obj : l){

				r.add(ModelToDto.modelToNonConformitaAccessiDto(obj, false));

			}

			return r;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int countTabNonConfAccess(long idAudit, long idSSessione,String filtro,	Integer pageNumber, Integer pageSize, int columnNameToOrder, String orderType, String search) {

		return auditDao.countTabNonConfAccess(idAudit, idSSessione, filtro, search );
	}

	@Override
	public NonConformitaAccessiDto getDettagliNonConformita(long idNonConfM, long idNonConfS) {

		Object[] o = auditDao.getDettagliNonConformita(idNonConfM, idNonConfS);

		NonConformitaAccessiDto nca = new NonConformitaAccessiDto();

		try{
			if(o != null){

				nca = ModelToDto.modelToNonConformitaAccessiDto(o, true);

			}	

			return nca;

		}catch(Exception e){
			e.printStackTrace();

			return null;
		}

	}


	@Override
	public List<RischiAccessiDto> getTabRischiAccess(long idSSessione, Integer pageNumber, Integer pageSize,int columnNameToOrder, String orderType, String search) {

		List<RischiAccessiDto> r = new ArrayList<RischiAccessiDto>();
		List<Object[]> l = auditDao.getTabRischiAccess(idSSessione,  pageNumber, pageSize,  columnNameToOrder,  orderType,search) ;

		try{

			for(Object[] obj : l){

				r.add(ModelToDto.modelToRischiAccessiDto(obj, false));

			}

			return r;

		}catch(Exception e){
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public int countTabRischiAccess(long idSSessione, Integer pageNumber, Integer pageSize, int columnNameToOrder, String orderType, String search) {

		return auditDao.countTabRischiAccess(idSSessione, search );

	}

	@Override
	public RischiAccessiDto getDettagliRischi(long idRischioM, long idRischioS) {

		Object[] o = auditDao.getDettagliRischi(idRischioM, idRischioS) ;
		RischiAccessiDto rsa = new RischiAccessiDto();

		try{
			if(o != null){

				rsa = ModelToDto.modelToRischiAccessiDto(o, true);

			}	

			return rsa;

		}catch(Exception e){
			e.printStackTrace();


		}
		return null;
	}


	/*
	 * TIPOLOGICHE
	 */
	@Override
	public List<AtpoTipologicheDto> getCorrettoErrato() {
		List<Object[]> list =auditDao.getCorrettoErrato();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;


	}

	@Override
	public List<AtpoTipologicheDto> getModalitaNotifica() {
		List<Object[]> list =auditDao.getModalitaNotifica();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getChiNotifica() {
		List<Object[]> list =auditDao.getChiNotifica();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;

	}

	@Override
	public List<SessioneDto> getListaSessioniUtenteAsDirigente(long idUtente,
			Integer pageNumber, Integer pageSize, String columnNameToOrder,
			String orderType, String textSearch) {
		List<AuSessioni> listaSessioni = auditDao.getListaSessioniUtenteAsDirigente(idUtente, pageNumber, pageSize, columnNameToOrder, orderType, textSearch);
		List<SessioneDto> listaSessioneDto= new ArrayList<SessioneDto>();
		for(Object sessioni : listaSessioni){			
			Object[] obj=(Object[])sessioni;
			listaSessioneDto.add(ModelToDto.modelToDto(obj));
		}

		return listaSessioneDto ;
	}

	@Override
	public Integer countAllSessioniUtenteAsDirigente(long idUtente,
			String textSearch) {
		return auditDao.countAllSessioniUtenteAsDirigente(idUtente, textSearch);
	}

	@Override
	public List<AtpoTipologicheDto> getSiNo() {
		List<Object[]> list =auditDao.getSiNo();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getEsitoReg() {
		List<Object[]> list =auditDao.getEsitoReg();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getErroreEsito() {
		List<Object[]> list =auditDao.getErroreEsito();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getComDiscRdl() {
		List<Object[]> list =auditDao.getComDiscRdl();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AtpoTipologicheDto> getCredPrescr() {
		List<Object[]> list =auditDao.getCredPrescr();
		List<AtpoTipologicheDto> dto= new ArrayList<AtpoTipologicheDto>();
		for(Object[] obj:list){
			dto.add(new AtpoTipologicheDto((Long)obj[0],(String)obj[1]));
		}
		return dto;
	}

	@Override
	public List<AuTplTipologicheDto> getTipologica(String tipo) {
		List<Object[]> list =auditDao.getTipologica(tipo);
		List<AuTplTipologicheDto> dto= new ArrayList<AuTplTipologicheDto>();
		for(Object[] obj:list){

			dto.add(new AuTplTipologicheDto((Long)obj[0],(String)obj[1],(String)obj[2],(String)obj[3]));
		}
		return dto;
	}

	@Override
	public AuSPratica getSPraticaByIDVerbale(long idVerbale) {
		return auditDao.getSPraticaByIDVerbale(idVerbale);
	}


	public <T> void salva(T obj){
		auditDao.salva(obj);
	}

	@Override
	public <T> T cerca(Class<T> obj, Object pk) {
		return auditDao.cerca(obj, pk);
	}

	@Override
	public List<AuSPraCalIndLog> calcolaIndicatoriPratica(long idVerbale)
	throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAllPraticheConRischio(int sessionId, String textSearch,
			String columnNameToOrder, String orderType, Integer pageNumber,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer countAllPraticheSessione(long idSessione, String textSearch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PraticaConRischioDto> getListaPraticheRischio(int sessionId,
			int getiDisplayStart, int getiDisplayLength, String colunmName,
			String getsSortDir_0, String getsSearch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VerbaleDto> getListaPraticheSessione(long idSessione,
			Integer pageNumber, Integer pageSize, String columnNameToOrder,
			String orderType, String textSearch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDescrizioneFaseAssociate(long idAudit) {
		List<String> lnc = new ArrayList<String>();

		List<Object[]> l = auditDao.getDescrizioneFaseAssociate(idAudit);
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

	@Override
	public List<AuMVarCompDto> getVarCompNonConfAccessi(long idNonConfM,long idNonConfS, 
			int pageNumber, int pageSize, int columnNameToOrder, String orderType, String search) {

		List<Object[]> ls = auditDao.getVarCompNonConfAccessi(idNonConfM,idNonConfS,  pageNumber,  pageSize,  columnNameToOrder,  orderType,  search);

		List<AuMVarCompDto> nca = new ArrayList<AuMVarCompDto>();
		try{

			if(ls !=null){
				for(int i=0;i<ls.size();i++){
					AuMVarCompDto dto= new AuMVarCompDto();
					//int index=0;
					dto.setIdMVarComp((Long)ls.get(i)[0]);
					dto.setDescrizioneVarComp((String)ls.get(i)[1]);
					dto.setNum((Integer)ls.get(i)[2]);
					BigDecimal suPs=(BigDecimal)ls.get(i)[3];
					dto.setPercSuPs(suPs.multiply(new BigDecimal(100)));
					BigDecimal pp=(BigDecimal)ls.get(i)[4];
					dto.setPerPesata(pp.multiply(new BigDecimal(100)));
					dto.setColore((String)ls.get(i)[5]);

					nca.add(dto);

				}

				return nca;
			}
		}catch(Exception e){
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<RischiAccessiDto> getStatoEsprRischioTable(long idMRischio,
			long idSsessione, int pageNumber, int pageSize, int columnNameToOrder, String orderType, String search){
		List<Object[]> l = auditDao.getStatoEsprRischioTable(idMRischio,idSsessione,  pageNumber,  pageSize,  columnNameToOrder,  orderType,  search);

		List<RischiAccessiDto> nca = new ArrayList<RischiAccessiDto>();
		try{

			if(l !=null){
				for(int i=0;i<l.size();i++){
					RischiAccessiDto dto= new RischiAccessiDto();
					//int index=0;
					dto.setIdSRisepr((Long)l.get(i)[0]);
					dto.setDescrizione((String)l.get(i)[1]);
					dto.setNum((Integer)l.get(i)[2]);
					BigDecimal pst=(BigDecimal)l.get(i)[3];
					dto.setPercSuTot(pst.multiply(new BigDecimal(100)));
					BigDecimal psp=(BigDecimal)l.get(i)[4];
					dto.setPercSuPS(psp.multiply(new BigDecimal(100)));
					dto.setTotImporto((BigDecimal)l.get(i)[5]);

					nca.add(dto);

				}

				return nca;
			}
		}catch(Exception e){
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public int countVarCompNonConfAccessi(long idNonConfM, long idNonConfS,
			String textSearch) {

		return auditDao.countVarCompNonConfAccessi( idNonConfM,  idNonConfS, textSearch) ;
	}

	@Override
	public int countStatoEsprRischioTable(long idMRischio, long idSsessione,
			String getsSearch) {
		// TODO Auto-generated method stub
		return auditDao.countStatoEsprRischioTable( idMRischio,  idSsessione, getsSearch) ;
	}

	@Override
	public List<Object> getListFase(String nameGrafico) {

		List<Object> r = new ArrayList<Object>();
		List<Object[]> l = auditDao.getListFase(nameGrafico);
		if(nameGrafico.equalsIgnoreCase("acquisizioneistanza") && l !=null){
			for(int i=0;i<l.size();i++){
				Object[] o = l.get(i);
				AtpoFaseAcquisizioneIstanza a= ObjectToDto.objectToAtpoAcquisizioneIstanza(o);
				r.add(a);	
			}
		}else if(nameGrafico.equalsIgnoreCase("giudizio") && l !=null){
			for(int i=0;i<l.size();i++){
				Object[] o = l.get(i);
				AtpoFaseAutotutelaResistenzaGiudizio a= ObjectToDto.objectToAtpoAutotutelaResGiud(o);
				r.add(a);	
			}
		}else if(nameGrafico.equalsIgnoreCase("istruttoria") && l !=null){
			for(int i=0;i<l.size();i++){
				Object[] o = l.get(i);
				AtpoFaseGestioneIstruttoria a= ObjectToDto.objectToAtpoFaseGestioneIstruttoria(o);
				r.add(a);	
			}
		}else if(nameGrafico.equalsIgnoreCase("peritale") && l !=null){
			for(int i=0;i<l.size();i++){
				Object[] o = l.get(i);
				AtpoFasePeritale a= ObjectToDto.objectToAtpoFasePeritale(o);
				r.add(a);	
			}
		}else if(nameGrafico.equalsIgnoreCase("pperitaleA") && l !=null){
			for(int i=0;i<l.size();i++){
				Object[] o = l.get(i);
				AtpoFasePostPeritale a= ObjectToDto.objectToAtpoFasePostPeritaleA(o);
				r.add(a);	
			}
		}else if(nameGrafico.equalsIgnoreCase("pperitaleB") && l !=null){
			for(int i=0;i<l.size();i++){
				Object[] o = l.get(i);
				AtpoFasePostPeritale a= ObjectToDto.objectToAtpoFasePostPeritaleA(o);
				r.add(a);	
			}
		}else if(nameGrafico.equalsIgnoreCase("provvedimenti") && l !=null){
			for(int i=0;i<l.size();i++){
				Object[] o = l.get(i);
				AtpoFaseEsecuzioneProvvedimenti a= ObjectToDto.objectToAtpoFaseEsecuzioneProvvedimenti(o);
				r.add(a);	
			}
			}else if(nameGrafico.equalsIgnoreCase("provvedimentiB") && l !=null){
				for(int i=0;i<l.size();i++){
					Object[] o = l.get(i);
					AtpoFaseEsecuzioneProvvedimenti a= ObjectToDto.objectToAtpoFaseEsecuzioneProvvedimenti(o);
					r.add(a);	
				}
		}else if(nameGrafico.equalsIgnoreCase("fascicolo") && l !=null){
			for(int i=0;i<l.size();i++){
				Object[] o = l.get(i);
				AtpoRiepilogoFascicolo a= ObjectToDto.objectToAtpoRiepFascicolo(o);
				r.add(a);	
			}
		}

		return r;
	}


}


