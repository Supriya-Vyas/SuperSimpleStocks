import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Timer;


public class StockTradingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StockExchange stck= new StockExchange();
		Scanner in = new Scanner(System.in);
		boolean value=true;
		
		/** Initialize all the predefined values **/
		stck.initializeStockExchange();
		
		/** It Is assumed that ticker price for every stock has 2 values old and current. Initially both the values are Par Values **/
		/**After every 1 minutes, the current ticker price is updated with new random price and old price is replaced with current price**/
		try{
			Timer timer = new Timer();
			timer.schedule(new UpdateTickerPriceList(stck.getTickerPriceList()), 60000, 60000);
			
		}
		catch(IllegalArgumentException iae)
		{
			iae.printStackTrace();
		}
		catch(IllegalStateException ise)
		{
			ise.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		/** Operations **/
		do
		{
			System.out.println("\n/******* Which operation do you want to perform *******/");
			System.out.format("%n");
			System.out.println("1)Perform trading or calculation for a particular stock\n2)Calculate the GBCE All Share Index using the geometric mean of prices for all stocks\n3)Exit\n");
			int operation = in.nextInt();
			if(operation==1) 
			{
				stck.selectStock();
				stck.tradeOrCalc();
			}
			else if(operation==2)
			{
				System.out.println("The geometric mean of prices for all stock is :"+Double.parseDouble(new DecimalFormat("##.##").format(stck.getGeometricMeanCal())));
			}
			else if(operation==3)
			{
				System.exit(1);
			}
			else
			{
				System.out.println("Invalid number entered. Please select again");
			}
		}while(value==true);
		
	}

}
