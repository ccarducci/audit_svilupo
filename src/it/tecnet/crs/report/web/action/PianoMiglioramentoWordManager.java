package it.tecnet.crs.report.web.action;

import it.tecnet.crs.report.service.PianoMiglioramentoWordService;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.struts2.ServletActionContext;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

public class PianoMiglioramentoWordManager {

	DecimalFormat  percentFormatter = new DecimalFormat("#0.00%");
	//NumberFormat decimalFormatter = new DecimalFormat("#0.00");
	DecimalFormat decimalFormatter = new DecimalFormat("###,###,###,##0.00");
	DecimalFormat numFormatter = new DecimalFormat("###,###,###,##0");
	
	public final String COLOR_VERDE =  "35c774";
	public final String COLOR_GIALLO = "FFFF00";
	public final String COLOR_ARANCIO = "ed9831";
	public final String COLOR_ROSSO = "ea4520";
	public final String COLOR_BIANCO = "ffffff";
	
	public final String COLOR_BLUE =  "357EC7";
	public final String COLOR_BLACK = "000000";
	private final int TABLE_SIZE_WITDTH = 40000;
	private final int FONT_SIZE_TITOLO = 24;
	private final int FONT_SIZE_SOTTOTITOLO = 14;
	private final int FONT_SIZE_SOTTO_SEZIONE = 18;
	
	private List<RowNonConformita> listaRowNonConformita = new ArrayList<RowNonConformita>();
	private List<RowRischoVarComp> listaRowRischiVarComp = new ArrayList<RowRischoVarComp>();
	
	private PianoMiglioramentoWordService pianoMiglioramentoWordService;
	private ReportAccessoPDFDto report;
		
	public void setPianoMiglioramentoWordService(
			PianoMiglioramentoWordService pianoMiglioramentoWordService) {
		this.pianoMiglioramentoWordService = pianoMiglioramentoWordService;
	}
	
	public void setReport(ReportAccessoPDFDto report) {
		this.report = report;
	}

	public static void main(String[] args) {
		Double percent = 89.4737;
		System.out.println(String.format("%s%.2f%s","Percentage In Exam: " ,percent,"%"));
	}

	public ByteArrayOutputStream creaWord() {
		String inFile = ServletActionContext.getServletContext().getRealPath("/assets/template.docx");
		ByteArrayOutputStream doc=null;	
		
		try {

			//XWPFDocument document = new XWPFDocument();
			XWPFDocument document = new XWPFDocument(OPCPackage.open(inFile));
			
			// CREO INTESTAZIONE
			//creaIntestazione(document);
			//createSpazio(document);
			createTitolo(document, "Piano di miglioramento", COLOR_BLUE);
			createSpazio(document);
			createNonConformita(document, COLOR_BLUE);
			createSpazio(document);
			createRischiConEspr(document, COLOR_BLUE);
			//createRischi(document, COLOR_BLUE);
			createSpazio(document);
			createEsitiGiudizi(document, COLOR_BLUE);
			createSpazio(document);
			createFascicolo(document, COLOR_BLUE);
			createSpazio(document);
			createIntervalliTemporali(document, COLOR_BLUE);
			createSpazio(document);
			createSpazio(document);
			createSpazio(document);
			
			XWPFParagraph para = document.createParagraph();
			XWPFRun rh = para.createRun();
			rh.addBreak();
			rh.setText("Data:");
			rh.addBreak();
			rh.addBreak();
			rh.addBreak();
			rh.setText("Hanno partecipato");
			rh.addBreak();
			rh.setText("Delegazione di Audit:");
			
			para = document.createParagraph();
			para.setVerticalAlignment(TextAlignment.CENTER);
			rh = para.createRun();
			rh.addBreak();
			rh.addBreak();
			rh.addBreak();
			rh.addBreak();
			rh.addBreak();
			rh.addBreak();
			rh.setText("       DIRETTORE DELLA SEDE 	                                                                                   IL DIRIGENTE DI AUDIT");
	

			// document.write(new FileOutputStream(new File("C:\\temp\\test.docx")));
			doc = new ByteArrayOutputStream();
			document.write(doc);
		} catch (Exception e) {
			
		}
		return doc;
	}

	@SuppressWarnings("unused")
	private void creaIntestazione(XWPFDocument document) {
		XWPFTable tab = document.createTable(3, 2);
		tab.setWidth(TABLE_SIZE_WITDTH);
		
		// LARGHEZZA
		setWidthTable(tab, 10000);

		// LARGHEZZA CELLA
		setCollWith(tab, 0, 0, 2000);
		
		// ALTEZZA CELLA
		setRowHeight(tab, 0, 500);
		setRowHeight(tab, 1, 500);

		// MERGE COL
		CTVMerge vmerge0 = CTVMerge.Factory.newInstance();
		vmerge0.setVal(STMerge.RESTART);
		tab.getRow(0).getCell(0).getCTTc().addNewTcPr().setVMerge(vmerge0);
		CTVMerge vmerge1 = CTVMerge.Factory.newInstance();
		vmerge1.setVal(STMerge.CONTINUE);
		tab.getRow(1).getCell(0).getCTTc().addNewTcPr().setVMerge(vmerge1);

		// ALLINIAMENTO CELLA 
		tab.getRow(0).getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
		tab.getRow(1).getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
		tab.getRow(2).getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
		
		tab.getRow(0).getCell(1).removeParagraph(0);
		tab.getRow(1).getCell(1).removeParagraph(0);
		
	    // create Paragraph
        XWPFParagraph paragraph = tab.getRow(0).getCell(1).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setVerticalAlignment(TextAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setText("DIREZIONE CENTRALE AUDIT");
        run.setColor(COLOR_BLUE);
        
        String testo = "Area Audit operativi sul processo assicurato pensionato e sulle aree";
        // create Paragraph
        paragraph = tab.getRow(0).getCell(1).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setVerticalAlignment(TextAlignment.CENTER);
        run = paragraph.createRun();
        run.setFontSize(10);
        run.setText(testo);
        run.setColor(COLOR_BLUE);
        
        paragraph = tab.getRow(1).getCell(1).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setVerticalAlignment(TextAlignment.CENTER);
        run = paragraph.createRun();
        run.setFontSize(12);
        run.setText("ACCERTAMENTO TECNICO PREVENTIVO OBBLIGATORIO INVALIDITA’ CIVILE");
        run.setColor(COLOR_BLUE);
    
        // DATA E LUOGO
        paragraph = tab.getRow(2).getCell(1).addParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT );
        paragraph.setVerticalAlignment(TextAlignment.CENTER);
        run = paragraph.createRun();
        run.setFontSize(8);
        run.setText("DATA E LUOGO: ");
        run.setColor(COLOR_BLUE);
        tab.getRow(2).getCell(1).removeParagraph(0);
        
        XWPFParagraph par;
        if(tab.getRow(0).getCell(0).getParagraphs().size() == 0){
        	par = tab.getRow(0).getCell(0).addParagraph();
        }else{
        	par = tab.getRow(0).getCell(0).getParagraphArray(0);
        }
        if(par!= null){
        	
        }
	}
	
