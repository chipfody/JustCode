package stocks;

public class TransactionMeta {
    private final String date;
    private final Double open;
    private final Double high;
    private final Double low;
    private final Double close;
    private final Integer volume;

    public TransactionMeta(TransactionMetaBuilder builder){
        this.date = builder.date;
        this.open = builder.open;
        this.high = builder.high;
        this.low = builder.low;
        this.close = builder.close;
        this.volume = builder.volume;
    }

    public String getDate(){
        return this.date;
    }

    public Double getOpen(){
        return this.open;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public Double getClose() {
        return close;
    }

    public Integer getVolume() {
        return volume;
    }

    public static class TransactionMetaBuilder{
        private String date;
        private Double open;
        private Double high;
        private Double low;
        private Double close;
        private Integer volume;

        public static TransactionMetaBuilder newInstance(){
            return new TransactionMetaBuilder();
        }

        public TransactionMetaBuilder setDate(String newDate){
            this.date = newDate;
            return this;
        }

        public TransactionMetaBuilder setOpen(Double newOpen){
            this.open = newOpen;
            return this;
        }

        public TransactionMetaBuilder setHigh(Double newHigh){
            this.high = newHigh;
            return this;
        }

        public TransactionMetaBuilder setLow(Double newLow){
            this.low = newLow;
            return this;
        }

        public TransactionMetaBuilder setClose(Double newClose){
            this.close = newClose;
            return this;
        }

        public TransactionMetaBuilder setVolume(Integer newVolume){
            this.volume = newVolume;
            return this;
        }

        public TransactionMeta build(){
            return new TransactionMeta(this);
        }
    }
}
