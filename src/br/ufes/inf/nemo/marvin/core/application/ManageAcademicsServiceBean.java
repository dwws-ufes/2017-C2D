package br.ufes.inf.nemo.marvin.core.application;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;
import br.ufes.inf.nemo.util.TextUtils;
import br.ufes.inf.nemo.util.ejb3.application.CrudException;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
@DeclareRoles({ "Admin" , "Alumni" , "Researcher" , "Student" , "Teacher" })
@RolesAllowed({ "Admin" })
public class ManageAcademicsServiceBean extends CrudServiceBean<Academic> implements ManageAcademicsService{

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	/** The DAO for Academic objects. */
	@EJB
	private AcademicDAO academicDAO;
	
	
	@Override
	public BaseDAO<Academic> getDAO() {
		return academicDAO;
	}

	@Override
	protected Academic createNewEntity() {
		return new Academic();
	}
	
	
	@Override
	public void validateUpdate(Academic entity) throws CrudException {
		Date now = new Date(System.currentTimeMillis());
		entity.setLastUpdateDate(now);
	}
	
	@Override
	public void validateCreate(Academic entity) throws CrudException {
		Date now = new Date(System.currentTimeMillis());
		entity.setLastUpdateDate(now);
		try {
			entity.setPassword(TextUtils.produceMd5Hash(entity.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}