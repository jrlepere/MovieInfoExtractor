package movie_saver;

import movie_extractor.Movie;

public interface MovieSaver {
	public void saveMovie(Movie m);
	public void close();
}
