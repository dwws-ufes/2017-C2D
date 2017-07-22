package br.ufes.inf.nemo.marvin.research.application;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.persistence.PublicationDAO;

@Stateless
@RolesAllowed({ "SysAdmin", "Professor" })
public class ListPublicationsServiceBean implements ListPublicationsService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(ListPublicationsServiceBean.class.getCanonicalName());
	
	@EJB
	private PublicationDAO publicationDAO;

	@Override
	public List<Publication> listPublicationsByAcademic(Academic researcher) {
		logger.log(Level.INFO, "Listing publications...");
		
		List<Publication> publications = publicationDAO.retrieveByAcademic(researcher);
		Collections.sort(publications);
		return publications;
	}
	
}
