package it.tecnet.crs.web.dto;

public class AuTplTipologicheDto {

	private long idTplTopologiche;
	private String audit;
	private String codifica;
	private String statoProcesso;
	
	public AuTplTipologicheDto(long id, String a, String c, String sp){
		super();
		this.idTplTopologiche=id;
		this.audit=a;
		this.codifica=c;
		this.statoProcesso=sp;
		
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
	
}
