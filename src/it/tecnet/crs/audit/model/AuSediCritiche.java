package it.tecnet.crs.audit.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_SEDI_CRITICHE")
public class AuSediCritiche implements Serializable {
	
	@Id
	@Column(name="ID_SEDI_CRITICHE")
	private long id_sedi_critiche;
	
	@Column(name="SEDE")
	private String sede;
	
	@Column(name="ANNO")
	private String anno;
	
	@Column(name="NumeroIstanze")
	private int numeroIstanze;

	@Column(name="TempoMedioAperturaPratica")
	private int tempoMedioAperturaPratica;
	
	@Column(name="TempoMedioChiusuraPratica")
	private int tempoMedioChiusuraPratica;
	
	@Column(name="Sfavorevoli")
	private int sfavorevoli;
	
	@Column(name="PercSfavorevoli")
	private double percSfavorevoli;
	
	@Column(name="ConFascicoliApertiSenzaDocumenti")
	private int conFascicoliApertiSenzaDocumenti;
	
	@Column(name="PercConFascicoliApertiSenzaDocumenti")
	private double percConFascicoliApertiSenzaDocumenti;
	
	@Column(name="ConVisitePeritali")
	private int conVisitePeritali;
	
	@Column(name="PercConVisitePeritali")
	private double percConVisitePeritali;
	
	@Column(name="ConPresenzaAttoCostituzione")
	private int conPresenzaAttoCostituzione;

	@Column(name="ConPresenzaParereAutotutela")
	private int conPresenzaParereAutotutela;
	
	@Column(name="ConPresenzaParereSanitario")
	private int conPresenzaParereSanitario;
	
	@Column(name="ConPresenzaParereSanitarioBozza")
	private int conPresenzaParereSanitarioBozza;
	
	@Column(name="ConPresenzaParereSanitarioDefinitiva")
	private int conPresenzaParereSanitarioDefinitiva;

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public int getNumeroIstanze() {
		return numeroIstanze;
	}

	public void setNumeroIstanze(int numeroIstanze) {
		this.numeroIstanze = numeroIstanze;
	}

	public int getTempoMedioAperturaPratica() {
		return tempoMedioAperturaPratica;
	}

	public void setTempoMedioAperturaPratica(int tempoMedioAperturaPratica) {
		this.tempoMedioAperturaPratica = tempoMedioAperturaPratica;
	}

	public int getTempoMedioChiusuraPratica() {
		return tempoMedioChiusuraPratica;
	}

	public void setTempoMedioChiusuraPratica(int tempoMedioChiusuraPratica) {
		this.tempoMedioChiusuraPratica = tempoMedioChiusuraPratica;
	}

	public int getSfavorevoli() {
		return sfavorevoli;
	}

	public void setSfavorevoli(int sfavorevoli) {
		this.sfavorevoli = sfavorevoli;
	}

	public double getPercSfavorevoli() {
		return percSfavorevoli;
	}

	public void setPercSfavorevoli(double percSfavorevoli) {
		this.percSfavorevoli = percSfavorevoli;
	}

	public int getConFascicoliApertiSenzaDocumenti() {
		return conFascicoliApertiSenzaDocumenti;
	}

	public void setConFascicoliApertiSenzaDocumenti(
			int conFascicoliApertiSenzaDocumenti) {
		this.conFascicoliApertiSenzaDocumenti = conFascicoliApertiSenzaDocumenti;
	}

	public double getPercConFascicoliApertiSenzaDocumenti() {
		return percConFascicoliApertiSenzaDocumenti;
	}

	public void setPercConFascicoliApertiSenzaDocumenti(
			double percConFascicoliApertiSenzaDocumenti) {
		this.percConFascicoliApertiSenzaDocumenti = percConFascicoliApertiSenzaDocumenti;
	}

	public int getConVisitePeritali() {
		return conVisitePeritali;
	}

	public void setConVisitePeritali(int conVisitePeritali) {
		this.conVisitePeritali = conVisitePeritali;
	}

	public double getPercConVisitePeritali() {
		return percConVisitePeritali;
	}

	public void setPercConVisitePeritali(double percConVisitePeritali) {
		this.percConVisitePeritali = percConVisitePeritali;
	}

	public int getConPresenzaAttoCostituzione() {
		return conPresenzaAttoCostituzione;
	}

	public void setConPresenzaAttoCostituzione(int conPresenzaAttoCostituzione) {
		this.conPresenzaAttoCostituzione = conPresenzaAttoCostituzione;
	}

	public int getConPresenzaParereAutotutela() {
		return conPresenzaParereAutotutela;
	}

	public void setConPresenzaParereAutotutela(int conPresenzaParereAutotutela) {
		this.conPresenzaParereAutotutela = conPresenzaParereAutotutela;
	}

	public int getConPresenzaParereSanitario() {
		return conPresenzaParereSanitario;
	}

	public void setConPresenzaParereSanitario(int conPresenzaParereSanitario) {
		this.conPresenzaParereSanitario = conPresenzaParereSanitario;
	}

	public int getConPresenzaParereSanitarioBozza() {
		return conPresenzaParereSanitarioBozza;
	}

	public void setConPresenzaParereSanitarioBozza(
			int conPresenzaParereSanitarioBozza) {
		this.conPresenzaParereSanitarioBozza = conPresenzaParereSanitarioBozza;
	}

	public int getConPresenzaParereSanitarioDefinitiva() {
		return conPresenzaParereSanitarioDefinitiva;
	}

	public void setConPresenzaParereSanitarioDefinitiva(
			int conPresenzaParereSanitarioDefinitiva) {
		this.conPresenzaParereSanitarioDefinitiva = conPresenzaParereSanitarioDefinitiva;
	}

	public long getId_sedi_critiche() {
		return id_sedi_critiche;
	}

	public void setId_sedi_critiche(long id_sedi_critiche) {
		this.id_sedi_critiche = id_sedi_critiche;
	}
	
	
}
