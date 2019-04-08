package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("user");
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		if("joinform".equals(action)) {
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinform.jsp");
			
		}else if("join".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			//vo만들기
			UserVo uservo = new UserVo(name, email, password, gender); //set으로 안하고 생성자에서 
			System.out.println(uservo.toString());
			
			//insert
			UserDao userdao = new UserDao();
			userdao.insert(uservo);
			
			//화면 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
			
		}else if("loginform".equals(action)) {
			WebUtil.forword(request, response, "/WEB-INF/views/user/loginform.jsp");
			
		}else if("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserDao userDao = new UserDao();
			UserVo uservo = userDao.getUser(email, password);
			//System.out.println(uservo.toString());
			
			//UserVo에 값이없을때
			if(uservo == null) { //로그인 실패
				WebUtil.redirect(request, response, "./user?action=loginform&result=fail");
				
			}else {  //로긴성공				
			//성공일때
			HttpSession session = request.getSession(true);//만들어져 있는거
			session.setAttribute("authUser", uservo);
			
			WebUtil.redirect(request, response, "./main");
			
			}
			
		}else if("logout".equals(action)) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();//데이터뿐만아니라 다날리는
			
			WebUtil.redirect(request, response, "/mysite/main");
	
		}else if("modifyform".equals(action)) {
			
			UserDao userDao = new UserDao();
			HttpSession session = request.getSession();  //만들어져 있는거
			UserVo vo = (UserVo)session.getAttribute("authUser");
			
			UserVo uservo = userDao.getUser(vo.getNo());
			System.out.println(uservo.toString());
			
			request.setAttribute("uservo", uservo);
			WebUtil.forword(request, response, "/WEB-INF/views/user/modifyform.jsp");
			
		}else if("modify".equals(action)) {	
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			UserDao userdao = new UserDao();
			HttpSession session = request.getSession();  
			
			UserVo vo = (UserVo)session.getAttribute("authUser");
			vo.setName(name);
			vo.setPassword(password);
			System.out.println(vo.toString());
			
			userdao.update(vo);
			System.out.println("update");

			WebUtil.redirect(request, response, "./main");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
