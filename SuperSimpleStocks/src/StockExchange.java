import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class StockExchange{

	Scanner in = new Scanner(System.in);
	int selectedStock;
	TradeImpl tradeImpl = new TradeImpl();
	CalculationsImpl calcImpl = new CalculationsImpl();
	private HashMap<String, ArrayList<Double>> tickerPriceList = new HashMap<String, ArrayList<Double>>(); 
	private ArrayList<Transaction> tradeList = new ArrayList<Transaction>();
	private HashMap<String, Stock> stockList= new HashMap<String, Stock>();
	
	public HashMap<String, ArrayList<Double>> getTickerPriceList() {
		return tickerPriceList;
	}
	
	
	/** Initialize the list with predefined value and show List of stocks to User for trading **/
	public void initializeStockExchange()
	{
		populateLists();
		viewStockList();
		
	}
	
	public void populateLists()
	{
		/** Populate the stock List with predefined values **/
		populateStocklist();
		
		/** Populate sample Transactions**/
		populateTrasactionlist();
		
		/**Populate Ticker Prices - Old and New (both are assumed as par values). **/
		populateTickerlist();

	}
	
	public void populateStocklist() {
		stockList.put(Constants.NAME_TEA, new Stock(Constants.NAME_TEA, Constants.TYPE_COMMON, 0, 0.0, 100));
		stockList.put(Constants.NAME_POP, new Stock(Constants.NAME_POP, Constants.TYPE_COMMON, 8, 0.0, 100));
		stockList.put(Constants.NAME_ALE, new Stock(Constants.NAME_ALE, Constants.TYPE_COMMON, 23, 0.0, 60));
		stockList.put(Constants.NAME_GIN, new Stock(Constants.NAME_GIN, Constants.TYPE_PREFERRED, 8, 0.02, 100));
		stockList.put(Constants.NAME_JOE, new Stock(Constants.NAME_JOE, Constants.TYPE_COMMON, 13, 0.0, 250));
	}
	
	public void populateTickerlist() {
		
		/** Ticker List Has 2 values old and new which are both par values initially.**/
		
		tickerPriceList.put(Constants.NAME_TEA, populateOldNewStockValue(Constants.NAME_TEA));
		tickerPriceList.put(Constants.NAME_POP, populateOldNewStockValue(Constants.NAME_POP));
		tickerPriceList.put(Constants.NAME_ALE, populateOldNewStockValue(Constants.NAME_ALE));
		tickerPriceList.put(Constants.NAME_GIN, populateOldNewStockValue(Constants.NAME_GIN));
		tickerPriceList.put(Constants.NAME_JOE, populateOldNewStockValue(Constants.NAME_JOE));
	}
	
	public ArrayList<Double> populateOldNewStockValue(String stockName)
	{
		ArrayList<Double> arr1 = new ArrayList<Double>();
		arr1.add(stockList.get(stockName).getParValue());
		arr1.add(stockList.get(stockName).getParValue());
		return arr1;
	}
	
	public void populateTrasactionlist() {
		try
		{
			tradeList.add(new Transaction(Constants.NAME_TEA,3,Constants.BUY_IND,100,new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a").parse("Friday, Jul 23, 2015 07:10:56 PM")));
			tradeList.add(new Transaction(Constants.NAME_TEA,4,Constants.BUY_IND,100,new Date()));
			tradeList.add(new Transaction(Constants.NAME_TEA,2,Constants.SELL_IND,250,new Date()));
			tradeList.add(new Transaction(Constants.NAME_POP,4,Constants.BUY_IND,80,new Date()));
			tradeList.add(new Transaction(Constants.NAME_POP,1,Constants.SELL_IND,100,new Date()));
			tradeList.add(new Transaction(Constants.NAME_ALE,10,Constants.SELL_IND,100,new Date()));
			tradeList.add(new Transaction(Constants.NAME_ALE,8,Constants.BUY_IND,150,new Date()));
			tradeList.add(new Transaction(Constants.NAME_GIN,3,Constants.BUY_IND,350,new Date()));
			tradeList.add(new Transaction(Constants.NAME_GIN,1,Constants.BUY_IND,250,new Date()));
			tradeList.add(new Transaction(Constants.NAME_JOE,1,Constants.BUY_IND,20,new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a").parse("Friday, Jul 23, 2015 07:10:56 PM")));
			tradeList.add(new Transaction(Constants.NAME_JOE,2,Constants.SELL_IND,50,new Date()));
			tradeList.add(new Transaction(Constants.NAME_JOE,1,Constants.SELL_IND,50,new Date()));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/** Show predefined stock sample data to user to select for trading **/
	
	public void viewStockList() {
		int i = 0;
		/** Print in a tabular format for user to select the stock **/

		System.out.println("/******* Following is the stock data *******/");
		System.out.format("%n");
		System.out.format("%15s%15s%20s%20s%15s\n", "Stock Symbol", "Type",
				"Last Dividend", "Fixed Dividend", "Par Value");
		System.out.format("%n");
		Object[][] table = new Object[5][];

		for (Stock value : stockList.values()) {
			table[i] = new Object[] { value.getStockSymbol(), value.getType(),
					value.getLastDividend(), value.getFixedDividend(),
					value.getParValue() };
			i++;
		}

		for (final Object[] row : table) {
			System.out.format("%15s%15s%15d%20f%18f\n", row);
		}
		System.out.format("%n");

	}
	
	public void selectStock()
	{
		double userStockOption=0;
		boolean value=true;
		do{
			System.out.println("Please select a stock no from the list");
			System.out.println("1)TEA\n2)POP\n3)ALE\n4)GIN\n5)JOE\n");
			userStockOption = in.nextDouble();
			if(userStockOption<1 || userStockOption>5)
			{
				System.out.println("Invalid number entered. Please select again");
			}
			else
			{
				selectedStock=new Double(userStockOption).intValue();
				value=false;
			}
		}while(value==true);
	}
	
	public void tradeOrCalc() {
			boolean value=true;
			String stockName=getStockNameFromId(selectedStock);
			do
			{
				System.out.println("\n/******* Which operation do you want to perform for this stock*******/\n");
				System.out.println("1)Calculate Dividend yield\n2)Calculate the P/E ratio\n3)Record Trade\n4)Calculate Stock Price based on trades recorded in past 15 minutes\n5)Exit to main menu\n");
				double operation = in.nextDouble();
				if(operation==1)
				{
					 double dividendYield=dividendYieldDecision(stockName);
					 System.out.println("current ticker price: "+getCurrentTickerPriceOfStock(stockName));
					 System.out.println("Dividend yield for common type= Last Dividend/ Ticker Price");
					 System.out.println("Dividend yield for Preferred type= Fixed Dividend * Par Value/ Ticker Price");
					 System.out.println("The Dividend yield for this stock is: "+Double.parseDouble(new DecimalFormat("##.##").format(dividendYield))+"%");
				}
				else if(operation==2)
				{
					double dividendYield=dividendYieldDecision(stockName);
					System.out.println("current ticker price: "+getCurrentTickerPriceOfStock(stockName));
					System.out.println("The Dividend yield for this stock is: "+Double.parseDouble(new DecimalFormat("##.##").format(dividendYield))+"%");
					System.out.println("P/E ratio = Ticker Price/ Dividend");
					if(dividendYield!=0)
					{
						System.out.println("The P/E ratio for this stock is: "+Double.parseDouble(new DecimalFormat("##.##").format(calcImpl.CalcPERatio(getCurrentTickerPriceOfStock(stockName),dividendYield))));
					}
					else
					{
						System.out.println("Since dividend yield is 0, P/E ration cannot be calculated");
					}
				}
				else if(operation==3)
				{
					recordTransaction(stockName);
				}
				else if(operation==4)
				{
					tradeImpl.viewMyTrasactionsForLast15Min(stockName,tradeList);
					System.out.println("\nthe stock Price based on transactions in last 15 min is: "+Double.parseDouble(new DecimalFormat("##.##").format(getStockPriceOfStock(tradeImpl.getTransactionListForLast15Min(stockName,tradeList)))));
				}
				else if(operation==5)
				{
					value=false;
				}
				else
				{
					System.out.println("Invalid number entered. Please select again");
				}
			}while(value==true);
	}

	public double dividendYieldDecision(String stockName)
	{
		double dividendYeild=0.0;
		if(stockList.get(stockName).getType() == Constants.TYPE_COMMON)
		{
			dividendYeild=calcImpl.CalcDividendYieldForCommon(stockList.get(stockName).getLastDividend(),getCurrentTickerPriceOfStock(stockName));
		}
		else if(stockList.get(stockName).getType() == Constants.TYPE_PREFERRED)
		{
			dividendYeild=calcImpl.CalcDividendYieldForPreff(stockList.get(stockName).getFixedDividend(),getCurrentTickerPriceOfStock(stockName),stockList.get(stockName).getParValue());
		}
		return dividendYeild;
	}
	
	/** Buy and sell is not been considered as Bid and Ask **/
	public void recordTransaction(String stockName) {
		
		boolean value=true;
		tradeImpl.viewMyTrasactionsForStock(stockName,tradeList);
		do
		{
			boolean correctQuant = true;
			boolean correctPrice = true;
			System.out.format("%n");
			System.out.println("----- Buy or Sell?.-----\n");
			System.out.println("1)Buy\n2)Sell\n3)back to main operations\n");
			int operation = in.nextInt();
			
			/** It is assumed that quantity cannot be 0 or less or more than 1000 and price should not be 0 or negative number**/
			
			if(operation==1)
			{
				double price;
				double Qnt;
				do
				{
					System.out.println("How many stocks do you want to buy");
					Qnt = in.nextDouble();
					if(Qnt<=0 || Qnt>1000)
					{
						System.out.println("Invalid number entered. Qnty should be more than 0 and less than 1000");
						correctQuant=true;
					}
					else
					{
						correctQuant=false;
					}
				}while(correctQuant == true);
				
				do
				{
					System.out.println("\nWhat is the trade price to buy");
					price = in.nextDouble();
					if(price<=0 || price>1000)
					{
						System.out.println("Invalid number entered. Price should be more than 0 and less than 1000");
						correctPrice=true;
					}
					else
					{
						correctPrice=false;
					}
				}while(correctPrice == true);
				
				tradeImpl.addTrade(stockName, Qnt,1,price,tradeList);
				tradeImpl.viewMyTrasactionsForStock(stockName,tradeList);
				value=false;
			}
			else if(operation==2)
			{
				double price;
				double Qnt;
				do
				{
					System.out.println("How many stocks do you want to sell");
					Qnt = in.nextDouble();
					if(Qnt<=0 || Qnt>1000)
					{
						System.out.println("Invalid number entered. Qnty should be more than 0 and less than 1000");
						correctQuant=true;
					}
					else
					{
						correctQuant=false;
					}
					
				}while(correctQuant == true);
				
				do
				{
					System.out.println("\nWhat is the trade price to sell");
					price = in.nextDouble();
					if(price<=0 || price>1000)
					{
						System.out.println("Invalid number entered. Price should be more than 0 and less than 1000");
						correctPrice=true;
					}
					else
					{
						correctPrice=false;
					}
					
				}while(correctPrice == true);
				tradeImpl.addTrade(stockName, Qnt,2,price,tradeList);
				tradeImpl.viewMyTrasactionsForStock(stockName,tradeList);
				value=false;
			}
			else if(operation==3)
			{
				value=false;
			}
			else
			{
				System.out.println("Invalid number entered. Please select again.");
				
			}
		}while(value==true);
	}
	
	/** get the stock Name from the user entered number **/
	
	public String getStockNameFromId(int stockId)
	{
		String stockName = "";
		switch (stockId) {
		case 1:
			stockName = "TEA";
			break;
		case 2:
			stockName = "POP";
			break;
		case 3:
			stockName = "ALE";
			break;
		case 4:
			stockName = "GIN";
			break;
		case 5:
			stockName = "JOE";
			break;
		}
		
		return stockName;
	}
	
	public double getStockPriceOfStock(ArrayList<Transaction> tradeListOfSpecificStock)
	{
		double totalTrade=0.0;
		double totalQnt=0.0;
		for(Transaction tr: tradeListOfSpecificStock)
		{
			totalTrade=totalTrade+ (tr.getPrice() * tr.getQuantityOfShares());
			totalQnt= totalQnt + tr.getQuantityOfShares();
		}
		if(totalQnt!=0.0)
		{
			return calcImpl.calculateStockPrice(totalTrade, totalQnt);
		}
		else
		{
			return calcImpl.calculateStockPrice(totalTrade, 1);	
		}
		
	}
	
	public double getCurrentTickerPriceOfStock(String stockName)
	{
		ArrayList<Double> arr1= new ArrayList<Double>();
		arr1=tickerPriceList.get(stockName);
		if(!arr1.isEmpty())
		{
				return arr1.get(arr1.size()-1);
		}
		else
			return 1;
	}
	
	/** It is assumed that the prices considered for geometric mean are the stock prices of the stocks. **/
	public double getGeometricMeanCal()
	{
		double pricesOfAllStock=1;
		int totalNumber=0;
		double numerator=0;
		for(String str: stockList.keySet())
		{
			ArrayList<Transaction> arr= new ArrayList<Transaction>();
			double price=1;
			for (Transaction s : tradeList) 
			{ 
				if(str.equalsIgnoreCase(s.getStockSymbol()))
				{
					arr.add(s);
					
				}
			}
			price= getStockPriceOfStock(arr);	
			if(price!=0)
			{
			pricesOfAllStock= pricesOfAllStock * price;
			}
			totalNumber++;
		}
		System.out.println("\nMultiplication of Prices of all stock is :"+new BigDecimal(pricesOfAllStock).longValue());
		System.out.println("total number of stocks traded :"+totalNumber);
		if(totalNumber>=0)
		{
			numerator=1/(double)(totalNumber);
			return calcImpl.CalGeometricMean(pricesOfAllStock, numerator);
		}
		else
		{
			return 0.0;
		}
	}
	
}
