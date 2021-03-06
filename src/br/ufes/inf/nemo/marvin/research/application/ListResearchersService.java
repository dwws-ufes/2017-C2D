package br.ufes.inf.nemo.marvin.research.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Academic;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Local
public interface ListResearchersService extends Serializable {
	/**
	 * TODO: document this method.
	 * 
	 * @return
	 */
	List<Academic> listResearchers();

	/**
	 * TODO: document this method.
	 * 
	 * @param researcher
	 * @return
	 */
	Long countPublications(Academic researcher);
}
