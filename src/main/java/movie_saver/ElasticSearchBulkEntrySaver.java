package movie_saver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import movie_extractor.Movie;

public class ElasticSearchBulkEntrySaver implements MovieSaver {

	private OutputStreamWriter fileWriter;
	
	public ElasticSearchBulkEntrySaver(String filename) throws IOException {
		fileWriter = new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8);
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
