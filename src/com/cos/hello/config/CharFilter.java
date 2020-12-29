package com.cos.hello.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharFilter implements javax.servlet.Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("필터걸림");
		chain.doFilter(request, response);//다음 체인을 때리러 가는데 안만들어서 index.jsp로 간다	
	}	
}