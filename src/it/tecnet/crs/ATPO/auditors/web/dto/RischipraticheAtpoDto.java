package it.tecnet.crs.ATPO.auditors.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RischipraticheAtpoDto {
		
	private long idRischio;
	private String codiceRischio;
	private String descrizione; 
	private String codiceEsprRischio;
	private String descrizioneEpsrRischio;
	private Date dataAttribuzione;
	private BigDecimal importo;
	public String getCodiceRischio() {
		return codiceRischio;
	}
	public void setCodiceRischio(String codiceRischio) {
		this.codiceRischio = codiceRischio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCodiceEsprRischio() {
		return codiceEsprRischio;
	}
	public void setCodiceEsprRischio(String codiceEsprRischio) {
		this.codiceEsprRischio = codiceEsprRischio;
	}
	public String getDescrizioneEpsrRischio() {
		return descrizioneEpsrRischio;
	}
	public void setDescrizioneEpsrRischio(String descrizioneEpsrRischio) {
		this.descrizioneEpsrRischio = descrizioneEpsrRischio;
	}
	public Date getDataAttribuzione() {
		return dataAttribuzione;
	}
	public void setDataAttribuzione(Date dataAttribuzione) {
		this.dataAttribuzione = dataAttribuzione;
	}
	
	public void setIdRischio(long idRischio) {
		this.idRischio = idRischio;
	}
	public long getIdRischio() {
		return idRischio;
	}
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	public BigDecimal getImporto() {
		return importo;
	}
	
	
	
		
}