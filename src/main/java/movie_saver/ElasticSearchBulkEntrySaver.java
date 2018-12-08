package movie_saver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.google.gson.GsonBuilder;

import movie_extractor.Movie;

public class ElasticSearchBulkEntrySaver implements MovieSaver {

	private OutputStreamWriter fileWriter;
	
	public ElasticSearchBulkEntrySaver(String filename) throws IOException {
		fileWriter = new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8);
	}
	
	public void saveMovie(Movie m) {
		try {
			fileWriter.write(getElasticSearchPostBulkEntry(m));
			fileWriter.write("\n");
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets a formatted json string for posting to elastic search bulk.
	 * {"index":{"_index":"movies","_type":"movies"}}
	 * {"title":...,"description":...,"url":...}
	 * @return formatted elasticsearch post bulk entry.
	 */
	private String getElasticSearchPostBulkEntry(Movie m) {
		return "{\"index\":{\"_index\":\"movies\",\"_type\":\"movie\"}}"
				+  "\n" + new GsonBuilder().disableHtmlEscaping().create().toJson(m);
	}

	public void close() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
