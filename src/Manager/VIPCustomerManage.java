package Manager;

import java.util.List;
import java.util.Scanner;

import Database.DAO;

public class VIPCustomerManage {

	Scanner scan = new Scanner(System.in);

	DAO dao = DAO.sharedInstance();

	public void run() {
		String term = this.inputString("> VIP ���� �Ⱓ(ex.201801) : ");

		List<String> vip_list = dao.getVipList(term); // DAO VIP ���� : ���� �Ⱓ ���� ���� ���� Ƽ���� ������ ����� top10

		System.out.println("----------------------------- VIP �� -----------------------------");
		int count = 1;
		for (String s : vip_list) {
			System.out.print(String.format("%d.%1s  ", count, s));
			count++;
		}
		System.out.println();
	}

	private String inputString(String string) {
		System.out.print(string);
		return scan.nextLine();
	}

}
