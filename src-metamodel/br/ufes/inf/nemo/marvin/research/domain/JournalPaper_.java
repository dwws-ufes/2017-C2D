package br.ufes.inf.nemo.marvin.research.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-29T14:30:37.328-0300")
@StaticMetamodel(JournalPaper.class)
public class JournalPaper_ extends Publication_ {
	public static volatile SingularAttribute<JournalPaper, String> journal;
	public static volatile SingularAttribute<JournalPaper, String> number;
	public static volatile SingularAttribute<JournalPaper, String> volume;
	public static volatile SingularAttribute<JournalPaper, String> issn;
}
