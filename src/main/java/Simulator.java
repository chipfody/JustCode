import account.User;
import utilities.Console;
import utilities.Messages;

public class Simulator {
    public static Console console = new Console(System.in,System.out);
    public static User user = new User();

    public static void main(String[] args) {
        console.println(Messages.welcome);
        user.setFirstName(console.getStringInput(Messages.enterFirstName));
        console.println(Messages.startingGuide);
        console.getIntegerInput(Messages.startingStocks());
    }


}
