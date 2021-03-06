package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.operations.CompanyOperations;
import com.operations.TransactionOperations;
import com.operations.impl.CompanyOperationsImpl;
import com.operations.impl.TransactionOperationsImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/check")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer=response.getWriter();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		TransactionOperations top = new TransactionOperationsImpl();
		Boolean result=top.Login(name, password);
		if(!result)
			writer.println("enter correct credentials");
		else if(name.equals("Admin")&& password.equals("Admin123")) {
			//RequestDispatcher to admin.jsp		
		}
		else {
			CompanyOperations cop = new CompanyOperationsImpl();
			String companyId=cop.getCompanyId(name);
			HttpSession session = request.getSession();
			session.setAttribute("name",companyId);
			//RequestDispatcher to companies.jsp
		}
	}

}
