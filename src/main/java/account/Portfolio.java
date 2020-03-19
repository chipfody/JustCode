package account;

import org.decimal4j.util.DoubleRounder;
import stocks.Stock;
import stocks.Transaction;
import utilities.Console;
import utilities.Messages;

import java.util.ArrayList;


public class Portfolio {
    private ArrayList<Stock> mainPortfolio;
    Console console = new Console(System.in,System.out);
    private Double buyingPower;
    private Double currentPortfolioValue;

    public Portfolio() {
        this.mainPortfolio = new ArrayList<>();
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
                console.println(Messages.notEnough);
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
        return DoubleRounder.round((stock.getCurrentStockPrice() * stock.getTotalNumOfShares()),2);
    }

    public String getPositionOfStock(String stockSymbol){
        StringBuilder builder = new StringBuilder();
        Stock stock = getStockFromPortfolio(stockSymbol);
        builder.append("Name : ")
                .append(stock.name)
                .append("\nSymbol : ")
                .append(stock.symbol)
                .append("\nNumber of Shares : ")
                .append(stock.totalNumOfShares)
                .append("\nCurrent Price : ")
                .append(stock.currentStockPrice)
                .append("\nEquity : ")
                .append(stock.valueOfPosition);
        return builder.toString();
    }

    public String getAllPositionsFromPortfolio(){
        StringBuilder builder = new StringBuilder();
        for (Stock s : mainPortfolio) {
            builder.append("*******************")
                    .append("\n")
                    .append(getPositionOfStock(s.symbol))
                    .append("\n");
        }
        return builder.toString();
    }
}
