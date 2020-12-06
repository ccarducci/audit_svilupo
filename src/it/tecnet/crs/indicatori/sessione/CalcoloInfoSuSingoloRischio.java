package it.tecnet.crs.indicatori.sessione;

import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuSRischio;
import it.tecnet.crs.jpa.model.AuSRisespr;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class CalcoloInfoSuSingoloRischio {

	private List<AuSRisespr> listAuSRisespr;
	private List<AuTotH3PerRischio> numConStessoRischioConRaggruppamento3;
	private List<AuTotH3PerRischio> numConStessoRischioConRaggruppamento4;
	private List<AuMRischio> listAuMRischio;
	private HashMap<Long, AuSRischio> listRischio;
	private Long numPraticheEsaminate;

	private HashMap<Long, SommaPerMedesimoRischio> sommaPerMedesimoRischioMap;
	private Long sommaRischi = 0L;

	public CalcoloInfoSuSingoloRischio(List<AuSRisespr> listAuSRisespr,
			List<AuTotH3PerRischio> numConStessoRischioConRaggruppamento3,
			List<AuTotH3PerRischio> numConStessoRischioConRaggruppamento4,
			List<AuMRischio> listAuMRischio,
			HashMap<Long, AuSRischio> listRischio, Long numPraticheEsaminate) {
		super();
		this.listAuSRisespr = listAuSRisespr;
		this.numConStessoRischioConRaggruppamento3 = numConStessoRischioConRaggruppamento3;
		this.numConStessoRischioConRaggruppamento4 = numConStessoRischioConRaggruppamento4;
		this.listAuMRischio = listAuMRischio;
		this.listRischio = listRischio;
		this.numPraticheEsaminate = numPraticheEsaminate;
	}

	public void calcoloInfoSuSingoloRischio() {
		sommaPerMedesimoRischioMap = getSommaPerMedesimoRischio();

		for (Entry<Long, AuSRischio> itemRischio : listRischio.entrySet()) {
			Long idMRischio = itemRischio.getKey();
			AuSRischio rischioToUpdate = itemRischio.getValue();

			SommaPerMedesimoRischio riepilodoIdMRischio = sommaPerMedesimoRischioMap
					.get(idMRischio);
			AuTotH3PerRischio auTotH3PerRischio = getNumConStessoRischioConRaggruppamento3(idMRischio);
			double numRischioPerRaggruppamento4 = getNumConStessoRischioConRaggruppamento4(idMRischio);

			if (rischioToUpdate != null && riepilodoIdMRischio != null) {
				/*
				 * num = numero di pratiche esaminate con medesimo rischio da
				 * S-RisEspr
				 */
				Long num = riepilodoIdMRischio.getNum();

				/*
				 * Importo_1 = somma Importo_1 da tabella S-RisEspr con medesimo
				 * rischio
				 */
				Double importo = 0D;
				if (riepilodoIdMRischio.getImporto() != null)
					importo = riepilodoIdMRischio.getImporto();

				/*
				 * num PS = num S-Rischio - num relativo a espressione di
				 * rischio con raggruppamento rischio = 3 su M-RisEspr
				 * [prestazioni non soggette])
				 */
				Long numPS = 0L;
				if (num.longValue() > 0 && auTotH3PerRischio != null
						&& auTotH3PerRischio.getSomma() != null)
					numPS = num - auTotH3PerRischio.getSomma();
				else if (num.longValue() > 0)
					numPS = num;
				else
					numPS = 0L;

				/*
				 * Peso Rischio = se Num PS su S-Rischio > 0 allora Peso Rischio
				 * = Peso Rischio da M-Rischio altrimenti Peso Rischio = 0
				 */
				Double pesoRischio = 0D;
				if (numPS.longValue() > 0)
					pesoRischio = getMPesoRischio(idMRischio);
				else
					pesoRischio = 0D;

				/*
				 * su tot PS (%) = num PS / numero pratiche esaminate da
				 * S-sessione
				 */
				Double suTotPsPerc = 0D;
				if (numPS.longValue() > 0
						&& numPraticheEsaminate.longValue() > 0)
					suTotPsPerc = numPS.doubleValue()
							/ numPraticheEsaminate.doubleValue();
				else
					suTotPsPerc = 0D;

				// -----------------------------------------------------------------------------------------------
				/*
				 * su tot (%)
				 */
				Double suTotPerc = 0D;
				if (numPraticheEsaminate != null
						&& numPraticheEsaminate.longValue() > 0 && num != null
						&& num.longValue() > 0)
					suTotPerc = num.doubleValue()
							/ numPraticheEsaminate.doubleValue();
				else
					suTotPerc = 0D;

				/*
				 * su PS (%)
				 */
				
				/*
				 * Long sommaPerRaggrH3 = 0L; if (auTotH3PerRischio != null &&
				 * auTotH3PerRischio.getSomma() != null) sommaPerRaggrH3 =
				 * auTotH3PerRischio.getSomma();
				 * 
				 * 
				 * if ( numPraticheEsaminate != null &&
				 * numPraticheEsaminate.longValue() > 0 && num != null &&
				 * num.longValue() > 0 ) suPsPerc = num.doubleValue() / (
				 * numPraticheEsaminate - sommaPerRaggrH3 ); else if
				 * (num.doubleValue() > 0) suPsPerc = num.doubleValue() /
				 * numPraticheEsaminate; else suPsPerc = 0D;
				 */
				double suPsPerc = 0d;
				double totRischio = 0d;
				double numRag3e4 = 0d;
				double numRag3 = 0d;
				
				if (num != null && num.longValue() > 0)totRischio = num.doubleValue();
				
				if (auTotH3PerRischio != null && auTotH3PerRischio.getSomma() != null){
					numRag3 =  auTotH3PerRischio.getSomma();
				}
				
				numRag3e4 = numRag3 + numRischioPerRaggruppamento4;
				
				double numForsuPsPerc = totRischio - numRag3e4;
				double totForsuPsPerc = totRischio - numRag3;
				
				if (numForsuPsPerc > 0 && totForsuPsPerc > 0){
					suPsPerc =  numForsuPsPerc / totForsuPsPerc;
				}else{
					suPsPerc = 0;
				}
				
				// ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
				// POPOLO I CAMPI DA AGGIORNARE
				/*
				 * num suTotPerc suPsPerc importo numPS pesoRischio suTotPsPerc
				 */
				rischioToUpdate.setNum(num.intValue());
				rischioToUpdate.setSuTotPerc(suTotPerc);
				rischioToUpdate.setSuPsPerc(suPsPerc);
				rischioToUpdate.setImporto(importo);
				rischioToUpdate.setNumPs(numPS.intValue());
				rischioToUpdate.setPesoRischio(pesoRischio);
				rischioToUpdate.setSuTotPsPerc(suTotPsPerc);
			}
		}

	}

	private Double getMPesoRischio(Long idMRischio) {
		for (AuMRischio itemMRischio : listAuMRischio) {
			if (itemMRischio.getIdMRischio() == idMRischio.longValue())
				return itemMRischio.getPesoRischio();
		}
		return 0D;
	}

	private AuTotH3PerRischio getNumConStessoRischioConRaggruppamento3(
			Long idMRischio) {
		for (AuTotH3PerRischio itemH3 : numConStessoRischioConRaggruppamento3) {
			if (itemH3.getIdRischio().longValue() == idMRischio.longValue())
				return itemH3;
		}
		return null;
	}

	private double getNumConStessoRischioConRaggruppamento4(Long idMRischio) {
		Double ret = 0d;
		for (AuTotH3PerRischio itemH4 : numConStessoRischioConRaggruppamento4) {
			if (itemH4.getIdRischio().longValue() == idMRischio.longValue() && itemH4.getRaggruppamento().equals("4"))
				ret = ret + itemH4.getSomma();
		}
		return ret;
	}

	private HashMap<Long, SommaPerMedesimoRischio> getSommaPerMedesimoRischio() {
		HashMap<Long, SommaPerMedesimoRischio> retMap = new HashMap<Long, SommaPerMedesimoRischio>();
		if (listAuSRisespr == null)
			return null;
		for (AuSRisespr auSRisesprItem : listAuSRisespr) {
			Long idMRischio = auSRisesprItem.getIdMRischio();

			SommaPerMedesimoRischio sommaPerMedesimoRischioItem = null;
			if (retMap.containsKey(idMRischio)) {

				sommaPerMedesimoRischioItem = retMap.get(idMRischio);

				Long num = sommaPerMedesimoRischioItem.getNum();
				num = num + auSRisesprItem.getNum();
				sommaPerMedesimoRischioItem.setNum(num);

				Double importo = sommaPerMedesimoRischioItem.getImporto();
				if (auSRisesprItem.getImporto() != null) {
					importo += auSRisesprItem.getImporto();
					sommaPerMedesimoRischioItem.setImporto(importo);
				}
			} else {

				sommaPerMedesimoRischioItem = new SommaPerMedesimoRischio();
				if (auSRisesprItem != null) {
					Integer num = auSRisesprItem.getNum();
					sommaPerMedesimoRischioItem.setNum(num.longValue());
				} else
					sommaPerMedesimoRischioItem.setNum(1L);

				if (auSRisesprItem.getImporto() != null)
					sommaPerMedesimoRischioItem.setImporto(auSRisesprItem
							.getImporto());
				else
					sommaPerMedesimoRischioItem.setImporto(0D);

			}
			retMap.put(idMRischio, sommaPerMedesimoRischioItem);
		}

		for (AuSRisespr auSRisesprItem : listAuSRisespr) {
			sommaRischi += auSRisesprItem.getNum();
		}
		return retMap;
	}

}
