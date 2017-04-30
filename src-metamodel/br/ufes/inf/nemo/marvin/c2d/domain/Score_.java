package br.ufes.inf.nemo.marvin.c2d.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-29T17:13:56.493-0300")
@StaticMetamodel(Score.class)
public class Score_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Score, Qualis> qualis;
	public static volatile SingularAttribute<Score, ScoreSystem> scoreSystem;
	public static volatile SingularAttribute<Score, Long> nationalScore;
	public static volatile SingularAttribute<Score, Long> internationalScore;
}
