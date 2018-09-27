package com.io;

import java.util.Scanner;

import com.operations.TransactionOperations;
import com.operations.impl.TransactionOperationsImpl;

public class DeleteTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the id of the transaction you want to delete");
		int deleteid= sc.nextInt();
		TransactionOperations dao = new TransactionOperationsImpl();
		dao.deleteTransaction(deleteid);

	}

}
