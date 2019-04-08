package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestBookVo;

@WebServlet("/gb") // http://localhost:8088/guestbook2/gb?action=
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GuestBookServlet 실행");
		request.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("action");

		if ("addlist".equals(actionName)) {
			System.out.println("addlist");

			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list = dao.getList();

			System.out.println(list.toString());
			request.setAttribute("addlist", list);

			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/addlist.jsp");

		} else if ("dform".equals(actionName)) {
			System.out.println("dform");
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
			//WebUtil.redirect(request, response, "/guestbook2/gb?action=addlist");

		} else if ("add".equals(actionName)) {
			System.out.println("add");
			String name = request.getParameter("name");
			String password = request.getParameter("passwd");
			String content = request.getParameter("cols");

			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);

			GuestBookDao dao = new GuestBookDao();
			dao.insert(vo);

			WebUtil.redirect(request, response, "./gb?action=addlist");

			
		}else if("delete".equals(actionName)){
			System.out.println("delete");
			String password = request.getParameter("passwd");
			String num = request.getParameter("no");
			System.out.println(password);
			GuestBookDao dao = new GuestBookDao();

			GuestBookVo res = dao.getSeleectNo(Integer.parseInt(num));
			System.out.print(res.getPassword());
			
			if (res.getPassword().equals(password)) { //디비에서 번호가져와서 패스워드 일치
			    	dao.delete(res.getNo());
			    	WebUtil.redirect(request, response, "./gb?action=addlist");
			}
			
		}else {
			System.out.println("addlist");

			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list = dao.getList();

			System.out.println(list.toString());
			request.setAttribute("addlist", list);

			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/addlist.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
