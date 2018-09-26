package com.servlets;

import java.io.IOException;
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
 * Servlet implementation class ConpanyServlet
 */
@WebServlet("/show_company")
public class ConpanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConpanyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("username");
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
