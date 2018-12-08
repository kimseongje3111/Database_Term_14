package Customer;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.ScreeningMovie;
import Database.Ticket;
import Database.User;

public class CancelReservation {

	Scanner scan = new Scanner(System.in);
	DAO dao = DAO.sharedInstance();

	public void run(User user, List<Ticket> list) {
		int choose = this.inputInt("> 예매를 취소할 티켓을 선택하세요. (ex.1) : ");
		Ticket remove_ticket = list.remove(choose - 1);
		int screenMovieNum = 0;

		List<ScreeningMovie> screening = dao.getScreeningMovieList();
		for (ScreeningMovie sm : screening) {
			if (sm.getScreenDate().equals(remove_ticket.getScreenDate())
					&& sm.getMovieName().equals(remove_ticket.getMovieName())
					&& sm.getScreenId().equals(remove_ticket.getTheaterName() + remove_ticket.getScreenNum())
					&& sm.getScreenTime().equals(remove_ticket.getScreenTime())) {
				screenMovieNum = sm.getScreenMovieId();
			}
		}

		dao.deleteTicket(remove_ticket, screenMovieNum); // DAO 예매 취소 : 티켓 삭제
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
