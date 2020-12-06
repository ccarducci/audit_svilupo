package it.tecnet.crs.ATPO.auditors.web.dto;

public class AtpoTipologicheDto {
	
	private long id;
	
	private String valore;
	
	public AtpoTipologicheDto(long id, String valore) {
		super();
		this.id = id;
		this.valore = valore;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getValore() {
		return valore;
	}
	public void setValore(String valore) {
		this.valore = valore;
	}
		
}
