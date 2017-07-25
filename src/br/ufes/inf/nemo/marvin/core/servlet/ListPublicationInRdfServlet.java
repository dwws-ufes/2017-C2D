package br.ufes.inf.nemo.marvin.core.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;
import br.ufes.inf.nemo.marvin.research.domain.ConferencePaper;
import br.ufes.inf.nemo.marvin.research.domain.JournalPaper;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.persistence.PublicationDAO;

/**
 * Servlet implementation class ListPackagesInRdfServlet
 */
@WebServlet(urlPatterns = { "/data/academics" } )
public class ListPublicationInRdfServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	private static final Logger logger = Logger.getLogger(ListPublicationInRdfServlet.class.getCanonicalName());
	
	@EJB
	private AcademicDAO academicDAO;
		
	@EJB
	private PublicationDAO publicationDAO;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
		resp.setContentType("text/xml");

		List<Academic> academics = academicDAO.retrieveAll();
		Collections.sort(academics);	
				
		List<Publication> publications = publicationDAO.retrieveAll();
		Collections.sort(publications);	
		
		Model model = ModelFactory.createDefaultModel();
	
		String myNSAcademic = "http://localhost:8080/c2d/data/Academic/";
		String myNSPublication = "http://localhost:8080/c2d/data/Publication/";
		String dblpNS = "http://dblp.uni-trier.de/rdf/schema#";
		
		model.setNsPrefix("dblp", dblpNS);
	
		//Resource and properties of Author
		Resource rAuthor = ResourceFactory.createResource(dblpNS + "Person");
		Property fullPersonName = ResourceFactory.createProperty(dblpNS + "fullPersonName");
		Property creatorOf = ResourceFactory.createProperty(dblpNS + "creatorOf");
		
		//Resource and properties of Publication
		Resource rPublication = ResourceFactory.createResource(dblpNS + "Publication");
		Property title = ResourceFactory.createProperty(dblpNS + "title");
		Property publicationType = ResourceFactory.createProperty(dblpNS + "publicationType");
		Property createdBy = ResourceFactory.createProperty(dblpNS + "createdBy");
		
		//Resource and properties of PublicationType
		Resource rArticle = ResourceFactory.createResource(dblpNS + "Article");
		Resource rInproceedings = ResourceFactory.createResource(dblpNS + "Inproceedings");
		
		
		for(Publication publication : publications) {
			Academic academic = publication.getOwner();
			Resource publResource = model.createResource(myNSPublication + publication.getId())
					.addProperty(RDF.type, rPublication)
					.addProperty(title, publication.getTitle());
			
			if(publication instanceof JournalPaper) {
				publResource.addProperty(publicationType, 
						model.createResource()
						.addProperty(RDF.type, rArticle))
				;
			}
			
			else  if(publication instanceof ConferencePaper) { 
					publResource.addProperty(publicationType, 
							model.createResource()
							.addProperty(RDF.type, rInproceedings));
			}
			
			Resource acadResource =	model.createResource(myNSAcademic + academic.getId())
			.addProperty(RDF.type, rAuthor)
			.addProperty(fullPersonName, academic.getName())
			.addProperty(creatorOf, publResource);
			academics.remove(academic);
			
			//publResource.addProperty(createdBy, acadResource);
			publResource.addProperty(createdBy, model.createResource()
							.addProperty(RDF.type, acadResource));
			
			logger.log(Level.INFO, "Added publication/" + publication.getId() + " to the RDF model");
			logger.log(Level.INFO, "Added academic/" + academic.getId() + " to the RDF model");
		}
		
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}