package br.ufes.inf.nemo.marvin.research.application;

import java.io.InputStream;
import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.research.exceptions.CSVParseException;

@Local
public interface ImportQualisDataService extends Serializable {

	/**
	 * TODO: document this method.
	 * 
	 * @param inputStream
	 * @return
	 * @throws CSVParseException
	 */
	boolean importQualisData(InputStream inputStream) throws CSVParseException;

	
}
