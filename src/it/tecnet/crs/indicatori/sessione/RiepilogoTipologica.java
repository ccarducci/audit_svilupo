package it.tecnet.crs.indicatori.sessione;

public class RiepilogoTipologica {
	// COMMON
	private int num;
	private String codifica;
	private String codifica2;
	private int ord;
	// TESITO
	private int numPrestazioni;
	private Double importoPrestazione;
	private Double speseLegali;
	private Double speseCtu;

	private String nc;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCodifica() {
		return codifica;
	}

	public void setCodifica(String codifica) {
		this.codifica = codifica;
	}

	public int getNumPrestazioni() {
		return numPrestazioni;
	}

	public void setNumPrestazioni(int numPrestazioni) {
		this.numPrestazioni = numPrestazioni;
	}

	public Double getImportoPrestazione() {
		return importoPrestazione;
	}

	public void setImportoPrestazione(Double importoPrestazione) {
		this.importoPrestazione = importoPrestazione;
	}

	public Double getSpeseLegali() {
		return speseLegali;
	}

	public void setSpeseLegali(Double speseLegali) {
		this.speseLegali = speseLegali;
	}

	public String getCodifica2() {
		return codifica2;
	}

	public void setCodifica2(String codifica2) {
		this.codifica2 = codifica2;
	}

	public String getNc() {
		return nc;
	}

	public void setNc(String nc) {
		this.nc = nc;
	}

	public Double getSpeseCtu() {
		return speseCtu;
	}

	public void setSpeseCtu(Double speseCtu) {
		this.speseCtu = speseCtu;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

}
