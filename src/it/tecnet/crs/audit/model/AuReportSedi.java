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
@Table(name="AU_REPORT_SEDI")
public class AuReportSedi implements Serializable {
	@Id
	@SequenceGenerator(name="AU_REPORT_SEDI_GENERATOR", sequenceName="ID_REPORT_SEDI_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_REPORT_SEDI_GENERATOR")
	@Column(name="ID_REPORT_SEDI")
	private long idReportSedi;

	@Column(name="ID_SEDE")
	private long idSede;
	
	@Column(name="ID_CAMPAGNA")
	private long idCampagna;
	
	@Column(name="INDICATORE1")
	private double indicatore1;

	@Column(name="INDICATORE2")
	private double indicatore2;
	
	@Column(name="INDICATORE3")
	private double indicatore3;
	
	@Column(name="INDICATORE4")
	private double indicatore4;
	
	@Column(name="INDICATORE5")
	private double indicatore5;
	
	@Column(name="INDICATORE6")
	private double indicatore6;
	
	@Column(name="INDICATORE7")
	private double indicatore7;
	
	@Column(name="INDICATORE8")
	private double indicatore8;
	
	@Column(name="INDICATORE9")
	private double indicatore9;
	
	@Column(name="INDICATORE10")
	private double indicatore10;

	public long getIdSede() {
		return idSede;
	}

	public void setIdSede(long idSede) {
		this.idSede = idSede;
	}

	public long getIdCampagna() {
		return idCampagna;
	}

	public void setIdCampagna(long idCampagna) {
		this.idCampagna = idCampagna;
	}

	public double getIndicatore1() {
		return indicatore1;
	}

	public void setIndicatore1(double indicatore1) {
		this.indicatore1 = indicatore1;
	}

	public double getIndicatore2() {
		return indicatore2;
	}

	public void setIndicatore2(double indicatore2) {
		this.indicatore2 = indicatore2;
	}

	public double getIndicatore3() {
		return indicatore3;
	}

	public void setIndicatore3(double indicatore3) {
		this.indicatore3 = indicatore3;
	}

	public double getIndicatore4() {
		return indicatore4;
	}

	public void setIndicatore4(double indicatore4) {
		this.indicatore4 = indicatore4;
	}

	public double getIndicatore5() {
		return indicatore5;
	}

	public void setIndicatore5(double indicatore5) {
		this.indicatore5 = indicatore5;
	}

	public double getIndicatore6() {
		return indicatore6;
	}

	public void setIndicatore6(double indicatore6) {
		this.indicatore6 = indicatore6;
	}

	public double getIndicatore7() {
		return indicatore7;
	}

	public void setIndicatore7(double indicatore7) {
		this.indicatore7 = indicatore7;
	}

	public double getIndicatore8() {
		return indicatore8;
	}

	public void setIndicatore8(double indicatore8) {
		this.indicatore8 = indicatore8;
	}

	public double getIndicatore9() {
		return indicatore9;
	}

	public void setIndicatore9(double indicatore9) {
		this.indicatore9 = indicatore9;
	}

	public double getIndicatore10() {
		return indicatore10;
	}

	public void setIndicatore10(double indicatore10) {
		this.indicatore10 = indicatore10;
	}

	public long getIdReportSedi() {
		return idReportSedi;
	}

	public void setIdReportSedi(long idReportSedi) {
		this.idReportSedi = idReportSedi;
	}
	
}
