package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AU_S_PRATICA_RIS")
public class AuSPraticaRis implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_PRATICA_RIS_GENERATOR", sequenceName = "ID_S_SESSIONI_RIS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_PRATICA_RIS_GENERATOR")
	@Column(name = "ID_S_PRATICA_RIS")
	private long idSPraticaRis;

	@Column(name = "ID_S_PRATICA")
	private long idSPratica;

	@Column(name = "ID_M_RISCHIO")
	private long idMRischio;

	@Column(name = "DATA_ATTRIBUZIONE")
	private Date dataAttribuzione;

	@Column(name = "ID_ESPR_RISCHIO")
	private long idEsprRischio;

	@Column(name = "IMPORTO")
	private Double importo;

	public long getIdSPraticaRis() {
		return idSPraticaRis;
	}

	public void setIdSPraticaRis(long idSPraticaRis) {
		this.idSPraticaRis = idSPraticaRis;
	}

	public long getIdSPratica() {
		return idSPratica;
	}

	public void setIdSPratica(long idSPratica) {
		this.idSPratica = idSPratica;
	}

	public long getIdMRischio() {
		return idMRischio;
	}

	public void setIdMRischio(long idMRischio) {
		this.idMRischio = idMRischio;
	}

	public Date getDataAttribuzione() {
		return dataAttribuzione;
	}

	public void setDataAttribuzione(Date dataAttribuzione) {
		this.dataAttribuzione = dataAttribuzione;
	}

	public long getIdEsprRischio() {
		return idEsprRischio;
	}

	public void setIdEsprRischio(long idEsprRischio) {
		this.idEsprRischio = idEsprRischio;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

}
