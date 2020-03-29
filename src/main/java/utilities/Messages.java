package utilities;

import sql.GetStocks;

import java.util.Map;

public class Messages {
    public static String welcome = "Welcome to the Historical Stock Market Sim App!";
    public static String enterFirstName = "Please enter your first name.";
    public static String enterLastName = "Please enter your last name.";
    public static String startingGuide = "Lets get you started!\nWe have created a basic portfolio for with a buying power of $2500.\nUse these funds to purchase stocks from the list below.";
    public static String chooseNum = "Choose a number to make a selection:";
    public static String notEnough = "Sorry, you don't have enough funds to make that purchase";






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

    public static void printStockPrices(String month,String[] stocks){
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        String titleDashes = "-------";
        String titleNum = "#  |";
        String titleTicker = " tckr";
        String titlePrice = "price";
        String titleIntro = "Stock Prices";
        String titleIntroDashes = "******";
        String dividerLine = "\n--------------------------";
        builder.append(String.format("%s %s %s",titleIntroDashes,titleIntro,titleIntroDashes));
        builder.append(String.format("\n%s %s %s",titleDashes,month,titleDashes));
        builder.append(String.format("\n| %-4s %-6s | %8s |",titleNum,titleTicker,titlePrice));
        builder.append(dividerLine);
        for (Map.Entry<String, Double> s :GetStocks.getAllStocksPrices(month,stocks).entrySet()) {
            builder.append(String.format("\n| %-4d %-6s = %8.2f %s",counter,s.getKey(),s.getValue(),"|"));
            counter++;
        }
        builder.append(dividerLine);
        System.out.println(builder.toString());
    }



}
