package it.tecnet.crs.jpa.model;

import java.io.Serializable;
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
@Table(name="AU_M_RISCHIO")
@NamedQueries({
    @NamedQuery(
        name = AuMRischio.QUERY_RISCHIO_BY_IDAUDIT,
        query = "SELECT e FROM AuMRischio e WHERE e.idAudit = :idAudit"),
    @NamedQuery(
        name = AuMRischio.QUERY_RISCHIO_ALL,
        query = "SELECT e FROM AuMRischio e")
})
public class AuMRischio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String QUERY_RISCHIO_BY_IDAUDIT = "AuMRischio.findAllByIdAudit";
	public static final String QUERY_RISCHIO_ALL = "AuMRischio.findAll";
	
	@Id
	@SequenceGenerator(name="AU_M_RISCHIO_GENERATOR", sequenceName="AU_M_RISCHIO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_M_RISCHIO_GENERATOR")
	@Column(name="ID_M_RISCHIO")
	private long idMRischio;
	
	@Column(name="ID_AUDIT")
	private long idAudit;
	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;
	
	@Column(name="DATA_FINE")
	private Date dataFine;
	
	@Column(name="DESCRIZIONE_RISCHIO")
	private String descrizioneRischio;
	
	@Column(name="NUM_DOM_QUESTIONARIO")
	private long numDomQuestionario;
	
	@Column(name="PESO_RISCHIO")
	private Double pesoRischio;
	
	@Column(name="VALORE_DECR_RISCHIO")
	private BigDecimal valoreDecrRischio;
	
	@Column(name="CODICE_RISCHIO")
	private String codiceRischio;

	public Double getPesoRischio() {
		return pesoRischio;
	}

	public void setPesoRischio(Double pesoRischio) {
		this.pesoRischio = pesoRischio;
	}

	public BigDecimal getValoreDecrRischio() {
		return valoreDecrRischio;
	}

	public void setValoreDecrRischio(BigDecimal valoreDecrRischio) {
		this.valoreDecrRischio = valoreDecrRischio;
	}

	public String getCodiceRischio() {
		return codiceRischio;
	}

	public void setCodiceRischio(String codiceRischio) {
		this.codiceRischio = codiceRischio;
	}


	
	public long getIdMRischio() {
		return idMRischio;
	}

	public void setIdMRischio(long idMRischio) {
		this.idMRischio = idMRischio;
	}

	public long getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
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

	public String getDescrizioneRischio() {
		return descrizioneRischio;
	}

	public void setDescrizioneRischio(String descrizioneRischio) {
		this.descrizioneRischio = descrizioneRischio;
	}

	public long getNumDomQuestionario() {
		return numDomQuestionario;
	}

	public void setNumDomQuestionario(long numDomQuestionario) {
		this.numDomQuestionario = numDomQuestionario;
	}
	
	


}
