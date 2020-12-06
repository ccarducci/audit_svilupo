package it.tecnet.crs.ATPO.auditors.web.dto;

public class AuTplTipologicheAtpoDto {
	private long idTplTopologiche;
	private String audit;
	private String codifica;
	private String statoProcesso;
	private String descrizione;
	private String tipo;
	
	public AuTplTipologicheAtpoDto(long id, String c, String t, String d){
		super();
		this.idTplTopologiche=id;
		this.codifica=c;
		this.setTipo(t);
		this.descrizione=d;
		
	}
	
	public long getIdTplTopologiche() {
		return idTplTopologiche;
	}
	public void setIdTplTopologiche(long idTplTopologiche) {
		this.idTplTopologiche = idTplTopologiche;
	}
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
	}
	public String getCodifica() {
		return codifica;
	}
	public void setCodifica(String codifica) {
		this.codifica = codifica;
	}
	public String getStatoProcesso() {
		return statoProcesso;
	}
	public void setStatoProcesso(String statoProcesso) {
		this.statoProcesso = statoProcesso;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}
