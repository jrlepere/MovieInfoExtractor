import java.io.IOException;
import java.util.Arrays;
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
		this.movieDocument = Jsoup.connect(rottenTomatoesURL).timeout(30*1000).get();
	}
	
	public Movie getMovieInfo() {
		return new Movie(
				getMovieTitle(),
				getMovieDescription(),
				getMovieURL(),
				getMovieRating(),
				getMovieCast());
	}
	
	public String getMovieDescription() {
		try {
			String description = this.movieDocument.select("meta[property=og:description]").get(0).attr("content");
			if (!description.trim().isEmpty()) {
				return description;
			} else {
				description = this.movieDocument.select("meta[property=og:description]").get(0).toString();
				int contentIndex = description.indexOf("content=");
				if (contentIndex > -1) {
					return description.substring(contentIndex + "content=".length());
				} else {
					return "";
				}
			}
		} catch (Exception e) {
			return "NA";
		}
	}
	
	public String getMovieTitle() {
		try {
			return this.movieDocument.select("meta[property=og:title]").get(0).attr("content");
		} catch (Exception e) {
			return "NA";
		}
	}
	
	public String getMovieURL() {
		try {
			return this.movieDocument.select("meta[property=og:url]").get(0).attr("content");
		} catch (Exception e) {
			return "NA";
		}
	}
	
	public String getMovieRating() {
		try {
			return this.movieDocument.getElementsByClass("critic-score").get(0).getElementsByClass("meter-value").get(0).text();
		} catch (Exception e) {
			return "NA";
		}
	}
	
	public List<String> getMovieCast() {
		List<String> cast = new LinkedList<String>();
		try {
			for (Element e : this.movieDocument.getElementById("movie-cast").getElementsByClass("cast-item")) {
				cast.add(e.text());
			}
			return cast;
		} catch (Exception e) {
			cast.add("NA");
			System.out.println(e.getMessage());
			return cast;
		}
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
