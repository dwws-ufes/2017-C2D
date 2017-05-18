package br.ufes.inf.nemo.marvin.research.application;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import br.ufes.inf.nemo.marvin.research.domain.AcademicScore;

@Local
public interface CalculateAcademicsScoreService extends Serializable {

	List<AcademicScore> calculateAcademicsScore(List<AcademicScore> academics, int startYear, int endYear);  ;

}
