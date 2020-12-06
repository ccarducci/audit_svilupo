package it.tecnet.crs.jpa.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_M_RISESPR")
@NamedQueries({
    @NamedQuery(
        name = AuMRisepr.QUERY_ESPR_BY_IDAUDIT,
        query = "SELECT e FROM AuMRisepr e WHERE e.idAudit = :idAudit"),
    @NamedQuery(
        name = AuMRisepr.QUERY_ESPR_ALL,
        query = "SELECT e FROM AuMRisepr e")
})
public class AuMRisepr {
	
	public static final String QUERY_ESPR_BY_IDAUDIT = "AuMRisepr.findAllByIdAudit";
	public static final String QUERY_ESPR_ALL = "AuMRisepr.findAll";
	
	@Id
	@SequenceGenerator(name="AU_M_RISESPR_GENERATOR", sequenceName="AU_M_RISESPR_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_M_RISESPR_GENERATOR")
	@Column(name="ID_M_RISESPR")
	private long idMRisepr;
		
	@Column(name="ID_M_RISCHIO")
	private long idMRischio;
	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;
	
	@Column(name="DATA_FINE")
	private Date dataFine;
	
	@Column(name="DESCRIZIONE")
	private String descrizione;
	
	@Column(name="CODICE")
	private String codice;
	
	@Column(name="CONTATORE")
	private long contatore;
	
	@Column(name="PERC_TOTALE_RISCHIO")
	private BigDecimal percTotaleRischio;
	
	@Column(name="RAGGRUPPAMENTO_RISCHIO")
	private String raggruppamentoRischio;

	public long getIdMRisepr() {
		return idMRisepr;
	}

	public void setIdMRisepr(long idMRisepr) {
		this.idMRisepr = idMRisepr;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	
	

}
