package br.ufes.inf.nemo.marvin.research.domain;

public class PublicationScore {
	private Publication publication;
	private int score;
	private Qualis qualis;
	
	public Qualis getQualis() {
		return qualis;
	}
	public void setQualis(Qualis qualis) {
		this.qualis = qualis;
	}
	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
