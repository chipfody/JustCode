import account.User;
import campaign.Campaign;
import utilities.Console;
import utilities.Messages;

public class Simulator {
    public static Console console =  new Console(System.in,System.out);

    public static void main(String[] args) {
        Campaign campaign = new Campaign();

        console.println(Messages.welcome);
        campaign.newOrContinue();
    }


}
