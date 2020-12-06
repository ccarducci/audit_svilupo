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
@Table(name="AU_NON_CONFORMITA")
public class AuNonConformita implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="AU_NON_CONFORMITA_GENERATOR", sequenceName="ID_NONCONFORMITA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_NON_CONFORMITA_GENERATOR")
	@Column(name="ID_NON_CONFORMITA")
	private long idNonConformita;

	@Column(name="DESCRIZIONE")
	private String descrizione;

	@Column(name="DESCRIZIONENONCONFORMITA")
	private String descrizioneNonConformita;

	@Column(name="SOGLIA1")
	private String soglia1;

	@Column(name="SOGLIA2")
	private String soglia2;

	@Column(name="SOGLIA3")
	private String soglia3;

	@Column(name="SOGLIA4")
	private String soglia4;

	@Column(name="SOGLIA5")
	private String soglia5;

	@Column(name="SOGLIA6")
	private String soglia6;

	@Column(name="RISCHIO")
	private String rischio;

	@Column(name="RISCHIO_ECONOMICO")
	private double rischioEconomico;
	
	@Column(name="FASE")
	private String fase;
	
	@Column(name="CODICE_NC")
	private String codiceNc;

	public String getDescrizioneNonConformita() {
		return descrizioneNonConformita;
	}

	public void setDescrizioneNonConformita(String descrizioneNonConformita) {
		this.descrizioneNonConformita = descrizioneNonConformita;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public AuNonConformita() {
		super();
	}

	public long getIdNonConformita() {
		return idNonConformita;
	}

	public void setIdNonConformita(long idNonConformita) {
		this.idNonConformita = idNonConformita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getSoglia1() {
		return soglia1;
	}

	public void setSoglia1(String soglia1) {
		this.soglia1 = soglia1;
	}

	public String getSoglia2() {
		return soglia2;
	}

	public void setSoglia2(String soglia2) {
		this.soglia2 = soglia2;
	}

	public String getSoglia3() {
		return soglia3;
	}

	public void setSoglia3(String soglia3) {
		this.soglia3 = soglia3;
	}

	public String getSoglia4() {
		return soglia4;
	}

	public void setSoglia4(String soglia4) {
		this.soglia4 = soglia4;
	}

	public String getSoglia5() {
		return soglia5;
	}

	public void setSoglia5(String soglia5) {
		this.soglia5 = soglia5;
	}

	public String getSoglia6() {
		return soglia6;
	}

	public void setSoglia6(String soglia6) {
		this.soglia6 = soglia6;
	}

	public String getRischio() {
		return rischio;
	}

	public void setRischio(String rischio) {
		this.rischio = rischio;
	}

	public double getRischioEconomico() {
		return rischioEconomico;
	}

	public void setRischioEconomico(double rischioEconomico) {
		this.rischioEconomico = rischioEconomico;
	}

	public void setCodiceNc(String codiceNc) {
		this.codiceNc = codiceNc;
	}

	public String getCodiceNc() {
		return codiceNc;
	}

	

}
