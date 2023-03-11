package com.smhrd.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//@MultipartConfig(location = "/home/ubuntu/web/apache-tomcat-9.0.73/work/Catalina/localhost/MyPlace_Image/Img")
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTACHES_DIR = "/home/ubuntu/web/apache-tomcat-9.0.73/work/Catalina/localhost/MyPlace_Image/Img";
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		// file 받아오기
		
		String contentType = request.getContentType();
		Part file = request.getPart("myFileInput");
		InputStream fileContent = file.getInputStream();
		PrintWriter out = response.getWriter();
		
        if (contentType != null &&  contentType.toLowerCase().startsWith("multipart/")) {
            // getParts()를 통해 Body에 넘어온 데이터들을 각각의  Part로 쪼개어 리턴
        	System.out.printf("파라미터 명 : %s, contentType :  %s,  size : %d bytes \n", file.getName(),
        			file.getContentType(), file.getSize());
 
            if  (file.getHeader("Content-Disposition").contains("filename=")) {
                String fileName =  extractFileName(file.getHeader("Content-Disposition"));
                
                if (file.getSize() > 0) {
                    System.out.printf("업로드 파일 명 : %s  \n", fileName);
                    file.write(ATTACHES_DIR + File.separator  + fileName);
                    file.delete();
                }
            } else {
                String formValue =  request.getParameter(file.getName());
                System.out.printf("name : %s, value : %s  \n", file.getName(), formValue);
            }
        System.out.println("업로드 완료");
        }
		
		String url = "MyPlace_Image/img/"+file.getName();
		// 파일 경로( url )
		// MyPlace_Image/img/_126241775_getty_cats.jpg
		// 만 돌려주기
	
		// 데이터 응답
		response.setContentType("text/html; charset=utf-8");
		
		
		out.print(url);
		
	}
		
	String extractFileName(String partHeader) {
		for (String cd : partHeader.split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf("=") +  1).trim().replace("\"", "");;
				int index = fileName.lastIndexOf(File.separator);
				return fileName.substring(index + 1);
			}
		}
		return null;
	}

	}
