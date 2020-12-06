package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "AU_S_RISESPR")
public class AuSRisespr implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_RISESPR_GENERATOR", sequenceName = "ID_S_ESPRESSIONE_RISCHIO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_RISESPR_GENERATOR")
	@Column(name = "ID_S_ESPRESSIONE_RISCHIO")
	private long idSEspressioneRischio;

	@Column(name = "ID_S_RISCHIO")
	private long idSRischio;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "NUM")
	private int num;

	@Column(name = "SU_TOT")
	private Double suTot;

	@Column(name = "SU_PS")
	private Double suPs;

	@Column(name = "IMPORTO")
	private Double importo;

	@Column(name = "ID_M_RISCHIO")
	private long idMRischio;

	@Column(name = "ID_M_RISESPR")
	private long idMRisespr;

	@Column(name = "POSSIBILI_MOTIVAZIONI_RISCHIO")
	private String possMotivRischio;

	@Column(name = "AZIONI_CORRETTIVE")
	private String azioniCorrettive;

	@Transient
	private String raggruppamentoRischio;

	public long getIdSEspressioneRischio() {
		return idSEspressioneRischio;
	}

	public void setIdSEspressioneRischio(long idSEspressioneRischio) {
		this.idSEspressioneRischio = idSEspressioneRischio;
	}

	public long getIdSRischio() {
		return idSRischio;
	}

	public void setIdSRischio(long idSRischio) {
		this.idSRischio = idSRischio;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Double getSuTot() {
		return suTot;
	}

	public void setSuTot(Double suTot) {
		this.suTot = suTot;
	}

	public Double getSuPs() {
		return suPs;
	}

	public void setSuPs(Double suPs) {
		this.suPs = suPs;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public long getIdMRischio() {
		return idMRischio;
	}

	public void setIdMRischio(long idMRischio) {
		this.idMRischio = idMRischio;
	}

	public long getIdMRisespr() {
		return idMRisespr;
	}

	public void setIdMRisespr(long idMRisespr) {
		this.idMRisespr = idMRisespr;
	}

	public void setPossMotivRischio(String possMotivRischio) {
		this.possMotivRischio = possMotivRischio;
	}

	public String getPossMotivRischio() {
		return possMotivRischio;
	}

	public void setAzioniCorrettive(String azioniCorrettive) {
		this.azioniCorrettive = azioniCorrettive;
	}

	public String getAzioniCorrettive() {
		return azioniCorrettive;
	}

	public String getRaggruppamentoRischio() {
		return raggruppamentoRischio;
	}

	public void setRaggruppamentoRischio(String raggruppamentoRischio) {
		this.raggruppamentoRischio = raggruppamentoRischio;
	}

}
