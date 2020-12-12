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
	@SequenceGenerator(name = "ID_AU_C_NONCONF_SEQ_GENERATOR", sequenceName = "ID_AU_C_NONCONF_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_AU_C_NONCONF_SEQ_GENERATOR")
	private Long id;	
	
	@Column(name = "ID_C_CAMPAGNA")
	private Long ID_C_CAMPAGNA;

	@Column(name = "ID_M_NONCONF")
	private Long ID_M_NONCONF;
	
	@Column(name = "PESO_NON_CONF")
	private Double PESO_NON_CONF;
	
	@Column(name = "INCC")
	private Double INCC;

	@Column(name="DATA_INIZIO")
	private Date DATA_INIZIO;
	
	@Column(name="DATA_FINE")
	private Date DATA_FINE;
	
	@Column(name="VALORE_PESATO_FASE")
	private Double VALORE_PESATO_FASE;
	
	@Column(name="ID_FASE")
	private Long ID_FASE;
	
	@Column(name = "TOT_PESO_NC")
	private Double TOT_PESO_NC;

	@Column(name = "TOT_PESO_FS")
	private Double TOT_PESO_FS;
	
	@Column(name = "VALORE_PESATO")
	private Double VALORE_PESATO;

	@Column(name = "NUM_NC")
	private Integer NUM_NC;
	
	
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

	public Long getID_M_NONCONF() {
		return ID_M_NONCONF;
	}

	public void setID_M_NONCONF(Long id_m_nonconf) {
		ID_M_NONCONF = id_m_nonconf;
	}

	public Double getPESO_NON_CONF() {
		return PESO_NON_CONF;
	}

	public void setPESO_NON_CONF(Double peso_non_conf) {
		PESO_NON_CONF = peso_non_conf;
	}

	public Double getINCC() {
		return INCC;
	}

	public void setINCC(Double incc) {
		INCC = incc;
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

	public Double getVALORE_PESATO_FASE() {
		return VALORE_PESATO_FASE;
	}

	public void setVALORE_PESATO_FASE(Double valore_pesato_fase) {
		VALORE_PESATO_FASE = valore_pesato_fase;
	}

	public Long getID_FASE() {
		return ID_FASE;
	}

	public void setID_FASE(Long id_fase) {
		ID_FASE = id_fase;
	}

	public Double getTOT_PESO_NC() {
		return TOT_PESO_NC;
	}

	public void setTOT_PESO_NC(Double tot_peso_nc) {
		TOT_PESO_NC = tot_peso_nc;
	}

	public Double getTOT_PESO_FS() {
		return TOT_PESO_FS;
	}

	public void setTOT_PESO_FS(Double tot_peso_fs) {
		TOT_PESO_FS = tot_peso_fs;
	}

	public Double getVALORE_PESATO() {
		return VALORE_PESATO;
	}

	public void setVALORE_PESATO(Double valore_pesato) {
		VALORE_PESATO = valore_pesato;
	}

	public Integer getNUM_NC() {
		return NUM_NC;
	}

	public void setNUM_NC(Integer num_nc) {
		NUM_NC = num_nc;
	}
	
}
