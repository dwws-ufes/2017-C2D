package br.ufes.inf.nemo.marvin.c2d.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.c2d.domain.Venue.Category;
import br.ufes.inf.nemo.marvin.c2d.domain.Venue.Scope;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-29T18:36:25.796-0300")
@StaticMetamodel(Venue.class)
public class Venue_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Venue, Qualification> qualification;
	public static volatile SingularAttribute<Venue, Scope> scope;
	public static volatile SingularAttribute<Venue, Category> category;
}
