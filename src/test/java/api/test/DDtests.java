package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.CRUD_operations;
import api.payloads.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class DDtests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = Dataproviders.class)
	public void testPostuser(String userID, String userName, String firstName, String lastName, String email,
			String password, String phone) {

		User userpayload = new User();
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(userName);
		userpayload.setFirstname(firstName);
		userpayload.setLastname(lastName);
		userpayload.setEmail(email);
		userpayload.setPassword(password);
		userpayload.setPhone(phone);

		Response response = CRUD_operations.createuser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = Dataproviders.class)
	public void testDeleteuserByNames(String UserName) {
		Response response = CRUD_operations.deleteuser(UserName);
		Assert.assertEquals(response.statusCode(), 200);
	}

}