	private void createSpazio(XWPFDocument document) {
		// create Paragraph
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run = paragraph.createRun();
		run.setFontSize(FONT_SIZE_TITOLO);
	}

	private void createTitolo(XWPFDocument document, String testo,
			String rgbColor) {
		// create Paragraph
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run = paragraph.createRun();
		run.setFontSize(FONT_SIZE_TITOLO);
		if (rgbColor == null)
			run.setColor("000000");
		else
			run.setColor(rgbColor);
		run.setText(testo);
	}

	private void createSottoTitolo(XWPFDocument document, String testo,
			String rgbColor) {
		// create Paragraph
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run = paragraph.createRun();
		run.setFontSize(FONT_SIZE_SOTTOTITOLO);
		if (rgbColor == null)
			run.setColor("000000");
		else
			run.setColor(rgbColor);
		run.setText(testo);
	}
	
	private void createRischiConEspr(XWPFDocument document,
			String rgbColor) {
		createTitolo(document, "Rischi", COLOR_BLUE);
		
		List<ReportAccessoPDFDto> listaRischi = pianoMiglioramentoWordService.getRiepilogoRischi(report.getIdSSessione());
		
		for (ReportAccessoPDFDto itemRischio : listaRischi) {
			listaRowRischiVarComp.clear();	
	        // OKKIO
			List<ReportAccessoPDFDto> listaRisVarComp = pianoMiglioramentoWordService.getRisEsprByIdMNonConf(itemRischio.getIdMRischio(), report.getIdSSessione());
			
			for (ReportAccessoPDFDto itemRischioVarcomp : listaRisVarComp) {
				RowRischoVarComp itemToAdd = new RowRischoVarComp();
				itemToAdd.descrizione = itemRischioVarcomp.getDescrizioneRisEspr();
				itemToAdd.num = itemRischioVarcomp.getNumSRischio();
				itemToAdd.perc = itemRischioVarcomp.getSuPsPerc().doubleValue() * 100;
				listaRowRischiVarComp.add(itemToAdd);
			}
			
			createTableRischiEspr(document,  listaRowRischiVarComp,  itemRischio.getDescrizioneRischio());
		}
		
	}
	
	private void createTableRischiEspr(XWPFDocument document,  List<RowRischoVarComp> listaRowRischiVarComp, String DescrizioneRischio) {
		int nRows = listaRowRischiVarComp.size() + 1;
		int nCols = 3;
		XWPFTable table = document.createTable(nRows, nCols);
		
		setWidthTable(table,10000);
		setRowHeight(table,0,1000);
		setCollWith(table, 0, 0, 4500);
		setCollWith(table, 0, 1, 500);
		setCollWith(table, 0, 2, 5000);
		
		// Set the table style. If the style is not defined, the table style
		// will become "Normal".
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");
		
		// Get a list of the rows in the table
		List<XWPFTableRow> rows = table.getRows();
		int rowCt = 0;
		int colCt = 0;
		int rowData = 0;
		
		int rowIndex = 0;
		
		for (XWPFTableRow row : rows) {
			setCollWith(table, 0, 0, 4500);
			setCollWith(table, 0, 1, 500);
			setCollWith(table, 0, 2, 5000);

			// get table row properties (trPr)
			CTTrPr trPr = row.getCtRow().addNewTrPr();
			// set row height; units = twentieth of a point, 360 = 0.25"
			CTHeight ht = trPr.addNewTrHeight();
			ht.setVal(BigInteger.valueOf(1000));
			// get the cells in this row
			List<XWPFTableCell> cells = row.getTableCells();

			// add content to each cell
			for (XWPFTableCell cell : cells) {

				// get a table cell properties element (tcPr)
				CTTcPr tcpr = cell.getCTTc().addNewTcPr();
				// set vertical alignment to "center"
				CTVerticalJc va = tcpr.addNewVAlign();
				va.setVal(STVerticalJc.CENTER);
				// create cell color element
				CTShd ctshd = tcpr.addNewShd();
				ctshd.setColor("auto");
				ctshd.setVal(STShd.CLEAR);

				if (rowCt == 0) {
					ht.setVal(BigInteger.valueOf(400));
					// header row
					ctshd.setFill("A7BFDE");
				} else if (rowCt % 2 == 0) {
					// even row
					//ctshd.setFill("D3DFEE");
					ctshd.setFill("EDF2F8");
					ht.setVal(BigInteger.valueOf(400));
				} else {
					// odd row
					//ctshd.setFill("EDF2F8");
					ht.setVal(BigInteger.valueOf(400));
				}

				// get 1st paragraph in cell's paragraph list
				XWPFParagraph para = cell.getParagraphs().get(0);
				// create a run to contain the content
				XWPFRun rh = para.createRun();
				
				// style cell as desired
				if (colCt == nCols - 1) {
					// last column is 10pt Courier
					rh.setFontSize(8);
					rh.setFontFamily("Verdana");
				}
				if (rowCt == 0) {
					rh.setFontSize(9);
					switch (colCt) {
					case 0:
						rh.setBold(true);
						setCollWith(table, rowCt, colCt, 4500);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText("Rischio: " + DescrizioneRischio);
						break;
					case 1:
						rh.setBold(true);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText("qtà");
						break;
					case 2:
						rh.setBold(true);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText("Az. di miglioramento condivise");
						break;		
					default:
						rh.setBold(true);
						break;
					}
				} else if (rowCt % 2 == 0) {
					rh.setFontSize(8);
					rh.setFontFamily("Verdana");
					// even row
					switch (colCt) {
					case 0:
						rh.setBold(false);
						setCollWith(table, rowCt, colCt, 4500);
						para.setAlignment(ParagraphAlignment.LEFT);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText(listaRowRischiVarComp.get(rowIndex).descrizione.trim());
						break;	
					case 1:
						String num = numFormatter.format(listaRowRischiVarComp.get(rowIndex).num);
						rh.setBold(false);
						setCollWith(table, rowCt, colCt, 500);
						para.setAlignment(ParagraphAlignment.LEFT);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText(num);
					default:
						break;
					}
					rowData++;
				} else {
					rh.setFontSize(8);
					rh.setFontFamily("Verdana");
					switch (colCt) {
					case 0:
						rh.setBold(false);
						setCollWith(table, rowCt, colCt, 4500);
						para.setAlignment(ParagraphAlignment.LEFT);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText(listaRowRischiVarComp.get(rowIndex).descrizione.trim());
						break;	
					case 1:
						String num = numFormatter.format(listaRowRischiVarComp.get(rowIndex).num);
						rh.setBold(false);
						setCollWith(table, rowCt, colCt, 500);
						para.setAlignment(ParagraphAlignment.LEFT);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText(num);
						break;
					default:
						break;
					}
					rowData++;
				}
				colCt++;
			}
			
			// for cell
			colCt = 0;
			if (rowCt >= 1){
				rowIndex++;
			}
			rowCt++;
		}
	}
	
