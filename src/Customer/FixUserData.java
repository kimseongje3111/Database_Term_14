package Customer;

import java.util.Scanner;

import Database.User;

public class FixUserData {
	
	Scanner scan = new Scanner(System.in);
	
	public void run(User user) {
		User c_user = null; // DAO 회원 정보 수정 : 회원 정보 가져오기
		System.out.println(c_user);
		
		
		
		// 사용자의 아이디 로 정보를 불러옴
		// 처음에 while 1. 패스원드 2. 성명 3. 생년월일 4. 주소 5. 전화번호 중에 무엇을 바꿀지 입력 받는다
		// 단 9. 종료
		// 하나하나 수정하여 디비를 업데이트 해준다.
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
