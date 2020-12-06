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
@Table(name = "CRS_ENTITA")
@NamedQueries( { @NamedQuery(name = CrsEntita.QUERY_ENTITA_AND_DESCR_ENTITA_LIKE, query = "SELECT t FROM CrsEntita t "
		+ " WHERE ( "
		+ "			lower(t.entita) like CONCAT(lower(:entita),'%') "
		+ "				or "
		+ "			lower(t.descrEntita) like CONCAT(lower(:descrEntita),'%')  "
		+ ")") })
public class CrsEntita implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_ENTITA_AND_DESCR_ENTITA_LIKE = "CrsEntita.findByLikeByEntitaAndDescrEntita";
	public static final String PARAM_ENTITA = "entita";
	public static final String PARAM_DESCRENTITA = "descrEntita";

	@Id
	@SequenceGenerator(name = "CRS_ENTITA_GENERATOR", sequenceName = "ID_ENTITA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRS_ENTITA_GENERATOR")
	@Column(name = "ID_ENTITA")
	private long idEntita;

	@Column(name = "ENTITA")
	private String entita;

	@Column(name = "DESCR_ENTITA")
	private String descrEntita;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "TIPO")
	private String tipo;

	public long getIdEntita() {
		return idEntita;
	}

	public void setIdEntita(long idEntita) {
		this.idEntita = idEntita;
	}

	public String getEntita() {
		return entita;
	}

	public void setEntita(String entita) {
		this.entita = entita;
	}

	public String getDescrEntita() {
		return descrEntita;
	}

	public void setDescrEntita(String descrEntita) {
		this.descrEntita = descrEntita;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("CrsEntita[ ");
		sb.append("idEntita: " + idEntita);
		sb.append(", ");
		sb.append("entita: " + entita);
		sb.append(", ");
		sb.append("descrEntita: " + descrEntita);
		sb.append(" ]");
		return super.toString();
	}

}
