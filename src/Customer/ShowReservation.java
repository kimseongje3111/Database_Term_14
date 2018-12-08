package Customer;

import java.util.Scanner;

import Database.User;

public class ShowReservation {

	Scanner scan = new Scanner(System.in);

	public void run(User user) {
		// 나의 예약 현황을 보여줌
		
		int chooseWork = this.inputInt("1.예매 취소   9.뒤로가기  ");
		
		switch (chooseWork) {
		case 1: // 애매 취소
			new CancelReservation().run(user);
			break;
			
		case 9: // 뒤로 가기
			System.out.println("영화 예약 관리를 종료합니다.");
			break;

		default:	
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
