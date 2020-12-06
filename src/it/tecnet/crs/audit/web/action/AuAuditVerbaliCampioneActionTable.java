package it.tecnet.crs.audit.web.action;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoAuMContesto;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoAuMContestoDto;
import it.tecnet.crs.audit.service.AuCampagnaService;
import it.tecnet.crs.audit.web.beans.ModelVerbaliCampione;
import it.tecnet.crs.audit.web.dto.PraticheCampagnaDto;
import it.tecnet.crs.jpa.model.AuCampione;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditVerbaliCampioneActionTable extends BaseAction implements ModelDriven<ModelVerbaliCampione> {
	
	private static final long serialVersionUID = 1L;
	private ModelVerbaliCampione mvc= new ModelVerbaliCampione();
	private AuCampagnaService campagnaService;
	
	public AuAuditVerbaliCampioneActionTable( AuCampagnaService campagnaService){
		super();
		this.campagnaService= campagnaService;
		
	}
	
	//controlla che la sessione abbia un campione
	public String getCampioneSessione(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			long idSessione=user.getIdSessione();
			AuCampione c=campagnaService.getCampioneBySessione(idSessione);
			if(c != null){
				getModel().setCampioneSessione(c);
				user.setIdCampione(c.getIdCampione());
			}else{
				user.setIdCampione(0);
			}
			
			
		
		}
		
		
		return SUCCESS;
		
	}
	
	public String getVerbaliCampione(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idCampione=user.getIdCampione();
	
		List<PraticheCampagnaDto> l =campagnaService.getListaVerbaliCampioneSess(idCampione,getModel()
				.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
				.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
				.getsSearch());
		
		 Integer total = campagnaService.countListaVerbaliCampioneSess(idCampione,getModel()
					.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
					.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
					.getsSearch());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);
		 
		 
		if(l != null){
			 for(PraticheCampagnaDto s : l){
					getModel().getAaData().add(s);
				}
			}
		}
		
		return SUCCESS;
	}
	
	public String calcolaDatiDiContesto(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			long idSessione= user.getIdSessione();
//			long idCampagna= user.getIdCampagna();
			
			
			String descrizioneCampione=getModel().getNomeCampione();
			
//			AuCampione c= new AuCampione();
//			c.setIdSessione(idSessione);
//			c.setDescrizione(descrizioneCampione);
//			
//			if(user.getIdCampione() != 0){
//				c.setIdCampione(user.getIdCampione());
//				
//			}else{
//				//devo mettere in sessione l id del campione corrente
//				AuCampione camp=campagnaService.getCampioneBySessione(idSessione);
//				if(camp != null){
//					user.setIdCampione(camp.getIdCampione());
//				}
//				
//			
//			}
			AuSessioni s= campagnaService.cerca(AuSessioni.class, idSessione);
			AtpoAuMContestoDto contestoDto = campagnaService.getDatiContesto(idSessione, s.getIdCampagna());
//			System.out.println("TROVO CONTESTO "+contestoDto);
			AtpoAuMContesto contesto = new AtpoAuMContesto(); 
			if(contestoDto != null){
				contesto= campagnaService.cerca(AtpoAuMContesto.class, contestoDto.getIdMContesto());
			}
			else{
				contesto.setIdSessione(idSessione);
			}
			contesto = fillContesto(contesto,s);
			campagnaService.salva(contesto);
			
		}
		return SUCCESS;
	}
	private AtpoAuMContesto fillContesto(AtpoAuMContesto contesto,AuSessioni sessioni){
//		System.out.println("QUI RIEMPIO IL CONTESTO "+contesto);
		List<PraticheCampagnaDto> list =campagnaService.getListaVerbaliCampagnaSede(sessioni.getIdCampagna(),sessioni.getSede());
//		System.out.println("TROVO "+list.size());
		double countCessataMateriaAContendere = 0 ;
		double countDissenso = 0 ;
		double countEstinta= 0 ;
		double countFav= 0 ;
		double countIna= 0 ;
		double countInc= 0 ;
		double countPar= 0 ;
		double countSfav= 0 ;
		double countPresenzaVisitePeritali= 0 ;
		double totPratiche = list.size() ;
		
		Set<String> funzionari = new HashSet<String>();
		Set<String> medici = new HashSet<String>();
		long[] tempiAperturaPratica=new long[list.size()];
		long[] tempiChiusuraPratica=new long[list.size()];
		
		
		double presenzaCtuSi= 0 ;
		
		for(int n = 0 ; n < list.size();n++){
			PraticheCampagnaDto campagnaDto = list.get(n);
//			System.out.println("CONTO PROTICHE");
			if(campagnaDto.getTempoAperturaPratica()!=null){
				try {
					tempiAperturaPratica[n]=Long.parseLong(campagnaDto.getTempoAperturaPratica());
				} catch (Exception e) {

				}
			}
			if(campagnaDto.getTempoChiusuraPratica()!=null){
				try {
					tempiChiusuraPratica[n]=Long.parseLong(campagnaDto.getTempoChiusuraPratica());
				} catch (Exception e) {

				}
			}
			
			if(campagnaDto.getPresenzaCTU()!= null && campagnaDto.getPresenzaCTU().equalsIgnoreCase("SI")){
				presenzaCtuSi++;
			}
			
			
			if(campagnaDto.getFunzionario()!= null && !campagnaDto.getFunzionario().trim().equalsIgnoreCase("Non specificato")){
				funzionari.add(campagnaDto.getFunzionario());
			}
			if(campagnaDto.getMedicoINPS()!= null && !campagnaDto.getMedicoINPS().trim().equalsIgnoreCase("Non specificato")){
				medici.add(campagnaDto.getMedicoINPS());
			}
			if(campagnaDto.getEsito()!= null && campagnaDto.getEsito().equalsIgnoreCase("CESSATA MATERIA CONTENDERE")){
				countCessataMateriaAContendere++;
			}
			if(campagnaDto.getEsito()!= null && campagnaDto.getEsito().equalsIgnoreCase("DISSENSO")){
				countDissenso++;
			}
			if(campagnaDto.getEsito()!= null && campagnaDto.getEsito().equalsIgnoreCase("ESTINTA")){
				countEstinta++;
			}
			if(campagnaDto.getEsito()!= null && campagnaDto.getEsito().equalsIgnoreCase("FAVOREVOLE ALL'ISTITUTO")){
				countFav++;
			}
			if(campagnaDto.getEsito()!= null && campagnaDto.getEsito().equalsIgnoreCase("INAMMISSIBILITA'")){
				countIna++;
			}
			if(campagnaDto.getEsito()!= null && campagnaDto.getEsito().equalsIgnoreCase("INCOMPETENZA")){
				countInc++;
			}
			if(campagnaDto.getEsito()!= null && campagnaDto.getEsito().equalsIgnoreCase("PARZIALMENTE FAVOREVOLE")){
				countPar++;
			}
			if(campagnaDto.getEsito()!= null && campagnaDto.getEsito().equalsIgnoreCase("SFAVOREVOLE ALL'ISTITUTO")){
				countSfav++;
			}
			if(campagnaDto.getPresenzaVisitePeritali()!= null && campagnaDto.getPresenzaVisitePeritali().trim().equalsIgnoreCase("SI")){
				countPresenzaVisitePeritali++;
			}
			
			//System.out.println("ESITO "+campagnaDto.getEsito());	
		}
		double perCountCessataMateriaAContendere=countCessataMateriaAContendere/totPratiche*100;
		double perCountDissenso=countDissenso/totPratiche*100;
		double perCountEstinta=countEstinta/totPratiche*100;
		double perCountFav=countFav/totPratiche*100;
		double perCountIna=countIna/totPratiche*100;
		double perCountInc=countInc/totPratiche*100;
		double perCountPar=countPar/totPratiche*100;
		double perCountSfav=countSfav/totPratiche*100;
		
		contesto.setFunzionari(funzionari.size()+"");
		contesto.setMediciInps(medici.size()+"");
		contesto.setGgMediApertura(median(tempiAperturaPratica));
		contesto.setGgMediChiusura(median(tempiChiusuraPratica));
		contesto.setCtu(presenzaCtuSi+"");
		double percv =0;
		try {
			percv = new Double(new Double(countPresenzaVisitePeritali).intValue()/new Double(presenzaCtuSi).intValue());	
		} catch (Exception e) {
		}
		double percm = 0;
		try {
			percm = new Double(new Double(countPresenzaVisitePeritali).intValue()/new Double(medici.size()).intValue());	
		} catch (Exception e) {
		}
		contesto.setPercCvpSuNrCtu(new Double(percv*100));
		contesto.setPercCvpSuNrMeidici(new Double(percm*100));
		
		contesto.setCessMatContendDom(""+countCessataMateriaAContendere);
		contesto.setCessMatContendRev(String.format("%.2f", perCountCessataMateriaAContendere));
		contesto.setDissensoDom(""+countDissenso);
		contesto.setDissensoRev(String.format("%.2f", perCountDissenso));

		contesto.setEstintaDom(""+countEstinta);
		contesto.setEstintaRev(String.format("%.2f", perCountEstinta));
		contesto.setFavDom(""+countFav);
		contesto.setFavRev(String.format("%.2f", perCountFav));
		
		contesto.setInammissDom(""+countIna);
		contesto.setInammissRev(String.format("%.2f", perCountIna));
		contesto.setIncompDom(""+countInc);
		contesto.setIncompRev(String.format("%.2f", perCountInc));
		
		contesto.setParzFavDom(""+countPar);
		contesto.setParzFavRev(String.format("%.2f", perCountPar));
		
		contesto.setSfavDom(""+countSfav);
		contesto.setSfavRev(String.format("%.2f", perCountSfav));
		
		contesto.setVisitePeritali(""+countPresenzaVisitePeritali);
		
		return contesto;
	}
	public  long median(long[] l)
	  {
		long sum = 0 ;
		for(int n = 0 ; n < l.length;n++){
			sum = sum+l[n];
		}
		long toret=0;
		try {
			toret= sum/l.length;	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return toret;
		
//	    Arrays.sort(l);
//	    int middle = l.length / 2;
//	    if (l.length % 2 == 0)
//	    {
//	      long left = l[middle - 1];
//	      long right = l[middle];
//	      return (left + right) / 2;
//	    }
//	    else
//	    {
//	      return l[middle];
//	    }
	  }
	public String salvaCampioneSessione(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			long idSessione= user.getIdSessione();
			String descrizioneCampione=getModel().getNomeCampione();
			
			AuCampione c= new AuCampione();
			c.setIdSessione(idSessione);
			c.setDescrizione(descrizioneCampione);
			
			if(user.getIdCampione() != 0){
				c.setIdCampione(user.getIdCampione());
				
			}else{
				
				//devo mettere in sessione l id del campione corrente
				AuCampione camp=campagnaService.getCampioneBySessione(idSessione);
				if(camp != null){
					user.setIdCampione(camp.getIdCampione());
				}
			
			}
			campagnaService.salva(c);
			
		}
		
		return SUCCESS;
	}
	
	public String checkIfPraticaIsOpen(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			long idCampione= user.getIdCampione();
			long idVerbale= getModel().getIdVerbale();
			AuSPratica sp= campagnaService.getAuSPraticaById(idVerbale);
			if(sp!=null){
				String statoEsame= sp.getEsamePratica();
				if(statoEsame.equalsIgnoreCase("C")){
					//la pratica non puo essere cancellata perche è chiusa
					getModel().setInfo("C");
				}else{
					//cancello
					getModel().setInfo("A");
					long idSPratica= sp.getIdSPratica();
					
					campagnaService.deletePraticaCampione(idCampione, idVerbale, idSPratica);
					//campagnaService.calcoloRegolaCampione(user.getIdSessione());
					
				}
			}
		}
		
		
		return SUCCESS;
	}
//	public String eliminaPraticaCampione(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//
//		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
//		if (user!= null) {
//			long idCampione= user.getIdCampione();
//			long idPratica= getModel().getIdVerbale();
//			
//			campagnaService.deletePraticaCampione(idCampione, idPratica);
//			//campagnaService.calcoloRegolaCampione(user.getIdSessione());
//			
//		}
//		
//		
//		
//		return SUCCESS;
//	}
	

	@Override
	public ModelVerbaliCampione getModel() {
		
		return mvc;
	}
	


}


