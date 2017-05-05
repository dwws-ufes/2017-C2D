package br.ufes.inf.nemo.marvin.research.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-04T19:24:10.688-0300")
@StaticMetamodel(Qualis.class)
public class Qualis_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Qualis, String> level;
	public static volatile SetAttribute<Qualis, Qualification> qualifications;
	public static volatile SetAttribute<Qualis, Score> scores;
}
