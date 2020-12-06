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
@Table(name="AU_MEDIA_FASE")
public class AuMediaFase implements Serializable {
	@Id
	@SequenceGenerator(name="AU_MEDIA_FASE_GENERATOR", sequenceName="ID_MEDIAFASE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_MEDIA_FASE_GENERATOR")
	@Column(name="ID_MEDIA_FASE")
	private long idMediaFase;


	@Column(name="ID_SESSIONE")
	private long idSessione;

	@ManyToOne
	@JoinColumn(name="ID_QUESTIONARIO")
	private AuQuestionario idQuestonario;

	private static final long serialVersionUID = 1L;

	@Column(name="NOTIFICA")
	private String notifica;


	@Column(name="DEFINIZIONE")
	private String definizione;
	
	@Column(name="QUESTIONARIO")
	private Integer questionario;
	

	public AuMediaFase() {
		super();
	}

	public long getIdMediaFase() {
		return idMediaFase;
	}

	public void setIdMediaFase(long idMediaFase) {
		this.idMediaFase = idMediaFase;
	}

	public long getIdSessione() {
		return idSessione;
	}

	public void setIdSessione(long idSessione) {
		this.idSessione = idSessione;
	}

	public AuQuestionario getIdQuestonario() {
		return idQuestonario;
	}

	public void setIdQuestonario(AuQuestionario idQuestonario) {
		this.idQuestonario = idQuestonario;
	}

	public String getNotifica() {
		return notifica;
	}

	public void setNotifica(String notifica) {
		this.notifica = notifica;
	}

	public String getDefinizione() {
		return definizione;
	}

	public void setDefinizione(String definizione) {
		this.definizione = definizione;
	}

	public void setQuestionario(Integer questionario) {
		this.questionario = questionario;
	}

	public Integer getQuestionario() {
		return questionario;
	}



}
