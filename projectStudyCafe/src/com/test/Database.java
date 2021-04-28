package com.test;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
	HashMap<Integer, ArrayList<String>> db_map = new HashMap<Integer, ArrayList<String>>();
	ArrayList<String> info = new ArrayList<String>();

	public Database() {
		// TODO Auto-generated constructor stub
	}

	public void sign_up(int id, int pw, String name, String addr) {
		info.add(pw + "");
		info.add(name);
		info.add(addr);
		db_map.put(id, info);
	}

}
