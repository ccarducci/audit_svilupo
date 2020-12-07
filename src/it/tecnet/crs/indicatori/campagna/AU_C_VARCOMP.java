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
@Table(name="AU_C_VARCOMP")
public class AU_C_VARCOMP {
	
    @Id
	@SequenceGenerator(name="AU_ASS_VERBALE_CAMPAGNA_GENERATOR", sequenceName="ID_ASS_VERB_CMPGN_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_ASS_VERBALE_CAMPAGNA_GENERATOR")
    private Long id;
	
   @Column(name="ID_C_CAMPAGNA")
	private Long ID_C_CAMPAGNA;
	
	@Column(name="ID_M_NONCONF")
	private Long ID_M_NON_CONF;
	
	@Column(name="ID_M_VARCOMP")
	private Long ID_M_VARCONP;
	
	@Column(name="DATA_INIZIO")
	private Date DATA_INIZIO;
	
	@Column(name="DATA_FINE")
	private Date DATA_FINE;
	
	@Column(name="NUM")
	private Integer NUM;
	
	@Column(name="PERC_SU_PS")
	private Double PERC_SU_PS;
	
	@Column(name="PERC_PESATA")
	private Double PERC_PESATA;
	
	@Column(name="ID_FASE")
	private Long ID_FASE;

	public Long getID_C_CAMPAGNA() {
		return ID_C_CAMPAGNA;
	}

	public void setID_C_CAMPAGNA(Long iD_C_CAMPAGNA) {
		ID_C_CAMPAGNA = iD_C_CAMPAGNA;
	}

	public Long getID_M_NON_CONF() {
		return ID_M_NON_CONF;
	}

	public void setID_M_NON_CONF(Long iD_M_NON_CONF) {
		ID_M_NON_CONF = iD_M_NON_CONF;
	}

	public Long getID_M_VARCONP() {
		return ID_M_VARCONP;
	}

	public void setID_M_VARCONP(Long iD_M_VARCONP) {
		ID_M_VARCONP = iD_M_VARCONP;
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

	public Integer getNUM() {
		return NUM;
	}

	public void setNUM(Integer nUM) {
		NUM = nUM;
	}

	public Double getPERC_SU_PS() {
		return PERC_SU_PS;
	}

	public void setPERC_SU_PS(Double pERC_SU_PS) {
		PERC_SU_PS = pERC_SU_PS;
	}

	public Double getPERC_PESATA() {
		return PERC_PESATA;
	}

	public void setPERC_PESATA(Double pERC_PESATA) {
		PERC_PESATA = pERC_PESATA;
	}

	public Long getID_FASE() {
		return ID_FASE;
	}

	public void setID_FASE(Long iD_FASE) {
		ID_FASE = iD_FASE;
	}
	
}
