package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "CRS_AMBITO")
@NamedQueries( {
		@NamedQuery(name = CrsAmbito.QUERY_AMBIO_BY_IDENTITA, query = "SELECT e FROM CrsAmbito e WHERE e.idEntita = :idEntita"),
		@NamedQuery(name = CrsAmbito.QUERY_AMBIO_BY_IDGLOSSARIO, query = "SELECT e FROM CrsAmbito e WHERE e.idGlossario = :idGlossario") })
public class CrsAmbito implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_AMBIO_BY_IDENTITA = "CrsAmbito.findAllByIdEntita";
	public static final String QUERY_AMBIO_BY_IDGLOSSARIO = "CrsAmbito.findAllByIdGlossario";
	public static final String PARAM_IDGLOSSARIO = "idGlossario";
	public static final String PARAM_IDENTITA = "idEntita";

	@Id
	@SequenceGenerator(name = "CRS_AMBITO_GENERATOR", sequenceName = "ID_AMBITO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRS_AMBITO_GENERATOR")
	@Column(name = "ID_AMBITO")
	private long idAmbito;

	@Column(name = "AMBITO")
	private String ambito;

	@Column(name = "DESCR_AMBITO")
	private String descrAmbito;

	@Column(name = "ID_GLOSSARIO")
	private long idGlossario;

	@Column(name = "ID_ENTITA")
	private long idEntita;

	@Column(name = "TIPO_AMBITO")
	private String tipoAmbito;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	public long getIdAmbito() {
		return idAmbito;
	}

	public void setIdAmbito(long idAmbito) {
		this.idAmbito = idAmbito;
	}

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public String getDescrAmbito() {
		return descrAmbito;
	}

	public void setDescrAmbito(String descrAmbito) {
		this.descrAmbito = descrAmbito;
	}

	public long getIdGlossario() {
		return idGlossario;
	}

	public void setIdGlossario(long idGlossario) {
		this.idGlossario = idGlossario;
	}

	public long getIdEntita() {
		return idEntita;
	}

	public void setIdEntita(long idEntita) {
		this.idEntita = idEntita;
	}

	public String getTipoAmbito() {
		return tipoAmbito;
	}

	public void setTipoAmbito(String tipoAmbito) {
		this.tipoAmbito = tipoAmbito;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

}
