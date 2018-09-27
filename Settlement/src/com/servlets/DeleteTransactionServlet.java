package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.beans.Transaction;

import com.operations.TransactionOperations;
import com.operations.impl.TransactionOperationsImpl;

/**
 * Servlet implementation class DeleteTransactionServlet
 */
@WebServlet("/deletetransaction")
public class DeleteTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int transId= Integer.parseInt(request.getParameter("transId"));
		TransactionOperations dao=new TransactionOperationsImpl();
		dao.deleteTransaction(transId);
		List<Transaction> list = dao.findAll();
		request.setAttribute("transactions", list);
		RequestDispatcher dispatcher1 = request.getRequestDispatcher("show_jstl.jsp");
		dispatcher1.forward(request, response);
		
	}

}
