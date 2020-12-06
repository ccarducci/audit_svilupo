package it.tecnet.crs.ATPO.auditors.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ATPO_FASE_RIEPILOGO_FASCICOLO")
@NamedQueries( { @NamedQuery(name = AtpoRiepilogoFascicolo.QUERY_FASERIEPILOGOFASCICOLO_BY_IDFASEDATI, 
		query = "SELECT t FROM AtpoRiepilogoFascicolo t WHERE t.idFaseDati = "
		+ AtpoRiepilogoFascicolo.QUERY_PARAM_IDFASEDATI) })
public class AtpoRiepilogoFascicolo implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FASERIEPILOGOFASCICOLO_BY_IDFASEDATI = "AtpoRiepilogoFascicolo.findAllByIdFaseDati";
	public static final String QUERY_PARAM_IDFASEDATI = ":idFaseDati";

	@Id
	@SequenceGenerator(name = "ATPO_RIEPILOGO_FASCICOLO_GENERATOR", sequenceName = "ATPO_RIEPILOGO_FASCICOLO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_RIEPILOGO_FASCICOLO_GENERATOR")
	@Column(name = "ID_RIEPILOGO_FASCICOLO")
	private long idRiepilogoFascicolo;

	@Column(name = "FASCICOLO_ELETTRONICO")
	private String fascicoloElettronico;

	@Column(name = "FASCICOLO_CARTACEO")
	private String fascicoloCartaceo;

	@Column(name = "DETT_DOC_MANCANTE")
	private String dettDocMancante;

	@Column(name = "ID_FASE_DATI")
	private long idFaseDati;
	
	@Column(name = "FASE_PRONTA")
	private String fasePronta;
	public long getIdRiepilogoFascicolo() {
		return idRiepilogoFascicolo;
	}

	public void setIdRiepilogoFascicolo(long idRiepilogoFascicolo) {
		this.idRiepilogoFascicolo = idRiepilogoFascicolo;
	}

	public String getFascicoloElettronico() {
		return fascicoloElettronico;
	}

	public void setFascicoloElettronico(String fascicoloElettronico) {
		this.fascicoloElettronico = fascicoloElettronico;
	}

	public String getFascicoloCartaceo() {
		return fascicoloCartaceo;
	}

	public void setFascicoloCartaceo(String fascicoloCartaceo) {
		this.fascicoloCartaceo = fascicoloCartaceo;
	}

	public String getDettDocMancante() {
		return dettDocMancante;
	}

	public void setDettDocMancante(String dettDocMancante) {
		this.dettDocMancante = dettDocMancante;
	}

	public long getIdFaseDati() {
		return idFaseDati;
	}

	public void setIdFaseDati(long idFaseDati) {
		this.idFaseDati = idFaseDati;
	}

	public void setFasePronta(String fasePronta) {
		this.fasePronta = fasePronta;
	}

	public String getFasePronta() {
		return fasePronta;
	}

}
