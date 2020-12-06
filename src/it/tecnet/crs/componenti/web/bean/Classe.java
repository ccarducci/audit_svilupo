package it.tecnet.crs.componenti.web.bean;

import java.util.Date;

public class Classe {
	
	private long idClasse;
	
	private long idTipo;
	
	private long idDescrizioneTipo;

	private long idProcesso;
	
	private long idSottoprocesso;
	
	private long idAttivitaComponente;
	
	private long idAttivitaDettaglio;

	private String descrizione;

	private Date dataInizio;

	private Date dataFine;

	
	
	public long getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(long idClasse) {
		this.idClasse = idClasse;
	}

	public long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public long getIdDescrizioneTipo() {
		return idDescrizioneTipo;
	}

	public void setIdDescrizioneTipo(long idDescrizioneTipo) {
		this.idDescrizioneTipo = idDescrizioneTipo;
	}

	public long getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(long idProcesso) {
		this.idProcesso = idProcesso;
	}

	public long getIdSottoprocesso() {
		return idSottoprocesso;
	}

	public void setIdSottoprocesso(long idSottoprocesso) {
		this.idSottoprocesso = idSottoprocesso;
	}

	public long getIdAttivitaComponente() {
		return idAttivitaComponente;
	}

	public void setIdAttivitaComponente(long idAttivitaComponente) {
		this.idAttivitaComponente = idAttivitaComponente;
	}

	public long getIdAttivitaDettaglio() {
		return idAttivitaDettaglio;
	}

	public void setIdAttivitaDettaglio(long idAttivitaDettaglio) {
		this.idAttivitaDettaglio = idAttivitaDettaglio;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	

}
