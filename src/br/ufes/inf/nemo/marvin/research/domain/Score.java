package br.ufes.inf.nemo.marvin.research.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.inf.nemo.marvin.research.domain.Venue.Category;

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
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Category category;
	
	@Basic
	private int nationalScore;
	
	@Basic
	private int internationalScore;

	public Qualis getQualis() {
		return qualis;
	}

	public void setQualis(Qualis qualis) {
		this.qualis = qualis;
	}
	
	/** Getter for category. */
	public Category getCategory() {
		return category;
	}

	/** Setter for category. */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/** Setter for category. */
	public void setCategory(String category) {
		if (category.equals("Conference")) this.category = Category.CONFERENCE;
		else this.category = Category.JOURNAL;
	}

	public ScoreSystem getScoreSystem() {
		return scoreSystem;
	}

	public void setScoreSystem(ScoreSystem scoreSystem) {
		this.scoreSystem = scoreSystem;
	}

	public int getNationalScore() {
		return nationalScore;
	}

	public void setNationalScore(int nationalScore) {
		this.nationalScore = nationalScore;
	}

	public int getInternationalScore() {
		return internationalScore;
	}

	public void setInternationalScore(int internationalScore) {
		this.internationalScore = internationalScore;
	}
	
	/** Venue category */
	public enum Category {
		JOURNAL("Journal"),
		CONFERENCE("Conference");
		
		String name;
		
		/** Getter for category name. */		
		public String getName() {
			return name;
		}

		/** Setter for category name. */
		public void setName(String name) {
			this.name = name;
		}

		Category(String name) {
			this.name = name;
		}
	}
	
}
