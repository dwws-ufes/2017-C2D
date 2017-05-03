package br.ufes.inf.nemo.marvin.research.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.research.domain.Score.Category;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-03T13:37:50.116-0300")
@StaticMetamodel(Score.class)
public class Score_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Score, Qualis> qualis;
	public static volatile SingularAttribute<Score, ScoreSystem> scoreSystem;
	public static volatile SingularAttribute<Score, Category> category;
	public static volatile SingularAttribute<Score, Integer> nationalScore;
	public static volatile SingularAttribute<Score, Integer> internationalScore;
}
