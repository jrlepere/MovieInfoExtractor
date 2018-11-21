import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import crawler.MovieSiteCrawler;
import movie_saver.ElasticSearchBulkEntrySaver;
import movie_saver.MovieSaver;

/**
 * Main method for crawling movies on rotten tomatoes
 * @author JLepere2
 * @date 11/15/2018
 */
public class CrawlMovies {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		// initial seeds for the crawler
		List<String> seeds = new LinkedList<String>();
		seeds.add("https://www.rottentomatoes.com/top/bestofrt/");
		seeds.add("https://www.rottentomatoes.com/m/the_ballad_of_buster_scruggs");
		seeds.add("https://www.rottentomatoes.com/m/beautiful_mind");
		
		int totalMoviesToCrawl = Integer.MAX_VALUE;
		
		MovieSaver movieSaver = new ElasticSearchBulkEntrySaver("requests");
		
		// crawl totalMoviesToCrawl from the initial seeds and save the results
		// in json format for elasticsearch bulk posts.
		MovieSiteCrawler crawler = new MovieSiteCrawler(seeds, totalMoviesToCrawl, movieSaver);
		crawler.crawl();
	}
	
}
