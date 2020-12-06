package it.tecnet.crs.ATPO.auditors.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.tecnet.crs.ATPO.auditors.jpa.dao.AuditorsAtpoAccessiDao;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPraticheSISCO;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoAuMContestoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.AuSMEsprRischioDTO;
import it.tecnet.crs.ATPO.auditors.web.dto.PraticheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.TipologiaPraticheCampioneDto;
import it.tecnet.crs.ATPO.util.ObjectToDto;
import it.tecnet.crs.audit.web.dto.CampagnaDto;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuSRischio;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;
import it.tecnet.crs.util.ModelToDto;
import it.tecnet.crs.web.dto.AuMVarCompDto;
import it.tecnet.crs.web.dto.AuSSessioneDto;
import it.tecnet.crs.web.dto.NonConformitaAccessiDto;

public class AuditorsAccessiATPOServiceImpl implements AuditorsAccessiATPOService{
	private AuditorsAtpoAccessiDao auditorsAtpoAccessiDao;

	public AuditorsAtpoAccessiDao getAuditorsAtpoAccessiDao() {
		return auditorsAtpoAccessiDao;
	}

	public void setAuditorsAtpoAccessiDao(
			AuditorsAtpoAccessiDao auditorsAtpoAccessiDao) {
		this.auditorsAtpoAccessiDao = auditorsAtpoAccessiDao;
	}


	public List<PraticheAtpoDto> getPraticheATPO(int idSessione, int filtroEsito, int filtroStato,int getiDisplayStart, int getiDisplayLength, int getiSortCol_0,
			String getsSortDir_0, String getsSearch) {
		List<PraticheAtpoDto> praticheATPOList= new ArrayList<PraticheAtpoDto>();
		List<Object[]> ls=(List<Object[]>)auditorsAtpoAccessiDao.getPraticheATPO(idSessione,filtroEsito, filtroStato, getiDisplayStart, getiDisplayLength,  getiSortCol_0,
				getsSortDir_0, getsSearch);
		for(int i=0;i<ls.size();i++){


			PraticheAtpoDto dto= new PraticheAtpoDto();
			//int index=0;
			dto.setIdPraticheATPO((Long)ls.get(i)[0]);
			dto.setCodSede((String)ls.get(i)[1]);
			dto.setNomeSede((String)ls.get(i)[2]);
			dto.setFascicolo((String)ls.get(i)[3]);
			dto.setParte((String)ls.get(i)[4]);
			dto.setStatoEsamePratica((String)ls.get(i)[5]);
			dto.setEsito((String)ls.get(i)[6]);


			praticheATPOList.add(dto);

		}
		return praticheATPOList;

	}

	@Override
	public Integer countListaPraticheATPO(int idSessione, int filtroEsito, int filtroStato, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch) {

		return auditorsAtpoAccessiDao.countListaPraticheATPO(idSessione, filtroEsito, filtroStato, getiDisplayStart,getiDisplayLength, getiSortCol_0,  getsSortDir_0,getsSearch);
	}


