import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Extracts relevant movie information from rotten tomatoes movie page.
 * @author JLepere2
 * @date 11/15/2018
 */
public class MovieInformationExtractor {

	private Document movieDocument;
	
	public MovieInformationExtractor(String rottenTomatoesURL) throws IOException {
		this.movieDocument = Jsoup.connect(rottenTomatoesURL).get();
	}
	
	public Movie getMovieInfo() {
		return new Movie(
				getMovieTitle(),
				getMovieDescription(),
				getMovieURL());
	}
	
	public String getMovieDescription() {
		return this.movieDocument.select("meta[property=og:description]").get(0).attr("content");
	}
	
	public String getMovieTitle() {
		return this.movieDocument.select("meta[property=og:title]").get(0).attr("content");
	}
	
	public String getMovieURL() {
		return this.movieDocument.select("meta[property=og:url]").get(0).attr("content");
	}
	
	public List<String> getAllOutGoingLinks() {
		List<String> allOutGoingLinks = new LinkedList<String>();
		Elements links = this.movieDocument.select("a[href]");
		for (Element link : links) {
			allOutGoingLinks.add(link.attr("abs:href"));
		}
		return allOutGoingLinks;
	}
	
}
