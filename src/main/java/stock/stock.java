package stock;
public class stock {
public String companyName;
private String stockRating;
private int price;
private int numberOfShares;
private String account_Number;
private String first_name;
private String last_name;
private 

public String getCompanyName() {
    return companyName;
}

public String getStockRating() {
    return stockRating;
}

public int getPrice() {
    return price;
}

public int getNumberOfShares() {
    return numberOfShares;
}

public Stock(String companyName, String stockRating, int price, int numberOfShares) {
    super();
    this.companyName = companyName;
    this.stockRating = stockRating;
    this.price = price;
    this.numberOfShares = numberOfShares;
}
}