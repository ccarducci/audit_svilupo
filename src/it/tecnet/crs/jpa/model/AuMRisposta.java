package it.tecnet.crs.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_M_RISPOSTA")
public class AuMRisposta {
	@Id
	@SequenceGenerator(name="AU_M_RISPOSTA_GENERATOR", sequenceName="AU_M_RISPOSTA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_M_RISPOSTA_GENERATOR")
	@Column(name="ID_RISPOSTA")
	private long idRisposta;
	
	@Column(name="ID_DOMANDA")
	private long idDomanda;

	@Column(name="DESCRIZIONE")
	private String descrizione;

	@Column(name="VALORE_RISPOSTA")
	private long valoreRisposta;
	
	@Column(name="DATA_INIZIO")
	private Date dataInizio;
	
	@Column(name="DATA_FINE")
	private Date dataFine;

	public long getIdRisposta() {
		return idRisposta;
	}

	public void setIdRisposta(long idRisposta) {
		this.idRisposta = idRisposta;
	}

	public long getIdDomanda() {
		return idDomanda;
	}

	public void setIdDomanda(long idDomanda) {
		this.idDomanda = idDomanda;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public long getValoreRisposta() {
		return valoreRisposta;
	}

	public void setValoreRisposta(long valoreRisposta) {
		this.valoreRisposta = valoreRisposta;
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
