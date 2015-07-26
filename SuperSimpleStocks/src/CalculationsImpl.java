import java.math.BigDecimal;


public class CalculationsImpl implements ICalculations{
	

	@Override
	public double CalcPERatio(double currentPrice, double dividendYield) {
		// TODO Auto-generated method stub
			return currentPrice/dividendYield;
	}

	@Override
	public double CalGeometricMean(double pricesOfAllStock,double totalNumber) {
		// TODO Auto-generated method stub
		return Math.pow(new BigDecimal(pricesOfAllStock).doubleValue(), totalNumber);
	}

	@Override
	public double CalcDividendYieldForCommon(double dividend,
			double currentTickPrice) {
		// TODO Auto-generated method stub
		return dividend/currentTickPrice;
	}

	@Override
	public double CalcDividendYieldForPreff(double dividend,
			double currentTickPrice, double parvalue) {
		// TODO Auto-generated method stub
		return (dividend*parvalue)/currentTickPrice;
		
	}

	@Override
	public double calculateStockPrice(double totalTrade, double totalQnt) {
		// TODO Auto-generated method stub
		return totalTrade/totalQnt;
	}
	
	
}
