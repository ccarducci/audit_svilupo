package it.tecnet.crs.ATPO.auditors.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ATPO_DETTAGLIO_FASCICOLO")
public class AtpoDettaglioFascicolo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATPO_DETTAGLIO_FASCICOLO_GENERATOR", sequenceName = "ID_DETT_FASC_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_DETTAGLIO_FASCICOLO_GENERATOR")
	@Column(name = "ID_DETT_FASC")
	private long idDettFasc;

	@Column(name = "ID_RIEPILOGO_FASCICOLO")
	private long idRiepilogoFascicolo;

	@Column(name = "CODIFICA")
	private String codifica;

	public long getIdDettFasc() {
		return idDettFasc;
	}

	public void setIdDettFasc(long idDettFasc) {
		this.idDettFasc = idDettFasc;
	}

	public long getIdRiepilogoFascicolo() {
		return idRiepilogoFascicolo;
	}

	public void setIdRiepilogoFascicolo(long idRiepilogoFascicolo) {
		this.idRiepilogoFascicolo = idRiepilogoFascicolo;
	}

	public String getCodifica() {
		return codifica;
	}

	public void setCodifica(String codifica) {
		this.codifica = codifica;
	}

}
