package Database;

import java.io.Serializable;

public class User implements Serializable {

	private String userId;
	private String pwd;
	private String name = null;
	private String birth = null;
	private String addr = null;
	private String phoneNum = null;
	private Integer point = null;
	private Integer ticketPurchaseNum = null;

	public User() {
		super();
	}

	public User(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getTicketPurchaseNum() {
		return ticketPurchaseNum;
	}

	public void setTicketPurchaseNum(Integer ticketPurchaseNum) {
		this.ticketPurchaseNum = ticketPurchaseNum;
	}

	@Override
	public String toString() {
		String format = "비밀번호 : %s / 이름 : %s / 생년월일 : %s / 주소 : %s / 전화번호 : %s / 포인트 : %s / 티켓 구매 횟수 : %s";
		return String.format(format, this.pwd, this.name, this.birth, this.addr, this.phoneNum, this.point,
				this.ticketPurchaseNum);
	}
}
