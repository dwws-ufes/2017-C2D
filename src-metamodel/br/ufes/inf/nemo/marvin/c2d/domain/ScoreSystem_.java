package br.ufes.inf.nemo.marvin.c2d.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-29T17:16:50.831-0300")
@StaticMetamodel(ScoreSystem.class)
public class ScoreSystem_ extends PersistentObjectSupport_ {
	public static volatile SetAttribute<ScoreSystem, Score> scores;
	public static volatile SingularAttribute<ScoreSystem, Date> startDate;
	public static volatile SingularAttribute<ScoreSystem, Date> endDate;
}
