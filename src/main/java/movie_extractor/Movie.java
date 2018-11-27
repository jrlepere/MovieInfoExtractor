package movie_extractor;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Movie object containing relevant movie information.
 * @author JLepere2
 * @date 11/15/2018
 */
public class Movie {

	private static final Gson GSON = new Gson();
	private String title, description, siteURL, rating, imageURL;
	private String[] cast;
	
	public Movie(String title, String description, String siteURL, String rating, List<String> cast, String imageURL) {
		this.title = title;
		this.description = description;
		this.siteURL = siteURL;
		this.rating = rating;
		this.imageURL = imageURL;
		this.cast = new String[cast.size()];
		int i = 0;
		for (String castMember : cast) {
			this.cast[i] = "\"" + castMember + "\"";
			i ++;
		}
	}
	
	/**
	 * Gets a formatted json string for posting to elastic search bulk.
	 * {"index":{"_index":"movies","_type":"movies"}}
	 * {"title":...,"description":...,"url":...}
	 * @return formatted elasticsearch post bulk entry.
	 */
	public String getElasticSearchPostBulkEntry() {
		return "{\"index\":{\"_index\":\"movies\",\"_type\":\"movie\"}}"
				+  "\n" + new GsonBuilder().disableHtmlEscaping().create().toJson(this);
	}
	
	public String toString() {
		return new GsonBuilder().disableHtmlEscaping().create().toJson(this);
	}
	
}
