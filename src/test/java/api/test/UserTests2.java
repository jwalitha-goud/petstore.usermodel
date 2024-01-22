package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker=new Faker();
	 User userPayload=new User(); 
	 public Logger logger;
     @BeforeClass
     
     public void testData()
     {
    	 
    	 
    	 userPayload.setId(faker.idNumber().hashCode());
    	 userPayload.setUsername(faker.name().username());
    	 userPayload.setFirstName(faker.name().firstName());
    	 userPayload.setLastName(faker.name().lastName());
    	 userPayload.setEmail(faker.internet().safeEmailAddress());
    	 userPayload.setPassword(faker.internet().password(5,10));
    	 userPayload.setPhone(faker.phoneNumber().cellPhone());
    	 
    	 //logs
    	 logger=LogManager.getLogger(this.getClass());
     }
     
     @Test(priority=1)
     
     public void testpostUser()
     {
    	 logger.info("**************Creating user***********");    	 
    	 Response response=UserEndPoints2.createUser(userPayload);
    	 
    	 response.then().log().all();
    	 Assert.assertEquals(response.getStatusCode(),200);
    	 logger.info("**************user is created***********");
     
     }
     
     @Test(priority=2)
     
     public void testgetUser()
     
     {
    	 logger.info("**************read user***********");
    	 Response response=UserEndPoints2.readUser(this.userPayload.getUsername());
    	 
    	 response.then().log().all();
    	 Assert.assertEquals(response.getStatusCode(),200);
    	 logger.info("************** user details we got***********");
     }
     
     @Test(priority=3)
     
     public void testupdateUser()  
     
     {
    	 logger.info("**************Updating user***********");
    	 userPayload.setFirstName(faker.name().firstName());
    	 userPayload.setLastName(faker.name().lastName());
    	 userPayload.setEmail(faker.internet().safeEmailAddress());
    	 
    	 Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
    	 response.then().log().body();
    	 Assert.assertEquals(response.getStatusCode(),200);
    	 logger.info("**************user updated***********");
     //response after update 
    	 Response responseafterupdate=UserEndPoints2.readUser(this.userPayload.getUsername());
    	
    	 response.then().log().body();
    	 Assert.assertEquals(responseafterupdate.getStatusCode(),200);
     }
     
     @Test(priority=4)
     
     public void testdeleteUser()
     {
    	 logger.info("**************deleting user***********");
    	 Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
    	 Assert.assertEquals(response.getStatusCode(),200);
    	 logger.info("**************user deleted***********");
     }
     
     

}
