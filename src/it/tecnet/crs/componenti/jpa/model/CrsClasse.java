package it.tecnet.crs.componenti.jpa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_CLASSE")
public class CrsClasse implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_CLASSE_GENERATOR", sequenceName="ID_CLASSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_CLASSE_GENERATOR")
	@Column(name="ID_CLASSE")
	private long idClasse;

	private String descrizione;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;

	private long idTipo;
	
	private long idDescrizioneTipo;

	private long idProcesso;
	
	private long idSottoprocesso;
	
	private long idAttivitaComponente;
	
	private long idAttivitaDettaglio;

	

	private static final long serialVersionUID = 1L;

	public CrsClasse() {
		super();
	}

	public long getIdClasse() {
		return this.idClasse;
	}

	public void setIdClasse(long idClasse) {
		this.idClasse = idClasse;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public long getIdTipo() {
		return idTipo;
	}

	public void setIdDescrizioneTipo(long idDescrizioneTipo) {
		this.idDescrizioneTipo = idDescrizioneTipo;
	}

	public long getIdDescrizioneTipo() {
		return idDescrizioneTipo;
	}

	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}

	public long getIdProcesso() {
		return idProcesso;
	}

	public void setIdSottoprocesso(long idSottoprocesso) {
		this.idSottoprocesso = idSottoprocesso;
	}

	public long getIdSottoprocesso() {
		return idSottoprocesso;
	}

	public void setIdAttivitaComponente(long idAttivitaComponente) {
		this.idAttivitaComponente = idAttivitaComponente;
	}

	public long getIdAttivitaComponente() {
		return idAttivitaComponente;
	}

	public void setIdAttivitaDettaglio(long idAttivitaDettaglio) {
		this.idAttivitaDettaglio = idAttivitaDettaglio;
	}

	public long getIdAttivitaDettaglio() {
		return idAttivitaDettaglio;
	}


}
