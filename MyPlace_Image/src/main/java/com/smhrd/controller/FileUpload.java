package com.smhrd.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		// 1. 어디에? path
		String savePath = request.getServletContext().getRealPath("img");
		
		// 2. file 최대 용량
		int max = 1024 * 1024 * 5; // byte
		
		// 3. 어떤 인코딩?
		String encoding = "UTF-8";
	
		// MultipartRequest객체
		MultipartRequest multi = new MultipartRequest(request,
														savePath,
														max,
														encoding,
														new DefaultFileRenamePolicy());
		
		// new DefaultFileRenamePolicy() : 중복 이름 처리
		// cat.jpg // cat2.jpg
		
		// file 저장하기
		// file의 이름을 꺼냈을 때 저장
		String fileName = multi.getFilesystemName("myFileInput");
	
		
	
		// 비동기 통신 응답
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		//s
		
		out.print("img/" + fileName);
		
	
	
	}

}
