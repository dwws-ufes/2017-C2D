package br.ufes.inf.nemo.marvin.research.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.AcademicScore;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.domain.PublicationScore;
import br.ufes.inf.nemo.marvin.research.domain.Qualification;
import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.domain.Score;
import br.ufes.inf.nemo.marvin.research.domain.ScoreSystem;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.domain.VenueCategory;
import br.ufes.inf.nemo.marvin.research.persistence.PublicationDAO;
import br.ufes.inf.nemo.marvin.research.persistence.QualificationDAO;
import br.ufes.inf.nemo.marvin.research.persistence.ScoreDAO;
import br.ufes.inf.nemo.marvin.research.persistence.ScoreSystemDAO;

@Stateless
public class CalculateAcademicsScoreServiceBean implements CalculateAcademicsScoreService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -494464487767933751L;
	
	@EJB
	private PublicationDAO publicationDAO;

	@EJB
	private ScoreSystemDAO scoreSystemDAO;
	
	@EJB
	private ScoreDAO scoreDAO;
	
	@EJB
	private QualificationDAO qualificationDAO;
	
	@Override
	public List<AcademicScore> calculateAcademicsScore(List<Academic> academics, int startYear, int endYear) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
			List<AcademicScore> academicScoreList = new ArrayList<AcademicScore>(); 
			List<Venue> venuesList = new ArrayList<Venue>();
			List<Qualification> qualificationList = new ArrayList<Qualification>();
			ScoreSystem currentScoreSystem = scoreSystemDAO.retrieveCurrentScoreSystem();
			List<Score> currentScoresList = scoreDAO.retrieveByScoreSystem(currentScoreSystem);
			Map<Qualis, Score> scoreQualisMap = new HashMap<Qualis, Score>();
			
			for(Score s : currentScoresList) {
				scoreQualisMap.put(s.getQualis(), s);
			}
			
			for(Academic a : academics) {
				AcademicScore as = new AcademicScore();
				academicScoreList.add(as);
				as.setAcademic(a);
				
				List<Publication> publicationsList = publicationDAO.retrieveByAcademicAndYearRange(a, startYear, endYear);
				List<PublicationScore> publicationScoreList = new ArrayList<PublicationScore>();
				
				int scoreAcademic = 0;
				
				for(Publication p : publicationsList) {
					Venue pubVenue = p.getVenue();
					List<Qualification> quaPubVenue = qualificationDAO.retrieveByVenue(pubVenue);
					PublicationScore publicationScore = new PublicationScore();
					
					publicationScore.setPublication(p);
					publicationScoreList.add(publicationScore);
					
					int currentScore = 0;
					boolean venueIsConference = true;
					
					if(pubVenue.getCategory().equals(VenueCategory.JOURNAL)) {
						venueIsConference = false;
					}
					
					for(Qualification q : quaPubVenue) {
						Qualis qualis = q.getQualis();
						Score score = scoreQualisMap.get(q.getQualis());
						
						if(venueIsConference) {
							currentScore += score.getScoreConference();
						}
						else currentScore += score.getScoreConference();; 
  					}
					publicationScore.setScore(currentScore);
					scoreAcademic += currentScore;
					 
				}
				as.setScoreTotal(scoreAcademic);

			}
			return null;
	}
			
			
			//iterar na lista de publicacoes
			//acha venue
			// busca qualification
			//buscar qualis
			//buscar bd de scores com todas as qualis vigentes(scoresystem valendo) 


}
