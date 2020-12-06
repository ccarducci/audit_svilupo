package it.tecnet.crs.ATPO.auditors.jpa.model;

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
@Table(name = "ATPO_FASE_ACQUISIZIONE_ISTANZA")
@NamedQueries( { @NamedQuery(name = AtpoFaseAcquisizioneIstanza.QUERY_FAEACQUISIZIONEISTA_BY_IDFASEDATI, 
		query = "SELECT t FROM AtpoFaseAcquisizioneIstanza t WHERE t.idFaseDati = :"
		+ AtpoFaseAcquisizioneIstanza.QUERY_PARAM_IDFASEDATI) })
public class AtpoFaseAcquisizioneIstanza {

	public static final String QUERY_FAEACQUISIZIONEISTA_BY_IDFASEDATI = "AtpoFaseAcquisizioneIstanza.findAllByIdFaseDati";
	public static final String QUERY_PARAM_IDFASEDATI = "idFaseDati";
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATPO_FASE_ACQUISIZIONE_ISTANZA_GENERATOR", sequenceName = "ATPO_FASE_ACQUISIZIONE_ISTANZA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_FASE_ACQUISIZIONE_ISTANZA_GENERATOR")
	@Column(name = "id_fase_acquisizione_istanza")
	private Long idFaseAcquisizioneIstanza;

	@Column(name = "data_notifica")
	private Date dataNotifica;

	@Column(name = "data_protocollo")
	private Date dataProtocollo;

	@Column(name = "voce_titolario")
	private String voceTitolarioErrata;

	@Column(name = "intervallo_notifica_protocollo")
	private long intervalloNotificaProtocollo;

	@Column(name = "protocollo_con_img")
	private String protocolloConImg;

	@Column(name = "data_acquisizione_SISCO")
	private Date dataAcquisizioneSISCO;

	@Column(name = "intervallo_notifica_SISCO")
	private long intervalloNotificaSISCO;

	@Column(name = "id_fase_dati")
	private long idFaseDati;
	
	@Column(name = "fase_pronta")
	private String fasePronta;

	public Long getIdFaseAcquisizioneIstanza() {
		return idFaseAcquisizioneIstanza;
	}

	public void setIdFaseAcquisizioneIstanza(Long idFaseAcquisizioneIstanza) {
		this.idFaseAcquisizioneIstanza = idFaseAcquisizioneIstanza;
	}

	public Date getDataNotifica() {
		return dataNotifica;
	}

	public void setDataNotifica(Date dataNotifica) {
		this.dataNotifica = dataNotifica;
	}

	public Date getDataProtocollo() {
		return dataProtocollo;
	}

	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	public String getVoceTitolarioErrata() {
		return voceTitolarioErrata;
	}

	public void setVoceTitolarioErrata(String voceTitolarioErrata) {
		this.voceTitolarioErrata = voceTitolarioErrata;
	}

	public long getIntervalloNotificaProtocollo() {
		return intervalloNotificaProtocollo;
	}

	public void setIntervalloNotificaProtocollo(long intervalloNotificaProtocollo) {
		this.intervalloNotificaProtocollo = intervalloNotificaProtocollo;
	}

	public String getProtocolloConImg() {
		return protocolloConImg;
	}

	public void setProtocolloConImg(String protocolloConImg) {
		this.protocolloConImg = protocolloConImg;
	}

	public Date getDataAcquisizioneSISCO() {
		return dataAcquisizioneSISCO;
	}

	public void setDataAcquisizioneSISCO(Date dataAcquisizioneSISCO) {
		this.dataAcquisizioneSISCO = dataAcquisizioneSISCO;
	}

	public long getIntervalloNotificaSISCO() {
		return intervalloNotificaSISCO;
	}

	public void setIntervalloNotificaSISCO(long intervalloNotificaSISCO) {
		this.intervalloNotificaSISCO = intervalloNotificaSISCO;
	}

	public long getIdFaseDati() {
		return idFaseDati;
	}

	public void setIdFaseDati(long idFaseDati) {
		this.idFaseDati = idFaseDati;
	}

	public void setFasePronta(String fasePronta) {
		this.fasePronta = fasePronta;
	}

	public String getFasePronta() {
		return fasePronta;
	}

}
