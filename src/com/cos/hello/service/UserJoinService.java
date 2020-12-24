//package com.cos.hello.service;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.cos.hello.dao.UsersDao;
//import com.cos.hello.model.Users;
//
//public class UserJoinService {
//
//	public void 회원가입(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		//데이터 원형 username=ssar&password=1234&email=ssar@nate.com
//		
//		//1번 form의 input태그에 있는 3가지 값 username, password, email 받기
//		String username = req.getParameter("username"); //key값이 name값
//		String password = req.getParameter("password"); //key값 뒤에 = 값을 골라주는 함수
//		String email = req.getParameter("email");		//파싱을 해준다
//		
//		Users user = Users.builder()
//				.username(username)
//				.password(password)
//				.email(email)
//				.build();
//		
//		UsersDao userDao = new UsersDao();	//싱글톤으로 바꿔보기
//		int result = userDao.insert(user);
//		
//		if(result==1) {
//			//3번 insert가 정상적으로 되었다면 index.jsp를 응답!!
//			resp.sendRedirect("auth/login.jsp");
//		}else {
//			resp.sendRedirect("auth/join.jsp");
//		}
//		
//		
//		
//		System.out.println("======joinProc=======");
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(email);
//		System.out.println("======joinProc=======");
//		
//		
//	}
//}
