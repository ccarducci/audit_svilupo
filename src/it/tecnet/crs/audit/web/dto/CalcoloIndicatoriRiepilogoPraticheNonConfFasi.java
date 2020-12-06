package it.tecnet.crs.audit.web.dto;

public class CalcoloIndicatoriRiepilogoPraticheNonConfFasi {
	private Long idMNonConf;
	private Long idFase;
	private Double valorePesato;

	public Long getIdMNonConf() {
		return idMNonConf;
	}

	public void setIdMNonConf(Long idMNonConf) {
		this.idMNonConf = idMNonConf;
	}

	public Long getIdFase() {
		return idFase;
	}

	public void setIdFase(Long idFase) {
		this.idFase = idFase;
	}

	public Double getValorePesato() {
		return valorePesato;
	}

	public void setValorePesato(Double valorePesato) {
		this.valorePesato = valorePesato;
	}

}
