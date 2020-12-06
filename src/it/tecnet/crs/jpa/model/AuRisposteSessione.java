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
@Table(name="AU_RISPOSTE_SESSIONE")
public class AuRisposteSessione implements Serializable {
	@Id
	@SequenceGenerator(name="AU_RISPOSTE_SESSIONE_GENERATOR", sequenceName="ID_RISP_SESSIO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_RISPOSTE_SESSIONE_GENERATOR")
	@Column(name="ID_RISPOSTA_SESSIONE")
	private long idRispostaSessione;

	@ManyToOne
	@JoinColumn(name="ID_RISPOSTA")
	private AuMRisposta idRisposta;

	@ManyToOne
	@JoinColumn(name="ID_SESSIONE")
	private AuSessioni idSessione;

	@ManyToOne
	@JoinColumn(name="ID_DOMANDA")
	private AuMDomanda idDomanda;

	private static final long serialVersionUID = 1L;

	public AuRisposteSessione() {
		super();
	}

	public long getIdRispostaSessione() {
		return idRispostaSessione;
	}

	public void setIdRispostaSessione(long idRispostaSessione) {
		this.idRispostaSessione = idRispostaSessione;
	}

	public AuMRisposta getIdRisposta() {
		return idRisposta;
	}

	public void setIdRisposta(AuMRisposta idRisposta) {
		this.idRisposta = idRisposta;
	}

	public AuSessioni getIdSessione() {
		return idSessione;
	}

	public void setIdSessione(AuSessioni idSessione) {
		this.idSessione = idSessione;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public AuMDomanda getIdDomanda() {
		return idDomanda;
	}

	public void setIdDomanda(AuMDomanda idDomanda) {
		this.idDomanda = idDomanda;
	}

	

}
