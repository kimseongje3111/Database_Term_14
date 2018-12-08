package Manager;

import java.util.List;
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
	private int deleteThisMovie;

	DAO dao = DAO.sharedInstance();
	ScreeningMovie screeningmovie = new ScreeningMovie();

	public void run() {
		System.out.println("----------------------------- 상영 영화 목록 -----------------------------");
		List<ScreeningMovie> screening_list = dao.getScreeningMovieList(); // DAO 상영 영화 : 상영 영화 목록 가져오기
		for (ScreeningMovie sm : screening_list) {
			System.out.println(sm);
		}
		System.out.println();

		System.out.println("실행할 업무를 선택하세요.");
		int chooseWork = this.inputInt("1.상영 영화 등록  2.상영 영화 삭제  9.다른 업무 보기  ");

		switch (chooseWork) {
		case 1: // 영화 등록
			System.out.println("> 상영 영화를 등록합니다.");
			this.addScreeningMovie();
			System.out.println();
			this.run();
			break;

		case 2: // 상영 영화 삭제
			deleteThisMovie = this.inputInt("> 삭제할 상영 영화 번호 (ex.1) : ");
			screeningmovie.setScreenMovieId(deleteThisMovie);

			boolean r = dao.checkScreeningMovieId(screeningmovie); // DAO 상영 영화 : 번호 중복 검사

			if (r) {
				this.deleteScreeningMovie();
				System.out.println();
			} else {
				System.out.println("> 일치하는 상영 영화가 없습니다.");
			}

			this.run();
			break;

		case 9:
			System.out.println("> 상영 영화 관리를 마칩니다.");
			break;

		default:
			this.run();
		}
	}

	private void addScreeningMovie() {
		screenMovieId = this.inputInt("> 등록할 상영 영화 번호 (ex.1) : ");
		screeningmovie.setScreenMovieId(screenMovieId);

		boolean b1 = dao.checkScreeningMovieId(screeningmovie); // DAO 상영 영화 : 번호 중복 검사

		if (!b1) {
			screenDate = this.inputString("> 상영 날짜 (ex.20181212) : ");
			movieName = this.inputString("> 상영할 영화 제목 (ex.ironman) : ");
			screenId = this.inputString("> 상영할 상영관 아이디 (ex.daejoen_cgv1) : ");
			screenTime = this.inputString("> 상영 시간 (ex.1315) : ");
			screeningmovie.setScreenDate(screenDate);
			screeningmovie.setMovieName(movieName);
			screeningmovie.setScreenId(screenId);
			screeningmovie.setScreenTime(screenTime);

			boolean b2 = dao.insertScreeningMovie(screeningmovie); // DAO 상영 영화 : 삽입

			if (b2) {
				System.out.println("> 상영 영화가 등록되었습니다.");
			} else {
				System.out.println("> 상영 영화 등록을 실패하였습니다.");
			}
		} else {
			System.out.println("> 이미 존재하는 상영 영화 번호입니다.");
		}

	}

	private void deleteScreeningMovie() {
		if (this.inputString("> 삭제하시겠습니까? (Y/N) ").equals("Y")) {

			boolean b = dao.deleteScreeningMovie(screeningmovie); // DAO 상영 영화 : 삭제

			if (b) {
				System.out.println("> 상영 영화가 삭제되었습니다.");
			} else {
				System.out.println("> 상영 영화 삭제를 실패하였습니다.");
			}
		} else {
			System.out.println("> 상영 영화 삭제 업무를 취소합니다.");
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
