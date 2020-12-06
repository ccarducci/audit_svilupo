package it.tecnet.crs.mod.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_ATTIVITA_COMPONENTE")
public class CrsAttivitaComponente implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_ATTIVITA_COMPONENTE_GENERATOR", sequenceName="ID_ATTIVITACOMPONENTE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_ATTIVITA_COMPONENTE_GENERATOR")
	@Column(name="ID_ATTIVITA_COMPONENTE")
	private long idAttivitaComponente;

	private String descrizione;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;

	private String vincolo;

	@Column(name="ID_SOTTOPROCESSO")
	private long idSottoprocesso;

	//private long idAttivitaDettaglio;
	
	private String stato;

	//private long idClasse;
	
	private int ordinamento;
	

	private static final long serialVersionUID = 1L;

	public CrsAttivitaComponente() {
		super();
	}

	public long getIdAttivitaComponente() {
		return this.idAttivitaComponente;
	}

	public void setIdAttivitaComponente(long idAttivitaComponente) {
		this.idAttivitaComponente = idAttivitaComponente;
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

	public String getVincolo() {
		return this.vincolo;
	}

	public void setVincolo(String vincolo) {
		this.vincolo = vincolo;
	}

	public void setIdSottoprocesso(long idSottoprocesso) {
		this.idSottoprocesso = idSottoprocesso;
	}

	public long getIdSottoprocesso() {
		return idSottoprocesso;
	}

//	public void setIdAttivitaDettaglio(long idAttivitaDettaglio) {
//		this.idAttivitaDettaglio = idAttivitaDettaglio;
//	}
//
//	public long getIdAttivitaDettaglio() {
//		return idAttivitaDettaglio;
//	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getStato() {
		return stato;
	}

	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}

	public int getOrdinamento() {
		return ordinamento;
	}

//	public void setIdClasse(long idClasse) {
//		this.idClasse = idClasse;
//	}
//
//	public long getIdClasse() {
//		return idClasse;
//	}


}
