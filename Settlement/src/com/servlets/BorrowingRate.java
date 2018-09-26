package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Security;
import com.operations.SecurityOperations;
import com.operations.impl.SecurityOperationsImpl;

/**
 * Servlet implementation class BorrowingRate
 */
@WebServlet("/borrow")
public class BorrowingRate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowingRate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SecurityOperations sop = new SecurityOperationsImpl();
		List <Security> sec1= sop.displayBorrowingRate();
		request.setAttribute("table1", sec1);
		RequestDispatcher dispatcher = request.getRequestDispatcher("security.jsp");
		dispatcher.forward(request, response);
		
	}

}
