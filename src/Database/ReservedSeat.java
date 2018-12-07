package Database;

public class ReservedSeat {

	private int screenMovieId;
	private String seat;
	private boolean reserveBool;
	private String onScreenDate;

	public ReservedSeat() {
		super();
	}

	public ReservedSeat(int screenMovieId) {
		super();
		this.screenMovieId = screenMovieId;
	}

	public int getScreenMovieId() {
		return screenMovieId;
	}

	public void setScreenMovieId(int screenMovieId) {
		this.screenMovieId = screenMovieId;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public boolean isReserveBool() {
		return reserveBool;
	}

	public void setReserveBool(boolean reserveBool) {
		this.reserveBool = reserveBool;
	}

	public String getOnScreenDate() {
		return onScreenDate;
	}

	public void setOnScreenDate(String onScreenDate) {
		this.onScreenDate = onScreenDate;
	}

	@Override
	public String toString() {
		return null;
	}

}
