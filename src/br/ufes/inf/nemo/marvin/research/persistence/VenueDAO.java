package br.ufes.inf.nemo.marvin.research.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.research.domain.Venue;

@Local
public interface VenueDAO extends BaseDAO<Venue> {

}
