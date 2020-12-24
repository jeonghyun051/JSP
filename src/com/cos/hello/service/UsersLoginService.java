//package com.cos.hello.service;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.catalina.User;
//
//import com.cos.hello.dao.UsersDao;
//import com.cos.hello.model.Users;
//
//public class UsersLoginService {
//
//	public void 로그인(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		
//
//		String username = req.getParameter("username"); //key값이 name값
//		String password = req.getParameter("password"); //key값 뒤에 = 값을 골라주는 함수
//		
//		Users user = Users.builder()
//
//				.username(username)
//				.password(password)
//				.build();
//		
//		UsersDao userDao = new UsersDao();	//싱글톤으로 바꿔보기
//		Users userEntity = UsersDao.login(user);
//		
//						
//		if (userEntity != null) {
//			HttpSession session = req.getSession();
//			session.setAttribute("sessionUser",userEntity);
//			System.out.println("로그인 성공");
//			resp.sendRedirect("index.jsp");
//		}else {
//			resp.sendRedirect("auth/login.jsp");
//		}
//		
//	}
//	
//	
//}
