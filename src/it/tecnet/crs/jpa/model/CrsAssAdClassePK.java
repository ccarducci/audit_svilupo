package it.tecnet.crs.jpa.model;

import java.io.Serializable;

/**
 * ID class for entity: CrsAssAdClasse
 *
 */
public class CrsAssAdClassePK implements Serializable {

	private static final long serialVersionUID = 1L;
	private long idAttivitaDettaglio;
	private long idClasse;

	public CrsAssAdClassePK() {
	}

	public long getIdAttivitaDettaglio() {
		return idAttivitaDettaglio;
	}

	public void setIdAttivitaDettaglio(long idAttivitaDettaglio) {
		this.idAttivitaDettaglio = idAttivitaDettaglio;
	}

	public long getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(long idClasse) {
		this.idClasse = idClasse;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idAttivitaDettaglio ^ (idAttivitaDettaglio >>> 32));
		result = prime * result + (int) (idClasse ^ (idClasse >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CrsAssAdClassePK)) {
			return false;
		}
		CrsAssAdClassePK other = (CrsAssAdClassePK) obj;
		if (idAttivitaDettaglio != other.idAttivitaDettaglio) {
			return false;
		}
		if (idClasse != other.idClasse) {
			return false;
		}
		return true;
	}
}