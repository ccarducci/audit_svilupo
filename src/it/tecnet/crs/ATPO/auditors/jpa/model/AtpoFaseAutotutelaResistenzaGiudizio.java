package it.tecnet.crs.ATPO.auditors.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ATPO_FASE_AUTOTUTELA_RESISTENZA_GIUDIZIO")
@NamedQueries( { @NamedQuery(name = AtpoFaseAutotutelaResistenzaGiudizio.QUERY_FASE_AUTOTUTELA_RES_GIUD_BY_IDFASEDATI, 
		query = "SELECT t FROM AtpoFaseAutotutelaResistenzaGiudizio t WHERE t.idFaseDati = :"
		+ AtpoFaseAutotutelaResistenzaGiudizio.QUERY_PARAM_IDFASEDATI) })
public class AtpoFaseAutotutelaResistenzaGiudizio {

	public static final String QUERY_FASE_AUTOTUTELA_RES_GIUD_BY_IDFASEDATI = "AtpoPraticheSISCO.findAllByIdFaseDati";
	public static final String QUERY_PARAM_IDFASEDATI = "idFaseDati";

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATPO_FASE_AUTOTUTELA_RESISTENZA_GIUDIZIO_GENERATOR", sequenceName = "ATPO_FASE_AUTOTUTELA_RESISTENZA_GIUDIZIO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_FASE_AUTOTUTELA_RESISTENZA_GIUDIZIO_GENERATOR")
	@Column(name = "id_autotutela")
	private Long idAutotutela;

	@Column(name = "parere")
	private String parere;

	@Column(name = "termini_prima_udienza")
	private String terminiPrimaUdienza;

	@Column(name = "id_fase_dati")
	private Long idFaseDati;

	@Column(name = "fase_pronta")
	private String fasePronta;
	
	public Long getIdAutotutela() {
		return idAutotutela;
	}

	public void setIdAutotutela(Long idAutotutela) {
		this.idAutotutela = idAutotutela;
	}

	public String getParere() {
		return parere;
	}

	public void setParere(String parere) {
		this.parere = parere;
	}

	public String getTerminiPrimaUdienza() {
		return terminiPrimaUdienza;
	}

	public void setTerminiPrimaUdienza(String terminiPrimaUdienza) {
		this.terminiPrimaUdienza = terminiPrimaUdienza;
	}

	public Long getIdFaseDati() {
		return idFaseDati;
	}

	public void setIdFaseDati(Long idFaseDati) {
		this.idFaseDati = idFaseDati;
	}

	public void setFasePronta(String fasePronta) {
		this.fasePronta = fasePronta;
	}

	public String getFasePronta() {
		return fasePronta;
	}

}
