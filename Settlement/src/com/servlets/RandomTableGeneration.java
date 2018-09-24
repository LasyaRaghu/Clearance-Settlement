package com.servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Transaction;
import com.operations.TransactionOperations;
import com.operations.impl.TransactionOperationsImpl;

/**
 * Servlet implementation class RandomTableGeneration
 */
@WebServlet("/randomtable")
public class RandomTableGeneration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RandomTableGeneration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int nooftransaction=Integer.parseInt(request.getParameter("nooftransaction"));;
		int i=1;
		  while(i<= nooftransaction)
		  {
			  int TradeId=i;
		   String[] sec= {"Apple","Walmart","LinkedIn","GE","Facebook"};
		   Random rnum= new Random();
		   int index= rnum.nextInt(sec.length);
		   String ransec= sec[index];
		   System.out.println(ransec);
		   int ranquantity= (rnum.nextInt(51)+1)*1000;
		   System.out.println(ranquantity);
		   double ranprice= rnum.nextDouble()*200 +10;
		   DecimalFormat numberformat= new DecimalFormat("#.00");
		   System.out.println(numberformat.format(ranprice));
		   String[] clmembers= {"JP Morgan","Goldman","citi","Deutsche Bank"};
		   int index1= rnum.nextInt(clmembers.length);
		   String buyclmember= clmembers[index1];
		   System.out.println("Buyer clearing member: "+ buyclmember);
		   int index2= rnum.nextInt(clmembers.length);
		   String sellclmember= clmembers[index2];
		   System.out.println("Selling clearing member: "+ sellclmember);
		   if(index1==index2)
			   continue;
		   else {
			   //insert code here
			   Transaction transaction= new com.beans.Transaction(String.valueOf(i),buyclmember,ransec, sellclmember, ranquantity, (float) ranprice);
				TransactionOperations dao= new TransactionOperationsImpl();
				 dao.addTransaction(transaction);
			   i++;
		   }
	}

}
}
