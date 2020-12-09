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
	
	@Column(name="NUM_VC")
	private Integer NUM_VC;
	
	@Column(name="NUM_NC")
	private Integer NUM_NC;
	
	@Column(name="SOGLIA")
	private String SOGLIA;
	
	@Column(name="PERC_SU_PS")
	private Double PERC_SU_PS;
	
	@Column(name="PERC_PESATA")
	private Double PERC_PESATA;

	@Column(name="DATA_INIZIO")
	private Date DATA_INIZIO;
	
	@Column(name="DATA_FINE")
	private Date DATA_FINE;
	
	@Column(name="ID_FASE")
	private Long ID_FASE;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getID_C_CAMPAGNA() {
		return ID_C_CAMPAGNA;
	}

	public void setID_C_CAMPAGNA(Long id_c_campagna) {
		ID_C_CAMPAGNA = id_c_campagna;
	}

	public Long getID_M_NON_CONF() {
		return ID_M_NON_CONF;
	}

	public void setID_M_NON_CONF(Long id_m_non_conf) {
		ID_M_NON_CONF = id_m_non_conf;
	}

	public Long getID_M_VARCONP() {
		return ID_M_VARCONP;
	}

	public void setID_M_VARCONP(Long id_m_varconp) {
		ID_M_VARCONP = id_m_varconp;
	}

	public Integer getNUM_VC() {
		return NUM_VC;
	}

	public void setNUM_VC(Integer num_vc) {
		NUM_VC = num_vc;
	}

	public Integer getNUM_NC() {
		return NUM_NC;
	}

	public void setNUM_NC(Integer num_nc) {
		NUM_NC = num_nc;
	}

	public String getSOGLIA() {
		return SOGLIA;
	}

	public void setSOGLIA(String soglia) {
		SOGLIA = soglia;
	}

	public Double getPERC_SU_PS() {
		return PERC_SU_PS;
	}

	public void setPERC_SU_PS(Double perc_su_ps) {
		PERC_SU_PS = perc_su_ps;
	}

	public Double getPERC_PESATA() {
		return PERC_PESATA;
	}

	public void setPERC_PESATA(Double perc_pesata) {
		PERC_PESATA = perc_pesata;
	}

	public Date getDATA_INIZIO() {
		return DATA_INIZIO;
	}

	public void setDATA_INIZIO(Date data_inizio) {
		DATA_INIZIO = data_inizio;
	}

	public Date getDATA_FINE() {
		return DATA_FINE;
	}

	public void setDATA_FINE(Date data_fine) {
		DATA_FINE = data_fine;
	}

	public Long getID_FASE() {
		return ID_FASE;
	}

	public void setID_FASE(Long id_fase) {
		ID_FASE = id_fase;
	}
}
