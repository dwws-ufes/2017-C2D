package br.ufes.inf.nemo.marvin.research.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.Publication;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Local
public interface ListPublicationsService extends Serializable {
	/**
	 * TODO: document this method.
	 * 
	 * @param researcher
	 * @return
	 */
	List<Publication> listPublicationsByAcademic(Academic researcher);

}
