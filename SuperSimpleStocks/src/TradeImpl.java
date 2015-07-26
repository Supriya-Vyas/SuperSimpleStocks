import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class TradeImpl implements ITrade{
	
	/** It is assumed that at a time only 1 stock can be purchased or sold **/
	
	@Override
	public void addTrade(String stockName, double qnty,int ind,double tradePrice,ArrayList<Transaction> tradeList) {
		// TODO Auto-generated method stub
		
		if(ind==1)
		{
			tradeList.add(new Transaction(stockName, qnty, Constants.BUY_IND, tradePrice,new Date() ));
		}
		else
		{
			tradeList.add(new Transaction(stockName, qnty, Constants.SELL_IND, tradePrice,new Date() ));
		}
		
	}

	/** this method shows the complete list of all types of stocks purchased or sold till now **/
	@Override
	public void printTradeList(ArrayList<Transaction> tradeList) {
		// TODO Auto-generated method stub
		
		/** Print in a tabular format for user to view his stock trade**/
		
		System.out.println("\n/******* Following is the Transaction data till now *******/\n");
		for (Transaction s : tradeList) 
		{ 
			System.out.println(s);
		}
		
		
	}

	/** this method shows the complete list of given 1 stock purchased or sold till now **/
	@Override
	public void viewMyTrasactionsForStock(String stockName,ArrayList<Transaction> tradeList)
	{
		System.out.println("\n/******* Following is the Transaction data till now for this particular Stock*******/\n");
		for (Transaction s : tradeList) 
		{ 
			if(stockName.equalsIgnoreCase(s.getStockSymbol()))
				System.out.println(s);
		}
	}
	
	/** this method shows the complete list of given 1 stock purchased or sold within 15 minutes till now **/
	
	public void viewMyTrasactionsForLast15Min(String stockName,ArrayList<Transaction> tradeList)
	{
		System.out.println("\n/******* Following is the Transaction data for the last 15 min for this particular Stock*******/\n");
		ArrayList<Transaction> tradeListFor15Min= getTransactionListForLast15Min(stockName,tradeList);
		if(tradeListFor15Min.isEmpty())
		{
			System.out.println("Sorry No transactions in Last 15 min");
		}
		else
		{
			for (Transaction s : tradeListFor15Min) 
			{ 
				System.out.println(s);
			}
		}
	}
	
	public ArrayList<Transaction> getTransactionListForLast15Min(String stockName,ArrayList<Transaction> tradeList)
	{
		ArrayList<Transaction> tradeListFor15Min=new ArrayList<Transaction>();
		long timestamp1 = new Date().getTime();
		long timestamp2 ;
		
		for (Transaction s : tradeList) 
		{ 
			if(stockName.equalsIgnoreCase(s.getStockSymbol()))
			{
				timestamp2=s.getTimestamp().getTime();
				if (Math.abs(timestamp1 - timestamp2) < TimeUnit.MINUTES.toMillis(15)) {
					tradeListFor15Min.add(s);
				}
				
			}
		}
		return tradeListFor15Min;
	}
	
	
	
	
}
	
