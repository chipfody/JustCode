package account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        String dob= "04262019";
        user.setDob(dob);
        String userDob = user.getDob();
        Assert.assertEquals(dob,userDob);
    }


}
