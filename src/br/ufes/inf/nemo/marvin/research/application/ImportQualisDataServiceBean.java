package br.ufes.inf.nemo.marvin.research.application;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.exceptions.CSVParseException;
import br.ufes.inf.nemo.marvin.research.persistence.QualisDAO;
import br.ufes.inf.nemo.marvin.research.persistence.VenueDAO;

@Stateless
@RolesAllowed("SysAdmin")
public class ImportQualisDataServiceBean implements ImportQualisDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VenueDAO venueDAO;
	
	@EJB
	private QualisDAO qualisDAO;

	@Override
	public boolean importQualisData(InputStream inputStream) throws CSVParseException {
		// TODO Auto-generated method stub
		CSVParser csvParser = new CSVParser(inputStream);
		Map<Venue,Qualis> qualifiedVenues = verifyParsedData(csvParser.getVenuesMap());
		return true;
	}
	
	private Map<Venue,Qualis> verifyParsedData(Map<String,String> parsedData) {
		List<Venue> venues = new LinkedList<Venue>(venueDAO.retrieveAll());
		List<Qualis> qualis = new LinkedList<Qualis>(qualisDAO.retrieveAll());
		Map<Venue,Qualis> qualifiedVenues = new HashMap<Venue,Qualis>();
		Venue selectedVenue;
		for (Venue v : venues) {
			if (parsedData.containsKey(v.getName().toLowerCase())) {
				
			}
		}
		return null;		
	}

}
