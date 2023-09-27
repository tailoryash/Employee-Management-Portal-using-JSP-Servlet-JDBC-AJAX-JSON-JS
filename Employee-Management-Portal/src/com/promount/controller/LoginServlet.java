package com.promount.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.promount.dao.TableCreationCRUD;
import com.promount.model.Employee;

@WebServlet(urlPatterns = { "/validateUser", "/loginUser", "/fetchUser", "/logout" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class LoginServlet extends HttpServlet {

//	private Gson gson = new Gson();

	@Override
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

			// checking if both passwords matches or not
			if (!password.equals(confirmPassword)) {
				response.sendRedirect("register.jsp");
			} else {
				System.out.println(filePart);

				for (Part part : request.getParts()) {
					part.write(uploadPath);
				}

				System.out.println(fullName + "," + phoneNo + "," + fileName + "," + email + "," + password + ","
						+ confirmPassword);

				// create table
				TableCreationCRUD creationCRUD = new TableCreationCRUD();
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

		} else {
			loginValidate(request, response);
		}

		/*
		 * if (request.getParameter("login-id").trim().toLowerCase() != null) {
		 * loginValidate(request, response); }
		 */

	}

	private void loginValidate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginId = request.getParameter("login-id").trim().toLowerCase();
		String loginPassword = request.getParameter("login-password");

		TableCreationCRUD creationCRUD = new TableCreationCRUD();
		boolean flag = creationCRUD.validateCredentials(loginId, loginPassword);
		if (flag) {
			HttpSession session = request.getSession();
			session.setAttribute("loginId", loginId);
			session.setAttribute("loginPassword", loginPassword);
			/*
			 * RequestDispatcher forwardReq = request.getRequestDispatcher("Dashboard.jsp");
			 * forwardReq.forward(request, response);
			 */

			response.sendRedirect("Dashboard.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if ("/fetchUser".equalsIgnoreCase(servletPath)) {
			TableCreationCRUD creationCRUD = new TableCreationCRUD();

			HttpSession session = req.getSession();
			String email = (String) session.getAttribute("loginId");
			Employee fetchedUserDetailsObject = creationCRUD.fetchedAllData(email);
			int id = fetchedUserDetailsObject.getId();
			System.out.println(id);
			session.setAttribute("empId", id);

//			ObjectMapper mapper = new ObjectMapper();
//			resp.setContentType("application/json");
//			mapper.writeValue(resp.getOutputStream(), fetchedUserDetailsObject);

			String jsonString = new Gson().toJson(fetchedUserDetailsObject);
			System.out.println(jsonString);
			resp.setContentType("application/json");
			resp.getWriter().write(jsonString);

			if (session.getAttribute("loginId") != null) {
				RequestDispatcher forwardReq = req.getRequestDispatcher("register.jsp?id="+id);
				forwardReq.forward(req, resp);
			} else {
				resp.sendRedirect("login.jsp");
			}

		}

		if ("/logout".equalsIgnoreCase(servletPath)) {
			HttpSession session = req.getSession();
			session.removeAttribute("loginId");
			session.invalidate();
			resp.sendRedirect("login.jsp");
		}

	}

}
