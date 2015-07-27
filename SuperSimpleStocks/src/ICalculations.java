public interface ICalculations {

	public double calculateStockPrice(double totalTrade,double totalQnt);
	public double CalcPERatio(double currentPrice, double dividendYield);
	public double CalcDividendYieldForCommon(double dividend,double currentTickPrice);
	public double CalcDividendYieldForPreff(double dividend,double currentTickPrice,double parvalue);
	public double CalGeometricMean(double pricesOfAllStock,double totalNumber);
}
