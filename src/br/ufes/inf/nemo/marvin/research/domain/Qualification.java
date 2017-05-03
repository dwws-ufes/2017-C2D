package br.ufes.inf.nemo.marvin.research.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Qualification extends PersistentObjectSupport {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@NotNull
	private Qualis qualis;
	
	@ManyToOne
	private Venue venue;
	
	@Basic
	private int year;

	/** Getter for qualis. */
	public Qualis getQualis() {
		return qualis;
	}

	/** Setter for qualis. */
	public void setQualis(Qualis qualis) {
		this.qualis = qualis;
	}


	/** Getter for year. */
	public int getYear() {
		return year;
	}

	/** Setter for year. */
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}
