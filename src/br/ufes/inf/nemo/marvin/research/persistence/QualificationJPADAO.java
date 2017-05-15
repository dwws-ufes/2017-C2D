package br.ufes.inf.nemo.marvin.research.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.research.domain.Qualification;
import br.ufes.inf.nemo.marvin.research.domain.Qualification_;
import br.ufes.inf.nemo.marvin.research.domain.Venue;

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

	@Override
	public List<Qualification> retrieveByVenue(Venue venue) {
		logger.log(Level.FINE, "Retrieving the qualifications of venue \"{0}\".", new Object[] { venue.getName() });

		// Constructs the query over the Publication class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Qualification> cq = cb.createQuery(Qualification.class);
		Root<Qualification> root = cq.from(Qualification.class);

		// Filters the query with the academic.
		cq.where(cb.equal(root.get(Qualification_.venue), venue));
		List<Qualification> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieve qualifications of venue \"{0}\" returned {1} results.", new Object[] { venue.getName(), result.size() });
		return result;
	}

	@Override
	public List<Qualification> retrieveByYear(int year) {
		logger.log(Level.FINE, "Retrieving the qualifications of year \"{0}\".", new Object[] { year });

		// Constructs the query over the Publication class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Qualification> cq = cb.createQuery(Qualification.class);
		Root<Qualification> root = cq.from(Qualification.class);

		// Filters the query with the academic.
		cq.where(cb.equal(root.get(Qualification_.year), year));
		List<Qualification> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieve qualifications of year \"{0}\" returned {1} results.", new Object[] { year, result.size() });
		return result;
	}
}