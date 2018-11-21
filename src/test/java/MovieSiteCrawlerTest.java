import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import crawler.MovieSiteCrawler;
import movie_extractor.MovieInformationExtractor;
import movie_saver.ElasticSearchBulkEntrySaver;
import movie_saver.MovieSaver;

public class MovieSiteCrawlerTest {

	private MovieSiteCrawler movieSiteCrawler;
	
	@Before
	public void loadMovieSiteCrawler() throws IOException {
		List<String> seeds = new LinkedList<String>();
		seeds.add("https://www.rottentomatoes.com/m/the_ballad_of_buster_scruggs");
		MovieSaver movieSaver = new ElasticSearchBulkEntrySaver("test");
		this.movieSiteCrawler = new MovieSiteCrawler(seeds, 25, movieSaver);
	}
	
	@Test
	public void crawlTest() throws InterruptedException, IOException {
		movieSiteCrawler.crawl();
	}
	
	@Test
	public void extractAcceptableAndUniqueURLsTest() throws IOException, InterruptedException {
		String url = "https://www.rottentomatoes.com/m/the_ballad_of_buster_scruggs";
		MovieInformationExtractor movieInfoExtractor = new MovieInformationExtractor(url);
		List<String> outLinks = movieInfoExtractor.getAllOutGoingLinks();
		for (String outLink : movieSiteCrawler.extractAcceptableAndUniqueURLs(outLinks)) {
			System.out.println(outLink);
		}
	}
	
}
