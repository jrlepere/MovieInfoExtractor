import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.Before;

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
	
}
