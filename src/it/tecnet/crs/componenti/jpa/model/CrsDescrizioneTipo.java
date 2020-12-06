package it.tecnet.crs.componenti.jpa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_DESCRIZIONE_TIPO")
public class CrsDescrizioneTipo implements Serializable {
	@Id
	@Column(name="ID_DESCRIZIONE_TIPO")
	@SequenceGenerator(name="CRS_DESCRIZIONE_TIPO_GENERATOR", sequenceName="ID_DESCRIZIONETIPO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_DESCRIZIONE_TIPO_GENERATOR")
	private long idDescrizioneTipo;
	
	@Column(name="ID_CLASSE")
	private long idClasse;

	@Column(name="ID_TIPO")
	private long idTipo;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;

	@Column(name="DESC_SINTETICA")
	private String descSintetica;

	@Column(name="DESC_DETTAGLIO")
	private String descDettaglio;

	private String riferimenti;

	private String causale;

	private String label1;

	private String label2;

	private String label3;

	private String label4;

	private String label5;

	private String label6;

	private String label7;

	private String label8;

	private String label9;

	private String label10;

	private String label11;

	private String label12;

	private String label13;

	private String label14;

	private String label15;

	private String label16;

	private String label17;

	private String label18;

	private String label19;

	private String label20;

	private String label21;

	private String label22;

	

	private static final long serialVersionUID = 1L;

	public CrsDescrizioneTipo() {
		super();
	}

	public long getIdDescrizioneTipo() {
		return this.idDescrizioneTipo;
	}

	public void setIdDescrizioneTipo(long idDescrizioneTipo) {
		this.idDescrizioneTipo = idDescrizioneTipo;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getDescSintetica() {
		return this.descSintetica;
	}

	public void setDescSintetica(String descSintetica) {
		this.descSintetica = descSintetica;
	}

	public String getDescDettaglio() {
		return this.descDettaglio;
	}

	public void setDescDettaglio(String descDettaglio) {
		this.descDettaglio = descDettaglio;
	}

	public String getRiferimenti() {
		return this.riferimenti;
	}

	public void setRiferimenti(String riferimenti) {
		this.riferimenti = riferimenti;
	}

	public String getCausale() {
		return this.causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getLabel1() {
		return this.label1;
	}

	public void setLabel1(String label1) {
		this.label1 = label1;
	}

	public String getLabel2() {
		return this.label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public String getLabel3() {
		return this.label3;
	}

	public void setLabel3(String label3) {
		this.label3 = label3;
	}

	public String getLabel4() {
		return this.label4;
	}

	public void setLabel4(String label4) {
		this.label4 = label4;
	}

	public String getLabel5() {
		return this.label5;
	}

	public void setLabel5(String label5) {
		this.label5 = label5;
	}

	public String getLabel6() {
		return this.label6;
	}

	public void setLabel6(String label6) {
		this.label6 = label6;
	}

	public String getLabel7() {
		return this.label7;
	}

	public void setLabel7(String label7) {
		this.label7 = label7;
	}

	public String getLabel8() {
		return this.label8;
	}

	public void setLabel8(String label8) {
		this.label8 = label8;
	}

	public String getLabel9() {
		return this.label9;
	}

	public void setLabel9(String label9) {
		this.label9 = label9;
	}

	public String getLabel10() {
		return this.label10;
	}

	public void setLabel10(String label10) {
		this.label10 = label10;
	}

	public String getLabel11() {
		return this.label11;
	}

	public void setLabel11(String label11) {
		this.label11 = label11;
	}

	public String getLabel12() {
		return this.label12;
	}

	public void setLabel12(String label12) {
		this.label12 = label12;
	}

	public String getLabel13() {
		return this.label13;
	}

	public void setLabel13(String label13) {
		this.label13 = label13;
	}

	public String getLabel14() {
		return this.label14;
	}

	public void setLabel14(String label14) {
		this.label14 = label14;
	}

	public String getLabel15() {
		return this.label15;
	}

	public void setLabel15(String label15) {
		this.label15 = label15;
	}

	public String getLabel16() {
		return this.label16;
	}

	public void setLabel16(String label16) {
		this.label16 = label16;
	}

	public String getLabel17() {
		return this.label17;
	}

	public void setLabel17(String label17) {
		this.label17 = label17;
	}

	public String getLabel18() {
		return this.label18;
	}

	public void setLabel18(String label18) {
		this.label18 = label18;
	}

	public String getLabel19() {
		return this.label19;
	}

	public void setLabel19(String label19) {
		this.label19 = label19;
	}

	public String getLabel20() {
		return this.label20;
	}

	public void setLabel20(String label20) {
		this.label20 = label20;
	}

	public String getLabel21() {
		return this.label21;
	}

	public void setLabel21(String label21) {
		this.label21 = label21;
	}

	public String getLabel22() {
		return this.label22;
	}

	public void setLabel22(String label22) {
		this.label22 = label22;
	}

	public void setIdClasse(long idClasse) {
		this.idClasse = idClasse;
	}

	public long getIdClasse() {
		return idClasse;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public long getIdTipo() {
		return idTipo;
	}

}
