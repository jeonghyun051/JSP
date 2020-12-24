package com.cos.hello.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//javax로 시작하는 패키지는 톰켓이 들고 있는 라이브러리
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.config.DBConn;
import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//디스패쳐의 역할 = 분기 필요한 view를 응답해주는 것
@WebServlet("/login")
public class userController extends HttpServlet{

	//http://localhost:8000/hello/front 이걸치면 밑에 함수가 발동된다.
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("user process 요청");
		
		//req와 res는 톰켓이 만들어 줍니다.(사용자의 요청이 있을때 마다)
		//req는 bufferedReader 할 수 있는 ByteStream
		//res는 bufferedWriter 할 수 있는 ByteStream 

		//String gubun = req.getRequestURI(); //결과 : /hello/front
		String gubun = req.getParameter("gubun"); //결과 : 1 //주소창:front?gubun=1
		System.out.println(gubun);
		route(gubun,req,resp);
		//http://localhost:8000/hello/user?gubun=login.jsp
		
	}
	
	private void route(String gubun,HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (gubun.equals("login")) {	//한번 더 request
			resp.sendRedirect("auth/login.jsp");
		}else if (gubun.equals("join")) {
			resp.sendRedirect("auth/join.jsp");
		}else if (gubun.equals("selectOne")) {
			//인증이 필요한 페이지
			String result;
			HttpSession session = req.getSession();
			if (session.getAttribute("sessionUser") != null) {
				Users user = (Users)session.getAttribute("sessionUser");
				result="인증되었습니다.";
				System.out.println(user);
			}else {
				result="인증되지않았습니다.";
			}
			//resp.sendRedirect("user/selectOne.jsp");
			//request를 두번해서 기존것이 날라가는데 request를 유지하는기법 밑에임
			req.setAttribute("result", result);
			RequestDispatcher dis =
					req.getRequestDispatcher("user/selectOne.jsp");
			dis.forward(req,resp); //덮어쓰기 
			
		}else if (gubun.equals("updateOne")) {
			resp.sendRedirect("user/updateOne.jsp");
		}else if(gubun.equals("joinProc")) {
			//데이터 원형 username=ssar&password=1234&email=ssar@nate.com
			
			//1번 form의 input태그에 있는 3가지 값 username, password, email 받기
			String username = req.getParameter("username"); //key값이 name값
			String password = req.getParameter("password"); //key값 뒤에 = 값을 골라주는 함수
			String email = req.getParameter("email");		//파싱을 해준다
			
			Users user = Users.builder()
					.username(username)
					.password(password)
					.email(email)
					.build();
			
			UsersDao userDao = new UsersDao();	//싱글톤으로 바꿔보기
			int result = userDao.insert(user);
			if(result==1) {
				//3번 insert가 정상적으로 되었다면 index.jsp를 응답!!
				resp.sendRedirect("auth/login.jsp");
			}else {
				resp.sendRedirect("auth/join.jsp");
			}
			
			System.out.println("======joinProc=======");
			System.out.println(username);
			System.out.println(password);
			System.out.println(email);
			System.out.println("======joinProc=======");
			
			
			
			
			
		}else if (gubun.equals("loginProc")) {
			//1번 전달되는 값 받기
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			System.out.println("======loginProc=======");
			System.out.println(username);
			System.out.println(password);
			System.out.println("======loginProc=======");
			//2번 데이터베이스 값이 있는 select해서 확인
			Users user = Users.builder()
					.id(1)
					.username(username)
					.build();
			//3번 
			HttpSession session = req.getSession();//세션 영역에 접근 힙메모리에
			//session에는 사용자 패스워드 절대 넣지 않기
			session.setAttribute("sessionUser",user);
			//모든 응답에는 jSessionid가 쿠키로 추가된다.
			
			//resp.setHeader("cos", "session=9998"); //key값이 다른거면 브라우저엔 저장이 안됨
			//resp.setHeader("Set-cookie", "session=9998");// key값이 Set-cookie이면 브라우저에 저장됨
			
			//4번 정보가 있으면 index.jsp로 이동 
			resp.sendRedirect("index.jsp");
			
			
		}
	}
}