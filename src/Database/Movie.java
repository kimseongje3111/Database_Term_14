package Database;

import java.io.Serializable;

public class Movie implements Serializable{

	private String movieId;
	private String movieName;
	private String director;
	private String casting;
	private String rating;
	private String keyInfo;
	private String premovieName;

	public Movie() {
		super();
	}

	public Movie(String movieId) {
		super();
		this.movieId = movieId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCast() {
		return casting;
	}

	public void setCast(String cast) {
		this.casting = cast;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getKeyInfo() {
		return keyInfo;
	}

	public void setKeyInfo(String keyInfo) {
		this.keyInfo = keyInfo;
	}

	public String getPremovieName() {
		return premovieName;
	}

	public void setPremovieName(String premovieName) {
		this.premovieName = premovieName;
	}

	@Override
	public String toString() {
		return null;
	}

}
