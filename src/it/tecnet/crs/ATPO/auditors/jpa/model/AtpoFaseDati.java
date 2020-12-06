package it.tecnet.crs.ATPO.auditors.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "ATPO_FASE_DATI")
@NamedQueries( { @NamedQuery(name = AtpoFaseDati.QUERY_FASEDATI_BY_FASCICOLO_AND_CODSEDE, 
		query = "SELECT t FROM AtpoFaseDati t WHERE trim(t.fascicolo) = :" + AtpoFaseDati.QUERY_PARAM_FASCICOLO  +  "  and trim(t.codSede) = :"
		+ AtpoFaseDati.QUERY_PARAM_CODSEDE
		) 
		}
)
public class AtpoFaseDati implements Serializable {


	private static final long serialVersionUID = 1L;

	public static final String QUERY_FASEDATI_BY_FASCICOLO_AND_CODSEDE = "AtpoFaseDati.findAllByIdsSessione";
	public static final String QUERY_PARAM_FASCICOLO = "fascicolo";
	public static final String QUERY_PARAM_CODSEDE = "codSede";

	@Id
	@SequenceGenerator(name = "ATPO_FASE_DATI_GENERATOR", sequenceName = "ATPO_FASE_DATI_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATPO_FASE_DATI_GENERATOR")
	@Column(name = "ID_FASE_DATI")
	private Long idfaseDati;

	@Column(name = "GIUDIZIO")
	private String giudizio;

	@Column(name = "IMPORTO_SPESE_LEGALI")
	private BigDecimal importoSpeseLegali;

	@Column(name = "IMPORTO_SPESE_CTU")
	private BigDecimal importoSpeseCTU;

	@Column(name = "IMPORTO_PRESTAZIONE_EROGATA")
	private BigDecimal importoPrestazioneErogata;

	@Column(name = "FASCICOLO")
	private String fascicolo;

	@Column(name = "PRESTAZIONE_ECONOMICA")
	private String prestazioneEconomica;

	@Column(name = "FASE_PRONTA")
	private String fasePronta;
	@Column(name = "COD_SEDE")
	private String codSede;
	
	public Long getIdfaseDati() {
		return idfaseDati;
	}

	public void setIdfaseDati(Long idfaseDati) {
		this.idfaseDati = idfaseDati;
	}

	public String getGiudizio() {
		return giudizio;
	}

	public void setGiudizio(String giudizio) {
		this.giudizio = giudizio;
	}

	public BigDecimal getImportoSpeseLegali() {
		return importoSpeseLegali;
	}

	public void setImportoSpeseLegali(BigDecimal bigDecimal) {
		this.importoSpeseLegali = bigDecimal;
	}

	public BigDecimal getImportoSpeseCTU() {
		return importoSpeseCTU;
	}

	public void setImportoSpeseCTU(BigDecimal importoSpeseCTU) {
		this.importoSpeseCTU = importoSpeseCTU;
	}

	public BigDecimal getImportoPrestazioneErogata() {
		return importoPrestazioneErogata;
	}

	public void setImportoPrestazioneErogata(
			BigDecimal importoPrestazioneErogata) {
		this.importoPrestazioneErogata = importoPrestazioneErogata;
	}

	public String getFascicolo() {
		return fascicolo;
	}

	public void setFascicolo(String fascicolo) {
		this.fascicolo = fascicolo;
	}

	public String getPrestazioneEconomica() {
		return prestazioneEconomica;
	}

	public void setPrestazioneEconomica(String prestazioneEconomica) {
		this.prestazioneEconomica = prestazioneEconomica;
	}

	public void setFasePronta(String fasePronta) {
		this.fasePronta = fasePronta;
	}

	public String getFasePronta() {
		return fasePronta;
	}

	public String getCodSede() {
		return codSede;
	}

	public void setCodSede(String codSede) {
		this.codSede = codSede;
	}

}
