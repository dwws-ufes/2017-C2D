package br.ufes.inf.nemo.marvin.research.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.research.application.ImportQualisDataService;
import br.ufes.inf.nemo.marvin.research.exceptions.CSVParseException;

@Named
@ConversationScoped
public class ImportQualisDataController extends JSFController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(UploadLattesCVController.class.getCanonicalName());
	
	/** Path to the folder where the view files (web pages) for this action are placed. */
	private static final String VIEW_PATH = "/research/importQualisData/";
	
	/** TODO: document this field. */
	@Inject
	private Conversation conversation;

	/** TODO: document this field. */
	@EJB
	private ImportQualisDataService importQualisDataService;
	
	/** TODO: document this field. */
	private UploadedFile file;
	
	private boolean isConference;
	
	private int year;

	/**
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isConference() {
		return isConference;
	}

	public void setConference(boolean isConference) {
		this.isConference = isConference;
	}

	public String upload() {
		if (conversation.isTransient()) conversation.begin();
		
		try {
			importQualisDataService.importQualisData(file.getInputstream());
		}
		catch (IOException | CSVParseException e) {
			logger.log(Level.INFO, "CSV parser error.");
			addGlobalI18nMessage("msgsResearch", FacesMessage.SEVERITY_ERROR, "importQualisData.error.CSVParseError.summary", "importQualisData.error.CSVParseError.detail");
			return null;
		}
		return VIEW_PATH + "success.xhtml?faces-redirect=true";
	}
	

}
