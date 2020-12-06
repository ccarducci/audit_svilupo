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
@Table(name="CRS_UTENTE_ADV")
public class CrsUtenteAdv implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_UTENTE_ADV_GENERATOR", sequenceName="ID_UTENTE_ADV_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_UTENTE_ADV_GENERATOR")
	@Column(name="ID_UTENTE")
	private long idUtente;

	@Column(name="NOME")
	private String nome;

	@Column(name="COGNOME")
	private String cognome;

	@Column(name="USERNAME")
	private String username;

	@Column(name="EMAIL")
	private String email;

	@Column(name="ATTIVO")
	private String attivo;
	
	@Column(name="DATA_ULTIMO_ACCESSO")
	private Date dataUltimoAccesso;
	

	private static final long serialVersionUID = 1L;


	public CrsUtenteAdv() {
		super();
	}


	public long getIdUtente() {
		return idUtente;
	}


	public void setIdUtente(long idUtente) {
		this.idUtente = idUtente;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAttivo() {
		return attivo;
	}


	public void setAttivo(String attivo) {
		this.attivo = attivo;
	}


	public Date getDataUltimoAccesso() {
		return dataUltimoAccesso;
	}


	public void setDataUltimoAccesso(Date dataUltimoAccesso) {
		this.dataUltimoAccesso = dataUltimoAccesso;
	}

}

