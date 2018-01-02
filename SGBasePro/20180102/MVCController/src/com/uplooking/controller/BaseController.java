package com.uplooking.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController extends HttpServlet {

	// ����properties �����ļ�
	private Properties properties = new Properties();
	// �ַ����뼯��
	private String charset = "utf-8";

	@Override
	public void init(ServletConfig config) throws ServletException {

		// ��ȡ��ʼ���� ���� key value
		String configname = config.getInitParameter("configname");

		try {
			// �Զ����������ļ�
			properties.load(this.getClass().getClassLoader().getResourceAsStream(configname));

			// ͨ�������ļ���ȡ���ַ����뼯
			String tmp = properties.getProperty("charset");
			if (tmp != null) {
				charset = tmp;
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("��ȡʧ�ܣ�");
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// �����ַ����뼯
			req.setCharacterEncoding(charset);
			resp.setCharacterEncoding(charset);

			// ��ȡ�� uri
			String act = req.getRequestURI();
			// System.out.println(act);
			// /MVCController/ user .do
			// user --- �ҵ�Config�ļ���com.uplooking.action.UserAction
			act = act.substring(act.lastIndexOf("/") + 1, act.lastIndexOf(".do"));
			// ��ȡ��·�� System.out.println(act);
			String classPath = properties.getProperty(act);
			// System.out.println(classPath);

			if (classPath == null || classPath.equals("")) {
				resp.setContentType("text/html");
				resp.getWriter().println("û����Ҫ���ʵ�·����");
				return;
			}

			// ���з������
			Class iActionClass = Class.forName(classPath);

			// ����ʵ��
			IAction iAction = (IAction) iActionClass.newInstance();

			// ���� �ӷ������д��� ������֮�� ֻҪ ���� String request response
			// ��װ����ֵ
			String result = iAction.execute(req, resp);
			System.out.println("����ֵ=" + result);

			// �з���ֵ��ʱ��
			if (result != null && !"".equals(result)) {

				/*
				 * 1 Ĭ��ת�� return "index.jsp"; 2 �ض��� return "@red_index.jsp" 3json return
				 * "@json_{XXXXXX}"
				 */
				if (result.contains("@red_")) {
					// �ض���
					resp.sendRedirect(result.split("_")[1]);
				} else if (result.contains("@json_")) {
					resp.setContentType("text/html");
					resp.getWriter().println(result.split("_")[1]);
					resp.getWriter().close();
				} else {
					req.getRequestDispatcher(result).forward(req, resp);
				}

			} else {
				// û�з���ֵ��ʱ��
				resp.setContentType("text/html");
				resp.getWriter().println("û�з���ֵ��");
				return;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
