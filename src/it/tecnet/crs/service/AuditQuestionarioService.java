package it.tecnet.crs.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import it.tecnet.crs.jpa.model.AuQuestionario;
import it.tecnet.crs.web.dto.QuestionarioDto;





public interface AuditQuestionarioService {
	
	
	
	/*
	 * 		QUESTIONARIO
	 * */
	
	public List<QuestionarioDto> getListaQuestionariSessione(long idSessione);

	public Integer countAllQuestionariSessione(long idSessione);
	
	@Transactional
	public void salvaRispostaQuestionariSessione(long idSessione,long idRisposta,long idDomanda);

	//public Integer calcolaPunteggio(long idSessione);

	public AuQuestionario getQuestionarioBySessione(Long idSessione);
	

}