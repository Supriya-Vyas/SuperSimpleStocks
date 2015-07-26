import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

/**After every 1 minutes, the ticker price is updated with new random price and old price is replaced with current price**/

public class UpdateTickerPriceList extends TimerTask{
	
	HashMap<String, ArrayList<Double>>  tickerPriceList;
	
	public UpdateTickerPriceList(HashMap<String, ArrayList<Double>>  tickerPriceListFromStockExch)
	{
		tickerPriceList= tickerPriceListFromStockExch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(String stock : tickerPriceList.keySet())
		{
			double rand = 100.0 * Math.random();
			tickerPriceList.get(stock).set(0, tickerPriceList.get(stock).get(tickerPriceList.get(stock).size()-1));
			tickerPriceList.get(stock).set(tickerPriceList.get(stock).size()-1, Double.parseDouble(new DecimalFormat("##.##").format(rand)));
		}	
	}

}
