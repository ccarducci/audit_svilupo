package it.tecnet.crs.jpa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRS_UTENTE_ADV")
public class CrsUtente implements Serializable {
	@Id
	@SequenceGenerator(name="CRS_UTENTE_GENERATOR", sequenceName="ID_UTENTE_ADV_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CRS_UTENTE_GENERATOR")
	@Column(name="ID_UTENTE")
	private long idUtente;

	private String nome;

	private String cognome;

	private String username;

	private String email;


	private String attivo;
	
//	private String audit;

	private static final long serialVersionUID = 1L;

	public CrsUtente() {
		super();
	}

	public long getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(long idUtente) {
		this.idUtente = idUtente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getAttivo() {
		return this.attivo;
	}

	public void setAttivo(String attivo) {
		this.attivo = attivo;
	}

//	public void setAudit(String audit) {
//		this.audit = audit;
//	}
//
//	public String getAudit() {
//		return audit;
//	}

}
