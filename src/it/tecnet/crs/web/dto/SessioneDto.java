package it.tecnet.crs.web.dto;

public class SessioneDto {

	private Long idSessione;

	private String audit;
	private String campagna;
	private String sede;
	private String tipo;
	private Object dataInizio;
	private Object dataFine;
	private String stato;
	private String dirigente;
	private String nota;
	
	public Long getIdSessione() {
		return idSessione;
	}
	public void setIdSessione(Long idSessione) {
		this.idSessione = idSessione;
	}
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
	}
	public String getCampagna() {
		return campagna;
	}
	public void setCampagna(String campagna) {
		this.campagna = campagna;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Object getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Object dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Object getDataFine() {
		return dataFine;
	}
	public void setDataFine(Object dataFine) {
		this.dataFine = dataFine;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getDirigente() {
		return dirigente;
	}
	public void setDirigente(String dirigente) {
		this.dirigente = dirigente;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getNota() {
		return nota;
	}
	
}
