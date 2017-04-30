package br.ufes.inf.nemo.marvin.c2d.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-29T17:40:53.561-0300")
@StaticMetamodel(Requirement.class)
public class Requirement_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Requirement, String> name;
	public static volatile SingularAttribute<Requirement, Date> startDate;
	public static volatile SingularAttribute<Requirement, Date> endDate;
	public static volatile SingularAttribute<Requirement, Long> totalScore;
	public static volatile SingularAttribute<Requirement, Long> journalScore;
}
