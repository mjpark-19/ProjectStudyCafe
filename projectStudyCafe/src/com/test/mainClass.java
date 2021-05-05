package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class mainClass {

	public static void main(String[] args) {
		System.out.println("\n\n");

		// id, pw, info.txt ������ ���Ͽ��� �ҷ��� String �Դϴ�.
		String id = "001;002;003;";
		String pw = "1111;2222;3333;";
		String info = "2020-11-11;2020-22-22;2020-33-33;";
		
		// ������ String�� String[]�� ��ȯ
		String[] listId = id.split(";");
		String[] listPw = pw.split(";");
		String[] listInfo = info.split(";");

		// ���� ���� �� - ������ ȸ�� ������ ArrayList�� �߰�
		ArrayList<upload> listPerson= new ArrayList<upload>();

		for (int i = 0; i < listId.length; i++) {
			listPerson.add( new upload( listId[i], listPw[i], listInfo[i] ) );	
		}
		
		
		// �ű� ȸ�� ���� - �� ��
		listPerson.add( new upload( "008", "8888", "2020-88-88" ) );
		

		// �α��� �� ȸ���� �� ������ ���� ���� Index ã��
		System.out.print("// ��� �α��� �� ȸ�� -> �� ������ ���� ���� Index ã�� \n\n ");
		String login_ID = "002";

		for (int i = 0; i < listId.length; i++) {
			
			if (login_ID.equals(listId[i])) {
				System.out.println("\t\t�� ȸ���� ���� ������ index ["+i+"] �� �����մϴ�.");
			}
		}
		System.out.println("\n");
		
		
		
		// 2��° �մԿ� ���� ���� - �� ȸ
		System.out.println("// index 2 �� �մԿ� ���� ���� ȣ�� \n");
		System.out.println("\t\tID    ��й�ȣ  �Ⱓ�� ������");
		System.out.print("\t\t");
		
		System.out.print(listPerson.get(2).getId()+"\s,\s");
		System.out.print(listPerson.get(2).getPw()+"\s,\s");
		System.out.println(listPerson.get(2).getInfo());
		System.out.println("\n");
		
		
		// 2��° �մԿ� ���� ���� - �� ��
		System.out.print("// index 2 �� �մ��� ���� ���� \n\n");

		listPerson.get(2).setId("003");
		listPerson.get(2).setPw("9999");
		listPerson.get(2).setInfo("2020-99-99");
		
		System.out.print("\t\t");
		System.out.print(listPerson.get(2).getId()+"\s,\s");
		System.out.print(listPerson.get(2).getPw()+"\s,\s");
		System.out.println(listPerson.get(2).getInfo());
		System.out.println("\n\n");
		
		

		
		// 2��° �մԿ� ���� ���� ���� ( String[] -> Set ��ȯ )
		System.out.println("// �ű� ���� ID �ߺ� ���� ( String[] -> Set ��ȯ �� üũ )\n");
		Set<String> idSet = new HashSet<>();
		for (String ID : listId)
			idSet.add(ID);
		System.out.println("\t\tidSet : "+idSet);

		String newbie = "002";
		System.out.println();
		if (idSet.contains(newbie) ) {
			System.out.println("\t\t"+newbie+" �� ID �� �ߺ��˴ϴ�.");
		}else System.out.println("\t\t"+newbie+" �� ȸ������ �����մϴ�.");
		System.out.println("\n\n");

		
		// ���� ���� �� txt�� ��ȯ
		System.out.println("// ���� ���� -> ��� �� ������ ������ txt �� ���� (�켱 String ��ȯ) \n");

		String id_f = ""; 
		String pw_f = ""; 
		String info_f = "";
		for (int i = 0; i < listPerson.size(); i++) {
			id_f += listPerson.get(i).getId()+";"; 
			pw_f += listPerson.get(i).getPw()+";";
			info_f += listPerson.get(i).getInfo()+";";
		}
		
		System.out.println("id   : "+id_f);
		System.out.println("pw   : "+pw_f);
		System.out.println("info : "+info_f);
		System.out.println();
		System.out.println("  �� String �� id, pw, info.txt�� �����մϴ�.");

	}

}
