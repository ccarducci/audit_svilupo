package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_QUESTIONARIO")
public class AuQuestionario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="AU_QUESTIONARIO_GENERATOR", sequenceName="ID_QUESTIONARIO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_QUESTIONARIO_GENERATOR")
	@Column(name="ID_QUESTIONARIO")
	private long idQuestionario;

	@Column(name="id_audit")
	private Long idAudit;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;
	
	@Column(name="DESCRIZIONE")
	private String descrizione;

	public AuQuestionario() {
		super();
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



	public long getIdQuestionario() {
		return this.idQuestionario;
	}

	public void setIdQuestionario(long idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	public Long getIdAudit() {
		return this.idAudit;
	}

	public void setIdAudit(Long idAudit) {
		this.idAudit = idAudit;
	}

	

}
