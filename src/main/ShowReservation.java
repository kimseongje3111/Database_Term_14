package main;

import java.util.Scanner;

public class ShowReservation {
	Scanner scan = new Scanner(System.in);
	public void run() {
		// ���� ��Ȳ�� ������
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