	private void createNonConformita(XWPFDocument document,
			String rgbColor) {
		List<ReportAccessoPDFDto> listaFasi = pianoMiglioramentoWordService.getRiepilogoFasi(report.getIdSSessione());
		
		for (ReportAccessoPDFDto currFase : listaFasi) {
				// create Paragraph
				XWPFParagraph paragraph = document.createParagraph();
				paragraph.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun run = paragraph.createRun();
				run.setFontSize(FONT_SIZE_SOTTO_SEZIONE);
				if (rgbColor == null)
					run.setColor("000000");
				else
					run.setColor(rgbColor);
				run.setText(currFase.getDescrizioneFase() + " - INCC: " + currFase.getRiepilogoINCC());
				
				
				Long idFase = currFase.getIdFase();
				List<ReportAccessoPDFDto> listaNC = pianoMiglioramentoWordService.getMNonConfByIdFase(idFase,report.getIdSSessione());
				for (ReportAccessoPDFDto nonConf : listaNC) {
					listaRowNonConformita.clear();
					Long idMNonConf = nonConf.getIdMNonConf();
					// rowNonConformita = nonConf.getDescrizioneMNonConf();
					List<ReportAccessoPDFDto> listaVC = pianoMiglioramentoWordService.getVarCompByIdMNonConf(report.getIdSSessione(),idFase,idMNonConf);
					
					for (ReportAccessoPDFDto varComp : listaVC) {
						RowNonConformita rowToAdd = new RowNonConformita();
						rowToAdd.descrizioneMNonConf = nonConf.getDescrizioneMNonConf();
						rowToAdd.descrizioneVarComp = varComp.getDescrizioneVarComp();
						rowToAdd.numSVarComp = varComp.getNumSVarComp();
						rowToAdd.percentuale = varComp.getPercentuale().doubleValue();
						rowToAdd.colore = varComp.getColore().toUpperCase();
						listaRowNonConformita.add(rowToAdd);
						
					}
					createTableNonConformita(document, listaNC, listaRowNonConformita,nonConf.getDescrizioneMNonConf());
				}
				
				paragraph = document.createParagraph();
		}
	}

	private String getColor(String colorString){
		if ("Verde".toUpperCase().equals(colorString.toUpperCase())) return COLOR_VERDE;
		if ("Giallo".toUpperCase().equals(colorString.toUpperCase())) return COLOR_GIALLO;
		if ("Arancio".toUpperCase().equals(colorString.toUpperCase())) return COLOR_ARANCIO;
		if ("Rosso".toUpperCase().equals(colorString.toUpperCase())) return COLOR_ROSSO;
		if ("Bianco".toUpperCase().equals(colorString.toUpperCase())) return COLOR_BIANCO;
		return "";
	}
	
	private void createTableNonConformita(XWPFDocument document, List<ReportAccessoPDFDto> listaNC, List<RowNonConformita> listaRowNonConformita, String nonConf) {

		int nRows = listaRowNonConformita.size() + 1;
		int nCols = 5;
		XWPFTable table = document.createTable(nRows, nCols);

		setWidthTable(table,10000);
		setRowHeight(table,0,1000);
		setCollWith(table, 0, 0, 000);
		setCollWith(table, 0, 1, 500);
		setCollWith(table, 0, 2, 500);
		setCollWith(table, 0, 3, 1500);
		//setCollWith(table, 0, 3, 450000);
		//setCollWith(table, 0, 4, 3000);
		
		// Set the table style. If the style is not defined, the table style
		// will become "Normal".
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");
		
		// Get a list of the rows in the table
		List<XWPFTableRow> rows = table.getRows();
		int rowCt = 0;
		int colCt = 0;
		int rowData = 0;
		
		int rowIndex = 0;
		
		for (XWPFTableRow row : rows) {
			setCollWith(table, 0, 0, 000);
			setCollWith(table, 0, 1, 500);
			setCollWith(table, 0, 2, 500);
			setCollWith(table, 0, 3, 1000);
			// get table row properties (trPr)
			CTTrPr trPr = row.getCtRow().addNewTrPr();
			// set row height; units = twentieth of a point, 360 = 0.25"
			CTHeight ht = trPr.addNewTrHeight();
			ht.setVal(BigInteger.valueOf(1000));
			// get the cells in this row
			List<XWPFTableCell> cells = row.getTableCells();

			// add content to each cell
			for (XWPFTableCell cell : cells) {

				// get a table cell properties element (tcPr)
				CTTcPr tcpr = cell.getCTTc().addNewTcPr();
				// set vertical alignment to "center"
				CTVerticalJc va = tcpr.addNewVAlign();
				va.setVal(STVerticalJc.CENTER);
				// create cell color element
				CTShd ctshd = tcpr.addNewShd();
				ctshd.setColor("auto");
				ctshd.setVal(STShd.CLEAR);

				if (rowCt == 0) {
					ht.setVal(BigInteger.valueOf(400));
					// header row
					ctshd.setFill("A7BFDE");
				} else if (rowCt % 2 == 0) {
					// even row
					//ctshd.setFill("D3DFEE");
					ctshd.setFill("EDF2F8");
					ht.setVal(BigInteger.valueOf(400));
				} else {
					// odd row
					//ctshd.setFill("EDF2F8");
					ht.setVal(BigInteger.valueOf(400));
				}

				// get 1st paragraph in cell's paragraph list
				XWPFParagraph para = cell.getParagraphs().get(0);
				// create a run to contain the content
				XWPFRun rh = para.createRun();
				
				// style cell as desired
				if (colCt == nCols - 1) {
					// last column is 10pt Courier
					rh.setFontSize(8);
					rh.setFontFamily("Verdana");
				}
				if (rowCt == 0) {
					rh.setFontSize(9);
					switch (colCt) {
					case 0:
						rh.setBold(true);
						setCollWith(table, rowCt, colCt, 4000);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText("N.C.: "  + nonConf);
						break;
					case 1:
						
						break;
					case 2:
						rh.setBold(true);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText("qtà");
						break;						
					case 3:		
						rh.setBold(true);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText("%");
						break;	
					case 4:
						rh.setBold(true);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText("Az. di miglioramento condivise");
						break;		
					default:
						rh.setBold(true);
						break;
					}
				} else if (rowCt % 2 == 0) {
					rh.setFontSize(8);
					rh.setFontFamily("Verdana");
					// even row
					switch (colCt) {
					case 0:
						rh.setBold(false);
						setCollWith(table, rowCt, colCt, 4000);
						para.setAlignment(ParagraphAlignment.LEFT);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText(listaRowNonConformita.get(rowIndex).descrizioneVarComp.trim());
						break;
					case 1:
						ctshd.setFill(getColor(listaRowNonConformita.get(rowIndex).colore));
						break;
					case 2:
						rh.setBold(false);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText(String.valueOf(listaRowNonConformita.get(rowIndex).numSVarComp));
						break;						
					case 3:
						rh.setBold(false);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						String perc = String.format("%s%.2f%s","" ,listaRowNonConformita.get(rowIndex).percentuale,"%");
						rh.setText(perc);
						break;	
					case 4:
						//rh.setText("");
						break;		
					default:
						break;
					}
					rowData++;
				} else {
					rh.setFontSize(8);
					rh.setFontFamily("Verdana");
					switch (colCt) {
					case 0:
						rh.setBold(false);
						setCollWith(table, rowCt, colCt, 4000);
						para.setAlignment(ParagraphAlignment.LEFT);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText(listaRowNonConformita.get(rowIndex).descrizioneVarComp.trim());
						break;
					case 1:
						ctshd.setFill(getColor(listaRowNonConformita.get(rowIndex).colore));
						break;
					case 2:
						rh.setBold(false);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText(String.valueOf(listaRowNonConformita.get(rowIndex).numSVarComp));
						break;						
					case 3:
						rh.setBold(false);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						String perc = String.format("%s%.2f%s","" ,listaRowNonConformita.get(rowIndex).percentuale,"%");
						rh.setText(perc);
						break;	
					case 4:
						//rh.setText("");
						break;		
					default:
						break;
					}
					rowData++;
				}
				colCt++;
			}
			// for cell
			colCt = 0;
			if (rowCt >= 1){
				rowIndex++;
			}
			rowCt++;
		}
		//mergeCellHorizontally(table, 0, 0, 1);
		mergeCellVertically(table, 4, 1, nRows-1);
	}
	
