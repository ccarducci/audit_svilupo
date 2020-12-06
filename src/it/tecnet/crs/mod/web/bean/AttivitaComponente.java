package it.tecnet.crs.mod.web.bean;

import java.util.Date;

public class AttivitaComponente {
	
	private long idAttivitaComponente;
	
	private String descrizione;
	
	private Date dataInizio;
	
	private Date dataFine;
	
	private String dataInizioAsString;
	
	private String dataFineAsString;
	
	private String vincolo;
	
	private String stato;
	
	private SottoProcesso sottoProcesso;
	
	private int ordinamento;
	
	// campi in visualizzazione nella lista dataTable
	private String descrizioneArea;
	private String descrizioneProcesso;
	
	

	public void setIdAttivitaComponente(long idAttivitaComponente) {
		this.idAttivitaComponente = idAttivitaComponente;
	}

	public long getIdAttivitaComponente() {
		return idAttivitaComponente;
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

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public void setDataInizioAsString(String dataInizioAsString) {
		this.dataInizioAsString = dataInizioAsString;
	}

	public String getDataInizioAsString() {
		return dataInizioAsString;
	}

	public void setDataFineAsString(String dataFineAsString) {
		this.dataFineAsString = dataFineAsString;
	}

	public String getDataFineAsString() {
		return dataFineAsString;
	}

	public void setVincolo(String vincolo) {
		this.vincolo = vincolo;
	}

	public String getVincolo() {
		return vincolo;
	}

	public void setSottoProcesso(SottoProcesso sottoProcesso) {
		this.sottoProcesso = sottoProcesso;
	}

	public SottoProcesso getSottoProcesso() {
		return sottoProcesso;
	}

	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}

	public int getOrdinamento() {
		return ordinamento;
	}

	public void setDescrizioneArea(String descrizioneArea) {
		this.descrizioneArea = descrizioneArea;
	}

	public String getDescrizioneArea() {
		return descrizioneArea;
	}

	public void setDescrizioneProcesso(String descrizioneProcesso) {
		this.descrizioneProcesso = descrizioneProcesso;
	}

	public String getDescrizioneProcesso() {
		return descrizioneProcesso;
	}


}
