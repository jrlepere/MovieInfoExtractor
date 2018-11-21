import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MovieInformationExtractorTest {

	private MovieInformationExtractor movieInformationExtractor;
	
	@Before
	public void loadMovieInformationExtractor() throws IOException {
		this.movieInformationExtractor = new MovieInformationExtractor(
				"https://www.rottentomatoes.com/m/the_ballad_of_buster_scruggs");
	}
	
	@Test
	public void getMovieDescriptionTest() throws IOException { 
		String movieDescriptionExpected = "The Ballad of Buster Scruggs is a six-part "
				+ "Western anthology film, a series of tales about the American frontier "
				+ "told through the unique and incomparable voice of Joel and Ethan Coen. "
				+ "Each chapter tells a distinct story about the American West.";
		String movieDescriptionActual = movieInformationExtractor.getMovieDescription();
		assertEquals(movieDescriptionExpected, movieDescriptionActual);
	}
	
	@Test
	public void getMovieTitleTest() throws IOException { 
		String movieTitleExpected = "The Ballad of Buster Scruggs";
		String movieTitleActual = movieInformationExtractor.getMovieTitle();
		assertEquals(movieTitleExpected, movieTitleActual);
	}
	
	@Test
	public void getMovieURLTest() throws IOException { 
		String movieURLExpected = "https://www.rottentomatoes.com/m/the_ballad_of_buster_scruggs/";
		String movieURLActual = movieInformationExtractor.getMovieURL();
		assertEquals(movieURLExpected, movieURLActual);
	}
	
	@Test
	public void getMovieRatingTest() throws IOException { 
		String movieRatingExpected = "93%";
		String movieRatingActual = movieInformationExtractor.getMovieRating();
		assertEquals(movieRatingExpected, movieRatingActual);
	}
	
	@Test
	public void getMovieCastTest() throws IOException { 
		List<String> movieRatingExpected = new LinkedList<String>();
		movieRatingExpected.add("Tim Blake Nelson");
		movieRatingExpected.add("Bill Heck");
		movieRatingExpected.add("James Franco");
		movieRatingExpected.add("Liam Neeson");
		movieRatingExpected.add("Zoe Kazan");
		movieRatingExpected.add("Tom Waits");
		movieRatingExpected.add("Tyne Daly");
		movieRatingExpected.add("Brendan Gleeson");
		List<String> movieRatingActual = movieInformationExtractor.getMovieCast();
		assertEquals(movieRatingExpected, movieRatingActual);
	}
	
	@Test
	public void getAllOutGoingLinksTest() throws IOException { 
		/*List<String> outLinks = movieInformationExtractor.getAllOutGoingLinks();
		for (String link : outLinks) {
			if (link.startsWith(MovieSiteCrawler.MOVIE_DOMAIN)) {
				System.out.println(link);
			}
		}*/
	}
	
}
