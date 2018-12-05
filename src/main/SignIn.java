package main;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.User;

public class SignIn {
	Scanner scan = new Scanner(System.in);
	public void run() {
		String userId = this.inputString("아이디 : ");
		String pwd = this.inputString("비밀번호 : ");
		String name = this.inputString("성명 (ex. 이성규) : ");
		String birth = this.inputString("생년월일 (ex. 19940216) : ");
		String addr = this.inputString("주소 : ");
		String phoneNum = this.inputString("전화번호 : ");
		
		// 사용자 테이블에 회원가입 정보가 저장된다 // 회원가입된 정보도 한번 보여줘?
		
		
		DAO dao = DAO.sharedInstance();
		User user = new User();
		user.setUserId(userId);
		user.setPwd(pwd);
		user.setName(name);
		user.setBirth(birth);
		user.setAddr(addr);
		user.setPhoneNum(phoneNum);
		
//		boolean r = dao.InsertUser(user);
//		
//		if(r)
//			System.out.println("회원가입이 완료되었습니다.");
//		else
//			System.out.println("회원가입이 실패하였습니다.");

		// 저장된 거 확인 겸 select 해봄
		List<User> list = dao.getUserList();
		
		for(User u : list) {
			System.out.println(u);
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
