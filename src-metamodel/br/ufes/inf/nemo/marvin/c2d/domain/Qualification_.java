package br.ufes.inf.nemo.marvin.c2d.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-29T22:54:19.652-0300")
@StaticMetamodel(Qualification.class)
public class Qualification_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Qualification, Qualis> qualis;
	public static volatile SetAttribute<Qualification, Venue> venues;
	public static volatile SingularAttribute<Qualification, Date> year;
}
