package com.promount.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.promount.model.Employee;
import com.utils.TableCreationCRUD;

@WebServlet(urlPatterns = { "/validateUser"
//				"/addUser"
})
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
    )
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private Gson gson = new Gson();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		   
		/*
		 * ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		 * List<FileItem> files = null; try { files = sf.parseRequest(request); } catch
		 * (FileUploadException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } for(FileItem file : files) { // file.getInputStream();
		 * System.out.println(file.getInputStream()); }
		 */
		 /* 
		 * 
		 * Part filePart = request.getPart("profile-photo"); String fileName =
		 * filePart.getSubmittedFileName(); System.out.println(fullName);
		 * System.out.println(phoneNo); System.out.println(techStack);
		 * System.out.println(email); System.out.println(password);
		 * System.out.println(confirmPassword);
		 */
		
		/*
		 * BufferedReader reader = request.getReader(); Employee employee =
		 * gson.fromJson(reader, Employee.class);
		 */
		
		/*
		 * Part filePart = request.getPart("profile-photo"); String fileName =
		 * Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); String
		 * uploadPath = "uploads" + File.separator + fileName;
		 * filePart.write(uploadPath); String imageUrl = "/uploads/" + fileName;
		 * response.getWriter().write(imageUrl); System.out.println(imageUrl);
		 */
		 
		 	Part filePart = request.getPart("profile-photo");
		    String fileName = filePart.getSubmittedFileName();
		    String uploadPath = "C:\\uploads\\" + fileName;
		    System.out.println(filePart);
		    for (Part part : request.getParts()) {
		      part.write(uploadPath);
		    }
		    
		
		    String fullName = request.getParameter("fullName").trim(); 
		    Long phoneNo = Long.parseLong(request.getParameter("phone")); 
		    String techStack = request.getParameter("tech").trim(); 
		    String email = request.getParameter("userName").trim(); 
		    String password = request.getParameter("userPassword").trim(); 
		    String confirmPassword = request.getParameter("confirmUserPassword").trim();
		    System.out.println(fullName + "," + phoneNo + "," + fileName  + "," + email + "," + password +"," + confirmPassword);		
		    
		    TableCreationCRUD creationCRUD = new TableCreationCRUD();
		    creationCRUD.createTable();
		    
		    Employee empObj = new Employee(fullName, phoneNo, techStack, uploadPath, email, password);
		    creationCRUD.insertData(empObj);
		    
		    RequestDispatcher forwardReq = request.getRequestDispatcher("login.jsp");
		    forwardReq.forward(request, response);
	}	

}
