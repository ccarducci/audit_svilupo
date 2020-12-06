package it.tecnet.crs.report.web.bean;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReportPDFBean {
	
	private ByteArrayInputStream exportPDFFile;
	
	private String exportPDFFileName;
	
	

	public void setExportPDFFile(ByteArrayInputStream exportPDFFile) {
		this.exportPDFFile = exportPDFFile;
	}

	public ByteArrayInputStream getExportPDFFile() {
		return exportPDFFile;
	}

	public void setExportPDFFileName(String exportPDFFileName) {
		this.exportPDFFileName = exportPDFFileName;
	}

	public String getExportPDFFileName() {
		return exportPDFFileName;
	}


}
