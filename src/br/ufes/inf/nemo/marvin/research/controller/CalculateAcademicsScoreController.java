package br.ufes.inf.nemo.marvin.research.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;
import br.ufes.inf.nemo.marvin.research.application.ListResearchersService;

public class CalculateAcademicsScoreController extends JSFController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int startYear;
	
	private int endYear;
	
	/** TODO: document this field. */
	private List<Academic> researchers;

	/** TODO: document this field. */
	private Academic selectedResearcher;
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(GenerateBibliographyController.class.getCanonicalName());

	/** TODO: document this field. */
	@Inject
	private Conversation conversation;
	
	/** TODO: document this field. */
	@EJB
	private ListResearchersService listResearchersService;

	/** TODO: document this field. */
	private PersistentObjectConverterFromId<Academic> academicConverter;

	public List<Academic> getResearchers() {
		return researchers;
	}
	public void setResearchers(List<Academic> researchers) {
		this.researchers = researchers;
	}
	public Academic getSelectedResearcher() {
		return selectedResearcher;
	}
	public void setSelectedResearcher(Academic selectedResearcher) {
		this.selectedResearcher = selectedResearcher;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public int getEndYear() {
		return endYear;
	}
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public Conversation getConversation() {
		return conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	/**
	 * TODO: document this method.
	 */
	public void addResearcher() {
		logger.log(Level.FINE, "Adding the selected researcher (if not null) to the configuration: {0}", selectedResearcher);
		if (selectedResearcher != null) researchers.add(selectedResearcher);
	}
	
	public String calculateScore() {
		
		return "";
	}
	
	

}
