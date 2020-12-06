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
@Table(name = "AU_S_PRATICA_VARCOMP")
@NamedQueries( { @NamedQuery(name = AuSPraticaVarcomp.QUERY_SPRATICAVARCOMP_BY_IDSPRATICA, 
		query = "SELECT e FROM AuSPraticaVarcomp e WHERE e.idSPratica = :idSPratica") })
public class AuSPraticaVarcomp implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String QUERY_SPRATICAVARCOMP_BY_IDSPRATICA = "AuSPraticaVarcomp.findAllByIdsSessione";
	public static final String DELETE_SPRATICAVARCOMP_BY_IDSSSESSIONE = "AuSPraticaVarcomp.deleteByIdsSessione";

	@Id
	@SequenceGenerator(name = "AU_S_PRATICA_VARCOMP_GENERATOR", sequenceName = "ID_S_SESSIONI_RIS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_PRATICA_VARCOMP_GENERATOR")
	@Column(name = "ID_S_PRATICA_VARCOMP")
	private long idSPraticaVarcomp;

	@Column(name = "ID_S_PRATICA")
	private long idSPratica;

	@Column(name = "ID_NONCONF")
	private long idNonConf;

	@Column(name = "DATA_ATTRIBUZIONE")
	private Date DataAttribuzione;

	@Column(name = "VAR_COMP", length = 100)
	private String varComp;

	public long getIdSPraticaVarcomp() {
		return idSPraticaVarcomp;
	}

	public void setIdSPraticaVarcomp(long idSPraticaVarcomp) {
		this.idSPraticaVarcomp = idSPraticaVarcomp;
	}

	public long getIdSPratica() {
		return idSPratica;
	}

	public void setIdSPratica(long idSPratica) {
		this.idSPratica = idSPratica;
	}

	public long getIdNonConf() {
		return idNonConf;
	}

	public void setIdNonConf(long idNonConf) {
		this.idNonConf = idNonConf;
	}

	public Date getDataAttribuzione() {
		return DataAttribuzione;
	}

	public void setDataAttribuzione(Date dataAttribuzione) {
		DataAttribuzione = dataAttribuzione;
	}

	public String getVarComp() {
		return varComp;
	}

	public void setVarComp(String varComp) {
		this.varComp = varComp;
	}

}
