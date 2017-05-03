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

	@OneToMany(mappedBy="venue")
	@NotNull
	private Set<Qualification> qualifications;
	
	@OneToMany(mappedBy="venue")
	private Set<Publication> publications;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Category category;

	/** Getter for qualifications. */	
	public Set<Qualification> getQualifications() {
		return qualifications;
	}

	/** Setter for qualifications. */
	public void setQualifications(Set<Qualification> qualifications) {
		this.qualifications = qualifications;
	}
	
	public void assignQualification(Qualification qualification) {
		if (this.qualifications == null) this.qualifications = new HashSet<>();
		qualifications.add(qualification);
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
	
	/** Getter for publications. */
	public Set<Publication> getPublications() {
		return publications;
	}

	/** Setter for publications. */
	public void setPublications(Set<Publication> publications) {
		this.publications = publications;
	}
	
	/**
	 * Assigns a publication to a venue, i.e., adds the publication to the set of publications.
	 * 
	 * @param publication
	 *          The publication to assign.
	 */
	public void assignPublication(Publication publication) {
		if (publications == null) publications = new HashSet<>();
		publications.add(publication);
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
