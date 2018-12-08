package Customer;

import java.util.Scanner;

import Database.User;

public class Customer {
	Scanner scan = new Scanner(System.in);

	public void run(User user) {
		boolean run = true;
		while (run) {
			System.out.println();
			System.out.println();
			System.out.println("실행할 업무를 선택하세요.");
			int chooseWork = this.inputInt("1.영화 차트 검색  2.영화 예약  3.영화 예약 관리  4.회원 관리  5.로그아웃  ");

			switch (chooseWork) {
			case 1: // 영화 차트 검색
				System.out.println("현재 상영중인 영화입니다. (예매율순)");
				new ShowScreeningMovie().run();
				System.out.println();
				break;

			case 2: // 영화 예약
				System.out.println("영화 예매를 진행합니다.");
				new ReserveTicket().run(user);
				System.out.println();
				break;

			case 3: // 영화 예약 관리
				System.out.println("예매 현황입니다.");
				new ShowReservation().run(user);
				System.out.println();
				break;

			case 4: // 회원 관리
				System.out.println("회원 관리입니다.");
				new UserData().run(user);
				System.out.println();
				break;

			case 5: // 로그아웃
				System.out.println("프로그램을 종료합니다.");
				run = false;
				break;

			default:
			}
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
