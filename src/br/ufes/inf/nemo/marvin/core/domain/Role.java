package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.json.JSONObject;

import br.ufes.inf.nemo.jbutler.ResourceUtil;
import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Entity
public class Role extends PersistentObjectSupport {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** Name of the System Administrator role. */
	public static final String SYSADMIN_ROLE_NAME = "SysAdmin";

	/** Name of the Professor role. */
	public static final String PROFESSOR_ROLE_NAME = "Professor";

	/** Name of the Staff role. */
	public static final String STAFF_ROLE_NAME = "Staff";

	/** Name of the Student role. */
	public static final String STUDENT_ROLE_NAME = "Student";

	/** Name of the Visitor role. */
	public static final String VISITOR_ROLE_NAME = "Visitor";

	/** The name that identifies the role across the system. */
	@Basic
	@Size(max = 10)
	private String name;

	/** Resource bundle key to the human-readable description of the role. */
	@Basic
	private String descriptionKey;

	/** Default constructor for JPA. */
	protected Role() {}

	/** Constructor. */
	public Role(String name, String descriptionKey) {
		this.name = name;
		this.descriptionKey = descriptionKey;
	}

	/** Getter for name. */
	public String getName() {
		return name;
	}

	/** Setter for name. */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter for description. */
	public String getDescriptionKey() {
		return descriptionKey;
	}

	/** Setter for description. */
	public void setDescriptionKey(String descriptionKey) {
		this.descriptionKey = descriptionKey;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport#toString() */
	@Override
	public String toString() {
		return name;
	}

	public static void main(String[] args) {
		System.out.println(ResourceUtil.getResourceAsFile("META-INF/installSystem/Role.json"));

		Role[] roles = new Role[] { new Role("SysAdmin", "core.role.sysadmin"), new Role("Professor", "core.role.professor"), new Role("Staff", "core.role.staff"), new Role("Student", "core.role.student"), new Role("Visitor", "core.role.visitor") };
		for (Role role : roles) {
			JSONObject obj = new JSONObject();
			obj.put("name", role.getName());
			obj.put("descriptionKey", role.getDescriptionKey());
			System.out.println(obj);
		}
	}
}
