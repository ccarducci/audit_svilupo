package it.tecnet.crs.audit.web.dto;

public class AuAuditorsDto {

private long idUtente;
	
	private String nome;
	
	private String cognome;
	
	private String associazione;
	
	private String username;

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

	public String getAssociazione() {
		return associazione;
	}

	public void setAssociazione(String associazione) {
		this.associazione = associazione;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}