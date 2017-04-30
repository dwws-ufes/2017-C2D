package br.ufes.inf.nemo.marvin.c2d.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Score extends PersistentObjectSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@NotNull
	private Qualis qualis;

	@ManyToOne
	@NotNull
	private ScoreSystem scoreSystem;
	
	@Basic
	private Long nationalScore;
	
	@Basic
	private Long internationalScore;
}
