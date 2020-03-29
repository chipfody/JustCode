package campaign;

import account.User;
import sql.SqlController;
import utilities.Console;
import utilities.Messages;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Campaign {

    private Console console = console = new Console(System.in,System.out);;
    private User user;

    public int generateId(){
        int generateLong = 1000 + (int) (Math.random() * (1000 - 9999));
        if (SqlController.hasId(generateLong))
            generateId();
        return generateLong;
    }

    public int getYearOfBirth(){
        int year = console.askYearOfBirth();
        while(!(year <= LocalDate.now().getYear() && year >= LocalDate.now().getYear()-80)){
            console.wrongYearError();
            year = console.askYearOfBirth();
        }
        return year;
    }

    public int getMonthOfBirth(){
        int month = console.askMonthOfBirth();
        while(!(month <= 12 && month >= 1)){
            console.wrongMonthError();
            month = console.askMonthOfBirth();
        }
        return month;
    }

    public int getDayOfBirth(int year,int month){
        int day = console.askDayOfBirth();
        int maxDay;
        Integer[] month31 = new Integer[]{1, 3, 5, 7, 8, 10, 12};
        ArrayList<Integer> list31 = new ArrayList<>(Arrays.asList(month31));
        Integer[] month30 = new Integer[]{4, 6, 9, 11};
        ArrayList<Integer> list30 = new ArrayList<>(Arrays.asList(month30));

        if(list31.contains(month))
            maxDay = 31;
        else if(list30.contains(month))
            maxDay = 30;
        else if(year % 4 == 0)
            maxDay = 29;
        else maxDay = 28;

        while(!(day <= maxDay && day >= 1)){
            console.wrongDayError();
            day = console.askDayOfBirth();
        }
        return day;
    }

    public LocalDate getDOB(){
        int year = getYearOfBirth();
        int month = getMonthOfBirth();
        int day = getDayOfBirth(year, month);
        return LocalDate.of(year, month, day);
    }

    public void createUser(){
        user = new User();
        user.setFirstName(console.getName("first"));
        user.setLastName(console.getName("last"));
        console.askDOB();
        user.setDob(getDOB());
        int id = generateId();
        user.setId(id);
        try {
            SqlController.insertUser(user.getId(), user.getFirstName(), user.getLastName(), user.getDob());
        }catch (SQLException e){
            Console.printSQLError("Insert");
        }
    }

    public void newCampaign(){
        createUser();
        console.printWelcome(user.getFirstName(), user.getLastName(), user.getId());
        console.printStartGuide();
        console.getIntegerInput(Messages.startingStocks());
    }

    public void continueCampaign(){
        int id = console.askForID();
        while (!SqlController.hasId(id)){
            console.idNotFoundError();
            id = console.askForID();
            if(id == 3)
                newOrContinue();
        }
    }

    public void newOrContinue(){
        console.printOpening();
        int answer = console.getNewGameOrContinueDecision();
        while (answer < 1 && answer > 2){
            console.newContinueError();
            answer = console.getNewGameOrContinueDecision();
        }
        if(answer == (1))
            newCampaign();
        else if(answer == (2))
            continueCampaign();
    }
}
