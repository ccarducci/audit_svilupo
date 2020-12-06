package it.tecnet.crs.jpa.model;

import java.io.Serializable;

/**
 * ID class for entity: CrsAssSottoprocessoClasse
 *
 */
public class CrsAssSottoprocessoClassePK implements Serializable {

	private static final long serialVersionUID = 1L;
	private long idSottoprocesso;
	private long idClasse;

	public CrsAssSottoprocessoClassePK() {
	}

	public long getIdSottoprocesso() {
		return idSottoprocesso;
	}

	public void setIdSottoprocesso(long idSottoprocesso) {
		this.idSottoprocesso = idSottoprocesso;
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
				+ (int) (idSottoprocesso ^ (idSottoprocesso >>> 32));
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
		if (!(obj instanceof CrsAssSottoprocessoClassePK)) {
			return false;
		}
		CrsAssSottoprocessoClassePK other = (CrsAssSottoprocessoClassePK) obj;
		if (idSottoprocesso != other.idSottoprocesso) {
			return false;
		}
		if (idClasse != other.idClasse) {
			return false;
		}
		return true;
	}
}