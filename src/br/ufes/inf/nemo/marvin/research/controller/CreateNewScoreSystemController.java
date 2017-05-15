package br.ufes.inf.nemo.marvin.research.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.research.application.CreateNewScoreSystemService;
import br.ufes.inf.nemo.marvin.research.domain.Score;

@Named
@ConversationScoped
public class CreateNewScoreSystemController extends JSFController {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(CreateNewScoreSystemController.class.getCanonicalName());
	
	/** Path to the folder where the view files (web pages) for this action are placed. */
	private static final String VIEW_PATH = "/research/createNewScoreSystem/";
	
	/** TODO: document this field. */
	@Inject
	private Conversation conversation;
	
	@EJB
	private CreateNewScoreSystemService createNewScoreSystemService;
	
	private List<Score> scores;
	
	@PostConstruct
	public void init() {
		logger.log(Level.FINE, "Initializing CreateNewScoreController: loading new scores from current qualis...");
		scores = createNewScoreSystemService.constructScoresFromQualis();
		// Begin the conversation.
		if (conversation.isTransient()) conversation.begin();
	}

	public List<Score> getScores() {
		return scores;
	}
	
	public String save() {
		//Creates the new score system
		createNewScoreSystemService.createNewScoreSystem(scores);
		// Ends the conversation and renders a result page.
		if (!conversation.isTransient()) conversation.end();
		return VIEW_PATH + "success.xhtml";
	}
}
