package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_M_DOMANDA")
public class AuMDomanda implements Serializable {
	@Id
	@SequenceGenerator(name="AU_DOMANDA_GENERATOR", sequenceName="ID_DOMANDA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_DOMANDA_GENERATOR")
	@Column(name="ID_DOMANDA")
	private long idDomanda;

	

	@Column(name="ID_QUESTIONARIO")
	private long idQuestionario;


	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;
	
	@Column(name="DATA_FINE")
	private Date dataFine;
	
	@Column(name="DESCRIZIONE")
	private String descrizione;
	
	@Column(name="PESO_DOMANDA")
	private long pesoDomanda;
	
	@Column(name="VALORE_MAX_RISPOSTA")
	private long valoreMaxRisposta;
	
	@Column(name="ID_M_RISCHIO")
	private long idMRischio;
	
	@Column(name="PESO_PERCENTUALE")
	private BigDecimal pesoPercentuale;
	
	@Column(name="PERC_RIPARTITA")
	private BigDecimal percRipartita;

	@Column(name="CONTROLLO_PROCESSO")
	private String controlloProcesso;
	
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

	public long getPesoDomanda() {
		return pesoDomanda;
	}

	public void setPesoDomanda(long pesoDomanda) {
		this.pesoDomanda = pesoDomanda;
	}

	public long getValoreMaxRisposta() {
		return valoreMaxRisposta;
	}

	public void setValoreMaxRisposta(long valoreMaxRisposta) {
		this.valoreMaxRisposta = valoreMaxRisposta;
	}

	

	public BigDecimal getPesoPercentuale() {
		return pesoPercentuale;
	}

	public void setPesoPercentuale(BigDecimal pesoPercentuale) {
		this.pesoPercentuale = pesoPercentuale;
	}

	public BigDecimal getPercRipartita() {
		return percRipartita;
	}

	public void setPercRipartita(BigDecimal percRipartita) {
		this.percRipartita = percRipartita;
	}

	private static final long serialVersionUID = 1L;

	public AuMDomanda() {
		super();
	}

	public long getIdDomanda() {
		return this.idDomanda;
	}

	public void setIdDomanda(long idDomanda) {
		this.idDomanda = idDomanda;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public long getIdQuestionario() {
		return this.idQuestionario;
	}

	public void setIdQuestionario(long idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	

	public void setIdMRischio(long idMRischio) {
		this.idMRischio = idMRischio;
	}

	public long getIdMRischio() {
		return idMRischio;
	}

	public void setControlloProcesso(String controlloProcesso) {
		this.controlloProcesso = controlloProcesso;
	}

	public String getControlloProcesso() {
		return controlloProcesso;
	}

}
