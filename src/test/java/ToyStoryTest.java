import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import movie_extractor.MovieInformationExtractor;

public class ToyStoryTest {

	private MovieInformationExtractor movieInformationExtractor;
	
	@Before
	public void loadMovieInformationExtractor() throws IOException, InterruptedException {
		this.movieInformationExtractor = new MovieInformationExtractor(
				"https://www.rottentomatoes.com/m/toy_story_3/");
	}
	
	@Test
	public void getMovieDescriptionTest() throws IOException { 
		String movieDescriptionExpected = "\"Toy Story 3\" welcomes Woody, Buzz and the "
				+ "whole gang back to the big screen as Andy prepares to depart for "
				+ "college and his loyal toys find themselves in... daycare! These "
				+ "untamed tots with their sticky little fingers do not play nice, "
				+ "so it's all for one and one for all as plans for the great escape get "
				+ "underway. A few new faces-some plastic, some plush-join the adventure, "
				+ "including iconic swinging bachelor and Barbie's counterpart Ken, a "
				+ "thespian hedgehog named Mr. Pricklepants and a pink, strawberry-scented "
				+ "teddy bear called Lots-o'-Huggin' Bear.\">".toLowerCase();
		String movieDescriptionActual = movieInformationExtractor.getMovieDescription();
		assertEquals(movieDescriptionExpected, movieDescriptionActual);
	}
	
	@Test
	public void getMovieTitleTest() throws IOException { 
		String movieTitleExpected = "Toy Story 3";
		String movieTitleActual = movieInformationExtractor.getMovieTitle();
		assertEquals(movieTitleExpected, movieTitleActual);
	}
	
	@Test
	public void getMovieRatineTest() throws IOException { 
		String movieRatingExpected = "98%";
		String movieRatingActual = movieInformationExtractor.getMovieRating();
		assertEquals(movieRatingExpected, movieRatingActual);
	}
	
	@Test
	public void getMovieURLTest() throws IOException { 
		String movieURLExpected = "https://www.rottentomatoes.com/m/toy_story_3/";
		String movieURLActual = movieInformationExtractor.getMovieURL();
		assertEquals(movieURLExpected, movieURLActual);
	}
	
}
