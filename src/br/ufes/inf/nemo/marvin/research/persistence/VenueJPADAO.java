package br.ufes.inf.nemo.marvin.research.persistence;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.research.domain.Venue;

@Stateless
public class VenueJPADAO extends BaseJPADAO<Venue> implements VenueDAO  {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(VenueJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
