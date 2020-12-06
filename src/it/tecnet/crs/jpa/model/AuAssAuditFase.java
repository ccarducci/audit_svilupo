package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AU_ASS_AUDIT_FASE")
public class AuAssAuditFase implements Serializable {
	@Id
	@SequenceGenerator(name="AU_ASS_AUDIT_FASE_GENERATOR", sequenceName="ID_ASSAUDITFASE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_ASS_AUDIT_FASE_GENERATOR")
	@Column(name="ID_AF")
	private long idAf;

	
	@Column(name="ID_AUDIT")
	private Long idAudit;


	public Long getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Long idAudit) {
		this.idAudit = idAudit;
	}

	public Long getIdSottoprocesso() {
		return idSottoprocesso;
	}

	public void setIdSottoprocesso(Long idSottoprocesso) {
		this.idSottoprocesso = idSottoprocesso;
	}

	@Column(name="ID_SOTTOPROCESSO")
	private Long idSottoprocesso;

	private static final long serialVersionUID = 1L;

	public AuAssAuditFase() {
		super();
	}

	public long getIdAf() {
		return this.idAf;
	}

	public void setIdAf(long idAf) {
		this.idAf = idAf;
	}

	
}
