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
        double Total = (double) shareCount * sharePrice;
        Total = (double)((int)(Total * 100.0 + 0.5) / 100.0);
        return Total;
    }
       
    @Override
    public String toString() {
        return type + "\t" + stockSymbol + "\t$" + sharePrice + "\t" + shareCount + "\t$" + GetTotal();
    }
}
