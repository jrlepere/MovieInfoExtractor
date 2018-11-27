package crawler;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import movie_extractor.Movie;
import movie_extractor.MovieInformationExtractor;
import movie_saver.MovieSaver;

/**
 * Rotten tomatoes site crawler for extracting movie information.
 * @author JLepere2
 * @date 11/15/2018
 */
public class MovieSiteCrawler {
	
	public static final String SITE_DOMAIN = "https://www.rottentomatoes.com/";
	public static final String MOVIE_DOMAIN = "https://www.rottentomatoes.com/m/";
	private static final int SLEEP_TIME = 0; // seconds
	private Queue<String> urlFrontier;
	private Set<String> visitedURLs;
	private int totalMoviesToCrawl;
	private MovieSaver movieSaver;
	
	public MovieSiteCrawler(List<String> seedURLs, int totalMoviesToCrawl, MovieSaver movieSaver) {
		this.urlFrontier = new LinkedList<String>(seedURLs);
		this.totalMoviesToCrawl = totalMoviesToCrawl;
		this.visitedURLs = new HashSet<String>(1021);
		this.movieSaver = movieSaver;
	}
	
	public void crawl() throws InterruptedException, IOException {
		int numMoviesCrawled = 0;
		while (numMoviesCrawled < this.totalMoviesToCrawl && !this.urlFrontier.isEmpty()) {
			String movieURL = urlFrontier.remove();
			if (visitedURLs.contains(movieURL)) {
				continue;
			}
			boolean success = handleMovieURL(movieURL);
			if (success) {
				System.out.println("complete: " + movieURL);
				numMoviesCrawled ++;
			}
			TimeUnit.SECONDS.sleep(SLEEP_TIME);
		}
		movieSaver.close();
	}
	
	public boolean handleMovieURL(String url) {
		try {
			addURLToVisitedSet(url);
			addAllAcceptableURLsToFrontier(getAllOutGoingLinks(url));
			if (isAcceptableMovieURL(url)) {
				MovieInformationExtractor movieInfoExtractor = new MovieInformationExtractor(url);
				saveMovie(movieInfoExtractor.getMovieInfo());
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + ": " + url);
		}
		return false;
	}
	
	public void saveMovie(Movie m) {
		movieSaver.saveMovie(m);
	}
	
	public void addAllAcceptableURLsToFrontier(List<String> urls) {
		for (String url : extractAcceptableAndUniqueURLs(urls)) {
			if (!visitedURLs.contains(url)) {
				urlFrontier.add(url);
			}
		}
	}
	
	public void addURLToVisitedSet(String url){
		visitedURLs.add(url);
	}
	
	public Set<String> extractAcceptableAndUniqueURLs(List<String> urls) {
		Set<String> urlSet = new HashSet<String>();
		for (String url : urls) {
			if (isInSiteDomain(url)
					&& !urlSet.contains(url)) {
				urlSet.add(url);
			}
		}
		return urlSet;
	}
	
	public boolean isAcceptableMovieURL(String url) {
		return isInMovieDomain(url) && isExactlyOneSiteWithinMovieDomain(url);
	}
	
	public boolean isInSiteDomain(String url) {
		return url.startsWith(SITE_DOMAIN);
	}
	
	public boolean isInMovieDomain(String url) {
		return url.startsWith(MOVIE_DOMAIN);
	}
	
	public boolean isExactlyOneSiteWithinMovieDomain(String url) {
		int domainLength = MOVIE_DOMAIN.length();
		return url.length() > domainLength
				&& !url.substring(domainLength).contains("/")
				&& !url.contains("#");
	}
	
	public List<String> getAllOutGoingLinks(String url) throws IOException {
		Document doc = Jsoup.connect(url).timeout(5*1000).get();
		List<String> allOutGoingLinks = new LinkedList<String>();
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			allOutGoingLinks.add(link.attr("abs:href"));
		}
		return allOutGoingLinks;
	}
	
}
