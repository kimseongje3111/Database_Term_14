package Manager;

import java.util.Scanner;

import Database.DAO;
import Database.Movie;

public class MovieManage {

	Scanner scan = new Scanner(System.in);
	private String movieId;
	private String movieName;
	private String director;
	private String cast;
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
			fixThisMovie = this.inputString("수정할 영화 : ");
			movie.setMovieName(fixThisMovie);

			boolean r1 = true; // DAO 영화 : 제목 중복 검사

			if (r1) {
				this.fixMovieInfo();
				System.out.println();
			} else {
				System.out.println("일치하는 영화가 없습니다.");
			}

			this.run();
			break;

		case 3: // 영화 삭제
			deleteThisMovie = this.inputString("삭제할 영화 : ");
			movie.setMovieName(deleteThisMovie);

			boolean r2 = true; // DAO 영화 : 제목 중복 검사

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
		movieId = this.inputString("등록할 영화 아이디 : ");
		movie.setMovieId(movieId);

		boolean b1 = false; // DAO 영화 : 아이디 중복 검사

		if (!b1) {
			movieName = this.inputString("등록할 영화 제목 : ");
			director = this.inputString("등록할 영화의 감독명 : ");
			cast = this.inputString("등록할 영화의 출연진 : ");
			rating = this.inputString("등록할 영화의 등급 : ");
			keyInfo = this.inputString("등록할 영화의 주요 정보 : ");
			movie.setMovieName(movieName);
			movie.setDirector(director);
			movie.setCast(cast);
			movie.setRating(rating);
			movie.setKeyInfo(keyInfo);

			boolean b2 = true; // DAO 영화 : 삽입

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
		System.out.println("변경할 정보를 선택하세요.");
		int chooseWork = this.inputInt("1.영화 제목  2.감독  3.출연진  4.등급  5.주요 정보  9.수정 종료  ");

		switch (chooseWork) {
		case 1: // 영화 제목
			String newmovieName = this.inputString("새로운 영화 제목 : ");
			movie.setPremovieName(fixThisMovie);
			movie.setMovieName(newmovieName);
			
			boolean b1 = false; // DAO 영화 : 제목 중복 검사
			
			if (!b1) {
				
				boolean b2 = true; // DAO 영화 : 정보 업데이트 (이전의 영화 제목을 보고)
				
				if (b2) {
					System.out.println("영화 제목이 변경되었습니다.");
				} else {
					System.out.println("영화 제목 변경을 실패하였습니다.");
				}
			} else {
				System.out.println("이미 존재하는 영화입니다.");
			}
			
			this.fixMovieInfo();
			break;

		case 2: // 감독
			String newdirector = this.inputString("새로운 영화 감독 : ");
			movie.setDirector(newdirector);
			
			boolean b3 = true; // DAO 영화 : 정보 업데이트
			
			if (b3) {
				System.out.println("영화 감독이 변경되었습니다.");
			} else {
				System.out.println("영화 감독 변경을 실패하였습니다.");
			}
			
			this.fixMovieInfo();
			break;

		case 3: // 출연진
			String newcast = this.inputString("새로운 영화 출연진 : ");
			movie.setCast(newcast);
			
			boolean b4 = true; // DAO 영화 : 정보 업데이트
			
			if (b4) {
				System.out.println("영화 출연진이 변경되었습니다.");
			} else {
				System.out.println("영화 출연진 변경을 실패하였습니다.");
			}
			
			this.fixMovieInfo();
			break;
			
		case 4: // 등급
			String newrating = this.inputString("새로운 영화 등급 : ");
			movie.setRating(newrating);
			
			boolean b5 = true; // DAO 영화 : 정보 업데이트

			if (b5) {
				System.out.println("영화 등급이 변경되었습니다.");
			} else {
				System.out.println("영화 등급 변경을 실패하였습니다.");
			}
			
			this.fixMovieInfo();
			break;
			
		case 5: // 주요 정보
			String newkeyInfo = this.inputString("새로운 영화 주요 정보 : ");
			
			boolean b6 = true; // DAO 영화 : 정보 업데이트
			
			if (b6) {
				System.out.println("영화 주요 정보가 변경되었습니다.");
			} else {
				System.out.println("영화 주요 정보 변경을 실패하였습니다.");
			}
			
			this.fixMovieInfo();
			break;
			
		case 9: // 수정 종료
			System.out.println("상영관 정보 수정을 마칩니다.");
			break;

		default:
			this.fixMovieInfo();
		}
	}

	private void deleteMovie() {
		if (this.inputString("삭제하시겠습니까? (Y/N) ").equals("Y")) {

			boolean b = true; // DAO 영화 : 삭제

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
