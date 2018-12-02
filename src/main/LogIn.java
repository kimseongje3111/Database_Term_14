package main;

import java.util.Scanner;

public class LogIn {
	Scanner scan = new Scanner(System.in);
	
	public void run() {
		String id = this.inputString("아이디 : ");
		String pw = this.inputString("비밀번호 : ");
		
//		id가 관리자이면 관리자로 실행
		System.out.println("관리자로 로그인 합니다.");
		new Manager().run();
		
//		id가 고객이면 고객으로 실행
		System.out.println("환영합니다 고객님.");
		new Customer().run();
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
