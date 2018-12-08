package Customer;

import java.util.Scanner;

import Database.User;

public class DeleteUserInfo {
	
	Scanner scan = new Scanner(System.in);
	
	public void run(User user) {
		String sure = this.inputString("정말 탈퇴하시겠습니까? (y / n) ");
		// 그냥 바로 삭제
		// 대신 티켓 등에서의 정보 cascade
		
		// < 탈퇴하면 프로그램 종료 >
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
