package br.ufes.inf.nemo.marvin.research.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Venue extends PersistentObjectSupport {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String name;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Category category;
	
	protected Venue () {}
	
	public Venue(String name) {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