	private void createRischi(XWPFDocument document,
			String color_blue2) {
		createTitolo(document, "Rischi", COLOR_BLUE);
		
		List<ReportAccessoPDFDto> listaRischi = pianoMiglioramentoWordService.getRiepilogoRischi(report.getIdSSessione());
		
		int nRows = listaRischi.size() + 1;
		int nCols = 2;
		XWPFTable table = document.createTable(nRows, nCols);

		int colWidth = 2000;
		setWidthTable(table,10000);
		setRowHeight(table,0,1000);
		setCollWith(table, 0, 0, colWidth);
		setCollWith(table, 0, 1, colWidth);

		// Set the table style. If the style is not defined, the table style
		// will become "Normal".
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");
		
		// Get a list of the rows in the table
		List<XWPFTableRow> rows = table.getRows();
		int rowCt = 0;
		int colCt = 0;
		int rowData = 0;
		
		int rowIndex = 0;
		
		for (XWPFTableRow row : rows) {
			setCollWith(table, rowIndex, 0, colWidth);
			setCollWith(table, rowIndex, 1, colWidth);
			
			// get table row properties (trPr)
			CTTrPr trPr = row.getCtRow().addNewTrPr();
			// set row height; units = twentieth of a point, 360 = 0.25"
			CTHeight ht = trPr.addNewTrHeight();
			ht.setVal(BigInteger.valueOf(1000));
			// get the cells in this row
			List<XWPFTableCell> cells = row.getTableCells();

			// add content to each cell
			for (XWPFTableCell cell : cells) {

				// get a table cell properties element (tcPr)
				CTTcPr tcpr = cell.getCTTc().addNewTcPr();
				// set vertical alignment to "center"
				CTVerticalJc va = tcpr.addNewVAlign();
				va.setVal(STVerticalJc.CENTER);
				// create cell color element
				CTShd ctshd = tcpr.addNewShd();
				ctshd.setColor("auto");
				ctshd.setVal(STShd.CLEAR);

				if (rowCt == 0) {
					ht.setVal(BigInteger.valueOf(400));
					// header row
					ctshd.setFill("A7BFDE");
				} else if (rowCt % 2 == 0) {
					// even row
					//ctshd.setFill("D3DFEE");
					ctshd.setFill("EDF2F8");
					ht.setVal(BigInteger.valueOf(400));
				} else {
					// odd row
					//ctshd.setFill("EDF2F8");
					ht.setVal(BigInteger.valueOf(400));
				}

				// get 1st paragraph in cell's paragraph list
				XWPFParagraph para = cell.getParagraphs().get(0);
				// create a run to contain the content
				XWPFRun rh = para.createRun();
				
				// style cell as desired
				if (colCt == nCols - 1) {
					// last column is 10pt Courier
					rh.setFontSize(8);
					rh.setFontFamily("Verdana");
				}
				if (rowCt == 0) {
					rh.setFontSize(9);
					rh.setFontFamily("Verdana");
					switch (colCt) {
					case 0:
						rh.setBold(true);
						setCollWith(table, rowCt, colCt, colWidth);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText("Rischi");
						break;	
					case 1:
						rh.setBold(true);
						para.setAlignment(ParagraphAlignment.CENTER);
						para.setVerticalAlignment(TextAlignment.CENTER);
						rh.setText("Az. di miglioramento condivise");
						break;		
					default:
						rh.setBold(true);
						break;
					}
				} else if (rowCt % 2 == 0) {
					rh.setFontSize(8);
					rh.setFontFamily("Verdana");
					// even row
					switch (colCt) {
					case 0:
						rh.setBold(false);
						setCollWith(table, rowCt, colCt, colWidth);
						para.setAlignment(ParagraphAlignment.LEFT);
						para.setVerticalAlignment(TextAlignment.CENTER);
						if (listaRischi.get(rowIndex).getDescrizioneRischio() != null
								&& listaRischi.get(rowIndex).getDescrizioneRischio().length() > 0	)rh.setText(listaRischi.get(rowIndex).getDescrizioneRischio().trim());
						break;	
					case 1:
						//rh.setText("");
						break;		
					default:
						break;
					}
					rowData++;
				} else {
					rh.setFontSize(8);
					rh.setFontFamily("Verdana");
					switch (colCt) {
					case 0:
						rh.setBold(false);
						setCollWith(table, rowCt, colCt, colWidth);
						para.setAlignment(ParagraphAlignment.LEFT);
						para.setVerticalAlignment(TextAlignment.CENTER);
						if (listaRischi.get(rowIndex).getDescrizioneRischio() != null
								&& listaRischi.get(rowIndex).getDescrizioneRischio().length() > 0	)rh.setText(listaRischi.get(rowIndex).getDescrizioneRischio().trim());
						break;	
					case 1:
						//rh.setText("");
						break;			
					default:
						break;
					}
					rowData++;
				}
				colCt++;
			}
			// for cell
			colCt = 0;
			if (rowCt >= 1){
				rowIndex++;
			}
			rowCt++;
		}
		
	}
	
