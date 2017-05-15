package br.ufes.inf.nemo.marvin.research.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;

import br.ufes.inf.nemo.marvin.research.controller.VenuesImportEvent;
import br.ufes.inf.nemo.marvin.research.domain.ConferencePaper;
import br.ufes.inf.nemo.marvin.research.domain.JournalPaper;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.domain.VenueCategory;
import br.ufes.inf.nemo.marvin.research.persistence.PublicationDAO;
import br.ufes.inf.nemo.marvin.research.persistence.VenueDAO;

public class MatchPublicationsVenuesServiceBean implements MatchPublicationsVenuesService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7272196626452553473L;
	
	@EJB
	private PublicationDAO publicationDAO;
	
	@EJB
	private VenueDAO venueDAO;

	@Override
	public void matchPublicationsVenues(@Observes VenuesImportEvent event) {
		// TODO Auto-generated method stub
		List<Publication> publications = publicationDAO.retrieveAll();
		List<Venue> venues = venueDAO.retrieveAll();
		
		Map<String, Venue> journals = new HashMap<String, Venue>();
		Map<String, Venue> conferences = new HashMap<String, Venue>();
		
		for (Venue v : venues) {
			if (v.getCategory().equals(VenueCategory.CONFERENCE)) {
				conferences.put(v.getName().toLowerCase(), v);
			}
			else {
				journals.put(v.getName().toLowerCase(), v);
			}
		}
		
		for (Publication publication : publications) {
			if (publication.getVenue() == null) {
				if (publication instanceof ConferencePaper) {
					ConferencePaper paper = (ConferencePaper) publication;
					String conferenceProceedings = paper.getBookTitle().toLowerCase();
					if (conferences.containsKey(conferenceProceedings)) {
						paper.setVenue(conferences.get(conferenceProceedings));
						publicationDAO.save(paper);
					}
				}
				else if (publication instanceof JournalPaper) {
					JournalPaper paper = (JournalPaper) publication;
					String journalName = paper.getJournal().toLowerCase();
					if (conferences.containsKey(journalName)) {
						paper.setVenue(conferences.get(journalName));
						publicationDAO.save(paper);
					}				
				}
			}
		}
	}

}
