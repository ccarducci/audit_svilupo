package it.tecnet.crs.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AU_S_PRA_CAL_IND_LOG")
public class AuSPraCalIndLog {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_PRATICA_CAL_IND_LOG_GENERATOR", sequenceName = "ID_S_PRA_CAL_IND_LOG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_PRATICA_CAL_IND_LOG_GENERATOR")
	@Column(name = "ID_S_PRA_CAL_IND_LOG")
	private long idSPraCalIndLog;

	@Column(name = "TIPO_CALCOLO", length = 100)
	private String tipoCalcolo;

	@Column(name = "TIPO_ERRORE", length = 100)
	private String tipoErrore;

	@Column(name = "MESSAGGIO")
	private String messaggio;

	@Column(name = "ID_PRATICA")
	private long idPratica;

	@Column(name = "DATA_INSERIMENTO")
	private Date dataInserimento;

	@Column(name = "ID_S_SESSIONE")
	private long idSSessione;

	public long getIdSPraCalIndLog() {
		return idSPraCalIndLog;
	}

	public void setIdSPraCalIndLog(long idSPraCalIndLog) {
		this.idSPraCalIndLog = idSPraCalIndLog;
	}

	public String getTipoCalcolo() {
		return tipoCalcolo;
	}

	public void setTipoCalcolo(String tipoCalcolo) {
		this.tipoCalcolo = tipoCalcolo;
	}

	public String getTipoErrore() {
		return tipoErrore;
	}

	public void setTipoErrore(String tipoErrore) {
		this.tipoErrore = tipoErrore;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public long getIdPratica() {
		return idPratica;
	}

	public void setIdPratica(long idPratica) {
		this.idPratica = idPratica;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AuSPraCalIndLog [ ");
		sb.append(" tipoCalcolo: ");
		sb.append(tipoCalcolo);
		sb.append(",");
		sb.append(" tipoErrore: ");
		sb.append(tipoErrore);
		sb.append(",");
		sb.append(" messaggio: ");
		sb.append(messaggio);
		sb.append(",");
		sb.append(" idPratica: ");
		sb.append(idPratica);
		sb.append(",");
		sb.append(" idSSessione: ");
		sb.append(idSSessione);
		sb.append(" ] ");
		return sb.toString();
	}
}
