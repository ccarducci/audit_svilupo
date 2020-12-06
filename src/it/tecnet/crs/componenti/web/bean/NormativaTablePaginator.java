package it.tecnet.crs.componenti.web.bean;

import it.tecnet.crs.util.datatables.DataTablesModel;

public class NormativaTablePaginator extends DataTablesModel {


	private static final long serialVersionUID = 1L;

		public int sessionId;
		
	  	public long idProcesso;
	  	public long idSottoProcesso;
	  	public long idAttivitaComponente;
	  	public long idAttivitaDettaglio;
	  	
	  	public boolean storico;
	  	
	  	public long idCircolariInps;
	  	public long idNoteDecreti;
	  	public long idMessaggiInps;
	  	public long idLeggiDecreti;
	  	
	  	public long idDocumento;
	  	public Integer idTipo;
	  	public long idNormativa;
	  	public String listId;
		

		public int getSessionId() {
			return sessionId;
		}


		public void setSessionId(int sessionId) {
			this.sessionId = sessionId;
		}


		public long getIdProcesso() {
			return idProcesso;
		}


		public void setIdProcesso(long idProcesso) {
			this.idProcesso = idProcesso;
		}


		public long getIdSottoProcesso() {
			return idSottoProcesso;
		}


		public void setIdSottoProcesso(long idSottoProcesso) {
			this.idSottoProcesso = idSottoProcesso;
		}


		public long getIdAttivitaComponente() {
			return idAttivitaComponente;
		}


		public void setIdAttivitaComponente(long idAttivitaComponente) {
			this.idAttivitaComponente = idAttivitaComponente;
		}


		public long getIdAttivitaDettaglio() {
			return idAttivitaDettaglio;
		}


		public void setIdAttivitaDettaglio(long idAttivitaDettaglio) {
			this.idAttivitaDettaglio = idAttivitaDettaglio;
		}
		
		public boolean isStorico() {
			return storico;
		}


		public void setStorico(boolean storico) {
			this.storico = storico;
		}


		public long getIdCircolariInps() {
			return idCircolariInps;
		}


		public void setIdCircolariInps(long idCircolariInps) {
			this.idCircolariInps = idCircolariInps;
		}


		public long getIdNoteDecreti() {
			return idNoteDecreti;
		}


		public void setIdNoteDecreti(long idNoteDecreti) {
			this.idNoteDecreti = idNoteDecreti;
		}


		public long getIdMessaggiInps() {
			return idMessaggiInps;
		}


		public void setIdMessaggiInps(long idMessaggiInps) {
			this.idMessaggiInps = idMessaggiInps;
		}


		public long getIdLeggiDecreti() {
			return idLeggiDecreti;
		}


		public void setIdLeggiDecreti(long idLeggiDecreti) {
			this.idLeggiDecreti = idLeggiDecreti;
		}


		public long getIdDocumento() {
			return idDocumento;
		}


		public void setIdDocumento(long idDocumento) {
			this.idDocumento = idDocumento;
		}


		public Integer getIdTipo() {
			return idTipo;
		}


		public void setIdTipo(Integer idTipo) {
			this.idTipo = idTipo;
		}


		public long getIdNormativa() {
			return idNormativa;
		}


		public void setIdNormativa(long idNormativa) {
			this.idNormativa = idNormativa;
		}


		public String getListId() {
			return listId;
		}


		public void setListId(String listId) {
			this.listId = listId;
		}


}