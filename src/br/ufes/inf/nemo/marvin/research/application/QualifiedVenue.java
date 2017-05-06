package br.ufes.inf.nemo.marvin.research.application;

import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.domain.Venue;

public interface QualifiedVenue {
	Venue getVenue();
	Qualis getQualis();
}
