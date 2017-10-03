package br.ufes.inf.nemo.marvin.research.application;

import java.io.Serializable;
import java.util.concurrent.Future;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.research.controller.VenuesImportEvent;

@Local
public interface MatchPublicationsVenuesService extends Serializable {
	void matchPublicationsVenues(VenuesImportEvent event);
}

