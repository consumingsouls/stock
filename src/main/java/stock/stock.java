package stock;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;

public class Stock {
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
        
    }
}