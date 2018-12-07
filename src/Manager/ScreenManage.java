package Manager;

import java.util.Scanner;

import Database.DAO;
import Database.Screen;
import Database.Theater;

public class ScreenManage {

	Scanner scan = new Scanner(System.in);
	private String screenId;
	private String theaterId;
	private String screenNum;
	private int availSeat;
	private String theaterIdToFix;
	private String fixThisScreen;

	DAO dao = DAO.sharedInstance();
	Screen screen = new Screen();

	public void run() {
		System.out.println("실행할 업무를 선택하세요.");
		int chooseWork = this.inputInt("1.상영관 등록  2.상영관 정보 수정  9.다른 업무 보기  ");

		switch (chooseWork) {
		case 1: // 상영관 등록
			System.out.println("상영관을 등록합니다.");
			this.addScreen();
			System.out.println();
			this.run();
			break;

		case 2: // 상영관 정보 수정
			theaterIdToFix = this.inputString("수정할 영화관 아이디 : ");
			screen.setTheaterId(theaterIdToFix);

			boolean r1 = true; // DAO 상영관 : 영화관 중복 검사

			if (r1) {
				fixThisScreen = this.inputString("수정할 상영관 번호 : ");
				screenId = theaterIdToFix + fixThisScreen;
				screen.setScreenId(screenId);

				boolean r2 = true; // DAO 상영관 : 아이디 중복 검사

				if (r2) {
					this.fixScreenInfo();
					System.out.println();
				} else {
					System.out.println("일치하는 상영관이 없습니다.");
				}
			} else {
				System.out.println("일치하는 영화관이 없습니다.");
			}

			this.run();
			break;

		case 9:
			System.out.println("영화관 관리를 마칩니다.");
			break;

		default:
			this.run();
		}
	}

	private void addScreen() {
		theaterId = this.inputString("상영관을 등록할 영화관 아이디 : ");
		screen.setTheaterId(theaterId);

		boolean b1 = true; // DAO 상영관 : 영화관 아이디 중복 검사

		if (b1) {
			screenNum = this.inputString("등록할 상영관 번호 : ");
			availSeat = this.inputInt("상영관의 최대 좌석 수 : ");
			screenId = theaterId + screenNum;
			screen.setScreenId(screenId);
			screen.setTheaterId(theaterId);
			screen.setScreenNum(screenNum);
			screen.setAvailSeat(availSeat);

			boolean b2 = false; // DAO 상영관 : 아이디 중복 검사

			if (!b2) {

				boolean b3 = true; // DAO 상영관 : 등록 (해당 영화관의 최대 상영관 수 확인)

				if (b3) {
					System.out.println("상영관이 등록되었습니다.");
				} else {
					System.out.println("상영관 등록을 실패하였습니다.");
				}
			} else {
				System.out.println("이미 존재하는 상영관입니다.");
			}
		} else {
			System.out.println("일치하는 영화관이 없습니다.");
		}

	}

	private void fixScreenInfo() {
		System.out.println("변경할 정보를 선택하세요.");
		int chooseWork = this.inputInt("1.상영관의 최대 좌석 수  9.수정 종료  ");

		switch (chooseWork) {
		case 1: // 상영관 최대 좌석 수
			int newAvailSeat = this.inputInt("새로운 상영관 좌석 수 : ");
			screen.setAvailSeat(newAvailSeat);

			boolean b = true; // DAO 상영관 : 정보 업데이트

			if (b) {
				System.out.println("상영관 좌석 수가 변경되었습니다.");
			} else {
				System.out.println("상영관 좌석 수 변경을 실패하였습니다.");
			}

			this.fixScreenInfo();
			break;

		case 9: // 수정 종료
			System.out.println("상영관 정보 수정을 마칩니다.");
			break;

		default:
			this.fixScreenInfo();
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
