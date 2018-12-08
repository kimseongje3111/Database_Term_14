package Database;

import java.io.Serializable;

public class Ticket implements Serializable{

	private String ticketId;
	private String movieName;
	private String theaterName;
	private String screenNum;
	private String screenDate;
	private String screenTime;
	private String seatNum;
	private String userId;
	private boolean paymentBool;
	private int usedPoint;

	public Ticket() {
		super();
	}

	public Ticket(String ticketId) {
		super();
		this.ticketId = ticketId;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getScreenNum() {
		return screenNum;
	}

	public void setScreenNum(String screenNum) {
		this.screenNum = screenNum;
	}

	public String getScreenDate() {
		return screenDate;
	}

	public void setScreenDate(String screenDate) {
		this.screenDate = screenDate;
	}

	public String getScreenTime() {
		return screenTime;
	}

	public void setScreenTime(String screenTime) {
		this.screenTime = screenTime;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isPaymentBool() {
		return paymentBool;
	}

	public void setPaymentBool(boolean paymentBool) {
		this.paymentBool = paymentBool;
	}

	public int getUsedPoint() {
		return usedPoint;
	}

	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}

	@Override
	public String toString() {
		return null;
	}

}
