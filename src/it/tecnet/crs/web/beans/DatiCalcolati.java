package it.tecnet.crs.web.beans;

import it.tecnet.crs.jpa.model.AuSPratica;

public class DatiCalcolati {

	private AuSPratica pratica = new AuSPratica();

	public AuSPratica getPratica() {
		return pratica;
	}

	public void setPratica(AuSPratica pratica) {
		this.pratica = pratica;
	}

}
