package it.tecnet.crs.web.dto;

public class QuestionarioDto {
	
	private long idQuestionario;
	private long idAudit;
	private long idDomanda;
	private String descrizioneDomanda;
	private long idRisposta;
	private String descrizioneRisposta;
	private int punteggioRisposta;
	private long idCampagna;
	private String nomeCampagna;
	private Object dataInizioCampagna;
	private Object dataFineCampagna;
	private String statoCampagna;
	private long idSessione;
	private String tipoSessione;
	private Object dataInizioSessione;
	private Object dataFineSessione;
	private String statoSessione;
	private String sedeSessione;
	private String dirigente;
	private String auditors;
	private boolean selezioneRisposta; 
	private String tipoNotifica;
	
	
	public long getIdQuestionario() {
		return idQuestionario;
	}
	public void setIdQuestionario(long idQuestionario) {
		this.idQuestionario = idQuestionario;
	}
	public long getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}
	public long getIdDomanda() {
		return idDomanda;
	}
	public void setIdDomanda(long idDomanda) {
		this.idDomanda = idDomanda;
	}
	public String getDescrizioneDomanda() {
		return descrizioneDomanda;
	}
	public void setDescrizioneDomanda(String descrizioneDomanda) {
		this.descrizioneDomanda = descrizioneDomanda;
	}
	public long getIdRisposta() {
		return idRisposta;
	}
	public void setIdRisposta(long idRisposta) {
		this.idRisposta = idRisposta;
	}
	public String getDescrizioneRisposta() {
		return descrizioneRisposta;
	}
	public void setDescrizioneRisposta(String descrizioneRisposta) {
		this.descrizioneRisposta = descrizioneRisposta;
	}
	public int getPunteggioRisposta() {
		return punteggioRisposta;
	}
	public void setPunteggioRisposta(int punteggio) {
		this.punteggioRisposta = punteggio;
	}
	public long getIdCampagna() {
		return idCampagna;
	}
	public void setIdCampagna(long idCampagna) {
		this.idCampagna = idCampagna;
	}
	public String getNomeCampagna() {
		return nomeCampagna;
	}
	public void setNomeCampagna(String nomeCampagna) {
		this.nomeCampagna = nomeCampagna;
	}
	public Object getDataInizioCampagna() {
		return dataInizioCampagna;
	}
	public void setDataInizioCampagna(Object dataInizioCampagna) {
		this.dataInizioCampagna = dataInizioCampagna;
	}
	public Object getDataFineCampagna() {
		return dataFineCampagna;
	}
	public void setDataFineCampagna(Object dataFineCampagna) {
		this.dataFineCampagna = dataFineCampagna;
	}
	public String getStatoCampagna() {
		return statoCampagna;
	}
	public void setStatoCampagna(String statoCampagna) {
		this.statoCampagna = statoCampagna;
	}
	public long getIdSessione() {
		return idSessione;
	}
	public void setIdSessione(long idSessione) {
		this.idSessione = idSessione;
	}
	public String getTipoSessione() {
		return tipoSessione;
	}
	public void setTipoSessione(String tipoSessione) {
		this.tipoSessione = tipoSessione;
	}
	public Object getDataInizioSessione() {
		return dataInizioSessione;
	}
	public void setDataInizioSessione(Object dataInizioSessione) {
		this.dataInizioSessione = dataInizioSessione;
	}
	public Object getDataFineSessione() {
		return dataFineSessione;
	}
	public void setDataFineSessione(Object dataFineSessione) {
		this.dataFineSessione = dataFineSessione;
	}
	public String getStatoSessione() {
		return statoSessione;
	}
	public void setStatoSessione(String statoSessione) {
		this.statoSessione = statoSessione;
	}
	
	public String getSedeSessione() {
		return sedeSessione;
	}
	public void setSedeSessione(String sedeSessione) {
		this.sedeSessione = sedeSessione;
	}
	public String getDirigente() {
		return dirigente;
	}
	public void setDirigente(String dirigente) {
		this.dirigente = dirigente;
	}
	public String getAuditors() {
		return auditors;
	}
	public void setAuditors(String auditors) {
		this.auditors = auditors;
	}
	public boolean isSelezioneRisposta() {
		return selezioneRisposta;
	}
	public void setSelezioneRisposta(boolean selezioneRisposta) {
		this.selezioneRisposta = selezioneRisposta;
	}
	public void setTipoNotifica(String tipoNotifica) {
		this.tipoNotifica=tipoNotifica;
		
	}
	public String getTipoNotifica() {
		return tipoNotifica;
	}
  
	
}