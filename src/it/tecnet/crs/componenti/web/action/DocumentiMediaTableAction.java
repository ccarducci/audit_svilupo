package it.tecnet.crs.componenti.web.action;


import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.jpa.model.CrsTplDocMedia;
import it.tecnet.crs.componenti.service.GestioneComponentiService;
import it.tecnet.crs.componenti.web.bean.DocumentiMediaPaginator;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

//import antlr.StringUtils;

import com.opensymphony.xwork2.ModelDriven;

public class DocumentiMediaTableAction extends BaseAction implements ModelDriven<DocumentiMediaPaginator> {

	private static final long serialVersionUID = 1L;
	private GestioneComponentiService gestioneComponentiService;
	private DocumentiMediaPaginator model= new DocumentiMediaPaginator();

	public DocumentiMediaTableAction(GestioneComponentiService gestioneComponentiService){
		super();
		this.setGestioneComponentiService(gestioneComponentiService);
	}

	public String getDocumentiMedia(){
		
		try{
			//popolo l'aside "tipo documento"
			List<CrsTplDocMedia>list=gestioneComponentiService.getTplDocMedia();
			getModel().setTplDocMedia(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		

		return SUCCESS;
	}

	//popola tabella senza filtro
	public String searchDocumentiMedia(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=user.getIdUtente();

		List<CrsDocumentiMedia> docMediaList =	gestioneComponentiService.getListDocMedia(getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = gestioneComponentiService.countListDocMedia(getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(docMediaList==null){
				return SUCCESS;
			}

			for(Object o : docMediaList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
	}
	/*
	 * controlla codice inserito dall'utente
	 */
	public String checkCodiceDocMedia(){
		try{
			String codice= getModel().getCodiceDocMedia().trim();
			Long idDocumento= getModel().getIdDocumento();
			Boolean c=gestioneComponentiService.checkCodEsistente(codice);
			if(idDocumento == -1){
				//la chiamata arriva da nuovo inserimento
				getModel().setCodEsistente(c);
				return SUCCESS;
			}else{
				//la chiamata arriva dal salvataggio della modifica
				if(c){
					//controllo che il codice trovato uguale non sia dell'oggetto che si sta modificando
					//(caso in cui il codice non venga modificato..)
					CrsDocumentiMedia cdm=gestioneComponentiService.cerca(CrsDocumentiMedia.class, idDocumento);
					if(codice.equalsIgnoreCase(cdm.getCodice().trim())){
						getModel().setCodEsistente(false);
					}else{
						getModel().setCodEsistente(true);
					}
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}


		return SUCCESS;
	}
	/*
	 * SALVA NUOVO DOCUMENTO
	 */
	public String salvaDocMedia(){
		CrsDocumentiMedia docMedia= null;
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");

		try {

			docMedia=getModel().getDocMedia();
			String[] b64= getModel().getBase64File().split(",");

			//Decoding in b64
			docMedia.setDoc(new BASE64Decoder().decodeBuffer(b64[1]));
			docMedia.setPrefisso(b64[0]+",");

			
			
			gestioneComponentiService.salva(docMedia);


		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	//scrive il doc nell'aside di modifica
	public String getDocMediaUpd(){
		try{
			long idDocMedia=getModel().getIdDocumento();
			CrsDocumentiMedia dm= gestioneComponentiService.cerca(CrsDocumentiMedia.class, idDocMedia);
			getModel().setDocMedia(dm);
			
			CrsTplDocMedia tpld= gestioneComponentiService.cerca(CrsTplDocMedia.class, dm.getIdCrsTplDocMedia());
			getModel().setOptionSelected(tpld);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	

	/*
	 * SALVA MODIFICA AL DOCUMENTO
	 */
	public String salvaModificaDocMedia(){
		try{

			CrsDocumentiMedia docMediaModificato= getModel().getDocMedia();
			//prendo file e estensione dal db per settarli nel doc modificato
			CrsDocumentiMedia docM= gestioneComponentiService.cerca(CrsDocumentiMedia.class, docMediaModificato.getId());
			docMediaModificato.setDoc(docM.getDoc());
			docMediaModificato.setNomeFile(docM.getNomeFile());
			docMediaModificato.setPrefisso(docM.getPrefisso());
			gestioneComponentiService.salva(docMediaModificato);
		}catch(Exception e){
			e.printStackTrace();
		}


		return SUCCESS;
	}
	/*
	 * ELIMINA DOCUMENTO
	 */
	public String eliminaDocMedia(){

		String listaId=getModel().getListId();
		String[] idDaCancellare=listaId.split(",");
		for(String i : idDaCancellare){
			CrsDocumentiMedia docM= gestioneComponentiService.cerca(CrsDocumentiMedia.class, Long.valueOf(i) );
			gestioneComponentiService.remove(docM);

		}

		return SUCCESS;
	}
	//LISTA CON FILTRO(data fine < data odierna)
	public String searchDocumentiMediaFiltro(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=user.getIdUtente();

		List<CrsDocumentiMedia> docMediaList =	gestioneComponentiService.getListDocMediaFiltro(getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());
		try{
			int total = gestioneComponentiService.countListDocMediaFiltro(getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(docMediaList==null){
				return SUCCESS;
			}

			for(Object o : docMediaList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
	}

	//scarica documento
	public String downloadDocumento(){
		long id=getModel().getIdDocumento();
		try{
			CrsDocumentiMedia doc=gestioneComponentiService.cerca(CrsDocumentiMedia.class, id);
			String docB64=new BASE64Encoder().encodeBuffer(doc.getDoc());
			getModel().setPrefix(doc.getPrefisso());
			getModel().setBase64File(docB64);
			getModel().setNomeDocumento(doc.getNomeFile());

			//myByte is my byte array

			
			getModel().setToBlob(doc.getDoc());
		}catch(Exception e){
			e.printStackTrace();
		}

		return SUCCESS;
	}

	@Override
	public DocumentiMediaPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public void setGestioneComponentiService(GestioneComponentiService gestioneComponentiService) {
		this.gestioneComponentiService = gestioneComponentiService;
	}

	public GestioneComponentiService getGestioneComponentiService() {
		return gestioneComponentiService;
	}





}
