package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class mainClass {

	public static void main(String[] args) {
		System.out.println("\n\n");

		// id, pw, info.txt 각각의 파일에서 불러온 String 입니다.
		String id = "001;002;003;";
		String pw = "1111;2222;3333;";
		String info = "2020-11-11;2020-22-22;2020-33-33;";
		
		// 각각의 String을 String[]로 변환
		String[] listId = id.split(";");
		String[] listPw = pw.split(";");
		String[] listInfo = info.split(";");

		// 영업 시작 전 - 각각의 회원 정보를 ArrayList에 추가
		ArrayList<upload> listPerson= new ArrayList<upload>();

		for (int i = 0; i < listId.length; i++) {
			listPerson.add( new upload( listId[i], listPw[i], listInfo[i] ) );	
		}
		
		
		// 신규 회원 정보 - 추 가
		listPerson.add( new upload( "008", "8888", "2020-88-88" ) );
		

		// 로그인 한 회원의 각 정보에 대한 접근 Index 찾기
		System.out.print("// 방금 로그인 한 회원 -> 각 정보에 대한 접근 Index 찾기 \n\n ");
		String login_ID = "002";

		for (int i = 0; i < listId.length; i++) {
			
			if (login_ID.equals(listId[i])) {
				System.out.println("\t\t이 회원에 대한 정보는 index ["+i+"] 로 접근합니다.");
			}
		}
		System.out.println("\n");
		
		
		
		// 2번째 손님에 대해 정보 - 조 회
		System.out.println("// index 2 인 손님에 대해 정보 호출 \n");
		System.out.println("\t\tID    비밀번호  기간권 시작일");
		System.out.print("\t\t");
		
		System.out.print(listPerson.get(2).getId()+"\s,\s");
		System.out.print(listPerson.get(2).getPw()+"\s,\s");
		System.out.println(listPerson.get(2).getInfo());
		System.out.println("\n");
		
		
		// 2번째 손님에 대한 정보 - 수 정
		System.out.print("// index 2 인 손님의 정보 수정 \n\n");

		listPerson.get(2).setId("003");
		listPerson.get(2).setPw("9999");
		listPerson.get(2).setInfo("2020-99-99");
		
		System.out.print("\t\t");
		System.out.print(listPerson.get(2).getId()+"\s,\s");
		System.out.print(listPerson.get(2).getPw()+"\s,\s");
		System.out.println(listPerson.get(2).getInfo());
		System.out.println("\n\n");
		
		

		
		// 2번째 손님에 대한 정보 수정 ( String[] -> Set 변환 )
		System.out.println("// 신규 가입 ID 중복 여부 ( String[] -> Set 변환 후 체크 )\n");
		Set<String> idSet = new HashSet<>();
		for (String ID : listId)
			idSet.add(ID);
		System.out.println("\t\tidSet : "+idSet);

		String newbie = "002";
		System.out.println();
		if (idSet.contains(newbie) ) {
			System.out.println("\t\t"+newbie+" 는 ID 가 중복됩니다.");
		}else System.out.println("\t\t"+newbie+" 는 회원가입 가능합니다.");
		System.out.println("\n\n");

		
		// 영업 종료 후 txt로 변환
		System.out.println("// 영업 종료 -> 모든 고객 정보를 각각의 txt 로 저장 (우선 String 전환) \n");

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
		System.out.println("  위 String 을 id, pw, info.txt로 저장합니다.");

	}

}
