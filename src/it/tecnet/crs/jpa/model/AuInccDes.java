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
@Table(name = "AU_INCC_DES")
@NamedQueries( { @NamedQuery(name = AuInccDes.QUERY_INCCDES_ALL, query = "SELECT e FROM AuInccDes e order by e.valoreIncc DESC ") })
public class AuInccDes implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_INCCDES_ALL = "AuInccDes.findAll";

	@Id
	@SequenceGenerator(name = "AU_INCC_DES_GENERATOR", sequenceName = "ID_INCC_DES_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_INCC_DES_GENERATOR")
	@Column(name = "ID_INCC_DES")
	private long idInccDes;

	@Column(name = "VALORE_INCC")
	private Double valoreIncc;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	public long getIdInccDes() {
		return idInccDes;
	}

	public void setIdInccDes(long idInccDes) {
		this.idInccDes = idInccDes;
	}

	public Double getValoreIncc() {
		return valoreIncc;
	}

	public void setValoreIncc(Double valoreIncc) {
		this.valoreIncc = valoreIncc;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
