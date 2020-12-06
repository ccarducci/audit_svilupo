package it.tecnet.crs.web.dto;

import java.math.BigDecimal;

public class NonConformitaAccessiDto {

	private long idMNonConf;
	private long idSNonConf;

	private int ordinamento;
	private String descrizioneFase;

	// tabella
	private String descrizione;
	private String codice;
	private String dataInizio;
	private String dataFine;
	// quantita è il numero di pratiche con quella non conformita
	private int quantita;
	private Double valoreINCC;
	private String descrittivoINCC;
	private Double percQuantSingolaNonConfsuSommatotale;
	private String praticaNonSoggetta;
	// aside
	private BigDecimal peso;
	private BigDecimal valorepesato;
	private String ncConValoreMinimo;
	private String ncConValoreMassimo;
	private int minimo;
	private int massimo;

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Double getValoreINCC() {
		return valoreINCC;
	}

	public void setValoreINCC(Double valoreINCC) {
		this.valoreINCC = valoreINCC;
	}

	public String getDescrittivoINCC() {
		return descrittivoINCC;
	}

	public void setDescrittivoINCC(String descrittivoINCC) {
		this.descrittivoINCC = descrittivoINCC;
	}

	public Double getPercQuantSingolaNonConfsuSommatotale() {
		return percQuantSingolaNonConfsuSommatotale;
	}

	public void setPercQuantSingolaNonConfsuSommatotale(
			Double percQuantSingolaNonConfsuSommatotale) {
		this.percQuantSingolaNonConfsuSommatotale = percQuantSingolaNonConfsuSommatotale;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getValorepesato() {
		return valorepesato;
	}

	public void setValorepesato(BigDecimal valorepesato) {
		this.valorepesato = valorepesato;
	}

	public String getNcConValoreMinimo() {
		return ncConValoreMinimo;
	}

	public void setNcConValoreMinimo(String ncConValoreMinimo) {
		this.ncConValoreMinimo = ncConValoreMinimo;
	}

	public String getNcConValoreMassimo() {
		return ncConValoreMassimo;
	}

	public void setNcConValoreMassimo(String ncConValoreMassimo) {
		this.ncConValoreMassimo = ncConValoreMassimo;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMassimo() {
		return massimo;
	}

	public void setMassimo(int massimo) {
		this.massimo = massimo;
	}

	public long getIdMNonConf() {
		return idMNonConf;
	}

	public void setIdMNonConf(long idMNonConf) {
		this.idMNonConf = idMNonConf;
	}

	public long getIdSNonConf() {
		return idSNonConf;
	}

	public void setIdSNonConf(long idSNonConf) {
		this.idSNonConf = idSNonConf;
	}

	public void setPraticaNonSoggetta(String praticaNonSoggetta) {
		this.praticaNonSoggetta = praticaNonSoggetta;
	}

	public String getPraticaNonSoggetta() {
		return praticaNonSoggetta;
	}

	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}

	public int getOrdinamento() {
		return ordinamento;
	}

	public void setDescrizioneFase(String descrizioneFase) {
		this.descrizioneFase = descrizioneFase;
	}

	public String getDescrizioneFase() {
		return descrizioneFase;
	}

}
