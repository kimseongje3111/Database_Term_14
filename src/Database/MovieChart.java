package Database;

import java.io.Serializable;

public class MovieChart implements Serializable{

	private String movieName;
	private int ticketingRate;

	public MovieChart() {
		super();
	}

	public MovieChart(String movieName) {
		super();
		this.movieName = movieName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getTicketingRate() {
		return ticketingRate;
	}

	public void setTicketingRate(int ticketingRate) {
		this.ticketingRate = ticketingRate;
	}
	
	@Override
	public String toString() {
		return null;
	}

}
