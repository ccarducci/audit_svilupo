package it.tecnet.crs.jpa.model;

import java.io.Serializable;

/**
 * ID class for entity: CrsAssAcClasse
 *
 */
public class CrsAssAcClassePK implements Serializable {

	private static final long serialVersionUID = 1L;
	private long idAttivitaComponente;
	private long idClasse;

	public CrsAssAcClassePK() {
	}

	public long getIdAttivitaComponente() {
		return idAttivitaComponente;
	}

	public void setIdAttivitaComponente(long idAttivitaComponente) {
		this.idAttivitaComponente = idAttivitaComponente;
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
				+ (int) (idAttivitaComponente ^ (idAttivitaComponente >>> 32));
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
		if (!(obj instanceof CrsAssAcClassePK)) {
			return false;
		}
		CrsAssAcClassePK other = (CrsAssAcClassePK) obj;
		if (idAttivitaComponente != other.idAttivitaComponente) {
			return false;
		}
		if (idClasse != other.idClasse) {
			return false;
		}
		return true;
	}
}