package it.tecnet.crs.web.dto;



import java.math.BigDecimal;
import java.util.Date;



public class VerbaleDto {
	
	//è l'id della tabella AU_ASS_CAMP_VERB
	private long idAssCv;


	private long idVerbale;
	
	private long idSessione;

	private long idCampagna;

	private Integer codiceSospensione;
	
	private Date dataFineIspezione;

	private Date dataInfasamento;

	private Date dataInserimentoNotifica;

	private Date dataInserimentoPosizione;

	private Date dataNotifica;

	private Date dataTrasmissioneLegale;

	private String destinatarioVW;

	private Integer esitoFlusso;

	private String indirizzoPecVW;

	private String motivoSospensione;

	private String tipoNotifica;

	private String azienda;

	private String indirizzo;

	private String partitaIva;

	private String codiceFiscale;

	private Object dataChiusuraVerbale;

	private String protocollo;

	private BigDecimal importo;
	
	private String sede;
	
	private String rischi;
	
	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}
	public long getIdVerbale() {
		return idVerbale;
	}

	public void setIdVerbale(long idVerbale) {
		this.idVerbale = idVerbale;
	}

	public long getIdCampagna() {
		return idCampagna;
	}

	public void setIdCampagna(long idCampagna) {
		this.idCampagna = idCampagna;
	}


	public String getDestinatarioVW() {
		return destinatarioVW;
	}

	public void setDestinatarioVW(String destinatarioVW) {
		this.destinatarioVW = destinatarioVW;
	}

	public Integer getEsitoFlusso() {
		return esitoFlusso;
	}

	public void setEsitoFlusso(Integer esitoFlusso) {
		this.esitoFlusso = esitoFlusso;
	}

	public String getIndirizzoPecVW() {
		return indirizzoPecVW;
	}

	public void setIndirizzoPecVW(String indirizzoPecVW) {
		this.indirizzoPecVW = indirizzoPecVW;
	}

	public String getMotivoSospensione() {
		return motivoSospensione;
	}

	public void setMotivoSospensione(String motivoSospensione) {
		this.motivoSospensione = motivoSospensione;
	}

	public String getTipoNotifica() {
		return tipoNotifica;
	}

	public void setTipoNotifica(String tipoNotifica) {
		this.tipoNotifica = tipoNotifica;
	}

	public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Object getDataChiusuraVerbale() {
		return dataChiusuraVerbale;
	}

	public void setDataChiusuraVerbale(Object dataChiusuraVerbale) {
		this.dataChiusuraVerbale = dataChiusuraVerbale;
	}

	public String getProtocollo() {
		return protocollo;
	}

	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getRischi() {
		return rischi;
	}

	public void setRischi(String rischi) {
		this.rischi = rischi;
	}

	public long getIdSessione() {
		return idSessione;
	}

	public void setIdSessione(long idSessione) {
		this.idSessione = idSessione;
	}

	public Date getDataFineIspezione() {
		return dataFineIspezione;
	}

	public void setDataFineIspezione(Date dataFineIspezione) {
		this.dataFineIspezione = dataFineIspezione;
	}

	public Date getDataInfasamento() {
		return dataInfasamento;
	}

	public void setDataInfasamento(Date dataInfasamento) {
		this.dataInfasamento = dataInfasamento;
	}

	public Date getDataInserimentoNotifica() {
		return dataInserimentoNotifica;
	}

	public void setDataInserimentoNotifica(Date dataInserimentoNotifica) {
		this.dataInserimentoNotifica = dataInserimentoNotifica;
	}

	public Date getDataInserimentoPosizione() {
		return dataInserimentoPosizione;
	}

	public void setDataInserimentoPosizione(Date dataInserimentoPosizione) {
		this.dataInserimentoPosizione = dataInserimentoPosizione;
	}

	public Date getDataNotifica() {
		return dataNotifica;
	}

	public void setDataNotifica(Date dataNotifica) {
		this.dataNotifica = dataNotifica;
	}

	public Date getDataTrasmissioneLegale() {
		return dataTrasmissioneLegale;
	}

	public void setDataTrasmissioneLegale(Date dataTrasmissioneLegale) {
		this.dataTrasmissioneLegale = dataTrasmissioneLegale;
	}

	public void setCodiceSospensione(Integer codiceSospensione) {
		this.codiceSospensione = codiceSospensione;
	}

	public Integer getCodiceSospensione() {
		return codiceSospensione;
	}

	
	public long getIdAssCv() {
		return idAssCv;
	}

	public void setIdAssCv(long idAssCv) {
		this.idAssCv = idAssCv;
	}

}