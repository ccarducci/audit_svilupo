package it.tecnet.crs.jpa.model;

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
@Table(name = "AU_TPL_TIPOLOGICHE")
@NamedQueries( { @NamedQuery(name = AuTplTipologiche.QUERY_TPLTIPOLOGICHE_ALL, query = "SELECT t FROM AuTplTipologiche t") })
public class AuTplTipologiche {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_TPLTIPOLOGICHE_ALL = "AuTplTipologiche.findAll";

	@Id
	@SequenceGenerator(name = "AU_TPL_TIPOLOGICHE_GENERATOR", sequenceName = "ID_TPL_TIPOLOGICHE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_TPL_TIPOLOGICHE_GENERATOR")
	@Column(name = "ID_TPL_TIPOLOGICHE")
	private long idTplTipologica;

	@Column(name = "TIPO", length = 5)
	private String tipo;

	@Column(name = "CODIFICA", length = 5)
	private String codifica;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	public long getIdTplTipologica() {
		return idTplTipologica;
	}

	public void setIdTplTipologica(long idTplTipologica) {
		this.idTplTipologica = idTplTipologica;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodifica() {
		return codifica;
	}

	public void setCodifica(String codifica) {
		this.codifica = codifica;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
