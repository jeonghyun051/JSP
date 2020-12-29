package com.cos.hello.utill;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Script {

	public static void back(HttpServletResponse resp, String msg) throws IOException {
		//

	PrintWriter out = resp.getWriter();
	out.println("<script>");
	out.println("alert('"+msg+"');");
	out.println("history.back();");
	out.println("</script>");	//뒤로이동 
	out.flush();
	}
	
	public static void href(HttpServletResponse resp, String url, String msg) throws IOException {
		//resp.setHeader("content-type", "text/html;charset=utf-8");
		//resp.setContentType("text/html;charset=utf-8");

		PrintWriter out = resp.getWriter();
		out.println("<script>");
		out.println("alert('"+msg+"');");
		out.println("location.href='"+url+"';");
		out.println("</script>");	//뒤로이동 
		out.flush();
		
		//1. workspace 환경이 utf-8
		//2. html파일이 utf-8
		//3. jsp파일이 utf-8
		//4. 응답헤더에 utf-8
		//5. 요청헤더에 utf-8
		//6. 데이터베이스에 utf-8(신경안써도됨) - 리눅스에서 하면 my.cnf / 윈도우에서 my.ini 
		
	}
	
	
}
