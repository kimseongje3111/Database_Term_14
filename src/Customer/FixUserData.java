package Customer;

import java.util.Scanner;

import Database.DAO;
import Database.User;

public class FixUserData {

	Scanner scan = new Scanner(System.in);
	DAO dao = DAO.sharedInstance();

	public void run(User user) {
		User c_user = null; // DAO 회원 정보 수정 : 회원 정보 가져오기
		System.out.println(c_user);
		System.out.println();

		System.out.println("변경할 정보를 입력하세요.");
		String newPassword = this.inputString("새로운 패스워드 : ");
		String newName = this.inputString("새로운 이름 : ");
		String newBirth = this.inputString("새로운 생년원일 : ");
		String newAddr = this.inputString("새로운 주소 : ");
		String newPhone = this.inputString("새로운 전화번호 : ");

		c_user.setPwd(newPassword);
		c_user.setName(newName);
		c_user.setBirth(newBirth);
		c_user.setAddr(newAddr);
		c_user.setPhoneNum(newPhone);

		boolean r = true; // DAO 회원 정보 수정 : 정보 업데이트

		if (r) {
			System.out.println("회원 정보가 변경되었습니다.");
		} else {
			System.out.println("회원 정보 변경을 실패하였습니다.");
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
