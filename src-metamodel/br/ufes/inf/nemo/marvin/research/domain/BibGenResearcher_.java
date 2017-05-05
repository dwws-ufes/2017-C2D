package br.ufes.inf.nemo.marvin.research.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-04T16:30:56.806-0300")
@StaticMetamodel(BibGenResearcher.class)
public class BibGenResearcher_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<BibGenResearcher, Academic> researcher;
	public static volatile SingularAttribute<BibGenResearcher, Integer> startYear;
	public static volatile SingularAttribute<BibGenResearcher, Integer> endYear;
}
