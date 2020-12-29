package com.cos.hello.config;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cos.hello.model.Users;

public class AttackFilter implements Filter{

	
	//2번째 순서 (마지막 순서)
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("공격 방어 필터 실행");
		//post요청만 받아서 처리!!
		//String method = request.getReader();
		HttpServletRequest req = (HttpServletRequest) request;
		String method =req.getMethod();
		
		if (method.equals("POST")) {
			String username = request.getParameter("username");
			username = username.replaceAll("<","&lt;")
				.replaceAll(">","&gt;");
			System.out.println("username: "+username);	//데이터베이스에는 꺽쇠 스크립트가 못들어가게 해야함
			//다시 request 에 넣어줘야함 
			String userEntity = username;
			
		
//			req.setAttribute("user",userEntity);
//			RequestDispatcher dis = 
//					req.getRequestDispatcher("user/index.jsp");
//			dis.forward(req, response);
				
			//username에 < >꺽쇠 들어오는것을 방어
			//만약에 꺽쇠가 들어오면 전부 &lt; &gt; 치환
			//그리고 다시 필터 타게 할 예정
		}
		
//		BufferedReader br = request.getReader();
//		String input;
//		while ((input=br.readLine())!=null) {
//			System.out.println(input);
//		}
		//joinProc 일때, 
		//String username = request.getParameter("username");
		
		
		chain.doFilter(request, response);
	}
}
