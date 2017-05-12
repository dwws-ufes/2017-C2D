package br.ufes.inf.nemo.marvin.research.application;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.research.domain.Qualification;
import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.exceptions.CSVParseException;
import br.ufes.inf.nemo.marvin.research.exceptions.QualisLevelNotRegisteredException;
import br.ufes.inf.nemo.marvin.research.persistence.QualificationDAO;
import br.ufes.inf.nemo.marvin.research.persistence.QualisDAO;
import br.ufes.inf.nemo.marvin.research.persistence.VenueDAO;

@Stateless
@RolesAllowed("SysAdmin")
public class ImportQualisDataServiceBean implements ImportQualisDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ImportQualisDataServiceBean.class.getCanonicalName());

	@EJB
	private VenueDAO venueDAO;
	
	@EJB
	private QualisDAO qualisDAO;
	
	@EJB
	private QualificationDAO qualificationDAO;


	@Override
	public Set<QualifiedVenue> importQualisData(InputStream inputStream, String category)
			throws CSVParseException, QualisLevelNotRegisteredException {
		// TODO Auto-generated method stub
		CSVParser csvParser = new CSVParser(inputStream);
		Set<QualifiedVenue> qualifiedVenues = verifyParsedData(csvParser.getVenuesMap(), category);
		return qualifiedVenues;
	}

	private Set<QualifiedVenue> verifyParsedData(Map<String, String> parsedData, String category)
			throws QualisLevelNotRegisteredException {
		//Creates a new set that holds the name and reference to all registered venues
		Map<String,Venue> venues = new HashMap<String,Venue>();
		for (Venue v : venueDAO.retrieveAll()) {
			venues.put(v.getName().toLowerCase(), v);
		}
		
		//Creates a new set that holds the information of the Venue objects and their respective Qualis
		Set<QualifiedVenue> qualifiedVenues = new HashSet<QualifiedVenue>();
		for (String key : parsedData.keySet()) {
			// Retrieves the Qualis level
			String level = parsedData.get(key);			
			try {
				Qualis qualis = qualisDAO.retrieveByLevel(level);
				if (venues.containsKey(key.toLowerCase())) {
					// Venue already exists in system
					qualifiedVenues.add(new CSVEntry(venues.get(key.toLowerCase()), qualis));
				} else {
					Venue newVenue = new Venue(key, category);
					qualifiedVenues.add(new CSVEntry(newVenue, qualis));
				}
			} catch (PersistentObjectNotFoundException e) {
				// If there is no Qualis with the given level, throw an
				// exception from the domain.
				logger.log(Level.WARNING,
						"No qualis found with level ",
						level);
				throw new QualisLevelNotRegisteredException(level);
			} catch (MultiplePersistentObjectsFoundException e) {
				// This is a bug. Log and throw a runtime exception.
				logger.log(Level.SEVERE, "Multiple qualis found with the same level: " + level, e);
				throw new EJBException(e);
			}
		}
		return qualifiedVenues;
	}

	@Override
	public void assignQualificationsToVenues(Set<QualifiedVenue> qualifiedVenues, int year) {
		// TODO Auto-generated method stub
		try {
		for (QualifiedVenue qv : qualifiedVenues) {
			Venue v = qv.getVenue();
			if (!v.isPersistent()) {
				venueDAO.save(v);
			}
			Qualis q = qv.getQualis();
			Qualification qualification = new Qualification(year, q, v);
			qualificationDAO.save(qualification);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
