package account;

import org.decimal4j.util.DoubleRounder;
import stocks.Stock;
import stocks.StockEnum;
import stocks.Transaction;
import utilities.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private ArrayList<Stock> mainPortfolio;
    Console console = new Console(System.in,System.out);

    private Map<StockEnum,Integer> portfolio;
    private Double buyingPower;
    private Double currentPortfolioValue;

    public Portfolio() {
        this.mainPortfolio = new ArrayList<>();
        this.portfolio = new HashMap<>();
        this.buyingPower = 2500.00;
        this.currentPortfolioValue = 0.00;
    }

    public Double getBuyingPower() {
        return buyingPower;
    }

    public Double getCurrentPortfolioValue() {
        return currentPortfolioValue + buyingPower;
    }

    public void addStockToPortfolio(Stock stock){
            mainPortfolio.add(stock);
    }

    public void purchaseStock(String stockSymbol, String month, Integer numOfShares){
            Double costOfPurchase = DoubleRounder.round(Stock.checkStockPrice(stockSymbol,month) * numOfShares,2);
            if(costOfPurchase < buyingPower){
                if(checkToSeeIfOwnStock(stockSymbol)){
                    getStockFromPortfolio(stockSymbol).addTransaction(Transaction.makeTransaction(stockSymbol,month,numOfShares));
                } else {
                    //TODO resolve way to get name for stock per symbol
                    Stock stock = new Stock(stockSymbol,"TempName");
                    stock.addTransaction(Transaction.makeTransaction(stockSymbol,month,numOfShares));
                    addStockToPortfolio(stock);
                }
                buyingPower = buyingPower - costOfPurchase;
            } else{
                //TODO Insert method to print "not enough funds to make purchase"
            }
    }

    public Boolean checkToSeeIfOwnStock(String stockSymbol){
        Boolean result = false;
        for (Stock s : mainPortfolio) {
            if(s.symbol.equalsIgnoreCase(stockSymbol)){
                result = true;
                break;
            }
        }
        return result;
    }

    public Stock getStockFromPortfolio(String stockSymbol){
        for (Stock s: mainPortfolio) {
            if(s.symbol.equalsIgnoreCase(stockSymbol)){
                return s;
            }
        }
        return null;
    }

    public void updateCurrentPortfolioValue(String month){
        Double newCurrentValue = 0.0;
        for (Stock s: mainPortfolio) {
            s.updateCurrentStockPrice(s.symbol,month);
            newCurrentValue += getEquityOfStock(s);
        }
        currentPortfolioValue = newCurrentValue;
    }

    public Double getEquityOfStock(Stock stock){
        return DoubleRounder.round(stock.getCurrentStockPrice() * stock.getTotalNumOfShares(),2);
    }

}
