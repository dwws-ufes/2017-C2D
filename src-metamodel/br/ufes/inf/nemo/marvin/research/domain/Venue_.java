package br.ufes.inf.nemo.marvin.research.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-17T21:49:05.656-0300")
@StaticMetamodel(Venue.class)
public class Venue_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Venue, String> acronym;
	public static volatile SingularAttribute<Venue, String> name;
	public static volatile SingularAttribute<Venue, VenueCategory> category;
	public static volatile SingularAttribute<Venue, String> issn;
}
