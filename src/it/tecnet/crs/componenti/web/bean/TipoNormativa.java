package it.tecnet.crs.componenti.web.bean;

import java.util.Date;

public class TipoNormativa {
	
	private long idTipo;

	private String descrizione;

	private Date dataInizio;

	private Date dataFine;

	private long idClasse;

	private long idDescrizioneTipo;

	
	
	
	public long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
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

	public long getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(long idClasse) {
		this.idClasse = idClasse;
	}

	public long getIdDescrizioneTipo() {
		return idDescrizioneTipo;
	}

	public void setIdDescrizioneTipo(long idDescrizioneTipo) {
		this.idDescrizioneTipo = idDescrizioneTipo;
	}

}
