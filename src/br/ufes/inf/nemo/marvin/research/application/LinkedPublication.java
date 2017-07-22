package br.ufes.inf.nemo.marvin.research.application;

import br.ufes.inf.nemo.marvin.research.domain.Publication;

public class LinkedPublication implements Comparable<LinkedPublication> {
	private Publication publication;
	private String uri;
	
	public Publication getPublication() {
		return publication;
	}
	
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}

	public LinkedPublication(Publication publication, String uri) {
		this.publication = publication;
		this.uri = uri;
	}

	@Override
	public int compareTo(LinkedPublication arg0) {
		// TODO Auto-generated method stub
		return publication.compareTo(arg0.getPublication());
	}
	
}
