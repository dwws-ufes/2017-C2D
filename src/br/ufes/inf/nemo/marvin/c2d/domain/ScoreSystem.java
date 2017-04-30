package br.ufes.inf.nemo.marvin.c2d.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class ScoreSystem extends PersistentObjectSupport {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Score> scores;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
}
