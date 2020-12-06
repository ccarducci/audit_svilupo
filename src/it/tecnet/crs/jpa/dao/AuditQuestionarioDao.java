package it.tecnet.crs.jpa.dao;

import it.tecnet.crs.jpa.model.AuQuestionario;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


public interface AuditQuestionarioDao {


	/*
	 * 		QUESTIONARIO
	 * */
	
		public List<AuQuestionario> getListaQuestionariSessione(long idSessione);
		
		public Integer countAllQuestionariSessione(long idSessione);

		

		//public Integer calcolaPunteggio(long idSessione);
		
		public AuQuestionario getQuestionarioBySessione(Long idSessione);

		@Transactional
		public void salvaRispostaQuestionariSessione(long idSessione,long idRisposta,long idDomanda);

}
