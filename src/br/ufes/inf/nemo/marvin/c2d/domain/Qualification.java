package br.ufes.inf.nemo.marvin.c2d.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Qualification extends PersistentObjectSupport {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@NotNull
	private Qualis qualis;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Venue> venues;
	
	@Temporal(TemporalType.DATE)
	private Date year;
	
}
