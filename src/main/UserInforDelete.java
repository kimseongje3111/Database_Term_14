package main;

import java.util.Scanner;

public class UserInforDelete {
	Scanner scan = new Scanner(System.in);
	public void run() {
		String sure = this.inputString("정말 탈퇴하시겠습니까? ");
		// 그냥 바로 삭제
		// 대신 티켓 등에서의 정보 cascade
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
