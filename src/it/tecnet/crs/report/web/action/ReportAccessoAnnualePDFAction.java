package it.tecnet.crs.report.web.action;


import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPratiche;
import it.tecnet.crs.indicatori.campagna.CalcoloIndicatoriCampagnaService;
import it.tecnet.crs.report.jpa.dao.CampagnaInfoDto;
import it.tecnet.crs.report.service.ReportPDFService;
import it.tecnet.crs.report.web.bean.ReportPDFBean;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ModelDriven;
 
public class ReportAccessoAnnualePDFAction extends BaseAction implements ModelDriven<ReportPDFBean>{
	
	public class BaseReportBuilder extends PdfPageEventHelper {
	    protected BaseFont baseFont;
	    private PdfTemplate totalPages;
	    private float footerTextSize = 8f;
	    private int pageNumberAlignment = Element.ALIGN_CENTER;
	 
	    public BaseReportBuilder() {
	        super();
	        try {
				baseFont = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1257, 
				          BaseFont.EMBEDDED);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 
	    @Override
	    public void onOpenDocument(PdfWriter writer, Document document) {
	        totalPages = writer.getDirectContent().createTemplate(100, 100);
	        totalPages.setBoundingBox(new Rectangle(-20, -20, 100, 100));
	    }
	 
	    @Override
	    public void onEndPage(PdfWriter writer, Document document) {
	        PdfContentByte cb = writer.getDirectContent();
	        cb.saveState();
	        String text = String.format("Pagina %s di ", writer.getPageNumber());
	 
	        float textBase = document.bottom() - 20;
	        float textSize = baseFont.getWidthPoint(text, footerTextSize);
	         
	        cb.beginText();
	        cb.setFontAndSize(baseFont, footerTextSize);
	        if(Element.ALIGN_CENTER == pageNumberAlignment) {
	            cb.setTextMatrix((document.right() / 2), textBase);
	            cb.showText(text);
	            cb.endText();
	            cb.addTemplate(totalPages, (document.right() / 2) + textSize, textBase);
	        } else if(Element.ALIGN_LEFT == pageNumberAlignment) {
	            cb.setTextMatrix(document.left(), textBase);
	            cb.showText(text);
	            cb.endText();
	            cb.addTemplate(totalPages, document.left() + textSize, textBase);
	        } else {
	            float adjust = baseFont.getWidthPoint("0", footerTextSize);
	            cb.setTextMatrix(document.right() - textSize - adjust, textBase);
	            cb.showText(text);
	            cb.endText();
	            cb.addTemplate(totalPages, document.right() - adjust, textBase);
	        }
	        cb.restoreState();
	    }
	 
	    @Override
	    public void onCloseDocument(PdfWriter writer, Document document) {
	        totalPages.beginText();
	        totalPages.setFontAndSize(baseFont, footerTextSize);
	        totalPages.setTextMatrix(0, 0);
	        totalPages.showText(String.valueOf(writer.getPageNumber() - 1));
	        totalPages.endText();
	    }
	 
	    public void setPageNumberAlignment(int pageNumberAlignment) {
	        this.pageNumberAlignment = pageNumberAlignment;
	    }
	}
	
	
	//private static final Font intestazione = new Font(FontFamily.TIMES_ROMAN,15.0f,Font.BOLD,BaseColor.BLUE);
	//private static final Font sezioneFont = new Font(FontFamily.TIMES_ROMAN,15.0f,Font.BOLD,BaseColor.BLUE);
	//private BaseColor coloreIntestazioniTab =  BaseColor.CYAN;
	private BaseColor coloreIntestazioniTab =  new BaseColor(205,252,251);
	//private BaseColor alternaviteColorRow =  BaseColor.LIGHT_GRAY;
	private BaseColor alternaviteColorRow =  new BaseColor(222,234,246);
	
	//private static final Font intestazione = new Font(FontFamily.TIMES_ROMAN,15.0f,Font.BOLD,new BaseColor(0,176,240));
	private static final Font intestazione = new Font(FontFamily.TIMES_ROMAN,15.0f,Font.BOLD,new BaseColor(74,132,172));
	
	private static final Font sezioneFont = new Font(FontFamily.TIMES_ROMAN,15.0f,Font.BOLD,new BaseColor(0,176,240));
	
	private static final Font testoFont = new Font(FontFamily.TIMES_ROMAN,10.0f,Font.NORMAL,BaseColor.BLACK);
	private static final Font testoFontAlert =  new Font(FontFamily.TIMES_ROMAN,10.0f,Font.NORMAL,BaseColor.RED);
	private static final Font testoSmallFont = new Font(FontFamily.TIMES_ROMAN,8.0f,Font.NORMAL,BaseColor.BLACK);
	private static final Font testoPs = new Font(FontFamily.TIMES_ROMAN,7.0f,Font.BOLDITALIC,BaseColor.BLACK);
	
	//private static final Font intestazioneFont = new Font(FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,BaseColor.BLUE);
	private static final Font intestazioneFont = new Font(FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,new BaseColor(74,132,172));
	
	//private static final Font intestazione3 = new Font(FontFamily.TIMES_ROMAN,6.0f,Font.BOLD,BaseColor.BLUE);
	private static final Font intestazione3 = new Font(FontFamily.TIMES_ROMAN,6.0f,Font.BOLD,new BaseColor(74,132,172));
	private static final Font intestazione4 = new Font(FontFamily.TIMES_ROMAN,8.0f,Font.BOLD,new BaseColor(74,132,172));
	
	//private static final Font fontIntestazione = new Font(FontFamily.TIMES_ROMAN,20.0f,Font.BOLD,BaseColor.BLUE);
	//private static final Font fontIntestazione = new Font(FontFamily.TIMES_ROMAN,20.0f,Font.BOLD,new BaseColor(0,176,240));
	
	//private static final Font fontSede = new Font(FontFamily.TIMES_ROMAN,15.0f,Font.BOLD,BaseColor.BLUE);
	//private static final Font fontSede = new Font(FontFamily.TIMES_ROMAN,15.0f,Font.BOLD,new BaseColor(0,176,240));
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = Logger.getLogger(ReportAccessoAnnualePDFAction.class);
	
	private ReportPDFService reportPDFService;
	private CalcoloIndicatoriCampagnaService calcoloIndicatoriCampagnaService;
	//private AuAuditIndicatoriService auditIndicatoriService;	
	
	private ReportPDFBean reportPDBBean = new ReportPDFBean();
	private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	
	private static Font titoloReport = new Font(Font.FontFamily.HELVETICA, 12);
	private static Font titoloSezione = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	private static Font titoloFase = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
	private static Font descrizioneVarComp = new Font(Font.FontFamily.HELVETICA, 8);
	
	public Paragraph paragrafo;
	public String capoverso1;
	public String capoverso2;
	public String capoverso3;
	public String capoverso4;
	public String capoverso5;
	public String capoverso6;
	public String capoverso7;
	
	Phrase frase;
	
	public static DecimalFormat formatMigliaia = new DecimalFormat("###,###,###,##0.00");
	private Font verdanaFontIntestazione;
	private Font verdanaIntestazione;

	public ReportAccessoAnnualePDFAction(ReportPDFService reportPDFService, CalcoloIndicatoriCampagnaService calcoloIndicatoriCampagnaService) {
		super();
		this.setReportPDFService(reportPDFService);
		this.calcoloIndicatoriCampagnaService = calcoloIndicatoriCampagnaService;
		String verdana = ServletActionContext.getServletContext().getRealPath("/assets/VERDANAB.TTF");
		verdanaFontIntestazione = FontFactory.getFont(verdana,16.0f,Font.BOLD,new BaseColor(0,176,240));
		verdanaIntestazione = FontFactory.getFont(verdana,10.0f,Font.BOLD,new BaseColor(74,132,172));
	}
	
	/* OK */
	public static void createIntestazione(Document document, ReportAccessoPDFDto report) throws DocumentException{
			float[] columnWidths = {0.6F, 5};

			PdfPTable table = new PdfPTable(columnWidths);
			table.setWidthPercentage(100);
			try{
				Image img = null;
				try{
					String path = ServletActionContext.getServletContext().getRealPath("/assets/global/img/InpsLogoReport.png");
					img = Image.getInstance(path);
			    	img.scaleToFit(50, 100);
				}catch(Exception e ){
					log.equals(e.getMessage());
				}
				PdfPCell cellImage = new PdfPCell();
				if ( img != null) cellImage.addElement(img);
				cellImage.setRowspan(2);
				cellImage.setVerticalAlignment(Element.ALIGN_CENTER);
				cellImage.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				
				Paragraph p = new Paragraph();
				Phrase p1 = new Phrase("\nDIREZIONE CENTRALE AUDIT\n\n",intestazione);
				Phrase p2 = new Phrase("Area Audit operativi sul processo assicurato pensionato e sulle aree professionali\n\n",intestazioneFont);
				p.add(p1);
				p.add(p2);
		        PdfPCell cellDirezione = new PdfPCell(p);
		        cellDirezione.setColspan(3);
		        cellDirezione.setHorizontalAlignment(Element.ALIGN_CENTER);
	
		        
		        PdfPCell cellAccertamento = new PdfPCell(new Phrase("\nACCERTAMENTO TECNICO PREVENTIVO OBBLIGATORIO INVALIDITA' CIVILE\n\n",intestazione4));
		        cellAccertamento.setColspan(3);
		        cellAccertamento.setHorizontalAlignment(Element.ALIGN_CENTER);
		        
		       
				PdfPCell cellPostTitolo1 = new PdfPCell();
			    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // creo l'oggetto
			    String dataStr = sdf.format(new Date());
				
	
				PdfPCell cellPostTitolo2 = new PdfPCell(new Phrase("\n" + report.getSede() + ", "+ dataStr +"\n\n",intestazione3));
				cellPostTitolo2.setHorizontalAlignment(Element.ALIGN_LEFT);
				
		        table.addCell(cellImage);
		        table.addCell(cellDirezione);
		        table.addCell(cellAccertamento);
		        table.addCell(cellPostTitolo1);
		        table.addCell(cellPostTitolo2);
		        document.add(table);
			}catch(Exception e){
				log.error(e.getMessage());
			}
	}
	
	public static void main(String [ ] args) throws DocumentException, IOException
	{
		
		//PdfWriter.getInstance(document, new FileOutputStream(new File("C:\\Users\\c.carducci\\Sviluppo\\Allegato.pdf")));
		
		// Apro il documento
		
		//createPdf("C:\\Users\\c.carducci\\Sviluppo\\Allegato.pdf");
		//createIntestazioneAllegato(document);
		
	}

	public static PdfPCell getCellWithoutBorders(String testo, Font font, int align){
		PdfPCell cell = new PdfPCell(new Phrase(testo,font));
		cell.setHorizontalAlignment(align);
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}
	
	public static PdfPCell getCellWithtBorders(String testo, Font font, int align){
		return getCellWithtBordersColor(testo, font, align, BaseColor.WHITE);
	}
	
	public static PdfPCell getCellWithtBordersColor(String testo, Font font, int align,BaseColor color ){
		PdfPCell cell = new PdfPCell(new Phrase(testo,font));
		cell.setHorizontalAlignment(align);
		cell.setBorder(Rectangle.BOX);
		cell.setBackgroundColor(color);
		return cell;
	}
	
	public static PdfPCell getCellWithtBordersColorAndRowsSpan(String testo, Font font, int align,BaseColor color,int rowspan ){
		PdfPCell cell = new PdfPCell(new Phrase(testo,font));
		cell.setHorizontalAlignment(align);
		cell.setBorder(Rectangle.BOX);
		cell.setBackgroundColor(color);
		cell.setRowspan(rowspan);
		return cell;
	}
	
	public PdfPTable intestazione2(String testo){
        PdfPTable tableInizio = new PdfPTable(1);
        tableInizio.setWidthPercentage(100);
        tableInizio.getDefaultCell().setBorder(0);
        tableInizio.getDefaultCell().setBorderWidth(0f);
        tableInizio.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        tableInizio.addCell(getCellWithoutBorders("",intestazione,Element.ALIGN_CENTER));
        tableInizio.addCell(getCellWithoutBorders("",intestazione,Element.ALIGN_CENTER));
        tableInizio.addCell(getCellWithoutBorders(testo,intestazione,Element.ALIGN_CENTER));
        return tableInizio;
	}
	
	public PdfPTable sezione1(String testo, String intestazioneSezione){
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);
        table.getDefaultCell().setBorderWidth(0f);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.addCell(getCellWithoutBorders(intestazioneSezione,sezioneFont,Element.ALIGN_CENTER));
        table.addCell(getCellWithoutBorders(testo,testoFont,Element.ALIGN_LEFT));
        table.addCell(getCellWithoutBorders("",testoFont,Element.ALIGN_LEFT));
        table.addCell(getCellWithoutBorders("",testoFont,Element.ALIGN_LEFT));
        return table;
	}
	