	private void createEsitiGiudizi(XWPFDocument document,
			String color_blue2) {
		List<ReportAccessoPDFDto> giudizi1WithDissensoRows = pianoMiglioramentoWordService.getEsitoByTipoDifesaWithCodeAndDissenso(report.getIdSSessione());
		List<ReportAccessoPDFDto> listaGiudizi = pianoMiglioramentoWordService.getRiepilogoGiudizi(report.getIdSSessione());
		
		double tot_U02_U03 = 0;
		double tot_U04 = 0;
		for (ReportAccessoPDFDto item: giudizi1WithDissensoRows) {
			if ("U04".equals(item.getTipoDifesa().trim())){
				tot_U04 += item.getQuantita();
			}else{
				tot_U02_U03 += item.getQuantita();
			}
		}
		
		List<RowGiudizo> listaRowGiudizo = new ArrayList<RowGiudizo>();
		for (ReportAccessoPDFDto item : listaGiudizi) {
			Double perc = 0D;
			if ("INCOMPLETA".equals(item.getDescrizioneEsito().trim()) ){
				if(item.getNumeroGiudizi() != null && item.getNumeroGiudizi().intValue() > 0
						&& tot_U04 > 0)
					perc = ( item.getNumeroGiudizi().doubleValue()  / tot_U04);
			}else{
				if(item.getNumeroGiudizi() != null && item.getNumeroGiudizi().intValue() > 0
						&&  tot_U04 > 0)
					perc = (   item.getNumeroGiudizi().doubleValue() / tot_U02_U03);				
			}
			
			// POPOLAMENTO RECORD DA VISUALIZZARE
			RowGiudizo row = new RowGiudizo();
			if (item.getDescrizioneEsito()!= null)row.descrizione = item.getDescrizioneEsito();
			if (item.getNumeroGiudizi()!= null)row.numGiudizi = item.getNumeroGiudizi();
			if (perc != null)row.perc = perc;
			if (item.getNumeroPrestazioni()!= null)row.numPrestazioni = item.getNumeroPrestazioni();
			
			if (item.getImportoPrestazioni()!= null)row.importoPrestazioni = item.getImportoPrestazioni().doubleValue();
			if (item.getSpeseLegali()!= null)row.speseLegali = item.getSpeseLegali().doubleValue();
			if (item.getSpeseCtu()!= null)row.speseCTU = item.getSpeseCtu().doubleValue();
			listaRowGiudizo.add(row);
		}
		
		createSottoTitolo(document, "Esiti del giudizio in relazione alla difesa con pagamento delle spese", COLOR_BLUE);
		createSottoTitolo(document, "Giudizi sfavorevoli + parzialmente favorevoli", COLOR_BLACK);
		createSpazio(document);
		
		int nRows = 3;
		int nCols = 7;
		XWPFTable table = document.createTable(nRows, nCols);

		setWidthTable(table,10000);

		
		// Set the table style. If the style is not defined, the table style
		// will become "Normal".
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");
		
		// Get a list of the rows in the table
		List<XWPFTableRow> rows = table.getRows();
		int rowCt = 0;
		int colCt = 0;
		
		
		// ---------------------------
		// HEADER
		// ---------------------------
		XWPFTableRow rowHeader = rows.get(0);
		CTTrPr trPrHeader = rowHeader.getCtRow().addNewTrPr();
		// set row height; units = twentieth of a point, 360 = 0.25"
		CTHeight htHeader = trPrHeader.addNewTrHeight();
		htHeader.setVal(BigInteger.valueOf(1000));
		// HEADER
		List<XWPFTableCell> headerCells = rowHeader.getTableCells();
		colCt = 0;
		for (XWPFTableCell cell : headerCells) {
			// get a table cell properties element (tcPr)
			CTTcPr tcpr = cell.getCTTc().addNewTcPr();
			// set vertical alignment to "center"
			CTVerticalJc va = tcpr.addNewVAlign();
			va.setVal(STVerticalJc.CENTER);
			// create cell color element
			CTShd ctshd = tcpr.addNewShd();
			ctshd.setColor("auto");
			ctshd.setVal(STShd.CLEAR);
			ctshd.setFill("A7BFDE");
			// get 1st paragraph in cell's paragraph list
			XWPFParagraph para = cell.getParagraphs().get(0);
			// create a run to contain the content
			XWPFRun rh = para.createRun();
			rh.setBold(true);
			rh.setFontFamily("Verdana");
			if (rowCt == 0) {
				rh.setFontSize(8);
				switch (colCt) {
				case 0:
					setCollWith(table, 0, 0, 2000);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("Difesa");
					break;	
				case 1:
					setCollWith(table, 0, 1, 500);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("N. giudizi");
					break;
				case 2:
					setCollWith(table, 0, 2, 500);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("%");
					break;
				case 3:
					setCollWith(table, 0, 3, 500);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("N. prestazioni");
					break;
				case 4:
					setCollWith(table, 0, 4, 1000);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("Imp. prestazioni");
					break;
				case 5:
					setCollWith(table, 0, 5, 1000);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("Spese legali");
					break;
				case 6:
					setCollWith(table, 0, 6, 1000);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("Spese ctu");
					break;
				default:
					break;
				}
			}
			colCt++;
		}
	
		// ---------------------------
		// ROW 1
		// ---------------------------
		XWPFTableRow row1 = rows.get(1);
		CTTrPr trPr1 = row1.getCtRow().addNewTrPr();
		// set row height; units = twentieth of a point, 360 = 0.25"
		CTHeight ht1 = trPr1.addNewTrHeight();
		ht1.setVal(BigInteger.valueOf(1000));
		// HEADER
		List<XWPFTableCell> row1Cells = row1.getTableCells();
		colCt = 0;
		for (XWPFTableCell cell : row1Cells) {
			// get a table cell properties element (tcPr)
			CTTcPr tcpr = cell.getCTTc().addNewTcPr();
			// set vertical alignment to "center"
			CTVerticalJc va = tcpr.addNewVAlign();
			va.setVal(STVerticalJc.CENTER);
			// create cell color element
			CTShd ctshd = tcpr.addNewShd();
			ctshd.setColor("auto");
			ctshd.setVal(STShd.CLEAR);
			//ctshd.setFill("EDF2F8");
			// get 1st paragraph in cell's paragraph list
			XWPFParagraph para = cell.getParagraphs().get(0);
			cell.setVerticalAlignment(XWPFVertAlign.CENTER);
			para.setVerticalAlignment(TextAlignment.CENTER);
			para.setAlignment(ParagraphAlignment.CENTER);
			// create a run to contain the content
			XWPFRun rh = para.createRun();
			rh.setBold(false);
			rh.setFontFamily("Verdana");
			if (rowCt == 0) {
				rh.setFontSize(8);
				if (listaRowGiudizo != null && listaRowGiudizo.size() == 2 );{
					int index = 0;
					switch (colCt) {
					case 0:
						rh.setText(listaRowGiudizo.get(index).descrizione);
						break;	
					case 1:
						rh.setText(listaRowGiudizo.get(index).numGiudizi.toString());
						break;
					case 2:
						rh.setText(percentFormatter.format(listaRowGiudizo.get(index).perc));
						break;
					case 3:
						rh.setText(listaRowGiudizo.get(index).numPrestazioni.toString());
						break;
					case 4:
						String  importoPrestazioni = decimalFormatter.format(listaRowGiudizo.get(index).importoPrestazioni);
						rh.setText(importoPrestazioni);
						break;
					case 5:
						String  speseLegali = decimalFormatter.format(listaRowGiudizo.get(index).speseLegali);
						rh.setText(speseLegali);
						break;
					case 6:
						String  speseCTU = decimalFormatter.format(listaRowGiudizo.get(index).speseCTU);
						rh.setText(speseCTU);
						break;
					default:
						break;
					}
				}
			}
			colCt++;
		}
	
		// ---------------------------
		// ROW 2
		// ---------------------------
		XWPFTableRow row2 = rows.get(2);
		CTTrPr trPr2 = row2.getCtRow().addNewTrPr();
		// set row height; units = twentieth of a point, 360 = 0.25"
		CTHeight ht2 = trPr2.addNewTrHeight();
		ht2.setVal(BigInteger.valueOf(1000));
		// HEADER
		List<XWPFTableCell> row2Cells = row2.getTableCells();
		colCt = 0;
		for (XWPFTableCell cell : row2Cells) {
			// get a table cell properties element (tcPr)
			CTTcPr tcpr = cell.getCTTc().addNewTcPr();
			// set vertical alignment to "center"
			CTVerticalJc va = tcpr.addNewVAlign();
			va.setVal(STVerticalJc.CENTER);
			// create cell color element
			CTShd ctshd = tcpr.addNewShd();
			ctshd.setColor("auto");
			ctshd.setVal(STShd.CLEAR);
			ctshd.setFill("EDF2F8");
			// get 1st paragraph in cell's paragraph list
			XWPFParagraph para = cell.getParagraphs().get(0);
			cell.setVerticalAlignment(XWPFVertAlign.CENTER);
			para.setVerticalAlignment(TextAlignment.CENTER);
			para.setAlignment(ParagraphAlignment.CENTER);
			// create a run to contain the content
			XWPFRun rh = para.createRun();
			rh.setBold(false);
			rh.setFontFamily("Verdana");
			if (rowCt == 0) {
				rh.setFontSize(8);
				if (listaRowGiudizo != null && listaRowGiudizo.size() == 2 );{
					int index = 1;
					switch (colCt) {
					case 0:
						rh.setText(listaRowGiudizo.get(index).descrizione);
						break;	
					case 1:
						rh.setText(listaRowGiudizo.get(index).numGiudizi.toString());
						break;
					case 2:
						rh.setText(percentFormatter.format(listaRowGiudizo.get(index).perc));
						break;
					case 3:
						rh.setText(listaRowGiudizo.get(index).numPrestazioni.toString());
						break;
					case 4:
						String  importoPrestazioni = decimalFormatter.format(listaRowGiudizo.get(index).importoPrestazioni);
						rh.setText(importoPrestazioni);
						break;
					case 5:
						String  speseLegali = decimalFormatter.format(listaRowGiudizo.get(index).speseLegali);
						rh.setText(speseLegali);
						break;
					case 6:
						String  speseCTU = decimalFormatter.format(listaRowGiudizo.get(index).speseCTU);
						rh.setText(speseCTU);
						break;
					default:
						break;
					}
				}
			}
			colCt++;
		}
		
		setRowHeight(table,0,300);
		setRowHeight(table,1,400);
		setRowHeight(table,2,400);
		
		XWPFParagraph para = document.createParagraph();
		XWPFRun rh = para.createRun();
		rh.addBreak();
		rh.setText("La tabella illustra i giudizi che comportano erogazione della prestazione e pagamento delle spese legali e di CTU (esito favorevole e parzialmente favorevole) distinti in relazione alla difesa espletata.");
	}

