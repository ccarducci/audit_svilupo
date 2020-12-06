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
@Table(name="CRS_BATCH_CARICAMENTO_PRATICHE")
public class CrsBatchCaricamentoPratiche implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@Id
	@SequenceGenerator(name="CRS_BATCH_CARICAMENTO_PRATICHE_GENERATOR", sequenceName="CRS_BATCH_CARICAMENTO_PRATICHE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_BATCH_CARICAMENTO_PRATICHE_GENERATOR")
	@Column(name="ID_BCP")
	private Long idBcp;
	
	@Column(name="ID_CAMPAGNA")
	private Long idCampagna;
	
	@Transient
	private String nomeSede;
	@Transient
	private String nomeCampagna;
	
	
	@Column(name="COD_SEDE")
	private String codiceSede;
	
	@Column(name="ANNO_CONCLUSIONE")
	private int annoConclusione;
	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;
	
	@Column(name="DATA_FINE")
	private Date dataFine;
	
	@Column(name="ESITO")
	private String esito;
	
	@Column(name="DESC_ERRORE")
	private String descErrore;

	public Long getIdBcp() {
		return idBcp;
	}

	public void setIdBcp(Long idBcp) {
		this.idBcp = idBcp;
	}

	public Long getIdCampagna() {
		return idCampagna;
	}

	public void setIdCampagna(Long idCampagna) {
		this.idCampagna = idCampagna;
	}

	public String getCodiceSede() {
		return codiceSede;
	}

	public void setCodiceSede(String codiceSede) {
		this.codiceSede = codiceSede;
	}

	public int getAnnoConclusione() {
		return annoConclusione;
	}

	public void setAnnoConclusione(int annoConclusione) {
		this.annoConclusione = annoConclusione;
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

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getDescErrore() {
		return descErrore;
	}

	public void setDescErrore(String descErrore) {
		this.descErrore = descErrore;
	}

	public String getNomeSede() {
		return nomeSede;
	}

	public void setNomeSede(String nomeSede) {
		this.nomeSede = nomeSede;
	}

	public String getNomeCampagna() {
		return nomeCampagna;
	}

	public void setNomeCampagna(String nomeCampagna) {
		this.nomeCampagna = nomeCampagna;
	}
	
	
}
