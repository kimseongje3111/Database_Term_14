package Manager;

import java.util.List;
import java.util.Scanner;

import Database.DAO;

public class VIPCustomerManage {

	Scanner scan = new Scanner(System.in);

	DAO dao = DAO.sharedInstance();

	public void run() {
		String term = this.inputString("> VIP 산정 기간 (ex.2018(01)) : ");

		List<String> vip_list = dao.getVipList(term); // DAO VIP 관리 : 일정 기간 동안 가장 많은 티켓을 구매한 사용자 top10

		System.out.println("----------------------- VIP 고객 -----------------------");
		int count = 1;
		for (String s : vip_list) {
			System.out.println("[" + count + "] " + s);
			count++;
		}
		System.out.println();
	}

	private String inputString(String string) {
		System.out.print(string);
		return scan.nextLine();
	}

}
