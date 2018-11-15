import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MovieSiteCrawlerTest {

	private MovieSiteCrawler movieSiteCrawler;
	
	@Before
	public void loadMovieSiteCrawler() throws IOException {
		List<String> seeds = new LinkedList<String>();
		seeds.add("https://www.rottentomatoes.com/m/the_ballad_of_buster_scruggs");
		this.movieSiteCrawler = new MovieSiteCrawler(seeds, 5);
	}
	
	@Test
	public void crawlTest() throws InterruptedException, IOException {
		movieSiteCrawler.crawl("test");
	}
	
	@Test
	public void extractAcceptableAndUniqueURLsTest() throws IOException {
		String url = "https://www.rottentomatoes.com/m/the_ballad_of_buster_scruggs";
		MovieInformationExtractor movieInfoExtractor = new MovieInformationExtractor(url);
		List<String> outLinks = movieInfoExtractor.getAllOutGoingLinks();
		for (String outLink : movieSiteCrawler.extractAcceptableAndUniqueURLs(outLinks)) {
			System.out.println(outLink);
		}
	}
	
}
