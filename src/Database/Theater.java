package Database;

import java.io.Serializable;

public class Theater implements Serializable {

	private String theaterId;
	private String theaterAddr;
	private String theaterTel;
	private int screenNum;

	public Theater() {
		super();
	}

	public Theater(String theaterId) {
		super();
		this.theaterId = theaterId;
	}

	public String getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}

	public String getTheaterAddr() {
		return theaterAddr;
	}

	public void setTheaterAddr(String theaterAddr) {
		this.theaterAddr = theaterAddr;
	}

	public String getTheaterTel() {
		return theaterTel;
	}

	public void setTheaterTel(String theaterTel) {
		this.theaterTel = theaterTel;
	}

	public int getScreenNum() {
		return screenNum;
	}

	public void setScreenNum(int screenNum) {
		this.screenNum = screenNum;
	}
	
	@Override
	public String toString() {
		return null;
	}

}
