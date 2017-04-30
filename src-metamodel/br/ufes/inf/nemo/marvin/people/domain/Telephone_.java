package br.ufes.inf.nemo.marvin.people.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-29T14:30:37.244-0300")
@StaticMetamodel(Telephone.class)
public class Telephone_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Telephone, String> number;
	public static volatile SingularAttribute<Telephone, ContactType> type;
}
