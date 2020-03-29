package utilities;

public class Messages {
    public static String welcome = "Welcome to the Historical Stock Market Sim App!";
    public static String enterFirstName = "Please enter your first name.";
    public static String enterLastName = "Please enter your last name.";
    public static String enterDOB = "Please enter your Date of Birth.";
    public static String enterYear = "Please enter the year.";
    public static String enterMonth = "Please enter the month.";
    public static String enterDay = "Please enter the day.";
    public static String startingGuide = "Lets get you started!\nWe have created a basic portfolio for with a buying power of $2500.\nUse these funds to purchase stocks from the list below.";
    public static String chooseNum = "Choose a number to make a selection:";
    public static String notEnough = "Sorry, you don't have enough funds to make that purchase";
    public static String newOrContinue = "1. Start new game\n2. Continue";
    public static String rememberID = "Please remember this ID continue your progress next time!\nYour id is: ";
    public static String enterID = "Please enter your id.";
    public static String idNotFound = "ID not found! Try again or enter 3 to return to login.";








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
