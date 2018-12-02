package Manager;

import java.util.Scanner;

public class ScreenManage {
	Scanner scan = new Scanner(System.in);
	private String screenId; // 영화관  + 상영관 번호
	private String theaterId;
	private String screeId;
	private int availSeat;
	private String theaterIdToFix; // 수정할 영화관의 아이디
	private String fixThisScreen;
	
	public void run() {
		System.out.println("실행할 업무를 선택하세요.");
		int chooseWork = this.inputInt("1.상영관 등록  2.상영관 정보 수정   9.다른 업무 보기");
		
		switch (chooseWork) {
		case 1: // 상영관 등록
			System.out.println("상영관을 등록합니다.");
			this.addScreen();
			this.run();
			break;
			
		case 2: // 상영관 정보 수정
			// 영화관 리스트를 보여줌 -> 영화관 id를 입력후 존재여부 확인
			//   -> 상영관 리스트를 보여줌 -> 상영관 번호를 입력후 존재여부 확인 존재시 하나씩 골라 수정 가능케 /
			
			// <영화관 리스트 출력 >
			theaterIdToFix = this.inputString("수정할 영화관 아이디 : ");
			if(!theaterIdToFix.equals("입력한 아이디가 존재 안하면")) 
				System.out.println("일치하는 영화관이 없습니다.");
			else { 
				// < 상영관 리스트 출력
				fixThisScreen = this.inputString("수정할 상영관 번호 : ");
				if(!fixThisScreen.equals("상영관이 존재 안하면")) 
					System.out.println("일치하는 상영관이 없습니다.");
				else
					this.fixScreenInfo();
			}
			this.run();
			break;
			
		case 9:
			System.out.println("영화관 관리를 마칩니다.");
			break;
			default :
				this.run();
		}
	}

	private void addScreen() {
		// < 영화관 ID를 전부 보여준다. >
		theaterId = this.inputString("상영관을 등록할 영화관 아이디 : ");
		// if < 영화관 ID가 겹치면> {
		screeId = this.inputString("등록할 상영관 번호 : ");
		availSeat = this.inputInt("상영관의 최대 좌석 수 : ");
//		}
//		else {
//			System.out.println("일치하는 영화관이 없습니다.");
//		}
		
		// 상영관 추가
		System.out.println("상영관이 등록되었습니다.");
	}
	
	private void fixScreenInfo() {
		System.out.println("변경할 정보를 선택하세요.");
		int chooseWork = this.inputInt("1.상영관 번호  2.상영관의 최대 좌석 수  9.수정 종료");
		switch(chooseWork) {
		case 1 : // 상영관 번호
			String newScreenId = this.inputString("새로운 영화관 번호 : ");
			// < 상영관 번호 수정 >
			this.fixScreenInfo();
			break;
		
		case 2 : // 상영관 최대 좌석 수
			int newAvailSeat = this.inputInt("새로운 상영관 좌석 수 : ");
			// < 좌석수 수정 > - 예외 처리 : 예약 된 수가 새로 입려간 수보다 클 떄??
			this.fixScreenInfo();
			break;
			
		case 9: // 수정 종료
			System.out.println("상영관 정보 수정을 마칩니다.");
			break;
			default :
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
