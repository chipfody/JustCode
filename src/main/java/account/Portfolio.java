package account;

import org.decimal4j.util.DoubleRounder;
import stocks.StockEnum;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<StockEnum,Integer> portfolio;
    private Double buyingPower;
    private Double currentPortfolioValue;

    public Portfolio() {
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

    public boolean addStockToBasePortfolio(StockEnum stock, Integer numberOfShares){
        double costOfShares = stock.getOpen() * numberOfShares;
        if (costOfShares < buyingPower){
            if(portfolio.containsKey(stock)){
                portfolio.replace(stock, portfolio.get(stock),portfolio.get(stock) + numberOfShares);
            } else {
                portfolio.put(stock,numberOfShares);
            }
            currentPortfolioValue = currentPortfolioValue + DoubleRounder.round((costOfShares),2);
            buyingPower = DoubleRounder.round((buyingPower - costOfShares),2);
            return true;
        } else {
            return false;
        }
    }

    public Double getEquityOfShare(String stock){
        for (Map.Entry<StockEnum, Integer> s : portfolio.entrySet()) {
            if (stock.equalsIgnoreCase(String.valueOf(s.getKey()))){
                return DoubleRounder.round((s.getKey().getOpen() * s.getValue()),2);
            }
        }
        return null;
    }

    public Integer getNumberOfShares(String stock){
        for(Map.Entry<StockEnum,Integer> s : portfolio.entrySet()){
            if (stock.equalsIgnoreCase(String.valueOf(s.getKey()))){
                return s.getValue();
            }
        }
        return null;
    }

    public Double getPortfolioDiversityOfShare(String stock){
        Double result = 0.00;
        Double equityOfShare = getEquityOfShare(stock);
        if(!stock.equalsIgnoreCase("cash")){
            result = DoubleRounder.round ((equityOfShare/getCurrentPortfolioValue()),2);
        }else{
            result = DoubleRounder.round ((getBuyingPower()/getCurrentPortfolioValue()),2);
        }

       return result;
    }



}
