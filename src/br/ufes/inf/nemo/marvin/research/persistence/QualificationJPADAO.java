package br.ufes.inf.nemo.marvin.research.persistence;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.research.domain.Qualification;

@Stateless
public class QualificationJPADAO extends BaseJPADAO<Qualification> implements QualificationDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(QualificationJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
