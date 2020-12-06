package it.tecnet.crs.ATPO.auditors.web.beans;

import it.tecnet.crs.ATPO.auditors.web.dto.AtpoAuMContestoDto;

public class ModelAuditorsAccessiDatiContestoATPO {
	
	private AtpoAuMContestoDto c = new AtpoAuMContestoDto();
	private int numeroFavorevole;
	private int numeroParzFavorevole;
	private int sfavorevole;
	private int dissenso;
	

	public int getNumeroFavorevole() {
		return numeroFavorevole;
	}

	public void setNumeroFavorevole(int numeroFavorevole) {
		this.numeroFavorevole = numeroFavorevole;
	}

	public int getNumeroParzFavorevole() {
		return numeroParzFavorevole;
	}

	public void setNumeroParzFavorevole(int numeroParzFavorevole) {
		this.numeroParzFavorevole = numeroParzFavorevole;
	}

	public int getSfavorevole() {
		return sfavorevole;
	}

	public void setSfavorevole(int sfavorevole) {
		this.sfavorevole = sfavorevole;
	}

	public int getDissenso() {
		return dissenso;
	}

	public void setDissenso(int dissenso) {
		this.dissenso = dissenso;
	}

	public void setC(AtpoAuMContestoDto c) {
		this.c = c;
	}

	public AtpoAuMContestoDto getC() {
		return c;
	}

}
