package movie_saver;

import java.io.FileWriter;
import java.io.IOException;

import movie_extractor.Movie;

public class ElasticSearchBulkEntrySaver implements MovieSaver {

	private FileWriter fileWriter;
	
	public ElasticSearchBulkEntrySaver(String filename) throws IOException {
		fileWriter = new FileWriter(filename);
	}
	
	public void saveMovie(Movie m) {
		try {
			fileWriter.write(m.getElasticSearchPostBulkEntry());
			fileWriter.write("\n");
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
