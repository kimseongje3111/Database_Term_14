package main;

import java.util.Scanner;

public class LogInSignIn {
	private Scanner scan = new Scanner(System.in);
	private boolean first = false;

	public void run() {
		if (first) {
			System.out.println();
			System.out.println();
		} else {
			first = true;
		}

		int loginORsignin = inputInt("DB 14조 TERM PROJECT 에 오신 것을 환영합니다. (1.로그인  2.회원가입  9.종료) ");
		if (loginORsignin == 1) {
			System.out.println("> 로그인을 해주세요.");
			new LogIn().run();
			System.out.println();
		} else if (loginORsignin == 2) {
			System.out.println("> 회원가입을 해주세요.");
			new SignIn().run();
			System.out.println();
			this.run();
		} else if (loginORsignin == 9) {
			System.out.println("> 프로그램을 종료합니다.");
		} else {
			this.run();
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
