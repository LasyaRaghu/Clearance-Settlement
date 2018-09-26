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

import com.beans.Company;
import com.beans.Manages;
import com.operations.CompanyOperations;
import com.operations.ManagesOperations;
import com.operations.impl.CompanyOperationsImpl;
import com.operations.impl.ManagesOperationsImpl;

/**
 * Servlet implementation class CompanyShortageServlet
 */
@WebServlet("/CompanyShortage")
public class CompanyShortageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyShortageServlet() {
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
		Company c = cop.findByCompanyId(companyId);
		ManagesOperations mop = new ManagesOperationsImpl();
		List <Manages> m = mop.findByComp(companyId);
		request.setAttribute("table1", c);
		request.setAttribute("table2", m);
		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		RequestDispatcher dispatcher = request.getRequestDispatcher("shortage.jsp");
		dispatcher.forward(request, response);
	}

}
