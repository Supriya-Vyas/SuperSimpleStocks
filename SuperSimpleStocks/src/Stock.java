import java.util.PriorityQueue;


public class Stock {
	private String stockSymbol;
	private String type;
	private int lastDividend;
	private double fixedDividend;
	private double parValue;
	
	/** constructor **/
	Stock(String stockName, String stockType,int dividend, double fDiv,double value)
	{
		this.stockSymbol=stockName;
		this.type=stockType;
		this.lastDividend= dividend;
		this.fixedDividend= fDiv;
		this.parValue= value;
	}
	
	/** getter Setters method **/
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(int lastDividend) {
		this.lastDividend = lastDividend;
	}
	public double getFixedDividend() {
		return fixedDividend;
	}
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	public double getParValue() {
		return parValue;
	}
	public void setParValue(int parvAlue) {
		this.parValue = parvAlue;
	}
	
	/** eaquals method **/
	public boolean equals(Object obj) {
        if ((obj instanceof Stock) && (((Stock)obj).getStockSymbol()== this.stockSymbol))
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }
	
	/** Hashcode method **/
	public int hashCode(){
	    return (int) stockSymbol.hashCode();
	  }
	

}
