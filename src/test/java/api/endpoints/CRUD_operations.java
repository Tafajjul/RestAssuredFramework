package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CRUD_operations {
	
	public static Response createuser(User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(urls.create_url);
		return response;
	}
	
	public static Response getuser(String userName)
	{
		Response response=given()
		.pathParam("username", userName)
		.when()
		.get(urls.get_url);
		return response;
	}
	
	public static Response Updateuser(String userName,User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username",userName )
		.body(payload)
		.when()
		.put(urls.update_url);
		return response;
	}
	
	public static Response deleteuser(String userName)
	{
		Response response=given()
		.pathParam("username", userName)
		.when()
		.delete(urls.delete_url);
		return response;
	}
}
