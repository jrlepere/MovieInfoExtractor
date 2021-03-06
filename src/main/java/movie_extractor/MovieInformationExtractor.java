package movie_extractor;
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
	private String url;
	
	public MovieInformationExtractor(String rottenTomatoesURL) throws IOException, InterruptedException {
		this.movieDocument = Jsoup.connect(rottenTomatoesURL).timeout(5*1000).get();
		this.url = rottenTomatoesURL;
	}
	
	public Movie getMovieInfo() {
		return new Movie(
				getMovieTitle(),
				getMovieDescription(),
				getMovieURL(),
				getMovieRating(),
				getMovieCast(),
				getMovieImageURL());
	}
	
	public String getMovieDescription() {
		try {
			String description = this.movieDocument.select("meta[property=og:description]").get(0).attr("content");
			if (!description.trim().isEmpty() && description.length() > 25) {
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
		return url;
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
			try {
				return new MovieInformationExtractor(url).getMovieCast();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			return cast;
		}
	}
	
	public String getMovieImageURL() {
		try {
			return this.movieDocument.select("meta[property=og:image]").get(0).attr("content");
		} catch (Exception e) {
			return "NA";
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
