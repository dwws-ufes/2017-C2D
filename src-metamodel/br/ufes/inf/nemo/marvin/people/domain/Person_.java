package br.ufes.inf.nemo.marvin.people.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-30T09:39:26.487-0300")
@StaticMetamodel(Person.class)
public class Person_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, Date> birthDate;
	public static volatile SingularAttribute<Person, Character> gender;
	public static volatile SingularAttribute<Person, String> cpf;
}
