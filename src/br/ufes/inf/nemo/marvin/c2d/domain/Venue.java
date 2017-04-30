package br.ufes.inf.nemo.marvin.c2d.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Venue extends PersistentObjectSupport {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@NotNull
	private Qualification qualification;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Scope scope;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Category category;
	
	/** Getter for qualification. */
	public Qualification getQualification() {
		return qualification;
	}

	/** Setter for qualification. */
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	/** Getter for scope. */
	public Scope getScope() {
		return scope;
	}

	/** Setter for scope. */
	public void setScope(Scope scope) {
		this.scope = scope;
	}
	
	/** Setter for scope. */
	public void setScope(String scope) {
		if (scope.equals("International")) this.scope = Scope.INTERNATIONAL;
		else this.scope = Scope.NATIONAL;
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
	
	/** Venue scope */
	public enum Scope {
		INTERNATIONAL("International"),
		NATIONAL("National");
		
		String name;
		
		/** Getter for category name. */		
		public String getName() {
			return name;
		}

		/** Setter for category name. */
		public void setName(String name) {
			this.name = name;
		}

		Scope(String name) {
			this.name = name;
		}
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
