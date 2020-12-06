package it.tecnet.crs.ATPO.auditors.web.beans;

import java.io.ByteArrayInputStream;

public class ModelAccessiWordReport {

	private ByteArrayInputStream exportWordFile;
	private String exportWordFileName;

	public ByteArrayInputStream getExportWordFile() {
		return exportWordFile;
	}

	public void setExportWordFile(ByteArrayInputStream exportWordFile) {
		this.exportWordFile = exportWordFile;
	}

	public String getExportWordFileName() {
		return exportWordFileName;
	}

	public void setExportWordFileName(String exportWordFileName) {
		this.exportWordFileName = exportWordFileName;
	}

}