	@Override
	public AtpoAuMContestoDto getDatiContesto(long idSessione, long idCampagna) {
		Object[] o=auditorsAtpoAccessiDao.getDatiContesto(idSessione,idCampagna);
		try{
			if(o!=null){
				AtpoAuMContestoDto a= ObjectToDto.objectToDatiContestoDto(o);
				return a;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	


	
	public <T> T cerca(Class<T> obj, Object pk) {
		// TODO Auto-generated method stub
		return auditorsAtpoAccessiDao.cerca(obj, pk);
	}

	@Override
	public AuAudit getAuditByIdCampagna(long idCampagna) {
		// TODO Auto-generated method stub
		return auditorsAtpoAccessiDao.getAuditByIdCampagna(idCampagna);
	}



	@Override
	public AuSSessioneDto getSSessione(long idSessione) {

		Object[] obj = auditorsAtpoAccessiDao.getSSessioneDatiGenerali(idSessione) ;

		AuSSessioneDto ss = null;

		if(obj != null){
			ss= ModelToDto.modelToSSessioneDto(obj);

		}

		return ss;
	}

	@Override
	public Integer getNumeroPraticheEsaminate(long idSessione) {
		Integer count= auditorsAtpoAccessiDao.getNumeroPraticheEsaminate(idSessione) ;
		if(count!=null){
			return count;
		}
		return null;
	}

	@Override
	public List<TipologiaPraticheCampioneDto> getTipologiaPraticheCampione(
			long idSessione) {
		List<TipologiaPraticheCampioneDto> l=new ArrayList<TipologiaPraticheCampioneDto>();

		List<Object[]> obj = auditorsAtpoAccessiDao.getTipologiaPraticheCampione(idSessione) ;

		if(obj !=null && obj.size() !=0){
			for(int i=0; i<obj.size();i++){
				TipologiaPraticheCampioneDto t= new TipologiaPraticheCampioneDto();
				t.setNumero((Integer)obj.get(i)[0]);
				t.setDescrizione((String)obj.get(i)[1]);
				l.add(t);
			}
		}

		return l;


	}

	@Override
	public List<AuMNonConf> getNonConformitaAccessiAtpo(Long idAudit) {
		List<AuMNonConf> l=new ArrayList<AuMNonConf>();

		List<Object[]> obj = auditorsAtpoAccessiDao.getNonConformitaAccessiAtpo(idAudit) ;

		if(obj !=null && obj.size() !=0){
			for(int i=0; i<obj.size();i++){
				AuMNonConf t= new AuMNonConf();
				t.setIdMNonConf((Long) obj.get(i)[0]);
				t.setIdAudit((Long) obj.get(i)[1]);
				t.setIdFase((Long) obj.get(i)[2]);
				t.setDataInizio((Date) obj.get(i)[3]);
				t.setDataFine((Date) obj.get(i)[4]);
				t.setDescrizione((String)obj.get(i)[5]);
				t.setPesoNonConf((Long) obj.get(i)[6]);
				t.setCodiceNc((String)obj.get(i)[7]);
				l.add(t);
			}
		}

		return l;
	}

	@Override
	public NonConformitaAccessiDto getSNConfByIdMNonConf(long idMNonConf,
			long idSSessione) {
		List<Object[]> l= auditorsAtpoAccessiDao.getSNonConfByIdMNonConf(idMNonConf, idSSessione);
		try{
			if(l!=null){
				NonConformitaAccessiDto ra = new NonConformitaAccessiDto();
				Object[] o=l.get(0);
				BigDecimal bc=(BigDecimal)o[0];
				ra.setValoreINCC(bc.doubleValue());
				ra.setPraticaNonSoggetta((String) o[1]);
				return ra;
			}
				
			}catch(Exception e){
				e.printStackTrace();
				
			}
		return null;
	}

	@Override
	public List<AuMVarCompDto> getVarComp(long idMNonConf, long idSSessione,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<AuMVarCompDto> vc= new ArrayList<AuMVarCompDto>();
		List<Object[]> l=auditorsAtpoAccessiDao.getVarComp( idMNonConf, idSSessione,  pageNumber, pageSize,  columnNameToOrder,  orderType,search) ;
		try{
			for(int y=0; y<l.size();y++){
				Object[] o=l.get(y);
				int i=0;
				AuMVarCompDto vcdto = new AuMVarCompDto();
				if(o[i] != null){
					vcdto.setIdSVarComp((Long)o[i]);
				}i++;
				if(o[i] != null){
					vcdto.setDescrizioneVarComp((String)o[i]);
				}i++;
				if(o[i] != null){
					vcdto.setPercSuPs((BigDecimal)o[i]);
				}i++;
				if(o[i] != null){
					vcdto.setCriticita((String)o[i]);
				}i++;
				if(o[i] != null){
					vcdto.setAzioniCorrettive((String)o[i]);
				}i++;
				vc.add(vcdto);
			}
		return vc;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int countVarComp(long idMNonConf, long idSSessione,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search) {
		// TODO Auto-generated method stub
		return auditorsAtpoAccessiDao.countVarComp(idMNonConf, idSSessione, search );
	}

	@Override
	public List<AuMRischio> getTabRischiPM(Long idAudit) {
		
		List<AuMRischio> l=new ArrayList<AuMRischio>();

		List<Object[]> lo= auditorsAtpoAccessiDao.getTabRischiPM(idAudit);

		if(lo !=null){
			for(int i=0; i<lo.size();i++){
				AuMRischio t= new AuMRischio();
				t.setIdMRischio((Long) lo.get(i)[0]);
				t.setDescrizioneRischio((String) lo.get(i)[1]);
			
				l.add(t);
			}
		}

		return l;
	}

	@Override
	public List<AuSMEsprRischioDTO> searchEsprRischiTablePM(long idRischio,
			long idSSessione, int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search) {
		List<AuSMEsprRischioDTO> er= new ArrayList<AuSMEsprRischioDTO>();
		List<Object[]> l=auditorsAtpoAccessiDao.searchEsprRischiTablePM( idRischio, idSSessione,  pageNumber, pageSize,  columnNameToOrder,  orderType,search) ;
		try{
			for(int y=0; y<l.size();y++){
				Object[] o=l.get(y);
				int i=0;
				AuSMEsprRischioDTO esprR = new AuSMEsprRischioDTO();
				if(o[i] != null){
					esprR.setIdSEsprRischio((Long)o[i]);
				}i++;
				if(o[i] != null){
					esprR.setDescrizioneEsprRischio((String)o[i]);
				}i++;
				if(o[i] != null){
					esprR.setNum((Integer)o[i]);
				}i++;
				if(o[i] != null){
					esprR.setSuPs((BigDecimal)o[i]);
				}i++;
				if(o[i] != null){
					esprR.setPossibiliMotivazioniRischio((String)o[i]);
				}i++;
				if(o[i] != null){
					esprR.setAzioniCorrettive((String)o[i]);
				}i++;
				er.add(esprR);
			}
		return er;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	} 
	
	@Override
	public int countEsprRischiTablePM(long idRischio, long idSSessione,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search){
		// TODO Auto-generated method stub
		return auditorsAtpoAccessiDao.countEsprRischiTablePM(idRischio, idSSessione, search );
	}

	@Override
	public <T> T salva(T obj) {
		// TODO Auto-generated method stub
		return auditorsAtpoAccessiDao.salva(obj);
	}

	@Override
	public List<ReportAccessoPDFDto> getRisultatiByTempo(long idSSessione,
			 int pageNumber, int pageSize, int columnNameToOrder,
				String orderType, String search) {
		List<ReportAccessoPDFDto> er= new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> l=auditorsAtpoAccessiDao.getRisultatiByTempo( idSSessione,  pageNumber, pageSize,  columnNameToOrder,  orderType,search) ;
		try{
			for(int y=0; y<l.size();y++){
				Object[] o=l.get(y);
				int i=0;
				ReportAccessoPDFDto t = new ReportAccessoPDFDto();
				if(o[i] != null){
					t.setCodifica((String)o[i]);
				}i++;
				if(o[i] != null){
					t.setDescrizioneTemporale((String)o[i]);
				}i++;
				if(o[i] != null){
					t.setMediaGG((BigDecimal)o[i]);
				}i++;
				if(o[i] != null){
					t.setNC((String)o[i]);
				}i++;
				if(o[i] != null){
					t.setOrdinamento((Integer)o[i]);
				}i++;
				
				er.add(t);
			}
		return er;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int countListTempo(long idSSessione,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search){
		// TODO Auto-generated method stub
		return auditorsAtpoAccessiDao.countListTempo(idSSessione, search );
	}

	



	



	




}
