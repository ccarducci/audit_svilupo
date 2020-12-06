package it.tecnet.crs.componenti.web.bean;


public class CircolariInps extends TipoNormativaCommon{
	
	private long idCircolariInps;
	
	private String direzioneEmittente1;
	
	private String direzioneEmittente2;
	
	private String direzioneEmittente3;
	
	private String direzioneEmittente4;
	
	private String direzioneEmittente5;
	
	private String direzioneEmittente6;
	
	private String sommario;
	
	private Integer anno;

	
	
	
	public long getIdCircolariInps() {
		return idCircolariInps;
	}

	public void setIdCircolariInps(long idCircolariInps) {
		this.idCircolariInps = idCircolariInps;
	}

	public String getDirezioneEmittente1() {
		return direzioneEmittente1;
	}

	public void setDirezioneEmittente1(String direzioneEmittente1) {
		this.direzioneEmittente1 = direzioneEmittente1;
	}

	public String getDirezioneEmittente2() {
		return direzioneEmittente2;
	}

	public void setDirezioneEmittente2(String direzioneEmittente2) {
		this.direzioneEmittente2 = direzioneEmittente2;
	}

	public String getDirezioneEmittente3() {
		return direzioneEmittente3;
	}

	public void setDirezioneEmittente3(String direzioneEmittente3) {
		this.direzioneEmittente3 = direzioneEmittente3;
	}

	public String getDirezioneEmittente4() {
		return direzioneEmittente4;
	}

	public void setDirezioneEmittente4(String direzioneEmittente4) {
		this.direzioneEmittente4 = direzioneEmittente4;
	}

	public String getDirezioneEmittente5() {
		return direzioneEmittente5;
	}

	public void setDirezioneEmittente5(String direzioneEmittente5) {
		this.direzioneEmittente5 = direzioneEmittente5;
	}

	public String getDirezioneEmittente6() {
		return direzioneEmittente6;
	}

	public void setDirezioneEmittente6(String direzioneEmittente6) {
		this.direzioneEmittente6 = direzioneEmittente6;
	}

	public void setSommario(String sommario) {
		this.sommario = sommario;
	}

	public String getSommario() {
		return sommario;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getAnno() {
		return anno;
	}


}
