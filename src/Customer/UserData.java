package Customer;

import java.util.Scanner;

import Database.User;

public class UserData {

	Scanner scan = new Scanner(System.in);

	public void run(User user) {
		System.out.println();
		System.out.println("실행할 업무를 선택하세요.");
		int chooseWork = this.inputInt("1.회원 정보 수정  2.회원 탈퇴  9.뒤로가기  ");

		switch (chooseWork) {
		case 1: // 회원 정보 수정
			new FixUserData().run(user);
			this.run(user);
			break;

		case 2: // 회원 탈퇴
			new DeleteUserInfo().run(user);
			this.run(user);
			break;

		case 9: // 뒤로 가기
			System.out.println("> 회원 관리를 종료합니다.");
			break;

		default:
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
