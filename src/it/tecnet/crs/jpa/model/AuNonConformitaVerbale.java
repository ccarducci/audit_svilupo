package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_NON_CONFORMITA_VERBALE")
public class AuNonConformitaVerbale implements Serializable {
	@Id
	@SequenceGenerator(name="AU_NON_CONFORMITA_VERBALE_GENERATOR", sequenceName="ID_NONCONFORMITAVERBALE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_NON_CONFORMITA_VERBALE_GENERATOR")
	@Column(name="ID_NON_CONFORMITA_VERBALE")
	private long idNonConformitaVerbale;


	@Column(name="ID_VERBALE")
	private long idVerbale;

	@Column(name="ID_NON_CONFORMITA")
	private long idNonConformita;

	@Column(name="RISULTATO")
	private double risultato;

	@Column(name="RISULTATO_REALE")
	private Double risultatoReale;

	@Column(name="FASE")
	private String fase;
	
	@Column(name="NOTE")
	private String note;
	
	private static final long serialVersionUID = 1L;

	public long getIdNonConformitaVerbale() {
		return idNonConformitaVerbale;
	}

	public void setIdNonConformitaVerbale(long idNonConformitaVerbale) {
		this.idNonConformitaVerbale = idNonConformitaVerbale;
	}

	public double getRisultato() {
		return risultato;
	}

	public void setRisultato(double risultato) {
		this.risultato = risultato;
	}

	public Double getRisultatoReale() {
		return risultatoReale;
	}

	public void setRisultatoReale(Double risultatoReale) {
		this.risultatoReale = risultatoReale;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public long getIdVerbale() {
		return idVerbale;
	}

	public void setIdVerbale(long idVerbale) {
		this.idVerbale = idVerbale;
	}

	public long getIdNonConformita() {
		return idNonConformita;
	}

	public void setIdNonConformita(long idNonConformita) {
		this.idNonConformita = idNonConformita;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


}
