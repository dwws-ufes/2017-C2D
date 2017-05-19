package br.ufes.inf.nemo.marvin.research.application;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.AcademicScore;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.domain.PublicationScore;
import br.ufes.inf.nemo.marvin.research.domain.Qualification;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.persistence.PublicationDAO;

@Stateless
public class CalculateAcademicsScoreServiceBean implements CalculateAcademicsScoreService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -494464487767933751L;
	
	@EJB
	private PublicationDAO publicationDAO;

	@Override
	public List<AcademicScore> calculateAcademicsScore(List<Academic> academics, int startYear, int endYear) {
			List<AcademicScore> academicScoreList = new ArrayList<AcademicScore>(); 
			//List<Venue> venuesList = new ArrayList<Venue>();
			//List<Qualification> qualificationList = new ArrayList<Qualification>();
					
			for(Academic a : academics) {
				AcademicScore as = new AcademicScore();
				academicScoreList.add(as);
				as.setAcademic(a);
				
				List<Publication> publicationsList = publicationDAO.retrieveByAcademicAndYearRange(a, startYear, endYear);
				List<PublicationScore> publicationScoreList = new ArrayList<PublicationScore>();
							
				for(Publication p : publicationsList) {
										
				}
				
				as.setPublicationsScores(publicationScoreList);

			}
			return null;
	}
			
			
			//iterar na lista de publicacoes
			//acha venue
			// busca qualification
			//buscar qualis
			//buscar bd de scores com todas as qualis vigentes(scoresystem valendo) 


}
