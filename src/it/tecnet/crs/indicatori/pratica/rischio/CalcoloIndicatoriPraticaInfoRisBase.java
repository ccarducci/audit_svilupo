package it.tecnet.crs.indicatori.pratica.rischio;

import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuSPraticaRis;

import java.util.Date;
import java.util.List;

public class CalcoloIndicatoriPraticaInfoRisBase {

	public void insertRischio(Long idMRischio, Date dataAttribuzione,
			String espressioneRischio, Long idSPratica, Long idSSessione,
			double importo, List<AuSPraticaRis> listaPraticheRisToInsert,
			List<AuMRisepr> listaAuMRisepr) {

		Long idMEsprRischio = getAuMRiseprByCodiceVc(espressioneRischio,
				listaAuMRisepr);

		if (idMEsprRischio != null) {
			AuSPraticaRis praticaRisToInsert = new AuSPraticaRis();
			praticaRisToInsert.setDataAttribuzione(dataAttribuzione);
			praticaRisToInsert.setIdMRischio(idMRischio);
			praticaRisToInsert.setIdSPratica(idSPratica);
			praticaRisToInsert.setIdEsprRischio(idMEsprRischio);
			// praticaRisToInsert.setIdSSessione(idSSessione);
			praticaRisToInsert.setImporto(importo);
			listaPraticheRisToInsert.add(praticaRisToInsert);
		}
	}

	private Long getAuMRiseprByCodiceVc(String codiceVc,
			List<AuMRisepr> listaAuMRisepr) {
		if (codiceVc != null) {
			for (AuMRisepr auMRisepr : listaAuMRisepr) {
				if (auMRisepr.getCodice().trim().toUpperCase().equals(
						codiceVc.trim().toUpperCase()))
					return auMRisepr.getIdMRisepr();
			}
		}
		return null;
	}
}
