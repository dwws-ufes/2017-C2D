package br.ufes.inf.nemo.marvin.c2d.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Qualis extends PersistentObjectSupport {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@Basic
	@NotNull
	private String level;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Qualification> qualifications;
	
	@OneToMany
	private Set<Score> scores;
	

}
