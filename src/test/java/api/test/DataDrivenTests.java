package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DatProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DatProviders.class)
	public void testPostUser(String UserID, String UserName, String FirstName, String LastName, String Email,
			String Password, String phoneNumber) {
		User userpayload = new User();
		userpayload.setId(Integer.parseInt(UserID)); 
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FirstName);

		userpayload.setLastName(LastName);
		userpayload.setEmail(LastName);
		userpayload.setPassword(Password);
		userpayload.setPhone(phoneNumber);

		Response res = UserEndpoints.createUser(userpayload);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DatProviders.class)
	public void testDeleteByUserName(String userName)
	{
		Response res1=UserEndpoints.deleteUser(userName);
		Assert.assertEquals(res1.getStatusCode(), 200);
	}

}
