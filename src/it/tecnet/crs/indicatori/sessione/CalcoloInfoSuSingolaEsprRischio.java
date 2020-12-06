package it.tecnet.crs.indicatori.sessione;

import it.tecnet.crs.jpa.model.AuSPraticaRis;
import it.tecnet.crs.jpa.model.AuSRisespr;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class CalcoloInfoSuSingolaEsprRischio {

	private List<AuSPraticaRis> listAuPraticaRis;
	private List<AuSRisespr> listAuSRisespr;
	/* private List<AuTotH3PerRischio> numConStessoRischioConRaggruppamento3; */
	private HashMap<Long, Long> numPerStessoRischio;
	private Long numeroPraticheEsaminateSessione;

	public CalcoloInfoSuSingolaEsprRischio(List<AuSRisespr> listAuSRisespr,
			List<AuSPraticaRis> listAuPraticaRis,
			HashMap<Long, Long> numPerStessoRischio,
			long numeroPraticheEsaminateSessione) {
		this.listAuSRisespr = listAuSRisespr;
		this.listAuPraticaRis = listAuPraticaRis;
		this.numPerStessoRischio = numPerStessoRischio;
		this.numeroPraticheEsaminateSessione = numeroPraticheEsaminateSessione;
	}

	public List<AuSRisespr> calcoloInfoSuSingolaEsprRischio() {

		// numPerEsprRischio = getNumPerEsprRischio();

		HashMap<Long, AuSRisespr> numEsprRischio = new HashMap<Long, AuSRisespr>();

		// CALCOLO CONTEGGIO NUM E IMPORTO
		for (AuSPraticaRis itemRis : listAuPraticaRis) {
			Long idEsprRischio = itemRis.getIdEsprRischio();
			AuSRisespr auSRisespr = null;
			if (numEsprRischio.containsKey(idEsprRischio)) {
				auSRisespr = numEsprRischio.get(idEsprRischio);
				int num = auSRisespr.getNum();
				num++;
				auSRisespr.setNum(num);
				Double importo = itemRis.getImporto() + auSRisespr.getImporto();
				auSRisespr.setImporto(importo);
			} else {
				auSRisespr = new AuSRisespr();
				auSRisespr.setNum(1);
				auSRisespr.setImporto(itemRis.getImporto());
			}

			auSRisespr.setIdMRischio(itemRis.getIdMRischio());
			auSRisespr.setIdMRisespr(itemRis.getIdEsprRischio());

			numEsprRischio.put(idEsprRischio, auSRisespr);
		}

		for (Entry<Long, AuSRisespr> item : numEsprRischio.entrySet()) {
			AuSRisespr auSRisespr = item.getValue();

			Long numeroRischio = numPerStessoRischio.get(auSRisespr
					.getIdMRischio());

			if (numeroRischio == null)
				numeroRischio = 0L;

			if (auSRisespr.getNum() > 0) {
				Integer num = auSRisespr.getNum();
				if (numeroPraticheEsaminateSessione != null
						&& numeroPraticheEsaminateSessione > 0) {

					/*
					 * su tot (%) = num / somma num con stesso Rischio da
					 * S-PraticaRis
					 */

					Double suTotPerc = num.doubleValue()
							/ numeroPraticheEsaminateSessione.doubleValue();

					auSRisespr.setSuTot(suTotPerc);

					/*
					 * su PS (%) = num / (somma num con stesso Rischio da
					 * S-PraticaRis - num relativo a espressione di rischio con
					 * raggruppamento rischio = 3 da sPraticaRis e M-RisEspr
					 * [prestazioni non soggette])
					 * 
					 * Long numPerRischio =
					 * getNumConStessoRischioConRaggruppamento
					 * (numEsprRischio,auSRisespr.getIdMRischio()); Double suPs
					 * = num.doubleValue() / ( numPerRischio.doubleValue() -
					 * numeroEsprRischioRag3.doubleValue() );
					 * 
					 * auSRisespr.setSuPs(suPs);
					 */
				}
			}

			listAuSRisespr.add(auSRisespr);
		}

		return listAuSRisespr;
	}

}
