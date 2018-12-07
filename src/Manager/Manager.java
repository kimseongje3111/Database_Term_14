package Manager;

import java.util.Scanner;

public class Manager {
	Scanner scan = new Scanner(System.in);
	public void run() {
		boolean run = true;
		while(run) {
			System.out.println();System.out.println();
			System.out.println("실행할 업무를 선택하세요.");
			int chooseWork = this.inputInt("1.영화관 정보 관리  2.상영관 정보 관리  3.영화 정보 관리  "
					+ "4.상영 영화 정보 관리  5.VIP 고객 관리  6.영화 티켓 발행  7.로그아웃  ");

			switch (chooseWork) {
			case 1: // 영화관 정보 관리 ( 등록 수정 삭제 )
				System.out.println("영화관을 관리합니다.");
				System.out.println();
				new TheaterManage().run();
				break;

			case 2: // 상영관 정보 관리 ( 등록 수정 삭제 )
				System.out.println("상영관을 관리합니다.");
				System.out.println();
				new ScreenManage().run();
				break;
				
			case 3: // 영화 정보 관리 ( 등록 수정 삭제 )
				System.out.println("영화를 관리합니다.");
				System.out.println();
				new MovieManage().run();
				break;
				
			case 4: // 상영 영화 정보 관리 ( 등록 수정 삭제 )
				System.out.println("상영 영화를 관리합니다.");
				System.out.println();
				new ScreeningMovieManage().run();
				break;
				
			case 5: // VIP 고객 관리
				System.out.println("VIP 고객을 관리합니다.");
				System.out.println();
				new VIPCustomerManage().run();
				break;
				
			case 6: // 영화 티켓 발행
				System.out.println("예매한 티켓을 발행합니다.");
				System.out.println();
				new PublishTicket().run();
				break;
				
			case 7: // 로그아웃
				System.out.println("종료합니다.");
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

