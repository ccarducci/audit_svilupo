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
@Table(name = "CRS_GLOSSARIO")
@NamedQueries( { @NamedQuery(name = CrsGlossario.QUERY_PAROLA_AND_DESCR_PAROLA_LIKE, query = "SELECT t FROM CrsGlossario t "
		+ " WHERE ( lower(t.parola) like CONCAT(lower(:parola),'%') "
		+ "         	or "
		+ "		  lower(t.descrParola) like CONCAT(lower(:descrParola),'%') "
		+ " )") })
public class CrsGlossario implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_PAROLA_AND_DESCR_PAROLA_LIKE = "CrsGlossario.findByLikeParolaAndDescrParola";
	public static final String PARAM_PAROLA = "parola";
	public static final String PARAM_DESCRPAROLA = "descrParola";

	@Id
	@SequenceGenerator(name = "CRS_GLOSSARIO_GENERATOR", sequenceName = "ID_GLOSSARIO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRS_GLOSSARIO_GENERATOR")
	@Column(name = "ID_GLOSSARIO")
	private long idGlossario;

	@Column(name = "PAROLA", length = 100)
	private String parola;

	@Column(name = "DESCR_PAROLA", length = 1000)
	private String descrParola;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	public long getIdGlossario() {
		return idGlossario;
	}

	public void setIdGlossario(long idGlossario) {
		this.idGlossario = idGlossario;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getDescrParola() {
		return descrParola;
	}

	public void setDescrParola(String descrParola) {
		this.descrParola = descrParola;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("CrsGlossario[ ");
		sb.append("idGlossario: " + idGlossario);
		sb.append(", ");
		sb.append("parola: " + parola);
		sb.append(", ");
		sb.append("descrParola: " + descrParola);
		sb.append(" ]");
		return super.toString();
	}

}
