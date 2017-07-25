package br.ufes.inf.nemo.marvin.research.controller;

import java.text.Normalizer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;
import br.ufes.inf.nemo.marvin.research.application.LinkedPublication;
import br.ufes.inf.nemo.marvin.research.application.ListPublicationsService;
import br.ufes.inf.nemo.marvin.research.application.ListResearchersService;
import br.ufes.inf.nemo.marvin.research.domain.Publication;

@Named
@ViewScoped
public class VerifyPublicationsController extends JSFController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(VerifyPublicationsController.class.getCanonicalName());
	
	private Academic selectedResearcher;
	private List<Academic> researchers;
	private List<LinkedPublication> linkedPublications;

	/** TODO: document this field. */
	private PersistentObjectConverterFromId<Academic> academicConverter;
	
	@EJB
	private ListResearchersService listResearchersService;
	
	@EJB
	private ListPublicationsService listPublicationsService;

	public Academic getSelectedResearcher() {
		return selectedResearcher;
	}
	
	public void setSelectedResearcher(Academic selectedResearcher) {
		this.selectedResearcher = selectedResearcher;
	}

	public List<Academic> getResearchers() {
		return researchers;
	}

	public List<LinkedPublication> getLinkedPublications() {
		return linkedPublications;
	}
	
	/** Getter for academicConverter. */
	public PersistentObjectConverterFromId<Academic> getAcademicConverter() {
		return academicConverter;
	}

	@Inject
	public void init(AcademicDAO academicDAO) {
		researchers = listResearchersService.listResearchers();
		academicConverter = new PersistentObjectConverterFromId<>(academicDAO);
	}

	public void processPublications() {
		if (selectedResearcher != null) {
			logger.log(Level.INFO, "Searching for publications of " + selectedResearcher.getName() + "...");
			linkedPublications = new LinkedList<LinkedPublication>();
			List<Publication> researcherPublications = listPublicationsService.listPublicationsByAcademic(selectedResearcher);
			
			for (Publication p : researcherPublications) {
				LinkedPublication lp = new LinkedPublication(p, "No URI available.");
				linkedPublications.add(lp);
			}
			
			String authorName = selectedResearcher.getName();
			//Normalizes the author name, separating any special accent it may have from the letter
			authorName = Normalizer.normalize(authorName, Normalizer.Form.NFD);
			//Removes all occurrences of accents in the string, thus making it "clean"
			authorName = authorName.replaceAll("[^\\p{ASCII}]", "");
			String authorURI = "<http://dblp.l3s.de/d2r/resource/authors/" + authorName.replace(" ", "_") + ">";
			String query = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " + 
			"PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"PREFIX dc: <http://purl.org/dc/elements/1.1/> " +
			"PREFIX owl:  <http://www.w3.org/2002/07/owl#> " +
			"PREFIX akt:  <http://www.aktors.org/ontology/portal#> " +
			"PREFIX akts: <http://www.aktors.org/ontology/support#> " +
			"SELECT DISTINCT ?title ?uri " + "WHERE { "
					+ "?uri foaf:maker ?author ."
					+ "?uri dc:title ?title ."
					+ "FILTER (?author = " + authorURI + ") "
					+ "}"
			+ "ORDER BY ?title";
			QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dblp.l3s.de/d2r/sparql", query);
			ResultSet results = queryExecution.execSelect();
			while (results.hasNext()) {
				QuerySolution querySolution = results.next();
				String title = querySolution.getLiteral("title").toString().toLowerCase();
				for (LinkedPublication lp : linkedPublications) {
					String adjustedTitle = lp.getPublication().getTitle().toLowerCase() + ".";
					if (adjustedTitle.contains(title)) {
						lp.setUri(querySolution.getResource("uri").getURI());
						break;
					}
				}
			}
			Collections.sort(linkedPublications);
		}
		//logger.log(Level.INFO, "Tamanho da nova lista de publicações: " + linkedPublications.size());
	}
}
