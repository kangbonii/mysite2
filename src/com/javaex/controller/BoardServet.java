package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;



@WebServlet("/bs")
public class BoardServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardServet");
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("action");
		
		if("list".equals(actionName)) {
			System.out.println("list");
			
			BoardDao dao = new BoardDao();
			List<BoardVo> list = dao.getList();
			
			System.out.println(list.toString());
			request.setAttribute("addlist", list);

			WebUtil.forword(request, response, "/WEB-INF/board/list.jsp");
		
		
		}else if("writeform".equals(actionName)) {
			System.out.println("writeform");

			WebUtil.forword(request, response, "/WEB-INF/board/writeform.jsp");

		}else if("write".equals(actionName)) {
			System.out.println("write");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
						
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			HttpSession session = request.getSession();
			UserVo uservo = (UserVo)session.getAttribute("authUser");
			vo.setUser_no(uservo.getNo());
		
					
			BoardDao dao = new BoardDao();
			dao.insert(vo);
	
			
			WebUtil.redirect(request, response, "./bs?action=list");
			
		}else if("read".equals(actionName)) {
			System.out.println("read");
			
			BoardDao dao = new BoardDao();
			int no = Integer.parseInt(request.getParameter("no"));
			BoardVo boardvo = dao.readList(no);
			request.setAttribute("boardvo", boardvo);
			
			
			
			
			WebUtil.forword(request, response, "/WEB-INF/board/read.jsp");
			
			
		}else if("modifyform".equals(actionName)) {
			System.out.println("modifyform");
			
			BoardDao dao = new BoardDao();
			int no = Integer.parseInt(request.getParameter("no"));
			BoardVo boardvo = dao.readList(no);
			request.setAttribute("boardvo", boardvo);
			
			WebUtil.forword(request, response, "/WEB-INF/board/modifyform.jsp");
			
			
		}else if("delete".equals(actionName)) {
			int del = Integer.parseInt(request.getParameter("no"));
			
			BoardDao dao = new BoardDao();
			dao.delete(del);
			WebUtil.redirect(request, response, "./bs?action=list");
		
		}else if("modify".equals(actionName)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int mof = Integer.parseInt(request.getParameter("no"));
			
			BoardVo vo = new BoardVo();
			vo.setNo(mof);
			vo.setTitle(title);
			vo.setContent(content);
			BoardDao dao = new BoardDao();
			dao.update(vo);
			WebUtil.redirect(request, response, "./bs?action=list");
			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
