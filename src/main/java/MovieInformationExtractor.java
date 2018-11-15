import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MovieInformationExtractor {

	private Document movieDocument;
	
	public MovieInformationExtractor(String rottenTomatoesURL) throws IOException {
		this.movieDocument = Jsoup.connect(rottenTomatoesURL).get();
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
	
}
