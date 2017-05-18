package br.ufes.inf.nemo.marvin.research.application;

import java.util.List;

import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.research.domain.AcademicScore;

@Stateless
public class CalculateAcademicsScoreServiceBean implements CalculateAcademicsScoreService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -494464487767933751L;

	@Override
	public List<AcademicScore> calculateAcademicsScore(List<AcademicScore> academicList, int startYear, int endYear) {
		//iterar
		//criar obj academic score para cada academico
		//set academic
		//buscar publicacao desse academic
		//filtrar pelo ano
		//olhar publicationDAO
		///
		
		//iterar na lista de publicacoes
		//acha venue
		// busca qualification
		//buscar qualis
		//buscar bd de scores com todas as qualis vigentes(scoresystem valendo) 
		
		
		
		return null;
	} 

}
