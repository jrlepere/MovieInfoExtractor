import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import movie_extractor.Movie;
import movie_extractor.MovieInformationExtractor;

public class MovieTest {

	Movie movie;
	
	@Before
	public void setMovie() throws IOException, InterruptedException {
		movie = new MovieInformationExtractor("https://www.rottentomatoes.com/m/toy_story_3/").getMovieInfo();
	}
	
	@Test
	public void toStringTest() {
		System.out.println(this.movie.toString());
	}
	
}
