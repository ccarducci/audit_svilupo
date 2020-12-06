package it.tecnet.crs.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PraticaConRischioDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public PraticaConRischioDto(){
		super();
	}
	
	public long idVerbale;
	private long idSessione;
	private String protocollo;
	private String azienda;
	private String partitaIva;
	private Object dataFineIspezione;
	private Object dataNotifica;
	private String tipoNotifica;
	private BigDecimal importo;
	
	private String rischi;
	
	
	
	
	public long getIdVerbale() {
		return idVerbale;
	}

	public void setIdVerbale(long idVerbale) {
		this.idVerbale = idVerbale;
	}

	public Object getDataNotifica() {
		return dataNotifica;
	}

	public void setDataNotifica(Object dataNotifica) {
		this.dataNotifica = dataNotifica;
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

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
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

	public void setDataFineIspezione(Object dataFineIspezione) {
		this.dataFineIspezione = dataFineIspezione;
	}

	public Object getDataFineIspezione() {
		return dataFineIspezione;
	}

}
