package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.AcademicRole;
import br.ufes.inf.nemo.marvin.core.domain.Role;

/**
 * Interface for a DAO for objects of the Role domain class.
 * 
 * Using a mini CRUD framework for EJB3, basic DAO operation definitions are inherited from the superclass, whereas
 * operations that are specific to the managed domain class (if any) are specified in this class.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 * @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO
 */
@Local
public interface AcademicRoleDAO extends BaseDAO<AcademicRole> {

	AcademicRole retrieveByName(String name) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;

	List<AcademicRole> findByName(String name);
	
}
