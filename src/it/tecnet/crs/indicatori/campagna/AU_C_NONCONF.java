package it.tecnet.crs.indicatori.campagna;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AU_C_NONCONF")
public class AU_C_NONCONF {

	@Id
	@SequenceGenerator(name = "AU_ASS_VERBALE_CAMPAGNA_GENERATOR", sequenceName = "ID_ASS_VERB_CMPGN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_ASS_VERBALE_CAMPAGNA_GENERATOR")
	private Long id;

	@Column(name = "ID_C_CAMPAGNA")
	private Long ID_C_CAMPAGNA;

	@Column(name = "ID_M_NONCONF")
	private Long ID_M_NONCONF;
	
	@Column(name = "ID_FASE")
	private Long ID_FASE;
	
	@Column(name = "DATA_INIZIO")
	private Date DATA_INIZIO;
	
	@Column(name = "DATA_FINE")
	private Date DATA_FINE;
	
	@Column(name = "CODICE")
	private String CODICE;
	
	@Column(name = "PESO_NONCONF")
	private Double PESO_NONCONF;
	
	@Column(name = "NUM")
	private Integer NUM;
	
	@Column(name = "VALORE_INCC")
	private Double VALORE_INCC;
	
	@Column(name = "VALORE_PESATO")
	private Double VALORE_PESATO;
	
	@Column(name = "VALORE_PESATO_BASE")
	private Double VALORE_PESATO_BASE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getID_C_CAMPAGNA() {
		return ID_C_CAMPAGNA;
	}

	public void setID_C_CAMPAGNA(Long iD_C_CAMPAGNA) {
		ID_C_CAMPAGNA = iD_C_CAMPAGNA;
	}

	public Long getID_M_NONCONF() {
		return ID_M_NONCONF;
	}

	public void setID_M_NONCONF(Long iD_M_NONCONF) {
		ID_M_NONCONF = iD_M_NONCONF;
	}

	public Long getID_FASE() {
		return ID_FASE;
	}

	public void setID_FASE(Long iD_FASE) {
		ID_FASE = iD_FASE;
	}

	public Date getDATA_INIZIO() {
		return DATA_INIZIO;
	}

	public void setDATA_INIZIO(Date dATA_INIZIO) {
		DATA_INIZIO = dATA_INIZIO;
	}

	public Date getDATA_FINE() {
		return DATA_FINE;
	}

	public void setDATA_FINE(Date dATA_FINE) {
		DATA_FINE = dATA_FINE;
	}

	public String getCODICE() {
		return CODICE;
	}

	public void setCODICE(String cODICE) {
		CODICE = cODICE;
	}

	public Double getPESO_NONCONF() {
		return PESO_NONCONF;
	}

	public void setPESO_NONCONF(Double pESO_NONCONF) {
		PESO_NONCONF = pESO_NONCONF;
	}

	public Integer getNUM() {
		return NUM;
	}

	public void setNUM(Integer nUM) {
		NUM = nUM;
	}

	public Double getVALORE_INCC() {
		return VALORE_INCC;
	}

	public void setVALORE_INCC(Double vALORE_INCC) {
		VALORE_INCC = vALORE_INCC;
	}

	public Double getVALORE_PESATO() {
		return VALORE_PESATO;
	}

	public void setVALORE_PESATO(Double vALORE_PESATO) {
		VALORE_PESATO = vALORE_PESATO;
	}

	public Double getVALORE_PESATO_BASE() {
		return VALORE_PESATO_BASE;
	}

	public void setVALORE_PESATO_BASE(Double vALORE_PESATO_BASE) {
		VALORE_PESATO_BASE = vALORE_PESATO_BASE;
	}
	
	
	
}
