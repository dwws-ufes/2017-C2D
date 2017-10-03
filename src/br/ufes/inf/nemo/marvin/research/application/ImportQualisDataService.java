package br.ufes.inf.nemo.marvin.research.application;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.domain.VenueCategory;
import br.ufes.inf.nemo.marvin.research.exceptions.CSVParseException;
import br.ufes.inf.nemo.marvin.research.exceptions.QualisLevelNotRegisteredException;

@Local
public interface ImportQualisDataService extends Serializable {

	/**
	 * TODO: document this method.
	 * 
	 * @param inputStream
	 * @param category
	 * @return
	 * @throws CSVParseException
	 * @throws QualisLevelNotRegisteredException 
	 */
	List<QualifiedVenue> importQualisData(InputStream inputStream, VenueCategory category)
			throws CSVParseException, QualisLevelNotRegisteredException;
	
	Future<String> assignQualificationsToVenues(List<QualifiedVenue> qualifiedVenues, int year);



	
}
