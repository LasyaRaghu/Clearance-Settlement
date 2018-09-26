package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Transaction;
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
		String name = request.getParameter("uname");
		String password = request.getParameter("psw");
		TransactionOperations top = new TransactionOperationsImpl();
		//writer.println(name+"\t"+password);
		Boolean result=top.Login(name, password);
		
		if(!result)
			writer.println("enter correct credentials");
		else if(name.equals("Admin")&& password.equals("Admin123")) {
			//RequestDispatcher to admin.jsp	
			TransactionOperations top1 = new TransactionOperationsImpl();
			List<Transaction> trans1 = top1.findAll();
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
			dispatcher.forward(request, response);
			
		}
		else {
//			HttpSession session = request.getSession();
//			session.setAttribute("name",companyId);
			
			CompanyOperations cop = new CompanyOperationsImpl();
			String companyId=cop.getCompanyId(name);
			TransactionOperations top1 = new TransactionOperationsImpl();
			List<Transaction> trans1 = top1.findTransactionByBuyer(companyId);
			List<Transaction> trans2 = top1.findTransactionBySeller(companyId);
			
			request.setAttribute("table1", trans1);
			request.setAttribute("table2", trans2);
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			RequestDispatcher dispatcher = request.getRequestDispatcher("assam.jsp");
			dispatcher.forward(request, response);
		}
	}

}
