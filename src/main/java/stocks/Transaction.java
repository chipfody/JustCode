package stocks;

import org.decimal4j.util.DoubleRounder;
import sql.SqlController;

public class Transaction {
    private final String dateOfTrade;
    private final Integer numOfShare;
    private final Double costPerShare;
    private final TransactionMeta metaData;

    public Transaction(TransactionMeta metaData, Integer numOfShare){
        this.metaData = metaData;
        dateOfTrade = this.metaData.getDate();
        this.numOfShare = numOfShare;
        costPerShare = this.metaData.getClose();
    }

    public String getDateOfTrade(){
        return dateOfTrade;
    }

    public Integer getNumOfShare(){
        return numOfShare;
    }

    public Double getCostPerShare(){
        return costPerShare;
    }

    public Double calculateTransactionTotal(){
        return DoubleRounder.round(costPerShare * numOfShare,2);
    }

    public static Transaction makeTransaction(String stockSymbol,String month,Integer numOfShares){
        TransactionMeta transactionMeta = SqlController.getStock(stockSymbol,month);
        return new Transaction(transactionMeta,numOfShares);
    }
}
