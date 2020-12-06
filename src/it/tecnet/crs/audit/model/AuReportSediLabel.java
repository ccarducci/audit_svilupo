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
@Table(name="AU_REPORT_SEDI_LABEL")
public class AuReportSediLabel implements Serializable {
	@Id
	@SequenceGenerator(name="AU_REPORT_SEDI_LABEL_GENERATOR", sequenceName="ID_REPORT_SEDI_LABEL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_REPORT_SEDI_LABEL_GENERATOR")
	@Column(name="ID_REPORT_SEDI_LABEL")
	private long idReportSediLabel;
	
	@Column(name="ID_CAMPAGNA")
	private long idCampagna;
	
	@Column(name="LABEL1")
	private String label1;

	@Column(name="LABEL2")
	private String label2;
	
	@Column(name="LABEL3")
	private String label3;
	
	@Column(name="LABEL4")
	private String label4;
	
	@Column(name="LABEL5")
	private String label5;
	
	@Column(name="LABEL6")
	private String label6;
	
	@Column(name="LABEL7")
	private String label7;
	
	@Column(name="LABEL8")
	private String label8;
	
	@Column(name="LABEL9")
	private String label9;
	
	@Column(name="LABEL10")
	private String label10;

	public long getIdReportSediLabel() {
		return idReportSediLabel;
	}

	public void setIdReportSediLabel(long idReportSediLabel) {
		this.idReportSediLabel = idReportSediLabel;
	}

	public long getIdCampagna() {
		return idCampagna;
	}

	public void setIdCampagna(long idCampagna) {
		this.idCampagna = idCampagna;
	}

	public String getLabel1() {
		return label1;
	}

	public void setLabel1(String label1) {
		this.label1 = label1;
	}

	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public String getLabel3() {
		return label3;
	}

	public void setLabel3(String label3) {
		this.label3 = label3;
	}

	public String getLabel4() {
		return label4;
	}

	public void setLabel4(String label4) {
		this.label4 = label4;
	}

	public String getLabel5() {
		return label5;
	}

	public void setLabel5(String label5) {
		this.label5 = label5;
	}

	public String getLabel6() {
		return label6;
	}

	public void setLabel6(String label6) {
		this.label6 = label6;
	}

	public String getLabel7() {
		return label7;
	}

	public void setLabel7(String label7) {
		this.label7 = label7;
	}

	public String getLabel8() {
		return label8;
	}

	public void setLabel8(String label8) {
		this.label8 = label8;
	}

	public String getLabel9() {
		return label9;
	}

	public void setLabel9(String label9) {
		this.label9 = label9;
	}

	public String getLabel10() {
		return label10;
	}

	public void setLabel10(String label10) {
		this.label10 = label10;
	}

	

}