	public PdfPTable sezione2(String testo, String intestazioneSezione){
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);
        table.getDefaultCell().setBorderWidth(0f);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.addCell(getCellWithoutBorders(intestazioneSezione,sezioneFont,Element.ALIGN_CENTER));
        table.addCell(getCellWithoutBorders(testo,testoFont,Element.ALIGN_MIDDLE));
        table.addCell(getCellWithoutBorders("",testoFont,Element.ALIGN_LEFT));
        table.addCell(getCellWithoutBorders("",testoFont,Element.ALIGN_LEFT));
        return table;
	}
	
	public PdfPTable sezione1Corpo(List<ReportAccessoPDFDto> rows){
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(80);
	    table.getDefaultCell().setBorder(0);
	    table.getDefaultCell().setBorderWidth(0f);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		float[] columnWidths = {5,2F };
		PdfPTable tableInner = new PdfPTable(columnWidths);
		tableInner.setWidthPercentage(60);
		tableInner.getDefaultCell().setBorder(1);
		tableInner.getDefaultCell().setBorderWidth(14f);
		tableInner.getDefaultCell().setBorder(Rectangle.BOX);
		    
		tableInner.addCell(getCellWithtBordersColor("Esito",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("Quantità",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		
		BaseColor baseColor = alternaviteColorRow; 
		for (ReportAccessoPDFDto item : rows) {
			if (baseColor == alternaviteColorRow)
				baseColor = BaseColor.WHITE;
			else
				baseColor = alternaviteColorRow;
			tableInner.addCell(getCellWithtBordersColor(item.getDescrizioneEsito() ,testoFont,Element.ALIGN_LEFT,baseColor));
			tableInner.addCell(getCellWithtBordersColor(item.getQuantita().toString() ,testoFont,Element.ALIGN_CENTER,baseColor));
		}

		table.addCell(tableInner);
		return table;
	}
	
	/* OK */
	public void creaIntestazioneCompleta(Document document, ReportAccessoPDFDto report) throws DocumentException{
		String anno = reportPDFService.getCampagnaAnno(report.getIdCampagna());
		List<String> listaSedi = reportPDFService.getSediByCampagna(report.getIdCampagna());
		List<CampagnaInfoDto> info = reportPDFService.getCampagnaInfoForReport(report.getIdCampagna());
		
		String testo = "\nGli accessi di Audit  effettuati dal 01/01/" + anno + " al 31/12/" + anno + " riguardano le seguenti sedi: \n\n";
		for (String sede : listaSedi) {
			testo += "    -    " + sede + "\n";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(); 
		sdf.applyPattern("dd/MM/yyyy");  
		String dataStr = sdf.format(info.get(0).getDataInzio()); 
		String data2Str = sdf.format(info.get(0).getDataFine());
	
		testo += "\n\ne sono costituiti da un campione di n. " + info.get(0).getNumPratiche() 
		+ " istanze di ATPO dell'invalidità civile definite dal " + dataStr + " al " 
		+ data2Str + ". Nei seguenti esiti:" ;
		document.add(sezione1(testo,""));
		List<ReportAccessoPDFDto> rowsRiepilogoIstanzelista = reportPDFService.getRiepilogoIstanzeAnnuale(report.getIdCampagna());
		document.add(sezione1Corpo(rowsRiepilogoIstanzelista));
		/*
        String testo = 	"L' accesso di Audit, effettuato dal " + report.getDataInizio() + " al " +  report.getDataFine() +
		" riguarda un campione costituito da n. " + report.getNumeroPraticheEsaminate() + " istanze " +
		"di ATPO dell' invalidità civile definite dal " + report.getDataInizioOsservazione() + 
		" al " + report.getDataFineOsservazione() + ". Nei seguenti esiti: \n\n";
		document.add(sezione1(testo,""));
		List<ReportAccessoPDFDto> rowsRiepilogoIstanzelista = reportPDFService.getRiepilogoIstanzeAnnuale(report.getIdSSessione());
		document.add(sezione1Corpo(rowsRiepilogoIstanzelista));
		*/
	}
	
	/* OK */
	public void creaConformitaProcessoCompleta(Document document, ReportAccessoPDFDto report) throws DocumentException{
		ReportAccessoPDFDto report2 = new ReportAccessoPDFDto();
		report2 = reportPDFService.getInccDescrizioneAnnuale(report.getIdCampagna());
		document.add(intestazione2("\nSezione 1 - Conformità del Processo"));		
		String testo = 	"Lo stato di conformità del processo è " + report2.getINCCDescrizione() + " con un valore dell'indicatore INCC* pari a " + formatMigliaia.format(report2.getINCC()) + ". Di seguito vengono riportate le fasi del processo a cui prestare maggiore attenzione:";
		document.add(sezione1(testo,""));
		
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(80);
	    table.getDefaultCell().setBorder(0);
	    table.getDefaultCell().setBorderWidth(0f);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    List<ReportAccessoPDFDto> listaOrder = reportPDFService.getRiepilogoFasiAnnuale(report.getIdCampagna());
	    Collections.sort(listaOrder, new ConformitaProcesoComparator());
	    //Collections.reverse(listaOrder); 
	    
	    int count = 1;
	    for (ReportAccessoPDFDto it : listaOrder) {
	    	if (count > 3) continue;
	    	table.addCell(getCellWithoutBorders("• " + it.getDescrizioneFase() + " ( INCC "+it.getRiepilogoINCC().toString()+" );",testoFont,Element.ALIGN_LEFT));
	    	count++;
		}
	    
	    document.add(table);
	    document.add(new Paragraph("\n"));
		String testo2 = "Di seguito viene illustrato il grado di conformità del processo per fasi – sintesi. ";
		document.add(sezione1(testo2,""));
		List<ReportAccessoPDFDto> lista = reportPDFService.getRiepilogoFasiAnnuale(report.getIdCampagna());
		document.add(creaConformitaProcessoCorpo( lista ));
		//document.add(new Paragraph("\n"));
		String finale = "Nella sezione 7 è presentata la conformità del processo nel dettaglio\n";
		document.add(sezione1(finale,""));
		document.add(new Paragraph("\n"));
		Paragraph ps = new Paragraph(
				"*Indicatore di conformità del processo INCC = indicatore di conformità dei comportamenti del processo, misurato da 0 a 1 (1 piena conformità, 0 nessuna conformità). "
				, testoPs);
		document.add(ps);
	}
	
	public PdfPTable creaConformitaProcessoCorpo(List<ReportAccessoPDFDto> rows) throws DocumentException{
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(80);
	    table.getDefaultCell().setBorder(0);
	    table.getDefaultCell().setBorderWidth(0f);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		
		float[] columnWidths = {5,2F };
		PdfPTable tableInner = new PdfPTable(columnWidths);
		tableInner.setWidthPercentage(60);
		tableInner.getDefaultCell().setBorder(1);
		tableInner.getDefaultCell().setBorderWidth(14f);
		tableInner.getDefaultCell().setBorder(Rectangle.BOX);
		    
		tableInner.addCell(getCellWithtBordersColor("Fase",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("INCC",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		
		BaseColor baseColor = alternaviteColorRow; 
		for (ReportAccessoPDFDto item : rows) {
			if (baseColor == alternaviteColorRow)
				baseColor = BaseColor.WHITE;
			else
				baseColor = alternaviteColorRow;
			tableInner.addCell(getCellWithtBordersColor(item.getDescrizioneFase() ,testoFont,Element.ALIGN_LEFT,baseColor));
			tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getRiepilogoINCC()),testoFont,Element.ALIGN_CENTER,baseColor));
		}

		table.addCell(tableInner);
		return table;
	}
	
	/* OK */
	public void creaRischioCompleta(Document document, ReportAccessoPDFDto report) throws DocumentException{
		document.add(intestazione2("\nSezione 2 - I rischi"));		
		String testo = 	"Si illustrano di seguito i rischi del processo con l'impatto degli eventi dannosi verificati:";
		document.add(sezione1(testo,""));
		List<ReportAccessoPDFDto> lista = reportPDFService.getRiepilogoRischiAnnuale(report.getIdCampagna());
		document.add(creaRischioCorpo( lista , report));
	}
	
	public PdfPTable creaRischioCorpo(List<ReportAccessoPDFDto> rows, ReportAccessoPDFDto report) throws DocumentException{
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(100);
	    table.getDefaultCell().setBorder(0);
	    table.getDefaultCell().setBorderWidth(0f);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		
		float[] columnWidths = {8F,1F,1F,1F };
		PdfPTable tableInner = new PdfPTable(columnWidths);
		tableInner.setWidthPercentage(60);
		tableInner.getDefaultCell().setBorder(1);
		tableInner.getDefaultCell().setBorderWidth(14f);
		tableInner.getDefaultCell().setBorder(Rectangle.BOX);
		    
		tableInner.addCell(getCellWithtBordersColor("Rischio e Espressione di Rischio",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("qtà",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("%",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("Impatto",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		
		Double totalNumRischi = 0D;
		for (ReportAccessoPDFDto item : rows) {
			totalNumRischi+=item.getNumSRischio();
		}
		
		BaseColor baseColor = alternaviteColorRow; 
		for (ReportAccessoPDFDto item : rows) {
			
			Long idMRischio = item.getIdMRischio();
			
			String descrizioneRischio = item.getDescrizioneRischio();
			String quantita = "";
			String suPs = "";
			String impatto = "";
			//NumberFormat decimalFormatter = new DecimalFormat("#0.00");
			DecimalFormat decimalFormatter = new DecimalFormat("###,###,###,##0.00");
			//2) estraggo le espressioni rischio per il rischio corrente
			List<ReportAccessoPDFDto> listaRisEspr = reportPDFService.getRisEsprByIdMNonConfAnnuale(idMRischio, report.getIdCampagna());
			for (ReportAccessoPDFDto reportAccessoPDFDto : listaRisEspr) {
				descrizioneRischio += "\n • " + reportAccessoPDFDto.getDescrizioneRisEspr();
				quantita += "\n" + reportAccessoPDFDto.getNumSRischio();
				suPs += "\n" + formatMigliaia.format(reportAccessoPDFDto.getSuPsPerc())+"%";
				impatto += "\n" + decimalFormatter.format(reportAccessoPDFDto.getImporto());
			}
				
			if (baseColor == alternaviteColorRow)
				baseColor = BaseColor.WHITE;
			else
				baseColor = alternaviteColorRow;
			/*
			tableInner.addCell(getCellWithtBordersColor(descrizioneRischio,testoFont,Element.ALIGN_LEFT,baseColor));
			tableInner.addCell(getCellWithtBordersColor(quantita ,testoFont,Element.ALIGN_CENTER,baseColor));
			tableInner.addCell(getCellWithtBordersColor(suPs+"%" ,testoFont,Element.ALIGN_CENTER,baseColor));
			tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(impatto) ,testoFont,Element.ALIGN_CENTER,baseColor));
			*/
			tableInner.addCell(getCellWithtBordersColor(descrizioneRischio,testoSmallFont,Element.ALIGN_LEFT,baseColor));
			tableInner.addCell(getCellWithtBordersColor(quantita ,testoSmallFont,Element.ALIGN_CENTER,baseColor));
			tableInner.addCell(getCellWithtBordersColor(suPs ,testoSmallFont,Element.ALIGN_CENTER,baseColor));
			tableInner.addCell(getCellWithtBordersColor(impatto,testoSmallFont,Element.ALIGN_RIGHT,baseColor));
		}

		table.addCell(tableInner);
		return table;
	}
	
	/* OK */
	public void creaStatoFascicoloCompleta(Document document, ReportAccessoPDFDto report) throws DocumentException{
		document.add(intestazione2("\nSezione 3 - Lo stato del fascicolo"));		
		String testo = 	"Si riepiloga lo stato del fascicolo: ";
		document.add(sezione1(testo,""));
		List<ReportAccessoPDFDto> fascicoloRows = reportPDFService.getRiepilogoFascicoloAnnuale(report.getIdCampagna());
		document.add(sezioneFascicoloCorpo(fascicoloRows));
		document.add(new Paragraph("\n"));
		List<ReportAccessoPDFDto> docRows = reportPDFService.getRiepilogoDocMancAnnuale(report.getIdCampagna());
		document.add(sezioneFascicoloDocCorpo(docRows));
	}
	
	public PdfPTable sezioneFascicoloCorpo(List<ReportAccessoPDFDto> rows){
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(100);
	    table.getDefaultCell().setBorder(0);
	    table.getDefaultCell().setBorderWidth(0f);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		float[] columnWidths = {5,2F };
		PdfPTable tableInner = new PdfPTable(columnWidths);
		tableInner.setWidthPercentage(60);
		tableInner.getDefaultCell().setBorder(1);
		tableInner.getDefaultCell().setBorderWidth(14f);
		tableInner.getDefaultCell().setBorder(Rectangle.BOX);
		    
		tableInner.addCell(getCellWithtBordersColor("Fascicolo elettronico o cartaceo ",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("N.",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		
		BaseColor baseColor = alternaviteColorRow; 
		for (ReportAccessoPDFDto item : rows) {
			if (baseColor == alternaviteColorRow)
				baseColor = BaseColor.WHITE;
			else
				baseColor = alternaviteColorRow;
			tableInner.addCell(getCellWithtBordersColor(item.getDescrizioneFascicolo() ,testoFont,Element.ALIGN_LEFT,baseColor));
			tableInner.addCell(getCellWithtBordersColor(item.getQuantita().toString() ,testoFont,Element.ALIGN_CENTER,baseColor));
		}

		table.addCell(tableInner);
		return table;
	}
	
	public PdfPTable sezioneFascicoloDocCorpo(List<ReportAccessoPDFDto> rows){
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(100);
	    table.getDefaultCell().setBorder(0);
	    table.getDefaultCell().setBorderWidth(0f);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		float[] columnWidths = {5,2F };
		PdfPTable tableInner = new PdfPTable(columnWidths);
		tableInner.setWidthPercentage(60);
		tableInner.getDefaultCell().setBorder(1);
		tableInner.getDefaultCell().setBorderWidth(14f);
		tableInner.getDefaultCell().setBorder(Rectangle.BOX);
		    
		tableInner.addCell(getCellWithtBordersColor("Documentazione mancante per fascicolo",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("N.",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		
		BaseColor baseColor = alternaviteColorRow; 
		for (ReportAccessoPDFDto item : rows) {
			if (baseColor == alternaviteColorRow)
				baseColor = BaseColor.WHITE;
			else
				baseColor = alternaviteColorRow;
			tableInner.addCell(getCellWithtBordersColor(item.getDescrizioneFascicolo() ,testoFont,Element.ALIGN_LEFT,baseColor));
			tableInner.addCell(getCellWithtBordersColor( item.getQuantita().toString() ,testoFont,Element.ALIGN_CENTER,baseColor));
		}

		table.addCell(tableInner);
		return table;
	}
	
	/* OK */
	public void creaGiudiziCompleta(Document document, ReportAccessoPDFDto report) throws DocumentException{
		document.add(intestazione2("\nSezione 4 - Esito dei giudizi del campione per tipo di difesa"));		
		String testo = 	"La sezione analizza gli esiti dei giudizi del campione per tipo di difesa.";
		document.add(sezione1(testo,""));
		
		List<ReportAccessoPDFDto> giudizi1WithDissensoRows = reportPDFService.getEsitoByTipoDifesaWithCodeAndDissensoAnnuale(report.getIdCampagna());
		//List<ReportAccessoPDFDto> giudizi1Rows = reportPDFService.getEsitoByTipoDifesaWithCode(report.getIdSSessione());
		document.add(creaGiudiziCompletaCorpo1(giudizi1WithDissensoRows));
		
		Font fontLeggenda = new Font();
		fontLeggenda.setSize(7);
		Paragraph parLegend1 = new Paragraph("Difesa Completa: Valutazione di autotutela/resistenza effettuata, costituzione in giudizio, visita peritale effettuata, osservazioni sanitarie rese", fontLeggenda);
		Paragraph parLegend2 = new Paragraph("Difesa Essenziale: Costituzione in giudizio, visita peritale effettuata", fontLeggenda);
		Paragraph parLegend3 = new Paragraph("Difesa Incompleta: Manca l'atto di costituzione e/o la visita peritale", fontLeggenda);
		Paragraph parLegend4 = new Paragraph("(I dissensi non sono considerati nella sezione)", fontLeggenda);
		com.itextpdf.text.List orderedList = new com.itextpdf.text.List();

		orderedList.setIndentationLeft(10);
		orderedList.setListSymbol("");
		orderedList.add(new ListItem(parLegend1));
		orderedList.add(new ListItem(parLegend2));
		orderedList.add(new ListItem(parLegend3));
		orderedList.add(new ListItem(parLegend4));
		document.add(orderedList);
		
		document.add(new Paragraph("\n"));
		document.add(creaGiudiziCompletaCorpo2( report, giudizi1WithDissensoRows));
		document.add(new Paragraph("\n"));
		String test2 = "Si illustra il riepilogo dei giudizi definiti che comportano erogazione della prestazione e pagamento delle spese legali e di CTU (sfavorevoli + parzialmente favorevoli) distinti tra:";
		document.add(sezione1(test2,""));
		List<ReportAccessoPDFDto> listaGiudizi = reportPDFService.getRiepilogoGiudiziAnnuale(report.getIdCampagna());
		document.add(creaGiudiziCompletaCorpo3(listaGiudizi,giudizi1WithDissensoRows));
		String test3 = "La tabella illustra i giudizi che comportano erogazione della prestazione e pagamento delle spese legali e di CTU (esito sfavorevole e parzialmente favorevole) distinti in relazione alla difesa espletata.";
		document.add(sezione1(test3,""));
	}
	
	public PdfPTable creaGiudiziCompletaCorpo1(List<ReportAccessoPDFDto> rows){
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(100);
	    table.getDefaultCell().setBorder(0);
	    table.getDefaultCell().setBorderWidth(0f);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		float[] columnWidths = {5,2F, 2F};
		PdfPTable tableInner = new PdfPTable(columnWidths);
		tableInner.setWidthPercentage(60);
		tableInner.getDefaultCell().setBorder(1);
		tableInner.getDefaultCell().setBorderWidth(14f);
		tableInner.getDefaultCell().setBorder(Rectangle.BOX);
		    
		tableInner.addCell(getCellWithtBordersColor("Tipo di difesa ",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("N.",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("%",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		
		BaseColor baseColor = alternaviteColorRow;
		Integer tot = 0;
		for (ReportAccessoPDFDto item : rows) {
			if(item.getQuantita() != null)
				tot = tot + item.getQuantita();
		}
		
		for (ReportAccessoPDFDto item : rows) {
			if (baseColor == alternaviteColorRow)
				baseColor = BaseColor.WHITE;
			else
				baseColor = alternaviteColorRow;
			
			String Descrizione = "";
			
			if (item.getDescrizioneEsito().toUpperCase().contains("COMPLETA"))
				Descrizione = "Difesa Completa";

			if (item.getDescrizioneEsito().toUpperCase().contains("ESSENZIALE"))
				Descrizione = "Difesa Essenziale";

			if (item.getDescrizioneEsito().toUpperCase().contains("INCOMPLETA"))
				Descrizione = "Difesa Incompleta";
			
			
			tableInner.addCell(getCellWithtBordersColor(Descrizione ,testoFont,Element.ALIGN_LEFT,baseColor));
			tableInner.addCell(getCellWithtBordersColor(item.getQuantita().toString() ,testoFont,Element.ALIGN_CENTER,baseColor));
			Double perc = 0D;
			if ( item.getQuantita() != null ){
				perc = ( item.getQuantita().doubleValue() / tot.doubleValue()) * 100;
			}
			
			tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(perc) + "%" ,testoFont,Element.ALIGN_CENTER,baseColor));
		}

		table.addCell(tableInner);
		return table;
	}

	public PdfPTable creaGiudiziCompletaCorpo2(ReportAccessoPDFDto report,List<ReportAccessoPDFDto> rows){
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(100);
	    table.getDefaultCell().setBorder(0);
	    table.getDefaultCell().setBorderWidth(0f);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		float[] columnWidths = {5,5,2F, 2F};
		PdfPTable tableInner = new PdfPTable(columnWidths);
		tableInner.setWidthPercentage(60);
		tableInner.getDefaultCell().setBorder(1);
		tableInner.getDefaultCell().setBorderWidth(14f);
		tableInner.getDefaultCell().setBorder(Rectangle.BOX);
		    
		tableInner.addCell(getCellWithtBordersColor("Tipo di difesa ",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("Esito",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("N.",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("%",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		
		
		Integer tot = 0;
		/*
		for (ReportAccessoPDFDto item : rows) {
			if(item.getQuantita() != null)
				tot = tot + item.getQuantita();
		}
		*/
		BaseColor baseColor = alternaviteColorRow; 
		int rowIndex = 1;
		for (ReportAccessoPDFDto item : rows) {
			tot = item.getQuantita();
			String descrizione = "";
			
			if (item.getDescrizioneEsito().toUpperCase().contains("COMPLETA"))
				descrizione = "Difesa Completa";
			
			if (item.getDescrizioneEsito().toUpperCase().contains("ESSENZIALE"))
				descrizione = "Difesa Essenziale";
			
			if (item.getDescrizioneEsito().toUpperCase().contains("INCOMPLETA"))
				descrizione = "Difesa Incompleta";
			
			tableInner.addCell(getCellWithtBordersColorAndRowsSpan( descrizione ,testoFont,Element.ALIGN_LEFT,baseColor,3));
			List<ReportAccessoPDFDto> listaDifesaIncompleta = reportPDFService.getTipoDifesaIncompletaAnnuale(report.getIdCampagna(), item.getTipoDifesa());
			for (ReportAccessoPDFDto reportAccessoPDFDto : listaDifesaIncompleta) {
				
				tableInner.addCell(getCellWithtBordersColor(reportAccessoPDFDto.getDescrizioneEsito() ,testoFont,Element.ALIGN_CENTER,baseColor));
				tableInner.addCell(getCellWithtBordersColor(reportAccessoPDFDto.getQuantita().toString() ,testoFont,Element.ALIGN_CENTER,baseColor));
				Double perc = reportAccessoPDFDto.getQuantita().doubleValue();
				if (tot > 0 && reportAccessoPDFDto.getQuantita() > 0 ) perc =( perc /tot) * 100;
				tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(perc) + "%" ,testoFont,Element.ALIGN_CENTER,baseColor));	
				rowIndex++;
			}
		}
		PdfPCell[] row1 = tableInner.getRow(1).getCells();
		PdfPCell[] row2 = tableInner.getRow(2).getCells();
		PdfPCell[] row3 = tableInner.getRow(3).getCells();
		//PdfPCell[] row4 = tableInner.getRow(4).getCells();
		//PdfPCell[] row5 = tableInner.getRow(5).getCells();
		//PdfPCell[] row6 = tableInner.getRow(6).getCells();
		PdfPCell[] row7 = tableInner.getRow(7).getCells();
		PdfPCell[] row8 = tableInner.getRow(8).getCells();
		PdfPCell[] row9 = tableInner.getRow(9).getCells();
		
		// COLORA COL 1
		PdfPCell cell = row1[0];
		cell.setBackgroundColor(BaseColor.WHITE);
		cell = row7[0];
		cell.setBackgroundColor(BaseColor.WHITE);
		
		row1[1].setBackgroundColor(BaseColor.WHITE);
		row1[2].setBackgroundColor(BaseColor.WHITE);
		row1[3].setBackgroundColor(BaseColor.WHITE);
		row2[1].setBackgroundColor(BaseColor.WHITE);
		row2[2].setBackgroundColor(BaseColor.WHITE);
		row2[3].setBackgroundColor(BaseColor.WHITE);
		row3[1].setBackgroundColor(BaseColor.WHITE);
		row3[2].setBackgroundColor(BaseColor.WHITE);
		row3[3].setBackgroundColor(BaseColor.WHITE);		

		row7[1].setBackgroundColor(BaseColor.WHITE);
		row7[2].setBackgroundColor(BaseColor.WHITE);
		row7[3].setBackgroundColor(BaseColor.WHITE);
		row8[1].setBackgroundColor(BaseColor.WHITE);
		row8[2].setBackgroundColor(BaseColor.WHITE);
		row8[3].setBackgroundColor(BaseColor.WHITE);
		row9[1].setBackgroundColor(BaseColor.WHITE);
		row9[2].setBackgroundColor(BaseColor.WHITE); 
		row9[3].setBackgroundColor(BaseColor.WHITE);
		
		
		table.addCell(tableInner);
		return table;
	}
	
	public PdfPTable creaGiudiziCompletaCorpo3(List<ReportAccessoPDFDto> rows , List<ReportAccessoPDFDto> giudizi1WithDissensoRows){
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(100);
	    table.getDefaultCell().setBorder(0);
	    table.getDefaultCell().setBorderWidth(0f);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		float[] columnWidths = {3F,1F,1F,1F,2F,2F,2F};
		PdfPTable tableInner = new PdfPTable(columnWidths);
		tableInner.setWidthPercentage(100);
		tableInner.getDefaultCell().setBorder(1);
		tableInner.getDefaultCell().setBorderWidth(14f);
		tableInner.getDefaultCell().setBorder(Rectangle.BOX);
		    
		tableInner.addCell(getCellWithtBordersColor("Difesa",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("N° giudizi",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("%",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("N° prestazioni",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("Imp. prestazioni",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("Spese legali",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("Spese CTU",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		
		double tot_U02_U03 = 0;
		double tot_U04 = 0;
		for (ReportAccessoPDFDto item: giudizi1WithDissensoRows) {
			if ("U04".equals(item.getTipoDifesa().trim())){
				tot_U04 += item.getQuantita();
			}else{
				tot_U02_U03 += item.getQuantita();
			}
		}
		
		NumberFormat decimalFormatter = new DecimalFormat("###,###,###,##0.00");
		
		BaseColor baseColor = alternaviteColorRow;
		for (ReportAccessoPDFDto item : rows) {
			if (baseColor == alternaviteColorRow)
				baseColor = BaseColor.WHITE;
			else
				baseColor = alternaviteColorRow;
			
			Double perc = 0D;
			if ("INCOMPLETA".equals(item.getDescrizioneEsito().trim()) /*U04*/){
				if(item.getNumeroGiudizi() != null && item.getNumeroGiudizi().intValue() > 0
						&& tot_U04 > 0)
					perc = ( item.getNumeroGiudizi().doubleValue()  / tot_U04) * 100;
			}else{
				if(item.getNumeroGiudizi() != null && item.getNumeroGiudizi().intValue() > 0
						&&  tot_U04 > 0)
					perc = (   item.getNumeroGiudizi().doubleValue() / tot_U02_U03) * 100;				
			}
			
			tableInner.addCell(getCellWithtBordersColor(" - " + item.getDescrizioneEsito() ,testoFont,Element.ALIGN_LEFT,baseColor));
			tableInner.addCell(getCellWithtBordersColor(item.getNumeroGiudizi().toString(),testoFont,Element.ALIGN_CENTER,baseColor));
			tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(perc) + "%",testoFont,Element.ALIGN_CENTER ,baseColor));
			tableInner.addCell(getCellWithtBordersColor(item.getNumeroPrestazioni().toString(),testoFont,Element.ALIGN_CENTER,baseColor));
			tableInner.addCell(getCellWithtBordersColor(decimalFormatter.format(item.getImportoPrestazioni()),testoFont,Element.ALIGN_RIGHT,baseColor));
			tableInner.addCell(getCellWithtBordersColor(decimalFormatter.format(item.getSpeseLegali()),testoFont,Element.ALIGN_RIGHT,baseColor));
			tableInner.addCell(getCellWithtBordersColor(decimalFormatter.format(item.getSpeseCtu().doubleValue())  ,testoFont,Element.ALIGN_RIGHT,baseColor));
			//
			//tableInner.addCell(getCellWithtBordersColor(" >> " + item.getDescrizioneEsito() ,testoFont,Element.ALIGN_LEFT,baseColor));
			//tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(perc) ,testoFont,Element.ALIGN_CENTER,baseColor));
		}

		table.addCell(tableInner);
		return table;
	}
	
	/* OK */
	public void creaRisultatiInRelTempoCompleta(Document document, ReportAccessoPDFDto report) throws DocumentException{
		document.add(intestazione2("\nSezione 5 - Risultati in relazione al tempo"));		
		String testo = 	"I risultati della sezione sono espressi come media degli intervalli temporali. Gli elementi in rosso indicano il superamento dei termini stabiliti nelle disposizioni per lo svolgimento delle attività. ";
		document.add(sezione1(testo,""));
		
		List<ReportAccessoPDFDto> lista = reportPDFService.getRisultatiByTempoAnnuale(report.getIdCampagna());
		document.add(creaRisultatiInRelTempoCorpo(lista));
	}
	
	public PdfPTable creaRisultatiInRelTempoCorpo(List<ReportAccessoPDFDto> rows){
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(100);
	    table.getDefaultCell().setBorder(0);
	    table.getDefaultCell().setBorderWidth(0f);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    
		float[] columnWidths = {5,2F,2F };
		PdfPTable tableInner = new PdfPTable(columnWidths);
		tableInner.setWidthPercentage(100);
		tableInner.getDefaultCell().setBorder(1);
		tableInner.getDefaultCell().setBorderWidth(14f);
		tableInner.getDefaultCell().setBorder(Rectangle.BOX);
		    
		tableInner.addCell(getCellWithtBordersColor("Arco temporale",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("Giorni",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		tableInner.addCell(getCellWithtBordersColor("NC",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab));
		
		BaseColor baseColor = alternaviteColorRow; 
		for (ReportAccessoPDFDto item : rows) {
			if (baseColor == alternaviteColorRow)
				baseColor = BaseColor.WHITE;
			else
				baseColor = alternaviteColorRow;
		
			
			tableInner.addCell(getCellWithtBordersColor(item.getDescrizioneTemporale() ,testoFont,Element.ALIGN_LEFT,baseColor));
			/*
			try{
				if (item.getNC()!= null && item.getNC().trim().length() > 0){
					tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getMediaGG()) ,testoFont,Element.ALIGN_CENTER,baseColor));
				}else{
					tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getMediaGG()) ,testoFont,Element.ALIGN_CENTER,baseColor));
				}
			}catch(Exception ex){
				tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getMediaGG()) ,testoFont,Element.ALIGN_CENTER,baseColor));
			}
			*/
			
			//System.out.println(item.getMediaGG());
			String test = item.getNC();
			if(test.trim().equals("> 3") &&  item.getMediaGG().doubleValue() > 3d){
				tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getMediaGG()) ,testoFontAlert,Element.ALIGN_CENTER,baseColor));
			}else if(test.trim().equals("< 5") &&  item.getMediaGG().doubleValue() < 5d){
				tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getMediaGG()) ,testoFontAlert,Element.ALIGN_CENTER,baseColor));
			}else if(test.trim().equals("> 0") &&  item.getMediaGG().doubleValue() < 0d){
				tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getMediaGG()) ,testoFontAlert,Element.ALIGN_CENTER,baseColor));
			}else if(test.trim().equals("> 30") &&  item.getMediaGG().doubleValue() > 30d){
				tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getMediaGG()) ,testoFontAlert,Element.ALIGN_CENTER,baseColor));
			}else if(test.trim().equals("> 120") &&  item.getMediaGG().doubleValue() > 120){
				tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getMediaGG()) ,testoFontAlert,Element.ALIGN_CENTER,baseColor));
			}else if(test.trim().equals("> 180") &&  item.getMediaGG().doubleValue() > 180){
				tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getMediaGG()) ,testoFontAlert,Element.ALIGN_CENTER,baseColor));
			}else{
				tableInner.addCell(getCellWithtBordersColor(formatMigliaia.format(item.getMediaGG()) ,testoFont,Element.ALIGN_CENTER,baseColor));
			}
			tableInner.addCell(getCellWithtBordersColor(item.getNC() ,testoFont,Element.ALIGN_CENTER,baseColor));
		}

		table.addCell(tableInner);
		return table;
	}
	
	/* OK */
	public void creaNonConformitaCompleta(Document document, ReportAccessoPDFDto report) throws DocumentException{
		document.add(intestazione2("\nSezione 6 – Conformità del processo nel dettaglio"));		
		//String testo = 	"I risultati della sezione sono espressi come media degli intervalli temporali tra le date considerate rilevanti ai fini della correttezza del processo. Gli elementi in rosso indicano il superamento dei tempi stabiliti nelle disposizioni per lo svolgimento delle attività. ";
		//document.add(sezione1(testo,""));
		//document.add(new Paragraph("\n"));
		
		List<ReportAccessoPDFDto> lista = reportPDFService.getRiepilogoFasiAnnuale(report.getIdCampagna());

		for(ReportAccessoPDFDto currFase : lista){

			String testoFase = currFase.getDescrizioneFase() + "     -     INCC: " + formatMigliaia.format(currFase.getRiepilogoINCC());
			
			Paragraph fase = new Paragraph(testoFase,intestazione);
			fase.setAlignment(1);
			document.add(fase);
			Long idFase = currFase.getIdFase();
			
			NumberFormat decimalFormatter = new DecimalFormat("#0.000");
			
			List<ReportAccessoPDFDto> listaNC = reportPDFService.getMNonConfByIdFaseAnnuale(idFase,report.getIdCampagna());
			for (ReportAccessoPDFDto nonConf : listaNC) {
				
				//System.out.println("SIAMO "+testoFase+" nonConf.getDescrizioneMNonConf()"+nonConf.getDescrizioneMNonConf());
				
				document.add(new Paragraph("\n"));
				
				
				float[] columnWidths = {1,10,2,2};
				PdfPTable tableInner = new PdfPTable(columnWidths);
				tableInner.setWidthPercentage(100);
				tableInner.getDefaultCell().setBorder(1);
				tableInner.getDefaultCell().setBorderWidth(14f);
				tableInner.getDefaultCell().setBorder(Rectangle.BOX);
				
				PdfPCell cellHeader1 = getCellWithtBordersColor("N.C.: " + nonConf.getDescrizioneMNonConf() ,testoFont,Element.ALIGN_LEFT,coloreIntestazioniTab);
				cellHeader1.setColspan(3);
				PdfPCell cellHeader2 = getCellWithtBordersColor("INCC: " + formatMigliaia.format(nonConf.getRiepilogoINCC()),testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab);
				cellHeader2.setColspan(1);
				tableInner.addCell(cellHeader1);
				tableInner.addCell(cellHeader2);
				
				PdfPCell cellSubHeader1 = getCellWithtBordersColor("",testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
				PdfPCell cellSubHeader2 = getCellWithtBordersColor("",testoFont,Element.ALIGN_LEFT,BaseColor.WHITE);
				PdfPCell cellSubHeader3 = getCellWithtBordersColor("qtà",testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
				PdfPCell cellSubHeader4 = getCellWithtBordersColor("%",testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
				tableInner.addCell(cellSubHeader1);
				tableInner.addCell(cellSubHeader2);
				tableInner.addCell(cellSubHeader3);
				tableInner.addCell(cellSubHeader4);
				
				Long idMNonConf = nonConf.getIdMNonConf();

				List<ReportAccessoPDFDto> listaVC = reportPDFService.getVarCompByIdMNonConfAnnuale(report.getIdCampagna(),idFase,idMNonConf);
				
				/*
				Integer TotaleListaVC = 0;
				for (ReportAccessoPDFDto item : listaVC) {
					TotaleListaVC+=item.getNumSVarComp();
				}
				*/

				for (ReportAccessoPDFDto varComp : listaVC) {
					BaseColor colore = BaseColor.WHITE;
					if (varComp.getColore()!=null){
						/*
						 	Giallo
							Verde
							Bianco
							Rosso
							Arancio
						 */
						
						if (varComp.getColore().toUpperCase().equals("Giallo".toUpperCase()))
							colore = new BaseColor(255,242,0);

						if (varComp.getColore().toUpperCase().equals("Verde".toUpperCase()))
							colore = new BaseColor(0,255,64);
						
						if (varComp.getColore().toUpperCase().equals("Rosso".toUpperCase()))
							colore = new BaseColor(255,0,0);
						
						if (varComp.getColore().toUpperCase().equals("Arancio".toUpperCase()))
							colore = new BaseColor(255,128,0);
					}
					
					PdfPCell field1 = getCellWithtBordersColor("",testoFont,Element.ALIGN_CENTER,colore);
					PdfPCell field2 = getCellWithtBordersColor(" • " +  varComp.getDescrizioneVarComp() ,testoFont,Element.ALIGN_LEFT,BaseColor.WHITE);
					PdfPCell field3 = getCellWithtBordersColor(varComp.getNumSVarComp().toString(),testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
					//PdfPCell field3 = getCellWithtBordersColor(TotaleListaVC.toString(),testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
					PdfPCell field4 = getCellWithtBordersColor(decimalFormatter.format(varComp.getPercentuale()) + "%" ,testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
					tableInner.addCell(field1);
					tableInner.addCell(field2);
					tableInner.addCell(field3);
					tableInner.addCell(field4);
				}
				
				document.add(tableInner);
				document.add(new Paragraph("\n"));
				if(currFase.getDescrizioneFase().trim().equals("Fase peritale") && nonConf.getDescrizioneMNonConf().trim().equalsIgnoreCase("gestione parere medico")){
					addParereMedicoSintesi(document, report, nonConf, idFase);
				}

			}
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			
			
			
		}
	}
	
	public void addParereMedicoSintesi(Document document, ReportAccessoPDFDto report,ReportAccessoPDFDto nonConf ,Long idFase) throws DocumentException{
		NumberFormat decimalFormatter = new DecimalFormat("#0.000");
		document.add(new Paragraph("\n"));
		
		
		
		float[] columnWidths = {1,10,2,2};
		PdfPTable tableInner = new PdfPTable(columnWidths);
		tableInner.setWidthPercentage(100);
		tableInner.getDefaultCell().setBorder(1);
		tableInner.getDefaultCell().setBorderWidth(14f);
		tableInner.getDefaultCell().setBorder(Rectangle.BOX);
		
		PdfPCell cellHeader1 = getCellWithtBordersColor("N.C.: gestione parere medico - sintesi" ,testoFont,Element.ALIGN_LEFT,coloreIntestazioniTab);
		cellHeader1.setColspan(3);
		PdfPCell cellHeader2 = getCellWithtBordersColor("",testoFont,Element.ALIGN_CENTER,coloreIntestazioniTab);
		cellHeader2.setColspan(1);
		tableInner.addCell(cellHeader1);
		tableInner.addCell(cellHeader2);
		
		PdfPCell cellSubHeader1 = getCellWithtBordersColor("",testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
		PdfPCell cellSubHeader2 = getCellWithtBordersColor("",testoFont,Element.ALIGN_LEFT,BaseColor.WHITE);
		PdfPCell cellSubHeader3 = getCellWithtBordersColor("qtà",testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
		PdfPCell cellSubHeader4 = getCellWithtBordersColor("%",testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
		tableInner.addCell(cellSubHeader1);
		tableInner.addCell(cellSubHeader2);
		tableInner.addCell(cellSubHeader3);
		tableInner.addCell(cellSubHeader4);
		
		Long idMNonConf = nonConf.getIdMNonConf();
		long hack = -1;
		List<ReportAccessoPDFDto> listaVC = reportPDFService.getVarCompByIdMNonConfAnnuale(report.getIdCampagna(),idFase,idMNonConf);
		List<ReportAccessoPDFDto> listaVCHack = reportPDFService.getVarCompByIdMNonConfAnnuale(report.getIdCampagna(),idFase,hack);
		
		double sintesi1=0;
		double sintesi2=0;
		double sintesi3=0;
		double sintesi4=0;
		Integer TotaleListaVC = 0;
		for (ReportAccessoPDFDto item : listaVC) {
			TotaleListaVC+=item.getNumSVarComp();
		}
		for (ReportAccessoPDFDto item : listaVCHack) {
			if(item.getCodiceVC()!= null && item.getCodiceVC().trim().equalsIgnoreCase("A406")){
				sintesi1+=item.getNumSVarComp();
			}
			if(item.getCodiceVC()!= null && item.getCodiceVC().trim().equalsIgnoreCase("A407")){
				sintesi2+=item.getNumSVarComp();
			}
			if(item.getCodiceVC()!= null && item.getCodiceVC().trim().equalsIgnoreCase("A453")){
				sintesi2+=item.getNumSVarComp();
			}
			if(item.getCodiceVC()!= null && item.getCodiceVC().trim().equalsIgnoreCase("A404")){
				sintesi3+=item.getNumSVarComp();
			}
			if(item.getCodiceVC()!= null && item.getCodiceVC().trim().equalsIgnoreCase("A451")){
				sintesi3+=item.getNumSVarComp();
			}

			if(item.getCodiceVC()!= null && item.getCodiceVC().trim().equalsIgnoreCase("A452")){
				sintesi4+=item.getNumSVarComp();
			}
			if(item.getCodiceVC()!= null && item.getCodiceVC().trim().equalsIgnoreCase("A454")){
				sintesi4+=item.getNumSVarComp();
			}
			if(item.getCodiceVC()!= null && item.getCodiceVC().trim().equalsIgnoreCase("A417")){
				sintesi4+=item.getNumSVarComp();
			}

		}
		
//		System.out.println("TotaleListaVC="+TotaleListaVC);
		double per1=sintesi1/TotaleListaVC*100;
		double per2=sintesi2/TotaleListaVC*100;
		double per3=sintesi3/TotaleListaVC*100;
		double per4=sintesi4/TotaleListaVC*100;
		
//		System.out.println("per1="+per1+"per2="+per2+"per3="+per3+"per4="+per4);
		/**/

		//for (ReportAccessoPDFDto varComp : listaVC) {
			BaseColor colore = BaseColor.WHITE;

			
		//}
//					Verde
					colore = new BaseColor(0,255,64);
					addField(tableInner, colore, "presenti pareri su bozza relazione e dissenso/accettazione", ""+sintesi1, ""+decimalFormatter.format(per1));
//					Arancio
					colore = new BaseColor(255,128,0);
					addField(tableInner, colore, "presente parere su bozza e assente parere dissenso/accettazione", ""+sintesi2, ""+decimalFormatter.format(per2));
//					Giallo
					colore = new BaseColor(255,242,0);
					addField(tableInner, colore, "presente solo parere di dissenso/accettazione", ""+sintesi3, ""+decimalFormatter.format(per3));
//					Rosso
					colore = new BaseColor(255,0,0);
					addField(tableInner, colore, "assenti entrambi i pareri sanitari",""+sintesi4, ""+decimalFormatter.format(per4));
		
		document.add(tableInner);
		document.add(new Paragraph("\n"));
	}
	private void addField(PdfPTable tableInner,BaseColor colore,String descrizione,String num,String percent){
//		presenti pareri su bozza relazione e dissenso/accettazione
		PdfPCell field1 = getCellWithtBordersColor("",testoFont,Element.ALIGN_CENTER,colore);
		PdfPCell field2 = getCellWithtBordersColor(" • " +  descrizione ,testoFont,Element.ALIGN_LEFT,BaseColor.WHITE);
		PdfPCell field3 = getCellWithtBordersColor(""+num,testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
		//PdfPCell field3 = getCellWithtBordersColor(TotaleListaVC.toString(),testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
		PdfPCell field4 = getCellWithtBordersColor(percent+" %" ,testoFont,Element.ALIGN_CENTER,BaseColor.WHITE);
		tableInner.addCell(field1);
		tableInner.addCell(field2);
		tableInner.addCell(field3);
		tableInner.addCell(field4);
		
	}
	
	/* OK */

	/* OK */
	public void generaPDF(Document document, ReportAccessoPDFDto report){
		try {
	        createIntestazione(document,report);
	        document.add(intestazione2("Report audit annuale nazionale\n\n"));
	        creaIntestazioneCompleta(document,report);
	        creaConformitaProcessoCompleta(document,report);
	        
	        creaRischioCompleta(document,report);
	        creaStatoFascicoloCompleta(document,report);
	        document.newPage();
	        creaGiudiziCompleta(document,report);
	        creaRisultatiInRelTempoCompleta(document,report);
	        creaNonConformitaCompleta(document,report);
	        document.add(new Paragraph("\n"));
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        String dataStr = sdf.format(new Date());
	        document.add(new Paragraph("Data: " + dataStr));
	        document.add(new Paragraph("Hanno partecipato\nDelegazione Audit:"));
	        document.add(new Paragraph("\n"));
	        document.add(new Paragraph("\n"));
	        document.add(new Paragraph("\n"));
	        document.add(new Paragraph("\n"));
	        document.add(new Paragraph("\n"));
	        document.add(new Paragraph("\n"));
	        Paragraph p = new Paragraph("DIRETTORE DELLA SEDE                                   IL DIRIGENTE DI AUDIT");
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(p);
	        
		}catch(Exception ex){
			log.error(ex.getMessage());
		}
	}
	

	private Paragraph ComponiIntroduzione(Document document, ReportAccessoPDFDto report) throws DocumentException {
		
		paragrafo = new Paragraph();
		
		List<ReportAccessoPDFDto> lista = reportPDFService.getRiepilogoIstanzeAnnuale(report.getIdSSessione());
		
		capoverso1 = "L' accesso di Audit, effettuato in data " + report.getDataInizio() +
							" riguarda un campione costituito da n. " + report.getNumeroPraticheEsaminate() + " istanze\n" +
							"di ATPO dell' invalidità civile definite dal " + report.getDataInizioOsservazione() + 
							" al " + report.getDataFineOsservazione() + ".\n" +
							"Le Pratiche sono state selezionate tra le seguenti: \n\n";
							
		
		paragrafo.add(capoverso1);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		createTableRiepilogoIstanze(lista, paragrafo);
		
		capoverso2 = "\nSede: " + report.getSede() + "\n\n";
		paragrafo.add(capoverso2);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		capoverso1 = "";
		capoverso2 = "";
		
		return paragrafo;
	}
	
	private Paragraph ComponiSezione1(Document document, ReportAccessoPDFDto report) throws DocumentException {
		
		paragrafo = new Paragraph();
		long idFase;
		long idMNonConf;
		
		List<ReportAccessoPDFDto> lista = reportPDFService.getRiepilogoFasiAnnuale(report.getIdSSessione());
		
		paragrafo.add("INCC: " + report.getINCC() + "\n");
		
		capoverso1 = "Indicatore di Conformità del Processo \n";
		capoverso2 = "INCC = indicatore di conformità dei comportamenti del processo, misurato da 0 a 1 \n" +
					"(valore 1 - piena conformità, valore 0 - nessuna conformità). \n" + 
					"Di seguito viene illustrato il grado di conformità del processo per fasi. \n" +
					"Riepilogo\n\n";
					
		
		paragrafo.add(capoverso1);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		paragrafo.add(capoverso2);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		createTableRiepilogoFasi(lista, paragrafo);
		
		// Stampo il dettaglio delle non conformità per singola fase.
		paragrafo.add(new Paragraph("\n\nDettaglio Fasi", titoloSezione));
		for(ReportAccessoPDFDto currFase : lista){
			
			idFase = currFase.getIdFase();
			
			//1) Stampo titolo fase
			createTableRiepilogoFasiDettaglio(currFase, paragrafo);
			
			//2) estraggo le non conformità per la fase corrente
			List<ReportAccessoPDFDto> listaNC = reportPDFService.getMNonConfByIdFaseAnnuale(idFase,report.getIdSSessione());
			
			paragrafo.add("NON CONFORMITA'");
			
			for(ReportAccessoPDFDto currNC : listaNC){
				
				idMNonConf = currNC.getIdMNonConf();
				
				//3) Stampo titolo non conformita
				createTableNonConformita(currNC, paragrafo);
				
				//4) estraggo le variabili comportamentali per la non conformita corrente
				//List<ReportAccessoPDFDto> listaVC_old = reportPDFService.getVarCompByIdMNonConf(idMNonConf);
				List<ReportAccessoPDFDto> listaVC = reportPDFService.getVarCompByIdMNonConfAnnuale(report.getIdSSessione(),idFase,idMNonConf);
				
				//5) stampo la lista delle variabili comportamentali con i relativi valori
				createTableVarComp(listaVC, paragrafo);
				
			}
			
			
		}
		
		capoverso1 = "";
		capoverso2 = "";
		
		return paragrafo;
	}
	
	private Paragraph ComponiSezione2(Document document, ReportAccessoPDFDto report) throws DocumentException {
		
		paragrafo = new Paragraph();
		long idMRischio;
		
		List<ReportAccessoPDFDto> lista = reportPDFService.getRiepilogoRischiAnnuale(report.getIdSSessione());
		
		capoverso1 = "Si illustrano di seguito i rischi del processo con l'impatto degli eventi dannosi verificati \n\n" +
					"RISCHI                                                                                             IMPATTO\n\n";
		
		paragrafo.add(capoverso1);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		// Stampo il dettaglio dei rischi.
		for(ReportAccessoPDFDto currRischio : lista){
			
			idMRischio = currRischio.getIdMRischio();
			
			//1) Stampo titolo rischio
			paragrafo.add(new Paragraph(currRischio.getDescrizioneRischio() +"\n", titoloFase));
			
			//2) estraggo le espressioni rischio per il rischio corrente
			List<ReportAccessoPDFDto> listaRisEspr = reportPDFService.getRisEsprByIdMNonConfAnnuale(idMRischio, report.getIdSSessione());
				
			//3) stampo la lista delle espressioni di rischio con i relativi valori
			createTableRisEspr(listaRisEspr, paragrafo);
				
			
			
		}
		
		capoverso1 = "";
		
		return paragrafo;
	}
	
	private Paragraph ComponiSezione3(Document document, ReportAccessoPDFDto report) throws DocumentException {
		
		paragrafo = new Paragraph();
		
		// recupero il fascicolo
		List<ReportAccessoPDFDto> lista = reportPDFService.getRiepilogoFascicoloAnnuale(report.getIdSSessione());
		
		capoverso1 = "Si riepiloga lo stato del Fascicolo \n\n" +
					"Fascicolo Elettronico \n\n";
		
		paragrafo.add(capoverso1);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		// Stampo il dettaglio del fascicolo elettronico.
		createTableFascicoloElettronico(lista, paragrafo);
		
		// recupero il docManc
		List<ReportAccessoPDFDto> listaDocManc = reportPDFService.getRiepilogoDocMancAnnuale(report.getIdSSessione());
		
		capoverso2 = "Documentazione mancante per fascicolo cartaceo/elettronico\n\n";
		
		paragrafo.add(capoverso2);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		// Stampo il dettaglio del docManc.
		createTableFascicoloElettronico(listaDocManc, paragrafo);
		
		capoverso1 = "";
		capoverso2 = "";
		
		return paragrafo;
	}
	
	private Paragraph ComponiSezione4(Document document, ReportAccessoPDFDto report) throws DocumentException {
		
		paragrafo = new Paragraph();
		
		// recupero il fascicolo
		List<ReportAccessoPDFDto> lista = reportPDFService.getEsitoByTipoDifesaAnnuale(report.getIdSSessione());
		
		List<ReportAccessoPDFDto> listaDifesaIncompleta = reportPDFService.getTipoDifesaIncompletaAnnuale(report.getIdSSessione(), "U04");
		
		capoverso1 = "La Sezione analizza gli Esiti dei giudizi del campione per tipo di difesa. \n\n";
		
		paragrafo.add(capoverso1);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		// Stampo gli esiti per tipo difesa.
		createTableEsitiByTipoDifesa(lista, paragrafo);
		
		capoverso4 = "Difesa Incompleta";
		paragrafo.add(capoverso4);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		// Stampo la difesa incompleta.
		createTableDifesaIncompleta(listaDifesaIncompleta, paragrafo);
		
		// recupero i giudizi
		List<ReportAccessoPDFDto> listaGiudizi = reportPDFService.getRiepilogoGiudiziAnnuale(report.getIdSSessione());
		
		capoverso2 = "\nSi illustra il riepilogo dei giudizi definiti che comportano erogazione della prestazione\n" + 
					"e pagamento delle spese legali e di CTU (sfavorevoli + parzialmente favorevoli) ditinti tra: \n" +
					"- Difesa Completa + Essenziale\n" +
					"- Difesa incompleta";
		
		paragrafo.add(capoverso2);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		// Stampo il dettaglio del docManc.
		createTableRiepilogoGiudizi(listaGiudizi, paragrafo);
		
		capoverso3= "\nLa tabella illustra i giudizi che comportano erogazione della prestazione e pagamento delle\n" +
					"spese legali e di CTU (esito sfavorevole e parzialmente favorevole) \n " +
					"distinti in relazione alla difesa espletata.\n\n";
		
		paragrafo.add(capoverso3);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		
		capoverso1 = "";
		capoverso2 = "";
		capoverso3 = "";
		
		return paragrafo;
	}
	
	private Paragraph ComponiSezione5(Document document, ReportAccessoPDFDto report) throws DocumentException {
		
		paragrafo = new Paragraph();
		
		// recupero il fascicolo
		List<ReportAccessoPDFDto> lista = reportPDFService.getRisultatiByTempoAnnuale(report.getIdSSessione());
		
		capoverso1 = "i risultati in relazione al tempo sono espressi come media degli intervalli temporali di \n" +
					"periodo considerati rilevanti ai fini della correttezza del processo. Gli elementi in rosso \n" +
					"indicano il superamento dei tempi stabiliti nelle disposizioni per lo svolgimento delle attività.\n\n";
		
		paragrafo.add(capoverso1);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		// Stampo gli esiti per tipo difesa.
		createTableRisultatiByTempo(lista, paragrafo);
		
		capoverso1 = "";
		
		return paragrafo;
	}
	
	private Paragraph ComponiSezione6(Document document, ReportAccessoPDFDto report) throws DocumentException {
		
		paragrafo = new Paragraph();
		
		// recupero la lista dei delegati
		List<String> listaAuditors = reportPDFService.getListaAuditorsAnnuale(report.getIdSSessione());
		String auditors = "";
		for(String auditor: listaAuditors){
			auditors += auditor + "\n";
		}
		
		capoverso1 = "\nDirigente: " + report.getDirigente() + "\n\n";
		
		paragrafo.add(capoverso1);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		capoverso2 = "Delegazione Audit: " + auditors;
		
		paragrafo.add(capoverso2);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		capoverso1 = "";
		capoverso2 = "";
		
		return paragrafo;
	}


	
	public void generaPDFAllegato(Document document, ReportAccessoPDFDto report,List<AtpoPratiche> objects, long idCampagna) throws Exception{
		paragrafo = null;
		try {
			
			//document.add(new Paragraph("Area Audit operativi sul processo assicurato pensionato e sulle aree professionali\n\n", titoloReport));		
			createIntestazione(document,report);
			
			String allegato = "\nAllegato 1\nEsiti del totale dei giudizi in relazione al tipo di difesa\n";
			//paragrafo = new Paragraph(allegato,fontIntestazione);
			paragrafo = new Paragraph(allegato,verdanaFontIntestazione);
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			document.add(paragrafo);
			String sede = "Riepilogo sedi " + report.getSede() + ".\n";
			// paragrafo = new Paragraph(sede,intestazione);
			paragrafo = new Paragraph(sede,verdanaIntestazione);

			paragrafo.setAlignment(Element.ALIGN_CENTER);
			document.add(paragrafo);
			
            ComponiSezione1Allegato(document, objects, idCampagna);
            
            

        }catch (Exception e) {
            log.error(e.getMessage());
        } 		
	}
	
	private void ComponiSezione1Allegato(Document document, List<AtpoPratiche> objects, long idCampagna) throws Exception {
		
		paragrafo = new Paragraph();		
		document.add(paragrafo);
		
		paragrafo = new Paragraph();
		
		capoverso1 ="";// "All.1 - Esiti del totale dei giudizi in relazione al tipo di difesa sede di:	" + report.getSede() + "\n" +
						//	"periodo di riferimento dal " + report.getDataInizioOsservazione() + 
						//	" al " + report.getDataFineOsservazione() + ".\n";
		capoverso2 = "";//"Giudizi definiti su Sisco: \n\n";
								
		
//		int count = reportPDFService.getGiudiziDefinitiviSiscoByCampagnaTotale(idCampagna , " 'U02' , 'U03' , 'U04' ", report.getSede());
		List<Object[]> listaEtichette = reportPDFService.getGiudiziDefinitiviEtichetteAnnuale();
		/*List<ReportAccessoPDFDto> listaU03 = reportPDFService.getGiudiziDefinitiviSiscoByCampagna(idCampagna, "U03", report.getSede());
		List<ReportAccessoPDFDto> listaU04 = reportPDFService.getGiudiziDefinitiviSiscoByCampagna(idCampagna, "U04", report.getSede());
		*/
		paragrafo.add(capoverso1);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		document.add(paragrafo);
		long difesaCompleta=0;
		long difesaEssenziale=0;
		long difesaIncompleta=0;
		
		
		long difesaCompletaPar=0;
		long difesaCompletaFav=0;
		long difesaCompletaSFav=0;
		
		long difesaEssenzialePar=0;
		long difesaEssenzialeFav=0;
		long difesaEssenzialeSFav=0;
		
		long difesaIncompletaPar=0;
		long difesaIncompletaFav=0;
		long difesaIncompletaSFav=0;
		
		for(AtpoPratiche pratica : objects){
			boolean isEssenziale=true;
			if(pratica.getPresenzaVisitePeritali()!= null && pratica.getPresenzaVisitePeritali().trim().equals("SI")){
				if(pratica.getConPresenzaAttoCostituzione()!= null && pratica.getConPresenzaAttoCostituzione().trim().equals("SI")){
					if(pratica.getPresenzaParereAutotutela() != null && pratica.getPresenzaParereAutotutela().trim().equals("SI")){
						if((pratica.getParereSuBozzaCTU() != null && pratica.getParereSuBozzaCTU().trim().equals("SI")) || (pratica.getParereDissensoAccettazione() != null )){
							isEssenziale=false;
							difesaCompleta++;
							if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("FAVOREVOLE ALL'ISTITUTO")){
								difesaCompletaFav++;
							}
							if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("SFAVOREVOLE ALL'ISTITUTO")){
								difesaCompletaSFav++;
							}
							if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("PARZIALMENTE FAVOREVOLE")){
								difesaCompletaPar++;
							}
							
						}	
						
					}	
				}	
			}
			if(pratica.getConPresenzaAttoCostituzione()!= null && pratica.getConPresenzaAttoCostituzione().trim().equals("NO")){
				if(pratica.getPresenzaVisitePeritali()!= null && pratica.getPresenzaVisitePeritali().trim().equals("SI")){
					difesaIncompleta++;
					isEssenziale=false;
					if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("FAVOREVOLE ALL'ISTITUTO")){
						difesaIncompletaFav++;
					}
					if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("SFAVOREVOLE ALL'ISTITUTO")){
						difesaIncompletaSFav++;
					}
					if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("PARZIALMENTE FAVOREVOLE")){
						difesaIncompletaPar++;
					}
				}
			}
			if(pratica.getConPresenzaAttoCostituzione()!= null && pratica.getConPresenzaAttoCostituzione().trim().equals("SI")){
				if(pratica.getPresenzaVisitePeritali()!= null && pratica.getPresenzaVisitePeritali().trim().equals("NO")){
					difesaIncompleta++;
					isEssenziale=false;
					if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("FAVOREVOLE ALL'ISTITUTO")){
						difesaIncompletaFav++;
					}
					if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("SFAVOREVOLE ALL'ISTITUTO")){
						difesaIncompletaSFav++;
					}
					if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("PARZIALMENTE FAVOREVOLE")){
						difesaIncompletaPar++;
					}
				}
			}
			if(pratica.getConPresenzaAttoCostituzione()!= null && pratica.getConPresenzaAttoCostituzione().trim().equals("NO")){
				if(pratica.getPresenzaVisitePeritali()!= null && pratica.getPresenzaVisitePeritali().trim().equals("NO")){
					difesaIncompleta++;
					isEssenziale=false;
					if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("FAVOREVOLE ALL'ISTITUTO")){
						difesaIncompletaFav++;
					}
					if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("SFAVOREVOLE ALL'ISTITUTO")){
						difesaIncompletaSFav++;
					}
					if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("PARZIALMENTE FAVOREVOLE")){
						difesaIncompletaPar++;
					}
				}
			}
			if(isEssenziale){
				if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("FAVOREVOLE ALL'ISTITUTO")){
					difesaEssenzialeFav++;
				}
				if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("SFAVOREVOLE ALL'ISTITUTO")){
					difesaEssenzialeSFav++;
				}
				if(pratica.getEsito()!= null && pratica.getEsito().trim().equalsIgnoreCase("PARZIALMENTE FAVOREVOLE")){
					difesaEssenzialePar++;
				}
			}
			 
		}
		difesaEssenziale=objects.size()-difesaCompleta-difesaIncompleta;
		
		paragrafo = new Paragraph();
		paragrafo.add(new Paragraph("\nGiudizi definiti su Sisco per tipologia di difesa - "+objects.size()+"\n", titoloSezione));
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		createTableGiudiziSisco(paragrafo, "Difesa Completa", objects.size(), difesaCompleta);
		createTableGiudiziSisco(paragrafo, "Difesa Essenziale", objects.size(), difesaEssenziale);
		createTableGiudiziSisco(paragrafo, "Difesa Incompleta", objects.size(), difesaIncompleta);

		document.add(paragrafo);
		
		paragrafo = new Paragraph();
		paragrafo.add(new Paragraph("\nGiudizi definiti per tipologia di esito (favorevole, parzialmente favorevole, sfavorevole). \n", titoloSezione));
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		document.add(paragrafo);
		
		paragrafo = new Paragraph();
		paragrafo.add(capoverso2);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		
		capoverso4 = "\tDifesa Completa";
		paragrafo.add(capoverso4);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		//List<ReportAccessoPDFDto> listaU02 = reportPDFService.getGiudiziDefinitiviSiscoByCampagna(idCampagna, "U02");
		createTableGiudiziSisco(paragrafo, "Istanze con Esito Favorevole", difesaCompleta, difesaCompletaFav);
		createTableGiudiziSisco(paragrafo, "Istanze con Esito Parzialmente favorevole", difesaCompleta, difesaCompletaPar);
		createTableGiudiziSisco(paragrafo, "Istanze con Esito Sfavorevole", difesaCompleta, difesaCompletaSFav);
		
		//createTableGiudiziSisco(listaU02, paragrafo);
//		List<ReportAccessoPDFDto> listaU02S = reportPDFService.getGiudiziDefinitiviSiscoByCampagnaAndSede(idCampagna, "'U02'", report.getSede());
//		createTableGiudiziSiscoForSede(listaU02S, paragrafo,listaEtichette , count, "U02");
		
		capoverso5 = "\tDifesa Essenziale";
		paragrafo.add(capoverso5);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		createTableGiudiziSisco(paragrafo, "Istanze con Esito Favorevole", difesaEssenziale, difesaEssenzialeFav);
		createTableGiudiziSisco(paragrafo, "Istanze con Esito Parzialmente favorevole", difesaEssenziale, difesaEssenzialePar);
		createTableGiudiziSisco(paragrafo, "Istanze con Esito Sfavorevole", difesaEssenziale, difesaEssenzialeSFav);

		//List<ReportAccessoPDFDto> listaU03 = reportPDFService.getGiudiziDefinitiviSiscoByCampagna(idCampagna, "U03", report.getSede());
		//createTableGiudiziSisco(listaU03, paragrafo);
//		List<ReportAccessoPDFDto> listaU03S = reportPDFService.getGiudiziDefinitiviSiscoByCampagnaAndSede(idCampagna, "'U03'", report.getSede());
//		createTableGiudiziSiscoForSede(listaU03S, paragrafo,listaEtichette , count, "U03");
		
		capoverso6 = "\tDifesa Incompleta";
		paragrafo.add(capoverso6);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		createTableGiudiziSisco(paragrafo, "Istanze con Esito Favorevole", difesaIncompleta, difesaIncompletaFav);
		createTableGiudiziSisco(paragrafo, "Istanze con Esito Parzialmente favorevole", difesaIncompleta, difesaIncompletaPar);
		createTableGiudiziSisco(paragrafo, "Istanze con Esito Sfavorevole", difesaIncompleta, difesaIncompletaSFav);
		//List<ReportAccessoPDFDto> listaU04 = reportPDFService.getGiudiziDefinitiviSiscoByCampagna(idCampagna, "U04", report.getSede());
		//createTableGiudiziSisco(listaU04, paragrafo);
//		List<ReportAccessoPDFDto> listaU04S = reportPDFService.getGiudiziDefinitiviSiscoByCampagnaAndSede(idCampagna, "'U04'", report.getSede());
//		createTableGiudiziSiscoForSede(listaU04S, paragrafo,listaEtichette , count, "U04");
		
		capoverso1 = "";
		capoverso2 = "";
		capoverso3 = "";
		capoverso4 = "";
		capoverso5 = "";
		capoverso6 = "";
		
		long compEss = difesaCompletaSFav+ difesaCompletaPar+difesaEssenzialeSFav+difesaEssenzialePar;
		long totEss = compEss + difesaCompletaFav + difesaEssenzialeFav;
//		 
		document.add(paragrafo);
		ComponiSezione2Allegato(document, totEss, difesaIncompletaSFav+difesaIncompletaPar+difesaIncompletaFav, compEss, difesaIncompletaSFav+difesaIncompletaPar);
	}
	
	private Paragraph ComponiSezione2Allegato(Document document, double totEss,double  tot, double compEss, double incomp) throws DocumentException {
		
		paragrafo = new Paragraph();
		String p = "\n\n\nGiudizi definiti che comportano erogazione della prestazione e delle spese\n"+
					"( esito sfavorevole + parzialmente favorevole per tipo difesa ).";
		
		paragrafo.add(new Paragraph(p, titoloSezione));
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		
		//document.add(paragrafo);
		
		
		/*
		paragrafo = new Paragraph();
		capoverso2 = "- esito sfavorevole + parzialmente favorevole - per tipo difesa. \n\n";
		paragrafo.add(capoverso2);
		paragrafo.setAlignment(Element.ALIGN_LEFT);
		*/
		
		// recupero i giudizi
		//List<ReportAccessoPDFDto> listaGiudizi = reportPDFService.getRiepilogoGiudiziByCampagna(idCampagna);
		//List<ReportAccessoPDFDto> giudizi1WithDissensoRows = reportPDFService.getEsitoByTipoDifesaWithCodeAndDissensoByCampagna(2);
		//createTableRiepilogoGiudizi2(listaGiudizi, paragrafo, giudizi1WithDissensoRows);
		
		//List<ReportAccessoPDFDto> listaU0X = reportPDFService.getGiudiziDefinitiviSiscoByCampagnaAndSedeWithOutFavorevoli(idCampagna, " 'U02', 'U03', 'U04' ", report.getSede());
		createTableRiepilogoGiudizi2BySede(paragrafo, totEss, tot, compEss, incomp);
		
		document.add(paragrafo);
		
		capoverso1 = "";
		capoverso2 = "";

		
		return paragrafo;
	}
	
	private static void createTableRiepilogoFasi(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{80,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Fase"));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		//c1.setBorder(0);
		c1.setBorderWidthBottom(1);
		c1.setBorderWidthLeft(0);
		c1.setBorderWidthRight(0);
		c1.setBorderWidthTop(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("INCC"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBorderWidthBottom(1);
		c1.setBorderWidthLeft(0);
		c1.setBorderWidthRight(0);
		c1.setBorderWidthTop(0);
		table.addCell(c1);
		table.setHeaderRows(1);
		
		for(ReportAccessoPDFDto curr : listaReport){
			
			PdfPCell cella1 = new PdfPCell(new Phrase(curr.getDescrizioneFase()));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(String.valueOf(curr.getRiepilogoINCC())));
			cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			
		}
		
		paragrafo.add(table);
	
	}
	
	private static void createTableRiepilogoFasiDettaglio(ReportAccessoPDFDto report, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{80,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell cella1 = new PdfPCell(new Phrase("\n" + report.getDescrizioneFase(), titoloFase));
		cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cella1.setBorder(0);
		
		PdfPCell cella2 = new PdfPCell(new Phrase(String.valueOf("\n" + report.getRiepilogoINCC())));
		cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cella2.setBorder(0);
		
		table.addCell(cella1);
		table.addCell(cella2);
			
		paragrafo.add(table);
	
	}
	
	private static void createTableNonConformita(ReportAccessoPDFDto report, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{80,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell cella1 = new PdfPCell(new Phrase("\n\t\t" + report.getDescrizioneMNonConf()));
		cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cella1.setBorder(0);
		
		PdfPCell cella2 = new PdfPCell(new Phrase(String.valueOf("\n" + report.getRiepilogoINCC())));
		cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cella2.setBorder(0);
		
		table.addCell(cella1);
		table.addCell(cella2);
			
		paragrafo.add(table);
	
	}

	private static void createTableVarComp(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{80,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		String descrizione = "";
		for(ReportAccessoPDFDto curr : listaReport){
			
			descrizione = "- " + curr.getDescrizioneVarComp().trim() + " " + curr.getNumSVarComp()+" su " + curr.getNumSNonConf();
			
			PdfPCell cella1 = new PdfPCell(new Phrase(descrizione, descrizioneVarComp));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getPercentuale()) + "%", descrizioneVarComp));
			cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			
		}
		
		paragrafo.add(table);
	
	}
	
	private static void createTableRisEspr(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{80,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		String descrizione = "";
		for(ReportAccessoPDFDto curr : listaReport){
			
			descrizione = "- " + curr.getDescrizioneRisEspr().trim() + " (pratiche: " + curr.getNumSRischio() + ")\n" +
							"€ " + curr.getImporto();
			
			PdfPCell cella1 = new PdfPCell(new Phrase(descrizione, descrizioneVarComp));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getSuPsPerc()) + "%", descrizioneVarComp));
			cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			
		}
		
		paragrafo.add(table);
	
	}
	
	private static void createTableFascicoloElettronico(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{80,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		for(ReportAccessoPDFDto curr : listaReport){
			
			PdfPCell cella1 = new PdfPCell(new Phrase(curr.getDescrizioneFascicolo(), descrizioneVarComp));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getQuantita()), descrizioneVarComp));
			cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			
		}
		
		paragrafo.add(table);
	
	}
	
	private static void createTableEsitiByTipoDifesa(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{80,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		for(ReportAccessoPDFDto curr : listaReport){
			
			String Descrizione = "";
			
			if (curr.getDescrizioneEsito().toUpperCase().contains("COMPLETA"))
				Descrizione = "Difesa Completa *";

			if (curr.getDescrizioneEsito().toUpperCase().contains("ESSENZIALE"))
				Descrizione = "Difesa Essenziale **";

			if (curr.getDescrizioneEsito().toUpperCase().contains("INCOMPLETA"))
				Descrizione = "Difesa Incompleta ***";
			
			PdfPCell cella1 = new PdfPCell(new Phrase(Descrizione, descrizioneVarComp));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getQuantita()), descrizioneVarComp));
			cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			
		}
		
		paragrafo.add(table);
		Paragraph parLegend = new Paragraph();
		parLegend.add(new Phrase("* valutazione di autotutela/resistenza effettuata, costituzione in giudizio, visita peritale\n**"));
		paragrafo.add(parLegend);
	
	}
	
	private static void createTableRiepilogoGiudizi(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(6);
		table.setWidths(new int[]{30,20,20,40,20,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Difesa"));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("N.Giudizi"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("%"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("N.Prestazioni"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Importo"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Spese Legali"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		table.setHeaderRows(1);
		
		for(ReportAccessoPDFDto curr : listaReport){
			
			PdfPCell cella1 = new PdfPCell(new Phrase(curr.getDescrizioneEsito(), descrizioneVarComp));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getNumeroGiudizi()), descrizioneVarComp));
			cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			
			PdfPCell cella3 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getPercentuale()) + "%", descrizioneVarComp));
			cella3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella3.setBorder(0);
			
			PdfPCell cella4 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getNumeroPrestazioni()), descrizioneVarComp));
			cella4.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella4.setBorder(0);
			
			PdfPCell cella5 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getImportoPrestazioni()), descrizioneVarComp));
			cella5.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella5.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella5.setBorder(0);
			
			PdfPCell cella6 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getSpeseLegali()), descrizioneVarComp));
			cella6.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella6.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			table.addCell(cella3);
			table.addCell(cella4);
			table.addCell(cella5);
			table.addCell(cella6);
			
		}
		
		paragrafo.add(table);
	
	}
	
	private static void createTableDifesaIncompleta(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(4);
		table.setWidths(new int[]{20,60,25,25});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Esito"));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("N.Pratiche"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Percentuale"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		table.setHeaderRows(1);
		
		Double totale = 0D;
		// CICLO PER DETERMINARE LA QUANTITA TOTALE DEGLI INCOMPLETI
		for(ReportAccessoPDFDto curr : listaReport){
			totale=totale + curr.getQuantita();
		}
		
		
		for(ReportAccessoPDFDto curr : listaReport){
			
			PdfPCell cella4 = new PdfPCell(new Phrase(""));
			cella4.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella4.setBorder(0);
			
			PdfPCell cella1 = new PdfPCell(new Phrase(curr.getDescrizioneEsito(), descrizioneVarComp));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getQuantita()), descrizioneVarComp));
			cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			
			Double perc = (curr.getQuantita() / totale) * 100;
			
			//PdfPCell cella3 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getPercentuale()) + "%", descrizioneVarComp));
			PdfPCell cella3 = new PdfPCell(new Phrase(formatMigliaia.format(perc) + "%", descrizioneVarComp));
			cella3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella3.setBorder(0);
			
			table.addCell(cella4);
			table.addCell(cella1);
			table.addCell(cella2);
			table.addCell(cella3);
			
		}
		
		paragrafo.add(table);
	
	}
	
	private static void createTableRisultatiByTempo(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(3);
		table.setWidths(new int[]{90,15,15});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Arco Temporale"));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Media GG"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("N.C."));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		table.setHeaderRows(1);
		
		for(ReportAccessoPDFDto curr : listaReport){
			
			PdfPCell cella1 = new PdfPCell(new Phrase("- " + curr.getDescrizioneTemporale(), descrizioneVarComp));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getMediaGG()), descrizioneVarComp));
			cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			
			PdfPCell cella3 = new PdfPCell(new Phrase(curr.getNC(), descrizioneVarComp));
			cella3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella3.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			table.addCell(cella3);
			
		}
		
		paragrafo.add(table);
	
	}
	
	private static void createTableRiepilogoIstanze(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[]{60,40});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		for(ReportAccessoPDFDto curr : listaReport){
			
			PdfPCell cella1 = new PdfPCell(new Phrase("\t- " + curr.getDescrizioneEsito()));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase("(" + formatMigliaia.format(curr.getQuantita()) + ")"));
			cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			
		}
		
		paragrafo.add(table);
	
	}
	
	
	/**
	 * ************************************************
	 * ********* TABELLE ALLEGATO REPORT **************
	 * ************************************************
	 */
	
	private static void createTableGiudiziSiscoForSede(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo
			, List<Object[]> listaEtichette, int totaleGiudizi, String tipoDifesa) throws DocumentException {
		PdfPTable table = new PdfPTable(4);
		table.setWidths(new int[]{20,70,10,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		Integer totale = 0;
		for(ReportAccessoPDFDto curr : listaReport){
			totale += curr.getQuantita();
		}
		
		DecimalFormat  percentFormatter = new DecimalFormat("0.00%");
		
		
		for (Object[] objects : listaEtichette) {
			String tipoDifesaItem = (String)objects[1];
			String codiceChiusuraCorretto = (String)objects[0];
			if ( !"4".equals(codiceChiusuraCorretto)){
				int quantita = 0;
				for (ReportAccessoPDFDto item : listaReport) {
					if (item.getDescrizioneEsito().equals(codiceChiusuraCorretto)){
						quantita = item.getQuantita();
					}
				}
				
				PdfPCell cella1 = new PdfPCell(new Phrase(""));
				cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cella1.setBorder(0);
				
				PdfPCell cella2 = new PdfPCell(new Phrase(tipoDifesaItem));
				cella2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cella2.setBorder(0);
				DecimalFormat formatSimpleMigliaia = new DecimalFormat("###,##0");
				PdfPCell cella3 = new PdfPCell(new Phrase("(" + formatSimpleMigliaia.format(quantita) + ")"));
				cella3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cella3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cella3.setBorder(0);
				
				Double perc = 0d;
				
				if ( quantita != 0 )
					perc = quantita / totale.doubleValue();
				
				PdfPCell cella4 = new PdfPCell(new Phrase(percentFormatter.format(perc)));
				cella4.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cella4.setVerticalAlignment(Element.ALIGN_RIGHT);
				cella4.setBorder(0);
				
				table.addCell(cella1);
				table.addCell(cella2);
				table.addCell(cella3);
				table.addCell(cella4);
			}
		}
		
		paragrafo.add(table);
	
	}
	
	private static void createTableGiudiziSisco(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo) throws DocumentException {
		PdfPTable table = new PdfPTable(4);
		table.setWidths(new int[]{20,70,10,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		Integer totale = 0;
		for(ReportAccessoPDFDto curr : listaReport){
			totale += curr.getQuantita();
		}
		
		DecimalFormat  percentFormatter = new DecimalFormat("0.00%");
		
		for(ReportAccessoPDFDto curr : listaReport){
			
			PdfPCell cella1 = new PdfPCell(new Phrase(""));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(curr.getDescrizioneEsito()));
			cella2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			DecimalFormat formatSimpleMigliaia = new DecimalFormat("###,##0");
			PdfPCell cella3 = new PdfPCell(new Phrase("(" + formatSimpleMigliaia.format(curr.getQuantita()) + ")"));
			cella3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella3.setBorder(0);
			
			Double perc = 0d;
			if ( curr.getQuantita().doubleValue() != 0 )
				perc = curr.getQuantita().doubleValue() / totale.doubleValue();
				
			PdfPCell cella4 = new PdfPCell(new Phrase(percentFormatter.format(perc)));
			cella4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cella4.setVerticalAlignment(Element.ALIGN_RIGHT);
			cella4.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			table.addCell(cella3);
			table.addCell(cella4);
			
		}
		
		paragrafo.add(table);
	
	}
	private static void createTableGiudiziSisco( Paragraph paragrafo,String descrizione,double totale,double qt) throws DocumentException {
		PdfPTable table = new PdfPTable(4);
		table.setWidths(new int[]{20,70,10,20});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
//		Integer totale = 0;
//		Integer qt = 0;
//		for(ReportAccessoPDFDto curr : listaReport){
//			totale += curr.getQuantita();
//		}
		
		DecimalFormat  percentFormatter = new DecimalFormat("0.00%");
		
//		for(ReportAccessoPDFDto curr : listaReport){
			
			PdfPCell cella1 = new PdfPCell(new Phrase(""));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(descrizione));
			cella2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			DecimalFormat formatSimpleMigliaia = new DecimalFormat("###,##0");
			PdfPCell cella3 = new PdfPCell(new Phrase("" + formatSimpleMigliaia.format(qt) + ""));
			cella3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella3.setBorder(0);
			
			Double perc = 0d;
			if ( qt != 0 )
				perc = qt / totale;
				
			PdfPCell cella4 = new PdfPCell(new Phrase(percentFormatter.format(perc)));
			cella4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cella4.setVerticalAlignment(Element.ALIGN_RIGHT);
			cella4.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			table.addCell(cella3);
			table.addCell(cella4);
			
//		}
		
		paragrafo.add(table);
	
	}
	
	private static void createTableRiepilogoGiudizi2BySede( Paragraph paragrafo,double totEss,double  tot, double compEss, double incomp) throws DocumentException {
			
		PdfPTable table = new PdfPTable(3);
		table.setWidths(new int[]{70,30,30});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Totale Giudizi per Tipo Difesa"));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("N.Giudizi"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("% Giudizi"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		table.setHeaderRows(1);
		
//		double tot_U02_U03 = 0;
//		double tot_U04 = 0;
//		double tot = 0;
		
//		for (ReportAccessoPDFDto item: listaReport) {
//			if(!item.getDescrizioneEsito().equals("4")){
//				if ("U04".equals(item.getTipoDifesa().trim())){
//					tot_U04 += item.getQuantita();
//				}else{
//					tot_U02_U03 += item.getQuantita();
//				}
//			}
//		}
//		
//		for (ReportAccessoPDFDto item: listaReport) {
//			if(!item.getDescrizioneEsito().equals("4")){
//				tot += item.getQuantita();
//			}
//		}
		
		
		PdfPCell cella1 = new PdfPCell(new Phrase("COMPLETA + ESSENZIALE"));
		cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cella1.setBorder(0);
		
		PdfPCell cella2 = new PdfPCell(new Phrase(formatMigliaia.format(compEss)));
		cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cella2.setBorder(0);
		
		Double perc = (compEss / totEss);
		perc = perc * 100;
		DecimalFormat formatMigliaiaLocal = new DecimalFormat("###,###,###,##0.000");
		PdfPCell cella3 = new PdfPCell(new Phrase(formatMigliaiaLocal.format(perc ) + "%"));
		cella3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cella3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cella3.setBorder(0);
		
		table.addCell(cella1);
		table.addCell(cella2);
		table.addCell(cella3);
		
		
		PdfPCell cella4 = new PdfPCell(new Phrase("INCOMPLETA"));
		cella4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cella4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cella4.setBorder(0);
		
		PdfPCell cella5 = new PdfPCell(new Phrase(formatMigliaia.format(incomp)));
		cella5.setHorizontalAlignment(Element.ALIGN_CENTER);
		cella5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cella5.setBorder(0);
		
		perc = (incomp / tot);
		perc = perc * 100;
		PdfPCell cella6 = new PdfPCell(new Phrase(formatMigliaiaLocal.format(perc) + "%"));
		cella6.setHorizontalAlignment(Element.ALIGN_CENTER);
		cella6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cella6.setBorder(0);
		
		table.addCell(cella4);
		table.addCell(cella5);
		table.addCell(cella6);
		
		paragrafo.add(table);
		
	}
	
	private static void createTableRiepilogoGiudizi2(List<ReportAccessoPDFDto> listaReport, Paragraph paragrafo, List<ReportAccessoPDFDto> giudizi1WithDissensoRows) throws DocumentException {
		PdfPTable table = new PdfPTable(3);
		table.setWidths(new int[]{70,30,30});
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Totale Giudizi per Tipo Difesa"));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("N.Giudizi"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("% Giudizi"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setPaddingBottom(6);
		c1.setBorder(0);
		table.addCell(c1);
		table.setHeaderRows(1);
		
		double tot_U02_U03 = 0;
		double tot_U04 = 0;
		for (ReportAccessoPDFDto item: giudizi1WithDissensoRows) {
			if ("U04".equals(item.getTipoDifesa().trim())){
				tot_U04 += item.getQuantita();
			}else{
				tot_U02_U03 += item.getQuantita();
			}
		}
		
		for(ReportAccessoPDFDto curr : listaReport){
			
			Double perc = 0D;
			if ("INCOMPLETA".equals(curr.getDescrizioneEsito().trim()) ){
				if(curr.getNumeroGiudizi() != null && curr.getNumeroGiudizi().intValue() > 0
						&& tot_U04 > 0)
					perc = ( curr.getNumeroGiudizi().doubleValue()  / tot_U04);
			}else{
				if(curr.getNumeroGiudizi() != null && curr.getNumeroGiudizi().intValue() > 0
						&&  tot_U04 > 0)
					perc = (   curr.getNumeroGiudizi().doubleValue() / tot_U02_U03);				
			}
			
			
			PdfPCell cella1 = new PdfPCell(new Phrase(curr.getDescrizioneEsito()));
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella1.setBorder(0);
			
			PdfPCell cella2 = new PdfPCell(new Phrase(formatMigliaia.format(curr.getNumeroGiudizi())));
			cella2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella2.setBorder(0);
			
			DecimalFormat formatMigliaiaLocal = new DecimalFormat("###,###,###,##0.000");
			PdfPCell cella3 = new PdfPCell(new Phrase(formatMigliaiaLocal.format(perc) + "%"));
			cella3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cella3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cella3.setBorder(0);
			
			table.addCell(cella1);
			table.addCell(cella2);
			table.addCell(cella3);
			
		}
		
		paragrafo.add(table);
	
	}
	
	@Override
	public ReportPDFBean getModel() {
		// TODO Auto-generated method stub
		return reportPDBBean;
	}
	
	public ReportPDFService getReportPDFService() {
		return reportPDFService;
	}


	public void setReportPDFService(ReportPDFService reportPDFService) {
		this.reportPDFService = reportPDFService;
	}

	class ConformitaProcesoComparator implements Comparator<ReportAccessoPDFDto>{
		@Override
		public int compare(ReportAccessoPDFDto o1,
				ReportAccessoPDFDto o2) {
			 return o1.getRiepilogoINCC().compareTo(o2.getRiepilogoINCC());
		}

	}
	
	
	/* ***********************************************************************
	 * 
	 * METODI DI ACTION ------
	 * 
	 * *********************************************************************** 
	 */
	
	/**
	 * *****************************************
	 * 		ACTION PER REPORT ACCESSO ATPO
	 * *****************************************
	 */
	public String getReportAccessoAnnualePDF(){
		
		Document document = new Document();
		ReportAccessoPDFDto report = new ReportAccessoPDFDto();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		
		long idCampagna = user.getIdCampagna();
		String idCampagnaS = (String)request.getParameter("idCampagna");
		
		if (idCampagnaS != null )
			idCampagna = Long.parseLong(idCampagnaS);
		
		// idCampagna = 2; // OKKIO DA LEVARE PER TEST
		
		try {
			
			//PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
			PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
		
			writer.setPageEvent(new BaseReportBuilder());
			// Eseguo la query che estrae i dati da visualizzare in stampa
			report = reportPDFService.getReportAccessoAnnualePDF(idCampagna);
			report.setIdCampagna(idCampagna);
			
			// Apro il documento
			document.open();
			// Imposto e implemento il pdf
			generaPDF(document, report);

			// Chiudo il documento
			document.close();
			getModel().setExportPDFFileName("ReportAccessoAnnualeATPO.pdf");
			getModel().setExportPDFFile(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
		
	}
	
	
	/**
	 * *************************************************
	 * 		ACTION PER ALLEGATO REPORT ACCESSO ATPO
	 * *************************************************
	 */
	
	public String getAllegatoReportAccessoPDFAnnuale(){
		
		Document document = new Document();
		ReportAccessoPDFDto report = new ReportAccessoPDFDto();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		
		long idCampagna = user.getIdCampagna();
		String idCampagnaS = (String)request.getParameter("idCampagna");
		
		if (idCampagnaS != null )
			idCampagna = Long.parseLong(idCampagnaS);
		
		//idCampagna = 2;
		 
		//calcoloIndicatoriCampagnaService.calcolaIndicatoriCampagna(idCampagna);
		//long idSSessione = user.getIdSSessione();
		
		try {
			
			//PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
			PdfWriter.getInstance(document, byteArrayOutputStream);
		
			// Eseguo la query che estrae i dati da visualizzare in stampa
			String campagnaDescrizione = reportPDFService.getAllegatoReportAccessoPDFAnnuale(idCampagna);
			report.setSede(campagnaDescrizione);
			List<AtpoPratiche> objects = reportPDFService.getReportAllegatiPDFAnnuale(idCampagna);
			// Apro il documento
			document.open();
			// Imposto e implemento il pdf
			generaPDFAllegato(document,report, objects, user.getIdCampagna());
			// Chiudo il documento
			document.close();
			
			getModel().setExportPDFFileName("AllegatoReportAccessoATPO_Annuale.pdf");
			getModel().setExportPDFFile(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
		
	}

}

