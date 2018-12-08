package Customer;

import java.util.Scanner;

import Database.User;

public class CancelReservation {
	
	Scanner scan = new Scanner(System.in);
	
	public void run(User user) {
		// 예약 번호를 입력하고 삭제 또는 동작 취소 선택
		// 포인트반납 , 예약 횟수 감소??
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
