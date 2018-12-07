package Database;

public class Screen {

	private String screenId;
	private String theaterId;
	private String screenNum;
	private int availSeat;
	private String preScreenId;

	public Screen() {
		super();
	}

	public Screen(String screenId) {
		super();
		this.screenId = screenId;
	}

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public String getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}

	public String getScreenNum() {
		return screenNum;
	}

	public void setScreenNum(String screenNum) {
		this.screenNum = screenNum;
	}

	public int getAvailSeat() {
		return availSeat;
	}

	public void setAvailSeat(int availSeat) {
		this.availSeat = availSeat;
	}
	
	public String getPreScreenId() {
		return preScreenId;
	}

	public void setPreScreenId(String preScreenId) {
		this.preScreenId = preScreenId;
	}

	@Override
	public String toString() {
		return null;
	}

}
