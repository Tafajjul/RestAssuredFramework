package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.CRUD_operations;
import api.payloads.User;
import io.restassured.response.Response;


public class Usertest {
	
	Faker faker;
	User userpayload;
	
	@BeforeClass
	public void Setup_data()
	{
		faker =new Faker();
		userpayload =new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		
	}

	@Test(priority=1)
	public void testpostuser()
	{
		System.out.println("First name " +userpayload.getFirstname());
		System.out.println("Last name " +userpayload.getLastname());
		System.out.println("User name " +userpayload.getUsername());
		
		Response response=CRUD_operations.createuser(userpayload);
		//System.out.println(response);
		//response.getBody().asString();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		
		
	}
	
	@Test(priority=2)
	public void testGetuserByName()
	{
		Response response=CRUD_operations.getuser(this.userpayload.getUsername());
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=3)
	public void testUpdateuserByName()
	{
		//updating details
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=CRUD_operations.Updateuser(this.userpayload.getUsername(),userpayload);
		//response.then().log().body().statusCode(200);
		Assert.assertEquals(response.getStatusCode(),200);
		
		//checking data after update 
		Response responseafterupdate=CRUD_operations.getuser(this.userpayload.getUsername());
		responseafterupdate.then().log().all();
		//Assert.assertEquals(response.getStatusCode(),200);
	}

	@Test(priority=4)
	public void testDeleteuserByName()
	{
		Response response=CRUD_operations.deleteuser(this.userpayload.getUsername());
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
}
