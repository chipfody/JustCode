package stocks;

public class Transaction {
    private final String dateOfTrade;
    private final Integer numOfShare;
    private final Double costPerShare;

    public Transaction(TransactionMeta metaData, Integer numOfShare){
        dateOfTrade = metaData.getDate();
        this.numOfShare = numOfShare;
        costPerShare = metaData.getClose();
    }


}
