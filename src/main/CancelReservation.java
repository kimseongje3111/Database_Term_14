package main;

import java.util.Scanner;

public class CancelReservation {
	Scanner scan = new Scanner(System.in);
	public void run() {
		// 예약 번호를 입력하고 삭제 또는 동작 취소 선택
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