	private void createFascicolo(XWPFDocument document,
			String color_blue2) {
		
		List<ReportAccessoPDFDto> fascicoloRows = pianoMiglioramentoWordService.getRiepilogoFascicolo(report.getIdSSessione());
		String descrizioneCompleto = "Completo";
		Integer numCompleto = fascicoloRows.get(1).getQuantita();

		String descrizioneInCompleto = "Incompleto";
		Integer numInCompleto = fascicoloRows.get(2).getQuantita();
		
		Integer totale = numInCompleto + numCompleto;
		Double percCompleti = ( numCompleto.doubleValue() / totale.doubleValue());
		Double percInCompleti =  ( numInCompleto.doubleValue() / totale.doubleValue());
		
		
		createSottoTitolo(document, "Fascicolo cartaceo/elettronico", COLOR_BLUE);
		createSottoTitolo(document, "Esame della documentazione presente nel fascicolo elettronico del campione", COLOR_BLACK);
		createSpazio(document);
		
		int nRows = 3;
		int nCols = 3;
		XWPFTable table = document.createTable(nRows, nCols);

		setWidthTable(table,10000);

		// Set the table style. If the style is not defined, the table style
		// will become "Normal".
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");
		
		// Get a list of the rows in the table
		List<XWPFTableRow> rows = table.getRows();
		int rowCt = 0;
		int colCt = 0;
		
		// ---------------------------
		// HEADER
		// ---------------------------
		XWPFTableRow rowHeader = rows.get(0);
		CTTrPr trPrHeader = rowHeader.getCtRow().addNewTrPr();
		// set row height; units = twentieth of a point, 360 = 0.25"
		CTHeight htHeader = trPrHeader.addNewTrHeight();
		htHeader.setVal(BigInteger.valueOf(1000));
		// HEADER
		List<XWPFTableCell> headerCells = rowHeader.getTableCells();
		colCt = 0;
		for (XWPFTableCell cell : headerCells) {
			// get a table cell properties element (tcPr)
			CTTcPr tcpr = cell.getCTTc().addNewTcPr();
			// set vertical alignment to "center"
			CTVerticalJc va = tcpr.addNewVAlign();
			va.setVal(STVerticalJc.CENTER);
			// create cell color element
			CTShd ctshd = tcpr.addNewShd();
			ctshd.setColor("auto");
			ctshd.setVal(STShd.CLEAR);
			ctshd.setFill("A7BFDE");
			// get 1st paragraph in cell's paragraph list
			XWPFParagraph para = cell.getParagraphs().get(0);
			// create a run to contain the content
			XWPFRun rh = para.createRun();
			rh.setBold(true);
			rh.setFontFamily("Verdana");
			if (rowCt == 0) {
				rh.setFontSize(8);
				switch (colCt) {
				case 0:
					setCollWith(table, 0, 0, 4000);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("Fascicolo");
					break;	
				case 1:
					setCollWith(table, 0, 1, 4000);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("N° casi");
					break;
				case 2:
					setCollWith(table, 0, 2, 4000);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("%");
					break;
				default:
					break;
				}
			}
			colCt++;
		}
	
		// ---------------------------
		// ROW 1
		// ---------------------------
		XWPFTableRow row1 = rows.get(1);
		CTTrPr trPr1 = row1.getCtRow().addNewTrPr();
		// set row height; units = twentieth of a point, 360 = 0.25"
		CTHeight ht1 = trPr1.addNewTrHeight();
		ht1.setVal(BigInteger.valueOf(1000));
		// HEADER
		List<XWPFTableCell> row1Cells = row1.getTableCells();
		colCt = 0;
		for (XWPFTableCell cell : row1Cells) {
			// get a table cell properties element (tcPr)
			CTTcPr tcpr = cell.getCTTc().addNewTcPr();
			// set vertical alignment to "center"
			CTVerticalJc va = tcpr.addNewVAlign();
			va.setVal(STVerticalJc.CENTER);
			// create cell color element
			CTShd ctshd = tcpr.addNewShd();
			ctshd.setColor("auto");
			ctshd.setVal(STShd.CLEAR);
			//ctshd.setFill("EDF2F8");
			// get 1st paragraph in cell's paragraph list
			XWPFParagraph para = cell.getParagraphs().get(0);
			cell.setVerticalAlignment(XWPFVertAlign.CENTER);
			para.setVerticalAlignment(TextAlignment.CENTER);
			para.setAlignment(ParagraphAlignment.CENTER);
			// create a run to contain the content
			XWPFRun rh = para.createRun();
			rh.setBold(false);
			rh.setFontFamily("Verdana");
			if (rowCt == 0) {
				rh.setFontSize(8);
				switch (colCt) {
				case 0:
					rh.setText(descrizioneCompleto);
					break;	
				case 1:
					rh.setText(numCompleto.toString());
					break;
				case 2:
					// rh.setText(percCompleti.toString());
					rh.setText(percentFormatter.format( percCompleti ) );
					break;
				default:
					break;
				}
			}
			colCt++;
		}
	
		// ---------------------------
		// ROW 2
		// ---------------------------
		XWPFTableRow row2 = rows.get(2);
		CTTrPr trPr2 = row2.getCtRow().addNewTrPr();
		// set row height; units = twentieth of a point, 360 = 0.25"
		CTHeight ht2 = trPr2.addNewTrHeight();
		ht2.setVal(BigInteger.valueOf(1000));
		// HEADER
		List<XWPFTableCell> row2Cells = row2.getTableCells();
		colCt = 0;
		for (XWPFTableCell cell : row2Cells) {
			// get a table cell properties element (tcPr)
			CTTcPr tcpr = cell.getCTTc().addNewTcPr();
			// set vertical alignment to "center"
			CTVerticalJc va = tcpr.addNewVAlign();
			va.setVal(STVerticalJc.CENTER);
			// create cell color element
			CTShd ctshd = tcpr.addNewShd();
			ctshd.setColor("auto");
			ctshd.setVal(STShd.CLEAR);
			ctshd.setFill("EDF2F8");
			// get 1st paragraph in cell's paragraph list
			XWPFParagraph para = cell.getParagraphs().get(0);
			cell.setVerticalAlignment(XWPFVertAlign.CENTER);
			para.setVerticalAlignment(TextAlignment.CENTER);
			para.setAlignment(ParagraphAlignment.CENTER);
			// create a run to contain the content
			XWPFRun rh = para.createRun();
			rh.setBold(false);
			rh.setFontFamily("Verdana");
			if (rowCt == 0) {
				rh.setFontSize(8);
				switch (colCt) {
				case 0:
					rh.setText(descrizioneInCompleto);
					break;	
				case 1:
					rh.setText(numInCompleto.toString());
					break;
				case 2:
					//rh.setText(percInCompleti.toString());
					rh.setText(percentFormatter.format( percInCompleti));
					break;
				default:
					break;
				}
			}
			colCt++;
		}
		
		setRowHeight(table,0,300);
		setRowHeight(table,1,400);
		setRowHeight(table,2,400);
		
		XWPFParagraph para = document.createParagraph();
		XWPFRun rh = para.createRun();
		rh.addBreak();
		rh.setText("Si ricorda che agli atti devono essere sempre presenti i seguenti documenti:");

		para = document.createParagraph();
		rh = para.createRun();
		rh.setText("   \u2022   istanza di ATPO;");
		rh.addCarriageReturn();
		rh.setText("   \u2022   copia di memoria di costituzione;");
		rh.addCarriageReturn();
		rh.setText("   \u2022   copia della bozza CTU;");
		rh.addCarriageReturn();
		rh.setText("   \u2022   copia della CTU definitiva;");
		rh.addCarriageReturn();
		rh.setText("   \u2022   decreto liquidazione CTU;");
		rh.addCarriageReturn();
		rh.setText("   \u2022   decreto di omologa;");
		rh.addCarriageReturn();
		rh.setText("   \u2022   atto di dissenso eventuale;");
		rh.addCarriageReturn();
		rh.setText("   \u2022   verbale prima udienza;");
		rh.addCarriageReturn();
		rh.setText("   \u2022   verbale sanitario impugnato.");
		rh.addCarriageReturn();		
		
	
	}
	
