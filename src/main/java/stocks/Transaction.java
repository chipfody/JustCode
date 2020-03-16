package stocks;

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
        return costPerShare * numOfShare;
    }

//    public TransactionMeta getTransactionPriceFull(){
//        return metaData;
//    }
}
