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

import com.cos.hello.service.UsersService;

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
		
		
		UsersService usersService = new UsersService();
		
		if (gubun.equals("login")) {	//한번 더 request
			resp.sendRedirect("auth/login.jsp");
			
		}else if (gubun.equals("join")) {
			resp.sendRedirect("auth/join.jsp");
			
		}else if (gubun.equals("selectOne")) {
			usersService.유저정보보기(req, resp);
			
		}else if (gubun.equals("updateOne")) {
			usersService.유저정보수정페이지(req,resp);
			
		}else if(gubun.equals("joinProc")) {
			usersService.회원가입(req, resp);
			
		}else if(gubun.equals("updateProc")) {
			usersService.유저정보수정하기(req, resp);
			
		}else if(gubun.equals("deleteProc")) {
			usersService.회원삭제(req, resp);
		
		}else if (gubun.equals("loginProc")) {
			
			//1. 쿼리문 select id, username, email FROM users WHERE username = ? AND password = ?
			//2. dao의 함수명 : login() return을 Users 오브젝트를 리턴
			//정상이면 : 세션에 Users오브젝트 담고 index.jsp 
			//비정상이면 : login.jsp
			
		
			usersService.로그인(req, resp);
			
			
//			//1번 전달되는 값 받기
//			String username = req.getParameter("username");
//			String password = req.getParameter("password");
//			
//			System.out.println("======loginProc=======");
//			System.out.println(username);
//			System.out.println(password);
//			System.out.println("======loginProc=======");
//			//2번 데이터베이스 값이 있는 select해서 확인
//			Users user = Users.builder()
//					.id(1)
//					.username(username)
//					.build();
//			//3번 
//			HttpSession session = req.getSession();//세션 영역에 접근 힙메모리에
//			//session에는 사용자 패스워드 절대 넣지 않기
//			session.setAttribute("sessionUser",user);
//			//모든 응답에는 jSessionid가 쿠키로 추가된다.
//			
//			//resp.setHeader("cos", "session=9998"); //key값이 다른거면 브라우저엔 저장이 안됨
//			//resp.setHeader("Set-cookie", "session=9998");// key값이 Set-cookie이면 브라우저에 저장됨
//			
//			//4번 정보가 있으면 index.jsp로 이동 
//			resp.sendRedirect("index.jsp");
			
			
		}
	}
}