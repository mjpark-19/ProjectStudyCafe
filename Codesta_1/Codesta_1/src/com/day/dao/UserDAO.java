package com.day.dao;

import java.util.ArrayList;
import java.util.Map;

import com.day.dto.User;
import com.day.exception.FindException;

public interface UserDAO {

	/**
	 * 고객정보 전체를 검색한다.
	 * @Return 고객정보 전체를 반환한다. 고객정보가 존재하지 않으면 null 값을 반환한다.
	 * @throws FindException 고객정보가 없을 경우 또는 찾기를 실패할 경우 발생한다.
	 * @throws com.day.exception.FindException 
	 */
	public Map<String, User> selectAll() throws FindException ; // FindException 사용자 정의

	/**
	 * 로그아웃 시 회원정보가 담긴 userInfo를 전달받아 txt 파일에 저장한다.
	 * @throws FindException 파일 찾기를 실패할 경우 발생한다.
	 * @throws com.day.exception.FindException 
	 */
	public void insertAllInfo(User userInfo) throws FindException;
	
	
	/**
	 * 회원가입시 id, pw를 입력받아 txt 파일에 저장한다.
	 * @param ID 회원가입 시 User에 저장한다.
	 * @param PW 회원가입 시 User에 저장한다.
	 * @throws FindException
	 */
	public void insertInfo(String id, String pw) throws FindException ; // FindException 사용자 정의
	
	/**
	 * 회원정보 조회를 위한 ID list를 반환받는다.
	 * @return 모든 고객의 ID list를 반환한다.
	 */
	public ArrayList<String> getIdList() throws FindException ; // FindException 사용자 정의
	
	/**
	 * 회원정보 조회를 위한 Pw list를 반환받는다.
	 * @return 모든 고객의 Pw list를 반환한다.
	 */
	public ArrayList<String> getPwList() throws FindException ; // FindException 사용자 정의


	
}
