package br.ufes.inf.nemo.marvin.research.domain;

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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="qualis")
	private Set<Qualification> qualifications;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="qualis")
	private Set<Score> scores;

	/** Getter for level. */
	public String getLevel() {
		return level;
	}

	/** Setter for level. */
	public void setLevel(String level) {
		this.level = level;
	}

	/** Getter for qualifications. */
	public Set<Qualification> getQualifications() {
		return qualifications;
	}

	/** Setter for qualifications. */
	public void setQualifications(Set<Qualification> qualifications) {
		this.qualifications = qualifications;
	}

	/** Getter for scores. */	
	public Set<Score> getScores() {
		return scores;
	}

	/** Setter for scores. */
	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}
	
	
	

}
