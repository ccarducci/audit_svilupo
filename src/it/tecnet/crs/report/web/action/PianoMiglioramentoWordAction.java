package it.tecnet.crs.report.web.action;

import it.tecnet.crs.ATPO.auditors.web.beans.ModelAccessiWordReport;
import it.tecnet.crs.report.service.PianoMiglioramentoWordService;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class PianoMiglioramentoWordAction extends BaseAction implements
		ModelDriven<ModelAccessiWordReport> {

	private static final long serialVersionUID = 1L;
	private ModelAccessiWordReport model = new ModelAccessiWordReport();
	private PianoMiglioramentoWordService pianoMiglioramentoWordService;

	public PianoMiglioramentoWordAction(
			PianoMiglioramentoWordService pianoMiglioramentoWordService) {
		super();
		this.pianoMiglioramentoWordService = pianoMiglioramentoWordService;
	}

	// tab non conformita in report/piano miglioramento
	public String getTabReportPianoMiglioramentoWord() {
		return SUCCESS;
	}

	// tab non conformita in report/piano miglioramento
	public String generateReportPianoMiglioramentoWord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
				"DatiUtente");
		if (user != null) {
			ReportAccessoPDFDto report = pianoMiglioramentoWordService.getReportAccessoPDF(user.getIdSSessione());
			PianoMiglioramentoWordManager wordManager = new PianoMiglioramentoWordManager();
			wordManager.setReport(report);
			wordManager.setPianoMiglioramentoWordService(pianoMiglioramentoWordService);
			ByteArrayOutputStream byteArrayOutputStream = wordManager.creaWord();
			getModel().setExportWordFileName("PianoMiglioramento.docx");
			getModel().setExportWordFile(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
		}
		return SUCCESS;
	}

	@Override
	public ModelAccessiWordReport getModel() {
		return model;
	}

	public static ByteArrayInputStream reteriveByteArrayInputStream(File file) {
		try {
			return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
