package stock;

public class Transaction {
    private String type, stockSymbol;
    private int shareCount;
    private double sharePrice;
    public Transaction(String type, String stockSymbol, int shareCount, double sharePrice) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.shareCount = shareCount;
        this.sharePrice = sharePrice;
    }
    public String getType() {
        return type;
    }
    public String getStockSymbol() {
        return stockSymbol;
    }
    public int getShareCount() {
        return shareCount;
    }
    public double getSharePrice() {
        return sharePrice;
    }
    public double GetTotal() {
        return (double) shareCount * sharePrice;
    }
       
    
}
