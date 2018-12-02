package main;

import java.util.Scanner;

public class ShowReservation {
	Scanner scan = new Scanner(System.in);
	public void run() {
		// 예약 현황을 보여줌
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
