package com.io;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

import com.beans.Transaction;
import com.operations.TransactionOperations;
import com.operations.impl.TransactionOperationsImpl;

public class RandomGeneration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int nooftransaction=Integer.parseInt(request.getParameter("nooftransaction"));;
		Scanner sc= new Scanner(System.in);
		int nooftransaction= sc.nextInt();
		int i=1;
		  while(i<= nooftransaction)
		  {
			  int TradeId=i;
		   String[] sec= {"S01","S02","S03","S04","S05"};
		   Random rnum= new Random();
		   int index= rnum.nextInt(sec.length);
		   String ransec= sec[index];
		 //  System.out.println(ransec);
		   int ranquantity= (rnum.nextInt(51)+1)*1000;
		   //System.out.println(ranquantity);
		   double ranprice= rnum.nextDouble()*200 +10;
		   DecimalFormat numberformat= new DecimalFormat("#.00");
		   //System.out.println(numberformat.format(ranprice));
		   String[] clmembers= {"C01","C02","C03","C04"};
		   int index1= rnum.nextInt(clmembers.length);
		   String buyclmember= clmembers[index1];
		   //System.out.println("Buyer clearing member: "+ buyclmember);
		   int index2= rnum.nextInt(clmembers.length);
		   String sellclmember= clmembers[index2];
		   //System.out.println("Selling clearing member: "+ sellclmember);
		   //System.out.println("abhi");
		   if(index1==index2)
			   continue;
		   else {
			   //insert code here
			   Transaction transaction= new com.beans.Transaction(i,buyclmember,ransec, sellclmember, ranquantity, (float) ranprice);
				TransactionOperations dao= new TransactionOperationsImpl();
				 dao.addTransaction(transaction);
			   i++;
		   }
	}
	}

}
