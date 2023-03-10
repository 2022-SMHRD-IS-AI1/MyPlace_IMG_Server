package com.smhrd.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ㅇㅇ
		final String IMAGE = "img/_126241775_getty_cats.jpg";
	
		PrintWriter out = response.getWriter();
		
		
		out.print("<h1>배포 테스트하기</h1>");
	
		out.print("<a");
		out.print("href='" + IMAGE +"'");
		out.print(">");
		
		
		out.print("고양이");
		out.print("</a>");
	
	
	
	
	}

}
