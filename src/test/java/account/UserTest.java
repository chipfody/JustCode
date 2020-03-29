package account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class UserTest {
     User user;
    @Before
        public  void init(){
         user = new User();
    }
    @Test
    public void IdTest(){
        long id= 14;
        user.setId((int) id);
        long userId= user.getId();
       Assert.assertEquals(id,userId);
    }
    @Test
    public void firstNameTest(){
        String firstName = "John";
        user.setFirstName(firstName);
        String userFirstName = user.getFirstName();
        Assert.assertEquals(firstName,userFirstName);
    }
    @Test
    public void dobNameTest(){
        String lastName = "Doe";
        user.setLastName(lastName);
        String userLastName = user.getLastName();
        Assert.assertEquals(lastName,userLastName);
    }

    @Test
    public void dobTest(){
        LocalDate dob = LocalDate.of(2019, 4, 26);
        user.setDob(dob);
        LocalDate userDob = user.getDob();
        Assert.assertEquals(dob,userDob);
    }


}
