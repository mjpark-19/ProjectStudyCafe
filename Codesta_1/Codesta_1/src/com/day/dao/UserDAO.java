package com.day.dao;

import java.util.ArrayList;
import java.util.Map;

import com.day.dto.User;
import com.day.exception.FindException;

public interface UserDAO {

	/**
	 * ������ ��ü�� �˻��Ѵ�.
	 * @Return ������ ��ü�� ��ȯ�Ѵ�. �������� �������� ������ null ���� ��ȯ�Ѵ�.
	 * @throws FindException �������� ���� ��� �Ǵ� ã�⸦ ������ ��� �߻��Ѵ�.
	 * @throws com.day.exception.FindException 
	 */
	public Map<String, User> selectAll() throws FindException ; // FindException ����� ����

	/**
	 * �α׾ƿ� �� ȸ�������� ��� userInfo�� ���޹޾� txt ���Ͽ� �����Ѵ�.
	 * @throws FindException ���� ã�⸦ ������ ��� �߻��Ѵ�.
	 * @throws com.day.exception.FindException 
	 */
	public void insertAllInfo(User userInfo) throws FindException;
	
	
	/**
	 * ȸ�����Խ� id, pw�� �Է¹޾� txt ���Ͽ� �����Ѵ�.
	 * @param ID ȸ������ �� User�� �����Ѵ�.
	 * @param PW ȸ������ �� User�� �����Ѵ�.
	 * @throws FindException
	 */
	public void insertInfo(String id, String pw) throws FindException ; // FindException ����� ����
	
	/**
	 * ȸ������ ��ȸ�� ���� ID list�� ��ȯ�޴´�.
	 * @return ��� ���� ID list�� ��ȯ�Ѵ�.
	 */
	public ArrayList<String> getIdList() throws FindException ; // FindException ����� ����
	
	/**
	 * ȸ������ ��ȸ�� ���� Pw list�� ��ȯ�޴´�.
	 * @return ��� ���� Pw list�� ��ȯ�Ѵ�.
	 */
	public ArrayList<String> getPwList() throws FindException ; // FindException ����� ����


	
}
