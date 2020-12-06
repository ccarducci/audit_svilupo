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
@Table(name="AU_SESSIONI")
@NamedQueries({
    @NamedQuery(
        name = "AuSessioni.findByIdCampagna",
        query = "SELECT s FROM AuSessioni s WHERE s.idCampagna = :idCampagna")
})
public class AuSessioni implements Serializable {
	
	@Id
	@SequenceGenerator(name="AU_SESSIONI_GENERATOR", sequenceName="ID_SESSIONI_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AU_SESSIONI_GENERATOR")
	@Column(name="ID_SESSIONE")
	private long idSessione;

	private String sede;

	private String tipo;

	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Column(name="DATA_FINE")
	private Date dataFine;

	private String stato;

	private String dirigente;

	@Column(name="id_campagna")
	private long idCampagna;

	
	
	@Column(name="NOTA")
	private String nota;

	private static final long serialVersionUID = 1L;

	public AuSessioni() {
		super();
	}

	public long getIdSessione() {
		return this.idSessione;
	}

	public void setIdSessione(long idSessione) {
		this.idSessione = idSessione;
	}

	public String getSede() {
		return this.sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getDirigente() {
		return this.dirigente;
	}

	public void setDirigente(String dirigente) {
		this.dirigente = dirigente;
	}

	public long getIdCampagna() {
		return this.idCampagna;
	}

	public void setIdCampagna(long idCampagna) {
		this.idCampagna = idCampagna;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getNota() {
		return nota;
	}

}

