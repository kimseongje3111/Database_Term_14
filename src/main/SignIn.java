package main;

import java.util.Scanner;

public class SignIn {
	Scanner scan = new Scanner(System.in);
	public void run() {
		String userID = this.inputString("아이디 : ");
		String pwd = this.inputString("비밀번호 : ");
		String name = this.inputString("성명 (ex. 이성규) : ");
		String birth = this.inputString("생년월일 (ex. 19940216) : ");
		String addr = this.inputString("주소 : ");
		String phoneNum = this.inputString("전화번호 : ");
		
		// 사용자 테이블에 회원가입 정보가 저장된다 // 회원가입된 정보도 한번 보여줘?
		System.out.println("회원가입이 완료되었습니다.");
		// 2 초 정도 타이머?
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
