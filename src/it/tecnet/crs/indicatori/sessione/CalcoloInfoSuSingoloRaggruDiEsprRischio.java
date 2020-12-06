package it.tecnet.crs.indicatori.sessione;

import it.tecnet.crs.jpa.model.AuSRisespr;
import it.tecnet.crs.jpa.model.AuSRisrag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalcoloInfoSuSingoloRaggruDiEsprRischio {

	private List<AuSRisrag> listAuSRisrag;
	private List<AuTotH3PerRischio> numConStessoRischioConRaggruppamento3;
	
	private List<AuTotH3PerRischio> numConStessoRischioConRaggr3 = new ArrayList<AuTotH3PerRischio>();
	private List<AuTotH3PerRischio> numConStessoRischio = new ArrayList<AuTotH3PerRischio>();;
	private Long idSSessione;

	public CalcoloInfoSuSingoloRaggruDiEsprRischio(
			List<AuSRisrag> listAuSRisrag,
			List<AuTotH3PerRischio> numConStessoRischioConRaggruppamento3,
			Long idSSessione) {
		super();
		this.listAuSRisrag = listAuSRisrag;
		this.numConStessoRischioConRaggruppamento3 = numConStessoRischioConRaggruppamento3;
		this.idSSessione = idSSessione;
	}

	public List<AuSRisrag> calcoloInfoSuSingoloRaggruDiEsprRischio(
			List<Object[]> listaRisRagObject,
			Integer numeroPraticheEsaminateInSessione) {
		
		getTotale(listaRisRagObject);
		
		for (Object[] row : listaRisRagObject) {

			double importo = 0D;
			double psPerc = 0D;
			double suTotPerc = 0D;

			Long idMRischio = (Long) row[0];
			String raggrupRischio = (String) row[1];
			if (raggrupRischio != null)
				raggrupRischio = raggrupRischio.trim();
			Integer num = (Integer) row[2];
			importo = ((BigDecimal) row[3]).doubleValue();

			if (num != null && num.intValue() > 0
					&& numeroPraticheEsaminateInSessione != null
					&& numeroPraticheEsaminateInSessione > 0) {
				Long numConStessoRischioConRaggruppamento3 = getNumConStessoRischioConRaggruppamento3(idMRischio);

				if (numConStessoRischioConRaggruppamento3 != null)
					numConStessoRischioConRaggruppamento3 = 0L;

				// su tot (%)
				suTotPerc = num.doubleValue()
						/ numeroPraticheEsaminateInSessione.doubleValue();

				// su PS (%)
				Long totConStessoRischio = getNumConStessoRischio(idMRischio);
				
				if (!raggrupRischio.equals("3")){
					psPerc = num.doubleValue() /  totConStessoRischio.doubleValue();
				}else{
					psPerc = 0;
				}
			}

			AuSRisrag itemToAdd = new AuSRisrag();
			itemToAdd.setDataFine(new Date());
			itemToAdd.setDataInizio(new Date());
			itemToAdd.setIdMRischio(idMRischio);
			itemToAdd.setIdSSessione(idSSessione);
			itemToAdd.setImporto(importo);
			itemToAdd.setNum(num);
			itemToAdd.setRaggrupRischio(Integer.parseInt(raggrupRischio));
			itemToAdd.setTotPerc(suTotPerc);
			itemToAdd.setPsPerc(psPerc);
			
			listAuSRisrag.add(itemToAdd);
		}
		return listAuSRisrag;
	}
	
	public void updateRieEspr(List<AuSRisespr> listAuSRisespr){
		for (AuSRisespr auSRisespr : listAuSRisespr) {
			Long totConStessoRischio = getNumConStessoRischio(auSRisespr.getIdMRischio());
			Double suPs = auSRisespr.getNum() / totConStessoRischio.doubleValue();
			auSRisespr.setSuPs(suPs);
		}
	}
	
	private void getTotale(List<Object[]> listaRisRagObject){
		numConStessoRischio.clear();
		for (Object[] objects : listaRisRagObject) {
			String raggruppamento = (String)objects[1];
			raggruppamento = raggruppamento.trim();
			if ( !raggruppamento.equals("3")){
				Long idMRischio = (Long)objects[0];
				Integer num = (Integer)objects[2];
				AuTotH3PerRischio itemToAdd = new AuTotH3PerRischio();
				itemToAdd.setIdRischio(idMRischio);
				itemToAdd.setSomma(num.longValue());
				itemToAdd.setRaggruppamento(raggruppamento);
				numConStessoRischio.add(itemToAdd);
			}
		}
	}
	
	
	private Long getNumConStessoRischioConRaggruppamento3(Long idMRischio) {
		if (numConStessoRischioConRaggruppamento3 == null
				|| numConStessoRischioConRaggruppamento3.size() == 0)
			return 0L;
		Long somma = 0L;
		for (AuTotH3PerRischio item : numConStessoRischioConRaggruppamento3) {
			if (item.getIdRischio().longValue() == idMRischio.longValue())
				somma += item.getSomma();
		}
		return somma;
	}

	public Long getNumConStessoRischio(Long idMRischio) {
		if (numConStessoRischio == null
				|| numConStessoRischio.size() == 0)
			return 0L;
		Long somma = 0L;
		for (AuTotH3PerRischio item : numConStessoRischio) {
			if (item.getIdRischio().longValue() == idMRischio.longValue() && !item.getRaggruppamento().trim().equals("3"))
				somma += item.getSomma();
		}
		return somma;
	}
	
}
