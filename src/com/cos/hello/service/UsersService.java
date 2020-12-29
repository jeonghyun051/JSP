package com.cos.hello.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.dao.UsersDao;
import com.cos.hello.dto.JoinDto;
import com.cos.hello.dto.LoginDto;
import com.cos.hello.model.Users;
import com.cos.hello.utill.Script;

public class UsersService {
	
	
	public void 회원삭제(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 데이터 원형 username=ssar&password=1234&email=ssar@nate.com

		// 1번 form의 input태그에 있는 3가지 값 username, password, email 받기
		
		int id = Integer.parseInt(req.getParameter("id"));

				
		 // 싱글톤으로 바꿔보기
		UsersDao usersDao = new UsersDao();
		int result = usersDao.delete(id);

		
		if (result == 1) {
			HttpSession session = req.getSession();
			session.invalidate();
			// 3번 insert가 정상적으로 되었다면 index.jsp를 응답!!
			resp.sendRedirect("user?gubun=selectOne.jsp");
			
		} else {
			//실패했을 때 이전페이지로 이동
			resp.sendRedirect("user?gubun=selectOne.jsp");
	
		}
		
	}
	
	public void 유저정보수정하기(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 데이터 원형 username=ssar&password=1234&email=ssar@nate.com

		// 1번 form의 input태그에 있는 3가지 값 username, password, email 받기
		int id = Integer.parseInt(req.getParameter("id"));
		String password = req.getParameter("password"); // key값 뒤에 = 값을 골라주는 함수
		String email = req.getParameter("email"); // 파싱을 해준다

		Users user = Users.builder()
				.id(id)
				.password(password)
				.email(email)
				.build();

		 // 싱글톤으로 바꿔보기
		UsersDao usersDao = new UsersDao();
		int result = usersDao.update(user);

		if (result == 1) {
			// 3번 insert가 정상적으로 되었다면 index.jsp를 응답!!
			resp.sendRedirect("index.jsp");
			System.out.println("성공");
		} else {
			//실패했을 때 이전페이지로 이동
			System.out.println("실패");
			resp.sendRedirect("user?gubun=updateOne.jsp");
		}
		
	}
	
	public void 유저정보수정페이지(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 인증이 필요한 페이지

		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("sessionUser");
		UsersDao usersDao = new UsersDao();
				
		if (user!= null) {
			Users userEntity = usersDao.selectById(user.getId());
			req.setAttribute("user",userEntity);
			RequestDispatcher dis = 
					req.getRequestDispatcher("user/updateOne.jsp");
			dis.forward(req, resp);
		// resp.sendRedirect("user/selectOne.jsp");
		// request를 두번해서 기존것이 날라가는데 request를 유지하는기법 밑에임
		}else {
				resp.sendRedirect("auth/login.jsp");
			}
	}
	
	
	public void 유저정보보기(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 인증이 필요한 페이지

		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("sessionUser");
		UsersDao usersDao = new UsersDao();
				
		if (user!= null) {
			Users userEntity = usersDao.selectById(user.getId());
			req.setAttribute("user",userEntity);
			RequestDispatcher dis = 
					req.getRequestDispatcher("user/selectOne.jsp");
			dis.forward(req, resp);
		// resp.sendRedirect("user/selectOne.jsp");
		// request를 두번해서 기존것이 날라가는데 request를 유지하는기법 밑에임
		}else {
				resp.sendRedirect("auth/login.jsp");
			}
	
		
	}
	public void 로그인(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//		String username = req.getParameter("username"); // key값이 name값
//		String password = req.getParameter("password"); // key값 뒤에 = 값을 골라주는 함수
//
//		Users user = Users.builder()
//
//				.username(username)
//				.password(password)
//				.build();
		
		LoginDto loginDto = (LoginDto)req.getAttribute("dto");
		

		UsersDao userDao = new UsersDao(); // 싱글톤으로 바꿔보기
		Users userEntity = userDao.login(loginDto);

		if (userEntity != null) {
			HttpSession session = req.getSession();
			session.setAttribute("sessionUser", userEntity);
			//한글처리를 위해 resp 객체를 건드린다.
			//MIME타입 
			//HTTP Headre Content-Type 공부해오기
			//resp.setCharacterEncoding("euc-kr");
			//resp.setContentType("text/html;charset=utf-8");

			Script.href(resp, "index.jsp", "로그인성공");
			
		} else {
//			resp.sendRedirect("auth/login.jsp");
//			System.out.println("로그인 실패");
			Script.back(resp, "로그인실패");

		}

	}

	public void 회원가입(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 데이터 원형 username=ssar&password=1234&email=ssar@nate.com

		// 1번 form의 input태그에 있는 3가지 값 username, password, email 받기
//		String username = req.getParameter("username"); // key값이 name값
//		String password = req.getParameter("password"); // key값 뒤에 = 값을 골라주는 함수
//		String email = req.getParameter("email"); // 파싱을 해준다
//
//		Users user = Users.builder().username(username).password(password).email(email).build();

		JoinDto joinDto = (JoinDto)req.getAttribute("dto");
		
		UsersDao userDao = new UsersDao(); // 싱글톤으로 바꿔보기
		int result = userDao.insert(joinDto);

		if (result == 1) {
			// 3번 insert가 정상적으로 되었다면 index.jsp를 응답!!
			resp.sendRedirect("auth/login.jsp");
		} else {
			resp.sendRedirect("auth/join.jsp");
		}

		System.out.println("======joinProc=======");
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(email);
		System.out.println("======joinProc=======");

	}

}
