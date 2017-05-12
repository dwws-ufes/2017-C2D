package br.ufes.inf.nemo.marvin.research.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.research.domain.Venue.Category;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-12T16:55:33.907-0300")
@StaticMetamodel(Venue.class)
public class Venue_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Venue, String> acronym;
	public static volatile SingularAttribute<Venue, String> name;
	public static volatile SingularAttribute<Venue, Category> category;
}
