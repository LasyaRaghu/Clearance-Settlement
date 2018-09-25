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
 * Servlet implementation class UpdateTransactionServlet
 */
@WebServlet("/updatetransaction")
public class UpdateTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int transId=  (int) request.getAttribute("transId");
		String buyerID=  (String) request.getAttribute("buyerID");
		String Security =  (String) request.getAttribute("Security");
		String SellerID =   (String) request.getAttribute("SellerID");
		int Quantity=  (int) request.getAttribute("Quantity");
		float price= (float) request.getAttribute("price");
		Transaction transaction = new Transaction( transId, buyerID, Security, SellerID, Quantity, price);
		TransactionOperations dao= new TransactionOperationsImpl();
		dao.updateTransaction(transaction);
		List<Transaction> list = dao.findAll();
		request.setAttribute("transactions", list);
		RequestDispatcher dispatcher1 = request.getRequestDispatcher("show_jstl.jsp");
		dispatcher1.forward(request, response);
	}

}
