package com.promount.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.promount.dao.TableCreationCRUD;
import com.promount.model.Employee;

@WebServlet(urlPatterns = { "/validateUser", "/loginUser", "/fetchUser", "/logout", "/updateUser" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TableCreationCRUD creationCRUD = new TableCreationCRUD();
		creationCRUD.createTable();
		String servletPath = request.getServletPath();

		if ("/validateUser".equalsIgnoreCase(servletPath)) {

			// fetching all information from request

			String fullName = request.getParameter("fullName").trim();
			Long phoneNo = Long.parseLong(request.getParameter("phone"));
			String techStack = request.getParameter("tech").trim();
			String email = request.getParameter("userName").trim();
			String password = request.getParameter("userPassword").trim();
			String confirmPassword = request.getParameter("confirmUserPassword").trim();

			// fetching file information
			Part filePart = request.getPart("profile-photo");
			String fileName = filePart.getSubmittedFileName();
			String uploadPath = "C:\\uploads\\" + fileName;

			final String UPLOAD_DIRECTORY = "C:/uploads";

			// checking if both passwords matches or not
			if (!password.equals(confirmPassword)) {
				response.sendRedirect("register.jsp");
			} else {

				try {
					// Get the file part from the request
					Part filePart1 = request.getPart("imageFile");

					// Extract the filename from the uploaded file part
					String fileName1 = getFileName(filePart1);

					// Ensure the upload directory exists
					File uploadDir = new File(UPLOAD_DIRECTORY);
					if (!uploadDir.exists()) {
						uploadDir.mkdirs();
					}

					// Save the file to the server's file system
					try (InputStream input = filePart1.getInputStream()) {
						File outputFile = new File(uploadDir, fileName1);
						Files.copy(input, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					}

				} catch (Exception e) {
					response.getWriter().println("File upload failed: " + e.getMessage());
				}

				// if(ServletFileUpload.isMultipartContent(request)){
//		            try {
//		                List<FileItem> multiparts = new ServletFileUpload(
//		                                         new DiskFileItemFactory()).parseRequest(request);
//		                for(FileItem item : multiparts){
//		                    if(!item.isFormField()){
//		                        File fileSaveDir = new File(UPLOAD_DIRECTORY);
//		                        if (!fileSaveDir.exists()) {
//		                            fileSaveDir.mkdir();
//		                        }
//		                        String name = new File(item.getName()).getName();
//		                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
//		                    }
//		                }
//		            } catch (Exception e) {
//		                // exception handling
//		            }
//		       
//		        }
				/*
				 * for (Part part : request.getParts()) {
				 * 
				 * part.write(UPLOAD_DIRECTORY + File.separator + fileName);
				 * System.out.println(part.toString()); }
				 */

				System.out.println(fullName + "," + phoneNo + "," + fileName + "," + email + "," + password + ","
						+ confirmPassword);

				// create table
				creationCRUD.createTable();

				// insert data into table
				Employee empObj = new Employee(fullName, phoneNo, techStack, uploadPath, email, password);
				creationCRUD.insertData(empObj);

				HttpSession session = request.getSession();
				session.setAttribute("fullName", fullName);

				// forwarding request to login.jsp
				RequestDispatcher forwardReq = request.getRequestDispatcher("login.jsp");
				forwardReq.forward(request, response);
			}

		} else if ("/updateUser".equalsIgnoreCase(servletPath)) {
			System.out.println("you are in updateUser method to call");
			updateUser(request, response);
		} else {
			loginValidate(request, response);
		}

	}

	private String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		String[] tokens = contentDisposition.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/updateUser".equalsIgnoreCase(servletPath)) {
			TableCreationCRUD creationCRUD = new TableCreationCRUD();
			System.out.println("you are in updateUser method");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Id is: " + id);
			Employee existEmp = creationCRUD.findEmployeeById(id);
			System.out.println("Updated Employee is : " + existEmp);

			String fullName = request.getParameter("fullName").trim();
			Long phoneNo = Long.parseLong(request.getParameter("phone"));
			String techStack = request.getParameter("tech").trim();
			String email = request.getParameter("userName").trim();
			String password = request.getParameter("userPassword").trim();
			String confirmPassword = request.getParameter("confirmUserPassword").trim();

			// fetching file information
			Part filePart = request.getPart("profile-photo");
			String fileName = filePart.getSubmittedFileName();
			String uploadPath = "C:\\uploads\\" + fileName;
			final String UPLOAD_DIRECTORY = "C:/uploads";
			// checking if both passwords matches or not
			if (!password.equals(confirmPassword)) {
				response.sendRedirect("register.jsp?id=" + id);
			} else {

				for (Part part : request.getParts()) {
					part.write(UPLOAD_DIRECTORY + File.separator + fileName);
					System.out.println(part.toString());
				}

				System.out.println("New Data : " + fullName + "," + phoneNo + "," + fileName + "," + email + ","
						+ password + "," + confirmPassword);

				existEmp.setId(id);
				existEmp.setFullName(fullName);
				existEmp.setTech(techStack);
				existEmp.setPhone(phoneNo);
				existEmp.setProfilePhotoUrl(uploadPath);
				existEmp.setUserName(email);
				existEmp.setPassword(confirmPassword);
				creationCRUD.update(existEmp);

				HttpSession session = request.getSession();
				session.setAttribute("fullName", fullName);

				// forwarding request to login.jsp
				RequestDispatcher forwardReq = request.getRequestDispatcher("login.jsp");
				forwardReq.forward(request, response);
			}
		}
	}

	private void loginValidate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginId = request.getParameter("userName").trim().toLowerCase();
		String loginPassword = request.getParameter("user-Password");

		TableCreationCRUD creationCRUD = new TableCreationCRUD();
		boolean flag = creationCRUD.validateCredentials(loginId, loginPassword);
		if (flag) {
			HttpSession session = request.getSession();
			session.setAttribute("loginId", loginId);
//			session.setAttribute("loginPassword", loginPassword);

//			String email = (String) session.getAttribute("loginId");
			Employee fetchedUserDetailsObject = creationCRUD.fetchedAllData(loginId);
			int id = fetchedUserDetailsObject.getId();

			session.setAttribute("empId", id);

			response.sendRedirect("Dashboard.jsp?id=" + id);
		} else {
			response.sendRedirect("login.jsp");
		}

	}

	public HttpServletResponse jsonCreate(Employee empData, HttpServletResponse resp) throws IOException {
		String jsonString = new Gson().toJson(empData);
		System.out.println("JSON in jsonCreate method:" + jsonString);
		resp.setContentType("application/json");
		resp.getWriter().write(jsonString);
		return resp;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		TableCreationCRUD creationCRUD = new TableCreationCRUD();
		creationCRUD.createTable();
		if ("/fetchUser".equalsIgnoreCase(servletPath)) {
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println("Get method Id is: " + id);

			HttpSession session = req.getSession();
			if (session.getAttribute("loginId") != null) {

				String email = (String) session.getAttribute("loginId");
				Employee fetchedUserDetailsObject = creationCRUD.fetchedAllData(email);

				resp = jsonCreate(fetchedUserDetailsObject, resp);
				System.out.println("Valued json fetched");
			} else {
				resp.sendRedirect("login.jsp");
			}
		}

		else if ("/logout".equalsIgnoreCase(servletPath)) {
			HttpSession session = req.getSession();
			session.removeAttribute("loginId");
			session.invalidate();
			resp.sendRedirect("login.jsp");
		}
	}
}
