package br.ufes.inf.nemo.marvin.research.application;

import java.io.Serializable;

import javax.ejb.Local;
import javax.enterprise.event.Observes;

import br.ufes.inf.nemo.marvin.core.controller.InstallEvent;

@Local
public interface InstallQualisService extends Serializable {
	public void installQualis(InstallEvent event);
}
