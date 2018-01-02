package com.uplooking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {

	/**
	 * 1 Ĭ��ת�� return "index.jsp"; 2 �ض��� return "@red_index.jsp" 3json return
	 * "@json_{XXXXXX}"
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	String execute(HttpServletRequest req, HttpServletResponse resp);

}
