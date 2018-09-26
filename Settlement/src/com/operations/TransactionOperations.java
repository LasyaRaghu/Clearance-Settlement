package com.operations;
import java.util.List;
import com.beans.Transaction;

public interface TransactionOperations {
  boolean Login(String username, String password);
  boolean NettingFunds();
  boolean NettingShares();
  List<Transaction> findAll();
  List<Transaction> findTransactionBySecurity(String securityId);
  List<Transaction> findTransactionByBuyer(String buyerCompanyId);
  List<Transaction> findTransactionBySeller(String sellerCompanyId);
  void addTransaction(Transaction transaction);
 
}
