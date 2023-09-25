package com.promount.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(
		urlPatterns = { 
				"/validateUser", 
				"/addUser"
		})
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName = request.getParameter("fullName").trim();
		Integer phoneNo = Integer.parseInt(request.getParameter("phone"));
		String techStack = request.getParameter("tech").trim();
		String email = request.getParameter("userName").trim();
		String password = request.getParameter("userPassword").trim();
		String confirmPassword = request.getParameter("confirmUserPassword").trim();
		
		/*
		 * ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		 * FileItem file = sf.parseRequest(request); System.out.println(file.getName());
		 */
		
		Part filePart = request.getPart("profile-photo");
		String fileName = filePart.getSubmittedFileName();
		System.out.println(fullName);
		System.out.println(phoneNo);
		System.out.println(techStack);
		System.out.println(email);
		System.out.println(password);
		System.out.println(confirmPassword);
	
	}

}
