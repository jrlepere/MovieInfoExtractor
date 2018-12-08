package movie_extractor;
import java.util.List;

/**
 * Movie object containing relevant movie information.
 * @author JLepere2
 * @date 11/15/2018
 */
public class Movie {

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
	
}
