package it.tecnet.crs.audit.web.dto;


public class AuditFasiDto {
	
	private long idSottoprocesso;
	private long idProcesso;
	private long idAudit;
	private long idSessione;
	private String descrizione;
	private String descrizioneProcesso;
	private String input;
	private String output;
	private String unitaOperativeCoinvolte;
	private String stato;
	
	public long getIdSottoprocesso() {
		return idSottoprocesso;
	}
	public void setIdSottoprocesso(long idSottoprocesso) {
		this.idSottoprocesso = idSottoprocesso;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getStato() {
		return stato;
	}
	public void setUnitaOperativeCoinvolte(String unitaOperativeCoinvolte) {
		this.unitaOperativeCoinvolte = unitaOperativeCoinvolte;
	}
	public String getUnitaOperativeCoinvolte() {
		return unitaOperativeCoinvolte;
	}
	public void setIdSessione(long idSessione) {
		this.idSessione = idSessione;
	}
	public long getIdSessione() {
		return idSessione;
	}
	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}
	public long getIdAudit() {
		return idAudit;
	}
	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}
	public long getIdProcesso() {
		return idProcesso;
	}
	public void setDescrizioneProcesso(String descrizioneProcesso) {
		this.descrizioneProcesso = descrizioneProcesso;
	}
	public String getDescrizioneProcesso() {
		return descrizioneProcesso;
	}
	
	
	
}
