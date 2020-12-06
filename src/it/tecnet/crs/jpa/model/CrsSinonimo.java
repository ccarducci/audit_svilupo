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
@Table(name = "CRS_SINONIMO")
@NamedQueries( { @NamedQuery(name = CrsSinonimo.QUERY_SINONIMO_AND_DESCR_SINONIMO_LIKE, query = "SELECT t FROM CrsSinonimo t "
		+ " WHERE ( "
		+ "			lower(t.sinonimo) like CONCAT(lower(:sinonimo),'%') "
		+ "				or "
		+ "			lower(t.descrSinonimo) like CONCAT(lower(:descrSinonimo),'%')  "
		+ ")"),
		
		 @NamedQuery(name = CrsSinonimo.QUERY_SINONIMO_BY_IDGLOSSARIO_, query = "SELECT t FROM CrsSinonimo t WHERE t.idGlossario = :idGlossario" )})
public class CrsSinonimo implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_SINONIMO_AND_DESCR_SINONIMO_LIKE = "CrsSinonimo.findByLikeBySinonimoAndDescrSinonimo";
	public static final String QUERY_SINONIMO_BY_IDGLOSSARIO_= "CrsSinonimo.findByByIdGlossario";
	public static final String PARAM_SINONIMO = "sinonimo";
	public static final String PARAM_DESCRSINONIMO = "descrSinonimo";
	public static final String PARAM_IDGLOSSARIO = "idGlossario";
	
	@Id
	@SequenceGenerator(name = "CRS_SINONIMO_GENERATOR", sequenceName = "ID_SINONIMO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRS_SINONIMO_GENERATOR")
	@Column(name = "ID_SINONIMO")
	private long idSinonimo;

	@Column(name = "ID_GLOSSARIO")
	private long idGlossario;

	@Column(name = "SINONIMO")
	private String sinonimo;

	@Column(name = "DESCR_SINONIMO")
	private String descrSinonimo;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	public long getIdSinonimo() {
		return idSinonimo;
	}

	public void setIdSinonimo(long idSinonimo) {
		this.idSinonimo = idSinonimo;
	}

	public long getIdGlossario() {
		return idGlossario;
	}

	public void setIdGlossario(long idGlossario) {
		this.idGlossario = idGlossario;
	}

	public String getSinonimo() {
		return sinonimo;
	}

	public void setSinonimo(String sinonimo) {
		this.sinonimo = sinonimo;
	}

	public String getDescrSinonimo() {
		return descrSinonimo;
	}

	public void setDescrSinonimo(String descrSinonimo) {
		this.descrSinonimo = descrSinonimo;
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
		StringBuilder sb = new StringBuilder("CrsSinonimo[ ");
		sb.append("idSinonimo: " + idSinonimo);
		sb.append(", ");
		sb.append("idGlossario: " + idGlossario);
		sb.append(", ");
		sb.append("sinonimo: " + sinonimo);
		sb.append(", ");
		sb.append("descrSinonimo: " + descrSinonimo);
		sb.append(" ]");
		return super.toString();
	}

}
