package it.tecnet.crs.audit.web.dto;

import java.math.BigDecimal;
import java.util.Date;


public class AuAuditMRisesprDto {
	
	//AU_M_RISESPR
	private long idMRisepr;
	private long idAudit;
	private long idMRischio;
	private String codice;
	private String descrizione;
	private BigDecimal percTotaleRischio;
	private String raggruppamentoRischio;
	private Date dataInizio;
	private Date dataFine;
	
	//AU_M_RISCHIO
	private String descrizioneRischio;
	private String codiceRischio;
	
	
	
	
	public String getCodiceRischio() {
		return codiceRischio;
	}

	public void setCodiceRischio(String codiceRischio) {
		this.codiceRischio = codiceRischio;
	}

	private long contatore;
	
	

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public long getIdMRisepr() {
		return idMRisepr;
	}

	public void setIdMRisepr(long idMRisepr) {
		this.idMRisepr = idMRisepr;
	}

	public long getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}

	public long getIdMRischio() {
		return idMRischio;
	}

	public void setIdMRischio(long idMRischio) {
		this.idMRischio = idMRischio;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public long getContatore() {
		return contatore;
	}

	public void setContatore(long contatore) {
		this.contatore = contatore;
	}

	public BigDecimal getPercTotaleRischio() {
		return percTotaleRischio;
	}

	public void setPercTotaleRischio(BigDecimal percTotaleRischio) {
		this.percTotaleRischio = percTotaleRischio;
	}

	public String getRaggruppamentoRischio() {
		return raggruppamentoRischio;
	}

	public void setRaggruppamentoRischio(String raggruppamentoRischio) {
		this.raggruppamentoRischio = raggruppamentoRischio;
	}
	public String getDescrizioneRischio() {
		return descrizioneRischio;
	}

	public void setDescrizioneRischio(String descrizioneRischio) {
		this.descrizioneRischio = descrizioneRischio;
	}

	
}
