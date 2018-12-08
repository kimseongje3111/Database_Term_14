package Manager;

import java.util.Scanner;

import Database.DAO;
import Database.Movie;

public class MovieManage {

	Scanner scan = new Scanner(System.in);
	private String movieId;
	private String movieName;
	private String director;
	private String casting;
	private String rating;
	private String keyInfo;
	private String fixThisMovie;
	private String deleteThisMovie;

	DAO dao = DAO.sharedInstance();
	Movie movie = new Movie();

	public void run() {
		System.out.println("실행할 업무를 선택하세요.");
		int chooseWork = this.inputInt("1.영화 등록  2.영화 정보 수정  3.영화 삭제  9.다른 업무 보기  ");

		switch (chooseWork) {
		case 1: // 영화 등록
			System.out.println("영화를 등록합니다.");
			this.addMovie();
			System.out.println();
			this.run();
			break;

		case 2: // 영화 정보 수정
			fixThisMovie = this.inputString("수정할 영화 제목 : ");
			movie.setMovieName(fixThisMovie);

			boolean r1 = dao.checkMovieName(movie); // DAO 영화 : 제목 중복 검사

			if (r1) {
				this.fixMovieInfo();
				System.out.println();
			} else {
				System.out.println("일치하는 영화가 없습니다.");
			}

			this.run();
			break;

		case 3: // 영화 삭제
			deleteThisMovie = this.inputString("삭제할 영화 제목 : ");
			movie.setMovieName(deleteThisMovie);

			boolean r2 = dao.checkMovieName(movie); // DAO 영화 : 제목 중복 검사

			if (r2) {
				this.deleteMovie();
				System.out.println();
			} else {
				System.out.println("일치하는 영화가 없습니다.");
			}

			this.run();
			break;

		case 9:
			System.out.println("영화 관리를 마칩니다.");
			break;

		default:
			this.run();
		}
	}

	private void addMovie() {
		movieId = this.inputString("등록할 영화 아이디 (ex.Movie1) : ");
		movie.setMovieId(movieId);

		boolean b1 = dao.checkMovieId(movie); // DAO 영화 : 아이디 중복 검사

		if (!b1) {
			movieName = this.inputString("등록할 영화 제목 (ex.ironman) : ");
			director = this.inputString("등록할 영화의 감독명 : ");
			casting = this.inputString("등록할 영화의 출연진 : ");
			rating = this.inputString("등록할 영화의 등급 : ");
			keyInfo = this.inputString("등록할 영화의 주요 정보 : ");
			movie.setMovieName(movieName);
			movie.setDirector(director);
			movie.setCast(casting);
			movie.setRating(rating);
			movie.setKeyInfo(keyInfo);

			boolean b2 = dao.insertMovie(movie); // DAO 영화 : 삽입

			if (b2) {
				System.out.println("영화가 등록되었습니다.");
			} else {
				System.out.println("영화 등록을 실패하였습니다.");
			}
		} else {
			System.out.println("이미 존재하는 영화입니다.");
		}

	}

	private void fixMovieInfo() {
		System.out.println("변경할 정보를 입력하세요.");
		String newdirector = this.inputString("새로운 영화 감독 : ");
		String newcast = this.inputString("새로운 영화 출연진 : ");
		String newrating = this.inputString("새로운 영화 등급 : ");
		String newkeyInfo = this.inputString("새로운 영화 주요 정보 : ");

		movie.setDirector(newdirector);
		movie.setCast(newcast);
		movie.setRating(newrating);
		movie.setKeyInfo(newkeyInfo);

		boolean b = dao.updateMovieInfo(movie);

		if (b) {
			System.out.println("영화 정보가 변경되었습니다.");
		} else {
			System.out.println("영화 정보 변경을 실패하였습니다.");
		}

	}

	private void deleteMovie() {
		if (this.inputString("삭제하시겠습니까? (Y/N) ").equals("Y")) {

			boolean b = dao.deleteMovie(movie); // DAO 영화 : 삭제

			if (b) {
				System.out.println("영화가 삭제되었습니다.");
			} else {
				System.out.println("영화 삭제를 실패하였습니다.");
			}
		} else {
			System.out.println("영화 삭제 업무를 취소합니다.");
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
