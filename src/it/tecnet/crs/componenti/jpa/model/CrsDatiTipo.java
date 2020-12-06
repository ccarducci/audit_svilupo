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
@Table(name="CRS_DATI_TIPO")
public class CrsDatiTipo implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_DATI_TIPO_GENERATOR", sequenceName="ID_DATITIPO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_DATI_TIPO_GENERATOR")
	@Column(name="ID_DATI_TIPO")
	private long idDatiTipo;
	
	@Column(name="ID_DESCRIZIONE_TIPO")
	private long idDescrizioneTipo;

	private Date date1;

	private Date date2;

	private String text1;

	private String text2;

	private String text3;

	private String text4;

	private String text5;

	private String text6;

	private String text7;

	private String text8;

	private String text9;

	private String text10;

	private Double number1;

	private Double number2;

	private Double number3;

	private Double number4;

	private Double number5;

	private Double number6;

	private Double number7;

	private Double number8;

	private Double number9;

	private Double number10;

	

	private static final long serialVersionUID = 1L;

	public CrsDatiTipo() {
		super();
	}

	public long getIdDatiTipo() {
		return this.idDatiTipo;
	}

	public void setIdDatiTipo(long idDatiTipo) {
		this.idDatiTipo = idDatiTipo;
	}

	public Date getDate1() {
		return this.date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return this.date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public String getText1() {
		return this.text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return this.text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getText3() {
		return this.text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

	public String getText4() {
		return this.text4;
	}

	public void setText4(String text4) {
		this.text4 = text4;
	}

	public String getText5() {
		return this.text5;
	}

	public void setText5(String text5) {
		this.text5 = text5;
	}

	public String getText6() {
		return this.text6;
	}

	public void setText6(String text6) {
		this.text6 = text6;
	}

	public String getText7() {
		return this.text7;
	}

	public void setText7(String text7) {
		this.text7 = text7;
	}

	public String getText8() {
		return this.text8;
	}

	public void setText8(String text8) {
		this.text8 = text8;
	}

	public String getText9() {
		return this.text9;
	}

	public void setText9(String text9) {
		this.text9 = text9;
	}

	public String getText10() {
		return this.text10;
	}

	public void setText10(String text10) {
		this.text10 = text10;
	}

	public void setIdDescrizioneTipo(long idDescrizioneTipo) {
		this.idDescrizioneTipo = idDescrizioneTipo;
	}

	public long getIdDescrizioneTipo() {
		return idDescrizioneTipo;
	}

	public Double getNumber1() {
		return number1;
	}

	public void setNumber1(Double number1) {
		this.number1 = number1;
	}

	public Double getNumber2() {
		return number2;
	}

	public void setNumber2(Double number2) {
		this.number2 = number2;
	}

	public Double getNumber3() {
		return number3;
	}

	public void setNumber3(Double number3) {
		this.number3 = number3;
	}

	public Double getNumber4() {
		return number4;
	}

	public void setNumber4(Double number4) {
		this.number4 = number4;
	}

	public Double getNumber5() {
		return number5;
	}

	public void setNumber5(Double number5) {
		this.number5 = number5;
	}

	public Double getNumber6() {
		return number6;
	}

	public void setNumber6(Double number6) {
		this.number6 = number6;
	}

	public Double getNumber7() {
		return number7;
	}

	public void setNumber7(Double number7) {
		this.number7 = number7;
	}

	public Double getNumber8() {
		return number8;
	}

	public void setNumber8(Double number8) {
		this.number8 = number8;
	}

	public Double getNumber9() {
		return number9;
	}

	public void setNumber9(Double number9) {
		this.number9 = number9;
	}

	public Double getNumber10() {
		return number10;
	}

	public void setNumber10(Double number10) {
		this.number10 = number10;
	}


}
