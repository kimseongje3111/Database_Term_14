package Manager;

import java.util.Scanner;

public class TheaterManage {
	Scanner scan = new Scanner(System.in);
	private String theaterId;
	private String theaterAddr;
	private String theaterTel;
	private int screenNum;
	private String theaterIdToFix; // 수정할 영화관의 아이디
	private String deleteThisTheater;
	
	public void run() {
		System.out.println("실행할 업무를 선택하세요.");
		int chooseWork = this.inputInt("1.영화관 등록  2.영화관 정보 수정  3.영화관 삭제  9.다른 업무 보기");
		
		switch (chooseWork) {
		case 1: // 영화관 등록
			System.out.println("영화관을 등록합니다.");
			this.addTheater();
			this.run();
			break;
			
		case 2: // 영화관 정보 수정
			// 영화관 id를 입력후 존재여부 확인 -> 존재시 하나씩 골라 수정 가능케 /
			theaterIdToFix = this.inputString("수정할 영화관 아이디 : ");
			if(theaterIdToFix.equals("입력한 아이디가 존재 하면")) this.fixTheaterInfo();
			else System.out.println("일치하는 영화관이 없습니다.");
			this.run();
			break;
			
		case 3 : // 영화관 삭제
			deleteThisTheater = this.inputString("삭제할 영화관 아이디 : ");
			if(deleteThisTheater.equals("입력한 아이디가 존재 하면")) this.deleteTheater();
			else System.out.println("일치하는 영화관이 없습니다.");
			this.run();
			
		case 9:
			System.out.println("영화관 관리를 마칩니다.");
			break;
			default :
				this.run();
		}
	}

	private void addTheater() {
		theaterId = this.inputString("영화관 아이디 : ");
		// if < 영화관 ID가 겹치면> {
		theaterAddr = this.inputString("영화관 주소 : ");
		theaterTel = this.inputString("영화관 전화번호 : ");
		screenNum = this.inputInt("영화관의 (최대)상영관 수 : ");
//		}
//		else {
//			System.out.println("일치하는 영화관이 없습니다.");
//		}
		
		// <영화관 추가>
		System.out.println("영화관이 등록되었습니다.");
	}
	
	private void fixTheaterInfo() {
		System.out.println("변경할 정보를 선택하세요.");
		int chooseWork = this.inputInt("1.영화관 주소  2.영화관 전화번호  3.(최대)상영관 수  9.수정 종료");
		switch(chooseWork) {
		case 1 : // 영화관 주소 수정
			String newTheaterAddress = this.inputString("새로운 영화관 주소 : ");
			// < 주소 정보 변경 >
			this.fixTheaterInfo();
			break;
		
		case 2 : // 영화관 전화번호 수정
			String newTheaterTel = this.inputString("새로운 영화관 전화번호 : ");
			// < 전화번호 정보 수정 >
			this.fixTheaterInfo();
			break;
			
		case 3 : // 영화관 최대 상영관 수 수정
			int newScreenNum = this.inputInt("새로 설정할 (최대)상영관 수 : ");
			// < 상영관 수 정보 수정 >
			this.fixTheaterInfo();
			break;
			
		case 9: // 수정 종료
			System.out.println("영화관 정보 수정을 마칩니다.");
			break;
			default :
				this.fixTheaterInfo();
		}
	}
	
	private void deleteTheater() {
		if(this.inputString("삭제하시겠습니까? (Y / N) ").equals("Y")) {
			// < 입력한 영화관 아이디를 삭제
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
