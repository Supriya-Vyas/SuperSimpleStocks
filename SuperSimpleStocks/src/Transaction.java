import java.util.Date;

public class Transaction {

	private String stockSymbol;
	private double quantityOfShares;
	private String buySellIndicator;
	private double tradePrice;
	private Date timestamp;
	
	Transaction(String stockName, double qnt, String ind, double price,Date time)
	{
		this.stockSymbol=stockName;
		this.quantityOfShares=qnt;
		this.buySellIndicator=ind;
		this.tradePrice=price;
		this.timestamp=time;
	}
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public double getQuantityOfShares() {
		return quantityOfShares;
	}
	public void setQuantityOfShares(double quantityOfShares) {
		this.quantityOfShares = quantityOfShares;
	}
	public String getBuySellIndicator() {
		return buySellIndicator;
	}
	public void setBuySellIndicator(String buySellIndicator) {
		this.buySellIndicator = buySellIndicator;
	}
	public double getPrice() {
		return tradePrice;
	}
	public void setPrice(int price) {
		this.tradePrice = price;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String toString()
	{
	    // return  (System.out.format("%15s%15d%15s%20f%18tc%n", stockSymbol,quantityOfShares,buySellIndicator,price,timestamp,timestamp)).toString();
		return "StockSymbol: "+stockSymbol+" quantityOfShares: "+quantityOfShares+" buySellIndicator: "+buySellIndicator+" price: "+tradePrice+" Date/Time: "+timestamp;
	}
}
