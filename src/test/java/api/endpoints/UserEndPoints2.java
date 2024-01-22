package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}

	public static Response createUser(User payload)
	{
		String Post_url=getURL().getString("Post_url");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
	.when()
		.post(Routes.Post_url);
		return response;
	}
	
	public static Response readUser(String userName)
	{
		String get_url=getURL().getString("get_url");
		Response response=given()
				.pathParam("username", userName)

				.when()
				.get(Routes.get_url);
		return response;
		
	}
	public static Response updateUser(String userName,User payload)
	{
		String update_url=getURL().getString("update_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username",userName)
				.when()
				.put(Routes.update_url);
		return response;
				
	}
	public static Response deleteUser(String userName)
	{
		String delete_url=getURL().getString("delete_url");
		Response response=given()
				.pathParam("username", userName)
				.when()
				.delete(Routes.delete_url);
		return response;
	}
}
