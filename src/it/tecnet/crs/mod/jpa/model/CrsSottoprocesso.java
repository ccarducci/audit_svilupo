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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_SOTTOPROCESSO")
public class CrsSottoprocesso implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_SOTTOPROCESSO_GENERATOR", sequenceName="ID_SOTTOPROCESSO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_SOTTOPROCESSO_GENERATOR")
	@Column(name="ID_SOTTOPROCESSO")
	private long idSottoprocesso;

	private String descrizione;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;

	private String input;

	private String output;

	@Column(name="UO_COINVOLTE")
	private String uoCoinvolte;

	@Column(name="ID_PROCESSO")
	private long idProcesso;
	
	private String stato;
	
	private int ordinamento;
	
	//private long idClasse;

	
	private static final long serialVersionUID = 1L;

	public CrsSottoprocesso() {
		super();
	}

	public long getIdSottoprocesso() {
		return this.idSottoprocesso;
	}

	public void setIdSottoprocesso(long idSottoprocesso) {
		this.idSottoprocesso = idSottoprocesso;
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

	public String getInput() {
		return this.input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return this.output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getUoCoinvolte() {
		return this.uoCoinvolte;
	}

	public void setUoCoinvolte(String uoCoinvolte) {
		this.uoCoinvolte = uoCoinvolte;
	}


	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}

	public long getIdProcesso() {
		return idProcesso;
	}

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