	private void createHeaderForIntervallTemporale(List<XWPFTableRow> rows,XWPFTable table){
		int rowCt = 0;
		int colCt = 0;
		
		// ---------------------------
		// HEADER
		// ---------------------------
		XWPFTableRow rowHeader = rows.get(0);
		CTTrPr trPrHeader = rowHeader.getCtRow().addNewTrPr();
		// set row height; units = twentieth of a point, 360 = 0.25"
		CTHeight htHeader = trPrHeader.addNewTrHeight();
		htHeader.setVal(BigInteger.valueOf(1000));
		// HEADER
		List<XWPFTableCell> headerCells = rowHeader.getTableCells();
		colCt = 0;
		for (XWPFTableCell cell : headerCells) {
			// get a table cell properties element (tcPr)
			CTTcPr tcpr = cell.getCTTc().addNewTcPr();
			// set vertical alignment to "center"
			CTVerticalJc va = tcpr.addNewVAlign();
			va.setVal(STVerticalJc.CENTER);
			// create cell color element
			CTShd ctshd = tcpr.addNewShd();
			ctshd.setColor("auto");
			ctshd.setVal(STShd.CLEAR);
			ctshd.setFill("A7BFDE");
			// get 1st paragraph in cell's paragraph list
			XWPFParagraph para = cell.getParagraphs().get(0);
			// create a run to contain the content
			XWPFRun rh = para.createRun();
			rh.setBold(true);
			rh.setFontFamily("Verdana");
			if (rowCt == 0) {
				rh.setFontSize(8);
				switch (colCt) {
				case 0:
					setCollWith(table, 0, 0, 4000);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("Arco temporale");
					break;	
				case 1:
					setCollWith(table, 0, 1, 500);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("Giorni");
					break;
				case 2:
					setCollWith(table, 0, 2, 500);
					para.setAlignment(ParagraphAlignment.CENTER);
					para.setVerticalAlignment(TextAlignment.CENTER);
					rh.setText("Non Conformita'");
					break;
				default:
					break;
				}
			}
			colCt++;
		}
	}
	
	private void createRowDataForIntervallTemporale(XWPFTable table, XWPFTableCell cell, int rowCt, int colCt, boolean color, ReportAccessoPDFDto rowData){
		// get a table cell properties element (tcPr)
		//CTTcPr tcpr = cell.getCTTc().addNewTcPr();
		// set vertical alignment to "center"
		//CTVerticalJc va = tcpr.addNewVAlign();
		//va.setVal(STVerticalJc.CENTER);
		// create cell color element
		//CTShd ctshd = tcpr.addNewShd();
		//ctshd.setColor("auto");
		//ctshd.setVal(STShd.CLEAR);
		//ctshd.setFill("A7BFDE");
		// get 1st paragraph in cell's paragraph list
		XWPFParagraph para = cell.getParagraphs().get(0);
		// create a run to contain the content
		XWPFRun rh = para.createRun();
		rh.setBold(false);
		rh.setFontFamily("Verdana");
		
		if (rowCt >= 1) {
			rh.setFontSize(8);
			rh.setFontFamily("Verdana");
			
			switch (colCt) {
			case 0:
				//setCollWith(table, 0, 0, 4000);
				para.setAlignment(ParagraphAlignment.LEFT);
				para.setVerticalAlignment(TextAlignment.CENTER);
				rh.setText(rowData.getDescrizioneTemporale());
				break;	
			case 1:
				//setCollWith(table, 0, 1, 500);
				para.setAlignment(ParagraphAlignment.CENTER);
				para.setVerticalAlignment(TextAlignment.CENTER);
				String medigGG = decimalFormatter.format(rowData.getMediaGG());
				rh.setText(medigGG);
				//rh.setText(rowData.getMediaGG().toString());
				rh.setColor(getColorFontByTemp(rowData.getMediaGG().doubleValue(),rowData.getNC())); 
				break;
			case 2:
				//setCollWith(table, 0, 2, 500);
				para.setAlignment(ParagraphAlignment.CENTER);
				para.setVerticalAlignment(TextAlignment.CENTER);
				rh.setText(rowData.getNC());
				break;
			default:
				break;
			}
		}
	}
	
