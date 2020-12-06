package it.tecnet.crs.service;


import it.tecnet.crs.jpa.dao.AuditQuestionarioDao;
import it.tecnet.crs.jpa.model.AuQuestionario;
import it.tecnet.crs.util.ModelToDto;
import it.tecnet.crs.web.dto.QuestionarioDto;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;



public class AuditQuestionarioServiceImpl implements AuditQuestionarioService{

	
	private AuditQuestionarioDao auditQuestionarioDao;
	

	/*QUESTIONARIO*/
	
	
	public List<QuestionarioDto> getListaQuestionariSessione(long idSessione) {
		List<AuQuestionario> listaQuestionari = auditQuestionarioDao.getListaQuestionariSessione(idSessione);
		List<QuestionarioDto> listaQuestionarioDto= new ArrayList<QuestionarioDto>();
		try{
			for(Object sessioni : listaQuestionari){			
				Object[] obj=(Object[])sessioni;
			//	listaQuestionarioDto.add(ModelToDto.modelToDomandaDto(obj));
			}
		}catch(Exception e){
			
		}
		
		
		
		return listaQuestionarioDto;
	}

	public Integer countAllQuestionariSessione(long idSessione){
		return auditQuestionarioDao.countAllQuestionariSessione(idSessione);
	}

	@Transactional
	public void salvaRispostaQuestionariSessione(long idSessione,long idRisposta,long idDomanda){
		 auditQuestionarioDao.salvaRispostaQuestionariSessione(idSessione, idRisposta,idDomanda);
	}

	/*public Integer calcolaPunteggio(long idSessione){
		return auditQuestionarioDao.calcolaPunteggio(idSessione);
	}*/

	public AuditQuestionarioDao getAuditQuestionarioDao() {
		return auditQuestionarioDao;
	}

	public void setAuditQuestionarioDao(AuditQuestionarioDao auditQuestionarioDao) {
		this.auditQuestionarioDao = auditQuestionarioDao;
	}

	public AuQuestionario getQuestionarioBySessione(Long idSessione){
		return auditQuestionarioDao.getQuestionarioBySessione(idSessione);
	}
}
