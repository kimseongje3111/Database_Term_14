package Manager;

import java.util.Scanner;

public class MovieManage {
	Scanner scan = new Scanner(System.in);
	private String movieId;
	private String movieName;
	private String director;
	private String cast;
	private String rating;
	private String keyInfo;
	
	public void run() {
		System.out.println("실행할 업무를 선택하세요.");
		int chooseWork = this.inputInt("1.영화 등록  2.영화 정보 수정  3.영화 삭제  9.다른 업무 보기");
		
		switch (chooseWork) {
		case 1: // 영화 등록
			System.out.println("영화를 등록합니다.");
			this.addMovie();
			this.run();
			break;
			
		case 2: // 영화 정보 수정
			// 영화 리스트를 보여줌 -> 영화 id를 입력후 존재여부 확인
			String fixThisMovie = this.inputString("수정할 영화 : ");
			if(fixThisMovie.equals("얘가 존재하면"))
				this.fixMovieInfo();
			else
				System.out.println("일치하는 영화가 없습니다.");
			break;
			
		case 3 : // 영화 삭제
			// 영화리스트를 보여줌
			String deleteThisMovie = this.inputString("삭제할 영화 : ");
			this.deleteMovie();
			break;
			
		case 9:
			System.out.println("영화관 관리를 마칩니다.");
			break;
			default :
				this.run();
		}
	}

	private void addMovie() {
		movieId = this.inputString("등록할 영화 아이디 : ");
		// < id 겹치는지 확인 >
		movieName = this.inputString("등록할 영화 제목 : ");
		director = this.inputString("등록할 영화의 감독명 : ");
		cast = this.inputString("등록할 영화의 출연진 : ");
		rating = this.inputString("등록할 영화의 등급 : ");
		keyInfo = this.inputString("등록할 영화의 주요 정보 : ");
		
		// 영화 추가
		System.out.println("영화가 등록되었습니다.");
	}
	
	private void fixMovieInfo() {
		System.out.println("변경할 정보를 선택하세요.");
		int chooseWork = this.inputInt("1.상영관 번호  2.상영관의 최대 좌석 수  9.수정 종료");
		switch(chooseWork) {
		case 1 : // 상영관 번호
			String newScreenId = this.inputString("새로운 영화관 번호 : ");
			// < 상영관 번호 수정 >
			this.fixMovieInfo();
			break;
		
		case 2 : // 상영관 최대 좌석 수
			int newAvailSeat = this.inputInt("새로운 상영관 좌석 수 : ");
			// < 좌석수 수정 > - 예외 처리 : 예약 된 수가 새로 입려간 수보다 클 떄??
			this.fixMovieInfo();
			break;
			
		case 9: // 수정 종료
			System.out.println("상영관 정보 수정을 마칩니다.");
			break;
			default :
				this.fixMovieInfo();
		}
	}
	
	private void deleteMovie() {
		// 삭제
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
