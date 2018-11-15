import com.google.gson.Gson;

/**
 * Movie object containing relevant movie information.
 * @author JLepere2
 * @date 11/15/2018
 */
public class Movie {

	private static final Gson GSON = new Gson();
	private String title, description, url;
	
	public Movie(String title, String description, String url) {
		this.title = title;
		this.description = description;
		this.url = url;
	}
	
	/**
	 * Gets a formatted json string for posting to elastic search bulk.
	 * {"index":{"_index":"movies","_type":"movies"}}
	 * {"title":...,"description":...,"url":...}
	 * @return formatted elasticsearch post bulk entry.
	 */
	public String getElasticSearchPostBulkEntry() {
		return "{\"index\":{\"_index\":\"movies\",\"_type\":\"movie\"}}"
		+  "\n" + GSON.toJson(this);
	}
	
	public String toString() {
		return GSON.toJson(this);
	}
	
}
