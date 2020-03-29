package campaign;

import account.User;
import sql.SqlController;
import utilities.Console;
import utilities.Messages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Campaign {

    public Console console;
    public User user;

    public Campaign(){
        console = new Console(System.in,System.out);
    }

    public int generateId(){
        int leftLimit = 1000;
        int rightLimit = 9999;
        int generateLong = 1000 + (int) (Math.random() * (rightLimit - leftLimit));
        if (SqlController.hasId(generateLong)){
            generateId();
        }
        return generateLong;
    }

    public int getYearOfBirth(){
        int year = console.getIntegerInput(Messages.enterYear);
        while(!(year <= LocalDate.now().getYear() && year >= LocalDate.now().getYear()-80)){
            year = console.getIntegerInput(Messages.enterYear);
        }
        return year;
    }

    public int getMonthOfBirth(){
        int month = console.getIntegerInput(Messages.enterMonth);
        while(!(month <= 12 && month >= 1)){
            month = console.getIntegerInput(Messages.enterMonth);
        }
        return month;
    }

    public int getDayOfBirth(int year,int month){
        int day = console.getIntegerInput(Messages.enterDay);
        int maxDay;
        Integer[] month31 = new Integer[]{1, 3, 5, 7, 8, 10, 12};
        ArrayList<Integer> list31 = new ArrayList<>(Arrays.asList(month31));
        Integer[] month30 = new Integer[]{4, 6, 9, 11};
        ArrayList<Integer> list30 = new ArrayList<>(Arrays.asList(month31));

        if(list31.contains(month))
            maxDay = 31;
        else if(list30.contains(month))
            maxDay = 30;
        else if(year % 4 == 0)
            maxDay = 29;
        else maxDay = 28;

        while(!(day <= maxDay && day >= 1)){
            day = console.getIntegerInput(Messages.enterDay);
        }
        return day;
    }

    public void createUser(){
        user = new User();
        user.setFirstName(console.getStringInput(Messages.enterFirstName));
        user.setLastName(console.getStringInput(Messages.enterLastName));
        console.getStringInput(Messages.enterDOB);
        int year = getYearOfBirth();
        int month = getMonthOfBirth();
        int day = getDayOfBirth(year, month);
        user.setDob(LocalDate.of(year, month, day));
        int id = generateId();
        user.setId(id);
        console.println("Welcome " + user.getFirstName());
        console.println(Messages.rememberID + id);
    }

    public void newCampaign(){
        createUser();
        console.println(Messages.startingGuide);
        console.getIntegerInput(Messages.startingStocks());
    }

    public void continueCampaign(){
        int id = console.getIntegerInput(Messages.enterID);
        while (!SqlController.hasId(id)){
            console.println(Messages.idNotFound);
            id = console.getIntegerInput(Messages.enterID);
            if(id == 3)
                newOrContinue();
        }
    }

    public void newOrContinue(){
        int answer = console.getIntegerInput(Messages.newOrContinue);
        while (answer < 1 && answer > 2){
            answer = console.getIntegerInput(Messages.newOrContinue);
        }
        if(answer == (1))
            newCampaign();
        else if(answer == (2))
            continueCampaign();
    }
}
