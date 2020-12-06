package it.tecnet.crs.mod.web.bean;

import java.util.Date;

public class SottoProcesso {
	
	private long idSottoProcesso;
	
	private String descrizione;
	
	private Date dataInizio;
	
	private Date dataFine;
	
	private String input;
	
	private String output;
	
	private String uoCoinvolte;
	
	private String stato;
	
	private String dataInizioAsString;
	
	private String dataFineAsString;
	
	private Processo processo;
	
	private int ordinamento;
	
	
	

	public long getIdSottoProcesso() {
		return idSottoProcesso;
	}

	public void setIdSottoProcesso(long idSottoProcesso) {
		this.idSottoProcesso = idSottoProcesso;
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

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getUoCoinvolte() {
		return uoCoinvolte;
	}

	public void setUoCoinvolte(String uoCoinvolte) {
		this.uoCoinvolte = uoCoinvolte;
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

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}

	public int getOrdinamento() {
		return ordinamento;
	}
	

}
