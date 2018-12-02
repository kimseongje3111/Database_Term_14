package main;

import java.util.Scanner;

public class Customer {
	Scanner scan = new Scanner(System.in);
	public void run() {
		boolean run = true;
		while(run) {
			System.out.println();System.out.println();
			System.out.println("실행할 업무를 선택하세요.");
			int chooseWork = this.inputInt("1.영화 차트 검색  2.영화 예약  3.예약 확인  4.예약 취소  5.회원정보수정  6.회원탈퇴  7.로그아웃  ");
			
			switch (chooseWork) {
			case 1: // 영화 검색
				// p.4 영화 검색 (차트의 예매율 순으로)
				System.out.println("현재 상영중인 영화입니다. (예매율순)");
				new ShowScreeningMovie().run();
				break;
			
			case 2: // 영화 예약
				// p.5 영화 예약 - 여러장도 가능
				System.out.println("영화 예매를 진행합니다.");
				new ReserveTicket().run();
				break;
			case 3: // 예약 확인
				System.out.println("예매 현황입니다.");
				new ShowReservation().run();
				break;
			case 4: // 예약 취소
				new ShowReservation().run();
				System.out.println("취소할 영화를 선택해 주세요.");
				new CancelReservation().run();
				break;
			case 5: // 회원정보수정
				System.out.println("회원님의 정보를 수정합니다.");
				new FixUserDate().run();
				break;
			case 6: // 회원탈퇴
				new UserInforDelete().run();
				break;
			case 7: // 로그아웃
				System.out.println("종료합니다.");
				run = false;
				break;
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
