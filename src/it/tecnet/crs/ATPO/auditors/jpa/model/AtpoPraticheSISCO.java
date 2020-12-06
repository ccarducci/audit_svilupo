package it.tecnet.crs.ATPO.auditors.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ATPO_PRATICHE_SISCO") 
@NamedQueries( {
	@NamedQuery(name = AtpoPraticheSISCO.QUERY_SPRATICA_CISCO_BY_FASCICOLO, query = "SELECT t FROM AtpoPraticheSISCO t WHERE t.fascicolo = "
		+ AtpoPraticheSISCO.QUERY_PARAM_FASCICOLO) 
})
public class AtpoPraticheSISCO implements Serializable {

	public static final String QUERY_SPRATICA_CISCO_BY_FASCICOLO = "AtpoPraticheSISCO.findAllByFascicolo";
	public static final String QUERY_PARAM_FASCICOLO = ":fascicolo";

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATPO_PRATICHE_SISCO_GENERATOR", sequenceName = "ATPO_PRATICHE_SISCO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_PRATICHE_SISCO_GENERATOR")
	@Column(name = "ID_PRATICHE_SISCO")
	private long idPraticheSisco;

	@Column(name = "COD_SEDE")
	private String codSede;

	@Column(name = "FASCICOLO")
	private String fascicolo;

	@Column(name = "RICHIESTA")
	private String richiesta;

	@Column(name = "ESITO")
	private String esito;

	@Column(name = "AUTOTUTELA")
	private String autotutela;

	@Column(name = "PARTE")
	private String parte;

	@Column(name = "DATA_NOTIFICA")
	private Date dataNotifica;

	@Column(name = "DATA_APERTURA")
	private Date dataApertura;

	@Column(name = "DATA_COSTITUZIONE")
	private Date dataCostituzione;

	@Column(name = "DATA_UDIENZA")
	private Date dataUdienza;

	@Column(name = "PARERE_MEDICO")
	private String parereMedico;

	@Column(name = "T_DISSENSO")
	private Date tDissenso;

	@Column(name = "DISS_INPS")
	private Date dissInps;

	@Column(name = "PROVV")
	private Date provv;

	@Column(name = "NOT_PROVV")
	private Date notProvv;

	@Column(name = "COM")
	private Date com;

	@Column(name = "CAR")
	private Date car;

	@Column(name = "ESEC")
	private Date esec;

	@Column(name = "PR_RIC")
	private String prRic;

	@Column(name = "PR_BZZ")
	private String prBzz;

	@Column(name = "PR_DEF")
	private String prDef;

	public long getIdPraticheSisco() {
		return idPraticheSisco;
	}

	public void setIdPraticheSisco(long idPraticheSisco) {
		this.idPraticheSisco = idPraticheSisco;
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

	public String getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(String richiesta) {
		this.richiesta = richiesta;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getAutotutela() {
		return autotutela;
	}

	public void setAutotutela(String autotutela) {
		this.autotutela = autotutela;
	}

	public String getParte() {
		return parte;
	}

	public void setParte(String parte) {
		this.parte = parte;
	}

	public Date getDataNotifica() {
		return dataNotifica;
	}

	public void setDataNotifica(Date dataNotifica) {
		this.dataNotifica = dataNotifica;
	}

	public Date getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public Date getDataCostituzione() {
		return dataCostituzione;
	}

	public void setDataCostituzione(Date dataCostituzione) {
		this.dataCostituzione = dataCostituzione;
	}

	public Date getDataUdienza() {
		return dataUdienza;
	}

	public void setDataUdienza(Date dataUdienza) {
		this.dataUdienza = dataUdienza;
	}

	public String getParereMedico() {
		return parereMedico;
	}

	public void setParereMedico(String parereMedico) {
		this.parereMedico = parereMedico;
	}

	public Date getTDissenso() {
		return tDissenso;
	}

	public void setTDissenso(Date dissenso) {
		tDissenso = dissenso;
	}

	public Date getDissInps() {
		return dissInps;
	}

	public void setDissInps(Date dissInps) {
		this.dissInps = dissInps;
	}

	public Date getProvv() {
		return provv;
	}

	public void setProvv(Date provv) {
		this.provv = provv;
	}

	public Date getNotProvv() {
		return notProvv;
	}

	public void setNotProvv(Date notProvv) {
		this.notProvv = notProvv;
	}

	public Date getCom() {
		return com;
	}

	public void setCom(Date com) {
		this.com = com;
	}

	public Date getCar() {
		return car;
	}

	public void setCar(Date car) {
		this.car = car;
	}

	public Date getEsec() {
		return esec;
	}

	public void setEsec(Date esec) {
		this.esec = esec;
	}

	public String getPrRic() {
		return prRic;
	}

	public void setPrRic(String prRic) {
		this.prRic = prRic;
	}

	public String getPrBzz() {
		return prBzz;
	}

	public void setPrBzz(String prBzz) {
		this.prBzz = prBzz;
	}

	public String getPrDef() {
		return prDef;
	}

	public void setPrDef(String prDef) {
		this.prDef = prDef;
	}


}
