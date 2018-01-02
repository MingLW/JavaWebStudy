package com.uplooking.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uplooking.controller.IAction;

public class UserAction implements IAction {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserAction");
		return "index.jsp";
	}
	
	
/*
 *   BaseController.java  ��  HttpServletRequest req, HttpServletResponse resp
 *   ͨ������ ����ָ������� ����ȥ���� 
 *   
 *             |
 *   		   |
 *   
 *          �����߼�����
 *   
 *   ������֮��        ת��
 *            �ض���
 *            json
 *            
 *      String   Xָ��   _   ���ʵ�ҳ��/����    
 *             |
 *             |
 *             |
 *      ���String --BaseController   
 *   
 * 	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/

}
