import java.util.ArrayList;


public interface ITrade {

	public void addTrade(String stockName, double qnty, int ind,double tradePrice,ArrayList<Transaction> tradeList);
	public void printTradeList(ArrayList<Transaction> tradeList);
	public void viewMyTrasactionsForStock(String stockName,ArrayList<Transaction> tradeList);
}