	private String getColorFontByTemp(double mediaGG, String test){
		if(test.trim().equals("> 3") &&  mediaGG > 3d){
			return COLOR_ROSSO;
		}else if(test.trim().equals("< 5") &&  mediaGG < 5d){
			return COLOR_ROSSO;
		}else if(test.trim().equals("> 0") &&  mediaGG < 0d){
			return COLOR_ROSSO;
		}else if(test.trim().equals("> 30") &&  mediaGG > 30d){
			return COLOR_ROSSO;
		}else if(test.trim().equals("> 120") &&  mediaGG > 120){
			return COLOR_ROSSO;
		}else if(test.trim().equals("> 180") &&  mediaGG > 180){
			return COLOR_ROSSO;
		}else{
			return COLOR_BLACK;
		}
	}
	
	private void createIntervalliTemporali(XWPFDocument document,
			String color_blue2) {
		createSottoTitolo(document, "Riepilogo intervalli temporali", COLOR_BLUE);
		createSottoTitolo(document, "Arco temporale", COLOR_BLACK);
		createSpazio(document);
		
		List<ReportAccessoPDFDto> lista = pianoMiglioramentoWordService.getRisultatiByTempo(report.getIdSSessione());
		
		int nRows = lista.size() + 1;
		int nCols = 3;
		XWPFTable table = document.createTable(nRows, nCols);

		setWidthTable(table,10000);

		// Set the table style. If the style is not defined, the table style
		// will become "Normal".
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");
		
		// Get a list of the rows in the table
		List<XWPFTableRow> rows = table.getRows();
		createHeaderForIntervallTemporale(rows, table);
		setRowHeight(table,0,300);
		
		int rowCt = 0;
		int colCt = 0;
		int rowData = 0;
		boolean color = false;
		
		for (@SuppressWarnings("unused") XWPFTableRow tableRow : rows) {	
			if ( rowCt != 0){
				// ---------------------------
				// ROW
				// ---------------------------
				XWPFTableRow rowItem = rows.get(rowCt);
				CTTrPr trPrItem = rowItem.getCtRow().addNewTrPr();
				// set row height; units = twentieth of a point, 360 = 0.25"
				CTHeight htItem = trPrItem.addNewTrHeight();
				htItem.setVal(BigInteger.valueOf(1000));
				// HEADER
				List<XWPFTableCell> headerCells = rowItem.getTableCells();
				colCt = 0;
				for (XWPFTableCell cell : headerCells) {

					createRowDataForIntervallTemporale(table, cell, rowCt, colCt, color, lista.get(rowData));
					colCt++;
				}
				if (color) color = false; else color = true;
				rowData++;
				setRowHeight(table,rowCt,400);
			}
			rowCt++;
		}

		XWPFParagraph para = document.createParagraph();
		XWPFRun rh = para.createRun();
		rh.addBreak();
		rh.setText("I risultati della tabella sono espressi come media degli intervalli temporali tra le date indicate, rilevati sulle singole pratiche, considerati ai fini della correttezza del processo. Gli elementi in rosso indicano il   superamento dei tempi stabiliti nelle disposizioni per lo svolgimento delle attività.");

	
	}
	
	private void setCollWith(XWPFTable tab, int row, int cell, int width) {
		tab.getRow(row).getCell(cell).getCTTc().addNewTcPr().addNewTcW().setW(
				BigInteger.valueOf(width));
	}

	private void setRowHeight(XWPFTable tab, int row, int height) {
		CTTrPr trPr1 = tab.getRow(row).getCtRow().addNewTrPr();
		CTHeight ht1 = trPr1.addNewTrHeight();
		ht1.setVal(BigInteger.valueOf(height));
	}

	private void setWidthTable(XWPFTable tab, int width) {
		CTTblWidth widthCell = tab.getCTTbl().addNewTblPr().addNewTblW();
		widthCell.setType(STTblWidth.DXA);
		widthCell.setW(BigInteger.valueOf(width));
	}

	static void mergeCellVertically(XWPFTable table, int col, int fromRow,
			int toRow) {
		for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
			XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
			CTVMerge vmerge = CTVMerge.Factory.newInstance();
			if (rowIndex == fromRow) {
				// The first merged cell is set with RESTART merge value
				vmerge.setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				vmerge.setVal(STMerge.CONTINUE);
				// and the content should be removed
				for (int i = cell.getParagraphs().size(); i > 0; i--) {
					cell.removeParagraph(0);
				}
				cell.addParagraph();
			}
			// Try getting the TcPr. Not simply setting an new one every time.
			CTTcPr tcPr = cell.getCTTc().getTcPr();
			if (tcPr != null) {
				tcPr.setVMerge(vmerge);
			} else {
				// only set an new TcPr if there is not one already
				tcPr = CTTcPr.Factory.newInstance();
				tcPr.setVMerge(vmerge);
				cell.getCTTc().setTcPr(tcPr);
			}
		}
	}

	static void mergeCellHorizontally(XWPFTable table, int row, int fromCol,
			int toCol) {
		for (int colIndex = fromCol; colIndex <= toCol; colIndex++) {
			XWPFTableCell cell = table.getRow(row).getCell(colIndex);
			CTHMerge hmerge = CTHMerge.Factory.newInstance();
			if (colIndex == fromCol) {
				// The first merged cell is set with RESTART merge value
				hmerge.setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				hmerge.setVal(STMerge.CONTINUE);
				// and the content should be removed
				for (int i = cell.getParagraphs().size(); i > 0; i--) {
					cell.removeParagraph(0);
				}
				cell.addParagraph();
			}
			// Try getting the TcPr. Not simply setting an new one every time.
			CTTcPr tcPr = cell.getCTTc().getTcPr();
			if (tcPr != null) {
				tcPr.setHMerge(hmerge);
			} else {
				// only set an new TcPr if there is not one already
				tcPr = CTTcPr.Factory.newInstance();
				tcPr.setHMerge(hmerge);
				cell.getCTTc().setTcPr(tcPr);
			}
		}
	}

	private class RowNonConformita{
		String descrizioneMNonConf;
		String descrizioneVarComp;
		int numSVarComp;
		Double percentuale;
		String colore;
	}
	
	private class RowGiudizo{
		String descrizione;
		Integer numGiudizi;
		Double perc;
		Integer numPrestazioni;
		Double importoPrestazioni;
		Double speseLegali;
		Double speseCTU;
	}
	
	private class RowRischoVarComp{
		String descrizione;
		Integer num;
		Double perc;
	}
}