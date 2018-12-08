package main;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.User;

public class SignIn {

	Scanner scan = new Scanner(System.in);
	DAO dao = DAO.sharedInstance();

	public void run() {
		String userId = this.inputString("> 아이디 : ");
		String pwd = this.inputString("> 비밀번호 : ");
		String name = this.inputString("> 성명 (ex.kimseongje) : ");
		String birth = this.inputString("> 생년월일 (ex.19940216) : ");
		String addr = this.inputString("> 주소 : ");
		String phoneNum = this.inputString("> 전화번호 : ");

		User user = new User();
		user.setUserId(userId);
		user.setPwd(pwd);
		user.setName(name);
		user.setBirth(birth);
		user.setAddr(addr);
		user.setPhoneNum(phoneNum);

		boolean r = dao.InsertUser(user);

		if (r) {
			System.out.println("> 회원가입이 완료되었습니다.");
		} else {
			System.out.println("> 회원가입을 실패하였습니다.");
		}

	}

	private int inputInt(String string) {
		System.out.print(string);
		return Integer.parseInt(scan.nextLine());
	}

	private String inputString(String string) {
		System.out.print(string);
		return scan.nextLine();
	}
}
