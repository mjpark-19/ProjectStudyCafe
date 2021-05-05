package com.db;

public class upload {
	String id ;
	String pw ;
	String info ;
	
	public upload(String id, String pw, String info) {
		this.id = id;
		this.pw = pw;
		this.info = info;	}
	
	public upload(String id, String pw) {
		this.id = id;
		this.pw = pw; }

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	


	
}
