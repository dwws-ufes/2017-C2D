package br.ufes.inf.nemo.marvin.research.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufes.inf.nemo.marvin.research.exceptions.CSVParseException;
import br.ufes.inf.nemo.marvin.research.exceptions.LattesParseException;

public class CSVParser {
	/** The logger. */
	private static final Logger logger = Logger.getLogger(LattesParser.class.getCanonicalName());
	
	/** CSV Contents */
	private Map<String,String> venuesMap;
		
	public Map<String,String> getVenuesMap() {
		return venuesMap;
	}

	public CSVParser(InputStream csvFile) throws CSVParseException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile))) {
        	String csvSplitBy = ";";
			String line = reader.readLine();
			venuesMap = new HashMap<String,String>();
			while (line != null) {
				String[] values = line.split(csvSplitBy);
				venuesMap.put(values[0].toLowerCase(), values[2].toLowerCase());
				line = reader.readLine();
			}

        } catch (Exception e) {
			// In case there's any exception that prevents us from reading the CSV file, wraps it in an exception the controller can
			// handle.
			logger.log(Level.SEVERE, "Exception while trying to read the contents of the CSV file that holds the Venues' qualifications.", e);
			throw new CSVParseException(e);
        }
	}

}
