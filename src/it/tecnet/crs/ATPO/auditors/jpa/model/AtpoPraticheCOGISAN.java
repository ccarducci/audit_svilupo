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
@Table(name="ATPO_PRATICHE_COGISAN")
public class AtpoPraticheCOGISAN implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@SequenceGenerator(name="ATPO_PRATICHE_COGISAN_GENERATOR", sequenceName="ATPO_PRATICHE_COGISAN_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATPO_PRATICHE_COGISAN_GENERATOR")
	@Column(name="ID_ASS_VERB_CMPGN")
	private Long idPraticheCogisan;
	
	
	@Column(name="FASCICOLO")
	private String fascicolo;
	
	@Column(name="RG")
	private String rg;
	
	@Column(name="PARTE")
	private String parte;
	
	@Column(name="VISITA_PERITALE")
	private String visitaPeritale;
	
	@Column(name="OSSERVAZIONI_SANITARIE")
	private String  osservazioniSanitarie;
	
	@Column(name="COD_SEDE")
	private String codSede;
	
	
	
	public Long getIdPraticheCogisan() {
		return idPraticheCogisan;
	}
	public void setIdPraticheCogisan(Long idPraticheCogisan) {
		this.idPraticheCogisan = idPraticheCogisan;
	}
	public String getCodSede() {
		return codSede;
	}
	public void setCodSede(String codSede) {
		this.codSede = codSede;
	}
	public String getFascicolo() {
		return fascicolo;
	}
	public void setFascicolo(String fascicolo) {
		this.fascicolo = fascicolo;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getParte() {
		return parte;
	}
	public void setParte(String parte) {
		this.parte = parte;
	}
	public String getVisitaPeritale() {
		return visitaPeritale;
	}
	public void setVisitaPeritale(String visitaPeritale) {
		this.visitaPeritale = visitaPeritale;
	}
	public String getOsservazioniSanitarie() {
		return osservazioniSanitarie;
	}
	public void setOsservazioniSanitarie(String osservazioniSanitarie) {
		this.osservazioniSanitarie = osservazioniSanitarie;
	}
	
	
}
