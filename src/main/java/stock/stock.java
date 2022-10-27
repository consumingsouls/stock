package stock;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;

public final class Stock {
    private static JSONParser pars = new JSONParser();
    private static JSONArray jsonArray;

    public String companyName;
    private String stockRating;
    private int price;
    public String getCompanyName() {
        return companyName;
    }

    public String getStockRating() {
        return stockRating;
    }

    public int getPrice() {
        return price;
    }

   //public int getNumberOfShares() {
     //   return numberOfShares;
   // }

    public Stock(String companyName, String stockRating, int price, int numberOfShares) {
        this.companyName = companyName;
        this.stockRating = stockRating;
        this.price = price;
       // this.numberOfShares = numberOfShares;
    }

    public static void main(String[] args) {
        InitializeList();
        for (int i = 1; i <= jsonArray.size(); i++) {
            Account account =getAccount(i);
            WritetoHtml(account);
        }
    }
    public static void InitializeList() {
        try {
            jsonArray = (JSONArray) pars.parse(new FileReader("stock_transations-3.by.account.holder.json"));
           } catch (FileNotFoundException e) {

                e.printStackTrace();
    
                System.out.println("The file could not be found");
    
            } catch (IOException e) {
    
                e.printStackTrace();
    
                System.out.println("Error reading or writing from file");
    
            } catch (ParseException e) {
    
                e.printStackTrace();
    
                System.out.println("The file could not be parsed correctly");
    
            }
    }     
        private static String GetJSONValue(int account_Number, String key) {
            String Value = "";
            if (account_Number< 1 || account_Number > jsonArray.size()) {
                System.out.println("account number does not exist");
                return Value;
            }
            Value =( (JSONObject) jsonArray.get(account_Number -1)).get(key).toString();
            return Value;
        }
        public static Account getAccount(int account_Number) {
            if (account_Number< 1 || account_Number > jsonArray.size()) {
                System.out.println("account number does not exist");
                return null;
            }
            int accountNum = Integer.parseInt(GetJSONValue((account_Number), "account_number"));
            String first_name = GetJSONValue(account_Number, "first_name");
            String ssn = GetJSONValue(account_Number, "ssn");
            String last_name = GetJSONValue(account_Number, "last_name");
            String email = GetJSONValue(account_Number, "email");
            String Phone_Number = GetJSONValue(account_Number, "phone");
            double balance = Double.parseDouble(GetJSONValue(account_Number, "beginning_balance").substring(1));
            balance = (double)((int)(balance * 100.0 + 0.5) / 100.0);

            Account account = new Account(accountNum, first_name, last_name, ssn, email, Phone_Number, balance);

            JSONArray transActionList =(JSONArray) ( (JSONObject) jsonArray.get(account_Number -1)).get("stock_trades");
            for (Object transaction : transActionList) {
                String type = ((JSONObject)transaction).get("type").toString();
                String symbol = ((JSONObject)transaction).get("stock_symbol").toString();
                double sharePrice = Double.parseDouble(((JSONObject)transaction).get("price_per_share").toString().substring(1));
                int shareCount = Integer.parseInt(((JSONObject)transaction).get("count_shares").toString());
                Transaction transaction2 = new Transaction(type, symbol, shareCount, sharePrice);
                account.AddTransaction(transaction2);
            }
            return account;
        }
        public static void WritetoHtml(Account account) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
            String date = dtf.format(LocalDateTime.now());
            NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
            
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("accountStatements/"+account.GetHtmlFileName()));

                //Write title, date, account info
                bw.write("<html>");
                bw.write("<head><title>"+account.GetFullName()+"</title><link rel='stylesheet' href='styles.css'></head>");
                bw.write("<body>");
                bw.write("<h4>"+date+"</h4>");
                bw.write("<h3>Account #:&emsp;"+account.GetAccountNumber()+"</h3>");
                bw.write("<h3>"+account.GetFullName()+"&emsp;&emsp;"+account.GetSsn()+"&emsp;&emsp;"+account.GetEmail()+"&emsp;&emsp;"+account.GetPhoneNum()+"</h3>");
                
                //Write transactions
                bw.write("<table>");
                bw.write("<tr><th>Type</th><th>Symbol</th><th>Price</th><th>Shares</th><th>Total</th></tr>");
                for (Transaction transaction : account.GetTransactions()) {
                    bw.write("<tr>");
                    bw.write("<td>"+transaction.getType()+"</td>");
                    bw.write("<td>"+transaction.getStockSymbol()+"</td>");
                    bw.write("<td>"+dollarFormat.format(transaction.getSharePrice())+"</td>");
                    bw.write("<td>"+transaction.getShareCount()+"</td>");
                    bw.write("<td>"+dollarFormat.format(transaction.GetTotal())+"</td>");
                    bw.write("</tr>");
                }
                bw.write("</table>");

                //Write balance and share count
                bw.write("<h3>"+dollarFormat.format(account.GetBalance())+"&emsp;&emsp;&emsp;&emsp;Owned Shares: "+account.GetNumberOfShares()+"</h3>");

                //close html body
                bw.write("</body>");
                bw.write("</html>");
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(".html file could not be found.");
            }

        }

        public static int GetJSONArraySize() {return jsonArray.size();}
        
}