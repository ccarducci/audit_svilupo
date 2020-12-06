package it.tecnet.crs.web.beans;


public class TableDatiGeneraliIndicatoriPaginator {
	
	
	private long idSessione;
	public String mediaNotifica;
	private String mediaDefinizione;
	private int idMediaFase;
	private String mediaQuestionario;
	
	public String[] aaData;
		
	
	public String[] getAaData() {
		return aaData;
	}
	public void setAaData(String[] aaData) {
		this.aaData = aaData;
	}
	public int getIdMediaFase() {
		return idMediaFase;
	}
	public void setIdMediaFase(int idMediaFase) {
		this.idMediaFase = idMediaFase;
	}
	public long getIdSessione() {
		return idSessione;
	}
	public void setIdSessione(long l) {
		this.idSessione = l;
	}
	public String getMediaNotifica() {
		return mediaNotifica;
	}
	public void setMediaNotifica(String mediaNotifica) {
		this.mediaNotifica = mediaNotifica;
	}
	public String getMediaDefinizione() {
		return mediaDefinizione;
	}
	public void setMediaDefinizione(String mediaDefinizione) {
		this.mediaDefinizione = mediaDefinizione;
	}
	public String getMediaQuestionario() {
		return mediaQuestionario;
	}
	public void setMediaQuestionario(String mediaQuestionario) {
		this.mediaQuestionario = mediaQuestionario;
	}

}
