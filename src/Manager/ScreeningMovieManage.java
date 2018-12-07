package Manager;

import java.util.Scanner;

import Database.DAO;
import Database.ScreeningMovie;

public class ScreeningMovieManage {
	
	Scanner scan = new Scanner(System.in);
	private int screenMovieId;
	private String screenDate;
	private String movieName;
	private String screenId;
	private String screenTime;
	
	DAO dao = DAO.sharedInstance();
	ScreeningMovie screeningmovie = new ScreeningMovie();
	
	public void run() {
		System.out.println("실행할 업무를 선택하세요.");
		int chooseWork = this.inputInt("1.상영 영화 등록  2.상영 영화 삭제  9.다른 업무 보기");
		
		switch (chooseWork) {
		case 1: // 영화 등록
			System.out.println("상영 영화를 등록합니다.");
			this.addScreeningMovie();
			System.out.println();
			this.run();
			break;
			
		case 2: // 상영 영화 삭제
			String fixThisMovie = this.inputString("삭제할 상영 영화 : ");
			screeningmovie.setMovieName(fixThisMovie);
			
			if(fixThisMovie.equals("얘가 존재하면"))
				this.deleteScreeningMovie();
			else
				System.out.println("일치하는 상영 영화가 없습니다.");
			break;

		case 9:
			System.out.println("상영 영화 관리를 마칩니다.");
			break;
			default :
				this.run();
		}
	}

	private void addScreeningMovie() {
		System.out.println("상영 영화가 등록되었습니다.");
	}

	private void deleteScreeningMovie() {
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
