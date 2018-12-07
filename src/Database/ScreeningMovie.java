package Database;

public class ScreeningMovie {

	private int screenMovieId;
	private String screenDate;
	private String movieName;
	private String screenId;
	private String screenTime;

	public ScreeningMovie() {
		super();
	}

	public ScreeningMovie(int screenMovieId) {
		super();
		this.screenMovieId = screenMovieId;
	}

	public int getScreenMovieId() {
		return screenMovieId;
	}

	public void setScreenMovieId(int screenMovieId) {
		this.screenMovieId = screenMovieId;
	}

	public String getScreenDate() {
		return screenDate;
	}

	public void setScreenDate(String screenDate) {
		this.screenDate = screenDate;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public String getScreenTime() {
		return screenTime;
	}

	public void setScreenTime(String screenTime) {
		this.screenTime = screenTime;
	}
	
	@Override
	public String toString() {
		return null;
	}

}
