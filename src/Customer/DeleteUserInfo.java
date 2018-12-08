package Customer;

import java.util.Scanner;

import Database.DAO;
import Database.User;

public class DeleteUserInfo {

	Scanner scan = new Scanner(System.in);
	DAO dao = DAO.sharedInstance();

	public void run(User user) {
		String sure = this.inputString("> 정말 탈퇴하시겠습니까? (Y/N) ");

		if (sure.equals("Y")) {

			boolean r = dao.deleteUser(user); // DAO 회원 탈퇴 : 정보 삭제

			if (r) {
				System.out.println("> 회원 탈퇴가 완료되었습니다.");
				System.out.println("> 프로그램을 종료합니다.");
				System.exit(0);
			} else {
				System.out.println("> 회원 탈퇴를 실패하였습니다.");
			}

		} else {
			System.out.println("> 회원 탈퇴를 취소합니다.");
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
