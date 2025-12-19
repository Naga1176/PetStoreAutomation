package api.test;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userpayload;
	
	public Logger logger;//for logs

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userpayload = new User();

		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		
		logger = LogManager.getLogger(this.getClass());
		
		logger.debug("Debugging.......");
		
		
		
		

	}

	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("****** Creating User *********");
		Response res = UserEndpoints.createUser(userpayload);
		res.then().log().all();

		Assert.assertEquals(res.getStatusCode(), 200);
		

		logger.info("****** User is created *********");
	}

	@Test(priority = 2)
	public void testgetUserByName() {
		

		logger.info("****** reading  User info *********");

		Response resp = UserEndpoints.readUser(this.userpayload.getUsername());
		resp.then().log().all();
		// resp.statusCode();
		// below for assertion
		Assert.assertEquals(resp.getStatusCode(), 200);
		
		logger.info("******User info is displayed*********");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {

		
		logger.info("****** updating  User info *********");
		// update data using same payload
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());

		Response res = UserEndpoints.updateUser(this.userpayload.getUsername(), userpayload);
		res.then().log().body();//

		Assert.assertEquals(res.getStatusCode(), 200);// testNg assertion
		// checkig data after updation

		Response respafterupdate = UserEndpoints.readUser(this.userpayload.getUsername());
		Assert.assertEquals(respafterupdate.getStatusCode(), 200);
		
		logger.info("****** updated  User info is completed *********");
	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("****** User info is deleting  *********");
		Response resdeleteuser = UserEndpoints.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(resdeleteuser.getStatusCode(), 200);
		logger.info("****** Deleted  User info *********");
	}

}
