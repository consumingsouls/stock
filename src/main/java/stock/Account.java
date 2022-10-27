package stock;

import java.util.ArrayList;

public class Account {
    private int numberOfShares;
    private int account_Number;
    private String first_name;
    private String last_name;
    private String ssn, email, phoneNum;
    private double balance;

    private ArrayList<Transaction> transactions;
    public Account(int account_Number, String first_name, String last_name, String ssn, String email, String phoneNum, double balance) {

        this.account_Number = account_Number;
        this.balance = balance;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.ssn = ssn;
        this.phoneNum = phoneNum;
        this.balance = balance;
        this.numberOfShares = 0;
        transactions = new ArrayList<>();
    }
    public int GetAccountNumber() {
        return account_Number;
    }
    public int GetNumberOfShares() {
        return numberOfShares;
    }
    public String GetEmail() {
        return email;
    }
    public String GetSsn() {
        return ssn;
    }
    public double GetBalance() {
        return balance;
    }
    public String GetPhoneNum() {
        return phoneNum;
    }
    public String GetLast_Name() {
        return last_name;
    }
    public String GetFirst_Name() {
        return first_name;
    }
    public String GetFullName() {
        return first_name +" " + last_name;
    
    }
    public ArrayList<Transaction> GetTransactions() {
        return transactions;
    }
    public int GetTransactionsCount() {
        return transactions.size();

    }
    public void AddTransaction(Transaction transaction) {
        transactions.add(transaction);
        switch(transaction.getType()){
            case "Buy":
            balance-= transaction.GetTotal();
            numberOfShares += transaction.getShareCount();
            break;
            
            
            
            case "Sell":
             balance += transaction.GetTotal();
            numberOfShares -= transaction.getShareCount();
            break;

        }
        balance = (double)((int)(balance * 100.0 + 0.5) / 100.0);
    }
    public String GetHtmlFileName() {

        return GetAccountNumber() + "-" + GetFirst_Name() + "_" + GetLast_Name() + ".html";

    }

    @Override

    public String toString() {
        String result = "";
        result += "Acount #: " + account_Number + "\n";
        result += GetFullName() + "\t" + ssn + "\t" + email + "\t" + phoneNum + "\n";
        for (Transaction t : transactions) {
            result += t + "\n";
        }
        result += "Balance: $" + balance + "\tTotal Shares: " + numberOfShares;
        return result;

    }

}
