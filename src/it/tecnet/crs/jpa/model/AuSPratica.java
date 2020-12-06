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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AU_S_PRATICA")
@NamedQueries( {
		@NamedQuery(name = AuSPratica.QUERY_SPRATICA_BY_IDSSESSIONE, query = "SELECT t FROM AuSPratica t WHERE t.idSSessione = :"
				+ AuSPratica.QUERY_PARAM_IDSSESSIONE),

		@NamedQuery(name = AuSPratica.QUERY_SPRATICA_BY_IDPRATICACISCO, query = "SELECT t FROM AuSPratica t WHERE t.idPratica = :"
				+ AuSPratica.QUERY_PARAM_IDPRATICACISCO),

		@NamedQuery(name = AuSPratica.QUERY_SPRATICA_BY_IDSSSESSIONE_AND_ESAMEPRATICA_C, query = "SELECT t FROM AuSPratica t WHERE t.esamePratica = 'C' and t.idSSessione = :"
				+ AuSPratica.QUERY_PARAM_IDSSESSIONE) })
public class AuSPratica implements Serializable {

	public static final String QUERY_SPRATICA_BY_IDSSESSIONE = "AuSPratica.findAllByIdsSessione";
	public static final String QUERY_SPRATICA_BY_IDPRATICACISCO = "AuSPratica.findAllByIdsPraticaCisco";
	public static final String QUERY_SPRATICA_BY_IDSSSESSIONE_AND_ESAMEPRATICA_C = "AuSPratica.findAllByIdsSessioneAndEsamePratica";
	public static final String QUERY_PARAM_IDSSESSIONE = "idSPratica";
	public static final String QUERY_PARAM_IDPRATICACISCO = "idPraticaCisco";

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AU_S_PRATICA_GENERATOR", sequenceName = "ID_S_PRATICA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_S_PRATICA_GENERATOR")
	@Column(name = "ID_S_PRATICA")
	private long idSPratica;

	@Column(name = "ID_S_SESSIONE")
	private long idSSessione;

	// ID_PRATICA_SISCO
	@Column(name = "ID_PRATICA")
	private long idPratica;

	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Column(name = "ESAME_PRATICA")
	private String esamePratica;

	@Column(name = "INT_TMP_DATAN_DATAP")
	private double intTmpDatanDatap;

	@Column(name = "INT_TMP_DATAN_DATAAS")
	private double intTmpDatanDataas;

	@Column(name = "TIPO_DIFESA")
	private String tipoDifesa;

	@Column(name = "IMPORTO")
	private double importo;

	
	@Column(name = "DATA_ULTIMA_MODIFICA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimaModifica;

	@Column(name = "USER_OWNER")
	private String userOwner;

	public long getIdSPratica() {
		return idSPratica;
	}

	public void setIdSPratica(long idSPratica) {
		this.idSPratica = idSPratica;
	}

	public long getIdSSessione() {
		return idSSessione;
	}

	public void setIdSSessione(long idSSessione) {
		this.idSSessione = idSSessione;
	}

	public long getIdPratica() {
		return idPratica;
	}

	public void setIdPratica(long idPratica) {
		this.idPratica = idPratica;
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

	public String getEsamePratica() {
		return esamePratica;
	}

	public void setEsamePratica(String esamePratica) {
		this.esamePratica = esamePratica;
	}

	public double getIntTmpDatanDatap() {
		return intTmpDatanDatap;
	}

	public void setIntTmpDatanDatap(double intTmpDatanDatap) {
		this.intTmpDatanDatap = intTmpDatanDatap;
	}

	public double getIntTmpDatanDataas() {
		return intTmpDatanDataas;
	}

	public void setIntTmpDatanDataas(double intTmpDatanDataas) {
		this.intTmpDatanDataas = intTmpDatanDataas;
	}

	public String getTipoDifesa() {
		return tipoDifesa;
	}

	public void setTipoDifesa(String tipoDifesa) {
		this.tipoDifesa = tipoDifesa;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public String getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(String userOwner) {
		this.userOwner = userOwner;
	}
	
}
