package it.tecnet.crs.componenti.web.bean;

import java.util.List;

import it.tecnet.crs.componenti.jpa.model.CrsCircolariInps;
import it.tecnet.crs.componenti.jpa.model.CrsLeggiDecreti;
import it.tecnet.crs.componenti.jpa.model.CrsMessaggiInps;
import it.tecnet.crs.componenti.jpa.model.CrsNoteDecreti;


public class GestioneComponentiModel {

	  
		private int sessionId;
	  	
	  	private long idTipo;
	  	
	  	private String checkReferenceNormativa;
	  	
	  	private CrsCircolariInps circolariInps;
	  	
	  	private CrsNoteDecreti noteDecreti;
	  	
	  	private CrsMessaggiInps messaggiInps;
	  	
	  	private CrsLeggiDecreti leggiDecreti;
	  	
	  	private long idNormativa;
	  	
	  	private List<EnteEmittente> entiEmittenti;

	  	private List<TipoLegge> tipiLegge;
	  	
	  	
	  	
	  	public int getSessionId() {
			return sessionId;
		}

		public void setSessionId(int sessionId) {
			this.sessionId = sessionId;
		}

		public long getIdTipo() {
			return idTipo;
		}

		public void setIdTipo(long idTipo) {
			this.idTipo = idTipo;
		}

		public String getCheckReferenceNormativa() {
			return checkReferenceNormativa;
		}

		public void setCheckReferenceNormativa(String checkReferenceNormativa) {
			this.checkReferenceNormativa = checkReferenceNormativa;
		}

		public void setCircolariInps(CrsCircolariInps circolariInps) {
			this.circolariInps = circolariInps;
		}

		public CrsCircolariInps getCircolariInps() {
			return circolariInps;
		}

		public void setNoteDecreti(CrsNoteDecreti noteDecreti) {
			this.noteDecreti = noteDecreti;
		}

		public CrsNoteDecreti getNoteDecreti() {
			return noteDecreti;
		}

		public void setMessaggiInps(CrsMessaggiInps messaggiInps) {
			this.messaggiInps = messaggiInps;
		}

		public CrsMessaggiInps getMessaggiInps() {
			return messaggiInps;
		}

		public void setLeggiDecreti(CrsLeggiDecreti leggiDecreti) {
			this.leggiDecreti = leggiDecreti;
		}

		public CrsLeggiDecreti getLeggiDecreti() {
			return leggiDecreti;
		}

		public void setIdNormativa(long idNormativa) {
			this.idNormativa = idNormativa;
		}

		public long getIdNormativa() {
			return idNormativa;
		}

		public void setEntiEmittenti(List<EnteEmittente> entiEmittenti) {
			this.entiEmittenti = entiEmittenti;
		}

		public List<EnteEmittente> getEntiEmittenti() {
			return entiEmittenti;
		}

		public void setTipiLegge(List<TipoLegge> tipiLegge) {
			this.tipiLegge = tipiLegge;
		}

		public List<TipoLegge> getTipiLegge() {
			return tipiLegge;
		}



}