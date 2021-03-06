package Manager;

import java.util.Scanner;

import Database.DAO;
import Database.Theater;

public class TheaterManage {

	Scanner scan = new Scanner(System.in);
	private String theaterId;
	private String theaterAddr;
	private String theaterTel;
	private int screenNum;
	private String theaterIdToFix;
	private String deleteThisTheater;

	DAO dao = DAO.sharedInstance();
	Theater theater = new Theater();

	public void run() {
		System.out.println("실행할 업무를 선택하세요.");
		int chooseWork = this.inputInt("1.영화관 등록  2.영화관 정보 수정  3.영화관 삭제  9.다른 업무 보기  ");

		switch (chooseWork) {
		case 1: // 영화관 등록
			System.out.println("> 영화관을 등록합니다.");
			this.addTheater();
			System.out.println();
			this.run();
			break;

		case 2: // 영화관 정보 수정
			theaterIdToFix = this.inputString("> 수정할 영화관 아이디 (ex.daejeon_cgv) : ");
			theater.setTheaterId(theaterIdToFix);

			boolean r1 = dao.checkTheaterId(theater); // DAO 영화관 : 아이디 중복 검사

			if (r1) {
				this.fixTheaterInfo();
				System.out.println();
			} else {
				System.out.println("> 일치하는 영화관이 없습니다.");
			}

			this.run();
			break;

		case 3: // 영화관 삭제
			deleteThisTheater = this.inputString("> 삭제할 영화관 아이디 (ex.daejeon_cgv) : ");
			theater.setTheaterId(deleteThisTheater);

			boolean r2 = dao.checkTheaterId(theater); // DAO 영화관 : 아이디 중복 검사

			if (r2) {
				this.deleteTheater();
				System.out.println();
			} else {
				System.out.println("> 일치하는 영화관이 없습니다.");
			}

			this.run();
			break;

		case 9: // 종료
			System.out.println("> 영화관 관리를 마칩니다.");
			break;

		default:
			this.run();
		}

	}

	private void addTheater() {
		theaterId = this.inputString("> 영화관 아이디 (ex.daejeon_cgv): ");
		theater.setTheaterId(theaterId);

		boolean b1 = dao.checkTheaterId(theater); // DAO 영화관 : 아이디 중복 검사

		if (!b1) {
			theaterAddr = this.inputString("> 영화관 주소 : ");
			theaterTel = this.inputString("> 영화관 전화번호 : ");
			screenNum = this.inputInt("> 영화관의 (최대)상영관 수 (ex.5) : ");
			theater.setTheaterAddr(theaterAddr);
			theater.setTheaterTel(theaterTel);
			theater.setScreenNum(screenNum);

			boolean b2 = dao.insertTheater(theater); // DAO 영화관 : 삽입

			if (b2) {
				System.out.println("> 영화관이 등록되었습니다.");
			} else {
				System.out.println("> 영화관 등록을 실패하였습니다.");
			}
		} else {
			System.out.println("> 이미 존재하는 영화관입니다.");
		}

	}

	private void fixTheaterInfo() {
		System.out.println("> 변경할 정보를 입력하세요.");
		String newTheaterAddress = this.inputString("> 새로운 영화관 주소 : ");
		String newTheaterTel = this.inputString("> 새로운 영화관 전화번호 : ");
		int newScreenNum = this.inputInt("> 새로 설정할 (최대)상영관 수 : ");

		theater.setTheaterAddr(newTheaterAddress);
		theater.setTheaterTel(newTheaterTel);
		theater.setScreenNum(newScreenNum);

		boolean b = dao.updateTheater(theater);

		if (b) {
			System.out.println("> 영화관 정보가 변경되었습니다.");
		} else {
			System.out.println("> 영화관 정보 변경을 실패하였습니다.");
		}

	}

	private void deleteTheater() {
		if (this.inputString("> 삭제하시겠습니까? (Y/N) ").equals("Y")) {

			boolean b = dao.deleteTheater(theater); // DAO 영화관 : 삭제

			if (b) {
				System.out.println("> 영화관이 삭제되었습니다.");
			} else {
				System.out.println("> 영화관 삭제를 실패하였습니다.");
			}
		} else {
			System.out.println("> 영화관 삭제 업무를 취소합니다.");
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
