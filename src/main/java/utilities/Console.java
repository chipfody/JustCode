package utilities;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Console {

    private final Scanner input;
    private final PrintStream output;

    public Console(InputStream in, PrintStream out) {
        this.input = new Scanner(in);
        this.output = out;
    }

    public void print(String val, Object... args) {
        output.format(val, args);
    }

    public void println(String val, Object... vals) {
        print(val + "\n", vals);
    }

    public void printlnS(String val, Object... vals) {
        System.out.println(val);
    }


    public String getStringInput(String prompt, Object... args) {
        println(prompt, args);
        return input.nextLine();
    }

    public String getStringInputWithoutln(String prompt, Object... args) {
        print(prompt, args);
        return input.nextLine();
    }

    public Double getDoubleInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            Double doubleInput = Double.parseDouble(stringInput);
            return doubleInput;
        } catch (NumberFormatException nfe) { // TODO - Eliminate recursive nature
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting a numeric value!");
            return getDoubleInput(prompt, args);
        }
    }

    public Long getLongInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            Long longInput = Long.parseLong(stringInput);
            return longInput;
        } catch (NumberFormatException nfe) { // TODO - Eliminate recursive nature
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting an integer value!");
            return getLongInput(prompt, args);
        }
    }

    public Integer getIntegerInput(String prompt, Object... args) {
        return getLongInput(prompt, args).intValue();
    }

    public Integer getIntegerInputWithoutln(String prompt, Object... args) {
        while(true) {
            print(prompt, args);
            String stringInput = input.nextLine();
            try {
                return Integer.parseInt(stringInput);
            } catch (NumberFormatException nfe) {
                println("Invalid Input! Try again.");
            }
        }
    }

    public void pressEnterToCount(){
        getStringInputWithoutln("press enter to continue");
    }

    public Integer getNewGameOrContinueDecision(){
        return getIntegerInput("1. Start new game\n2. Continue");
    }

    public String getName(String firstLast){
        return getStringInput("Please enter your " + firstLast + " name.");
    }

    //PRINTS
    public void printOpening(){
        System.out.println("Welcome to the Historical Stock Market Sim App!");
    }

    public void printWelcome(String firstName, String lastName, int id){
        System.out.printf("Welcome %1s %2s!" +
                "\nYour id is: %d." +
                "\nPlease remember this ID continue your progress next time!",
                firstName, lastName, id);
    }

    public void printStartGuide(){
        System.out.println("Lets get you started!" +
                "\nWe have created a basic portfolio for with a buying power of $2500." +
                "\nUse these funds to purchase stocks from the list below.");
    }

    public void askDOB(){
        System.out.println("Please enter your Date of Birth.");
    }

    public Integer askDayOfBirth(){
        return getIntegerInput("Please enter the day.");
    }

    public Integer askMonthOfBirth(){
        return getIntegerInput("Please enter the month.[1-12]");
    }

    public Integer askYearOfBirth(){
        return getIntegerInput("Please enter the year. [Must be younger than 80 to play]");
    }

    public Integer askForID(){
        return getIntegerInput("Please enter your id: ");
    }

    //ERRORS
    public void newContinueError(){
        System.out.println("Please enter 1 or 2.");
    }

    public void idNotFoundError(){
        System.out.println("ID not found! Try again or enter 3 to return to login.");
    }

    public void wrongDayError(){
        System.out.println("Please enter a day according to your birth month.");
    }

    public void wrongMonthError(){
        System.out.println("Please enter a month from 1 - 12.");
    }

    public void wrongYearError(){
        System.out.println("Please enter a valid year.");
    }

    public static void printSQLError(String crud){
        System.out.println("SQL ERROR: " + crud);
    }

    public static void printTransactionError(String date){
        System.out.println("Error : " + date + " transaction not found!");
    }
}
