package utilities;

public class Messages {
    private static Console console = new Console(System.in, System.out);

    public static String chooseNum = "Choose a number to make a selection:";
    public static String notEnough = "Sorry, you don't have enough funds to make that purchase";
    public static String enterID = "Please enter your id.";








    private static String[] stockArr = new String[]{"buffer","Apple","Amazon","BP","Coca-Cola","Costco","CVS","Chevron","Domino's","Exxon Mobil","Google","Home Depot","Johnson & Johnson","JPMorgan Chase & CO.","Microsoft","M&T Bank","Netflix","Target","Ulta Beauty Inc.", "Visa","Waste Management"};

    private static String startingStockListMaker(Integer num, String stock){
        return String.format("%3s %2d.  %-20s %4s","|",num,stock,"|");
    }

    public static String startingStocks(){
        String horizontalBorders = "  *- - - - - - - - - - - - - - - -*";
        StringBuilder builder = new StringBuilder();
        builder.append(horizontalBorders).append("\n");
        for (int i = 1; i < stockArr.length; i++) {
            builder.append(startingStockListMaker(i,stockArr[i]))
                    .append("\n");
        }
        builder.append(horizontalBorders);
        return builder.toString();
    }
}
