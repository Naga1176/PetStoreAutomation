package api.endpoints;


//create user --> https://petstore.swagger.io/#/user


public class Routes {
	
	public static  String basic_url ="https://petstore.swagger.io/v2";
	
	
	//user module of pet store
	public static  String post_url =basic_url+"/user";
	public static  String get_url =basic_url+"/user/{username}";
	public static  String update_url =basic_url+"/user/{username}";
	public static  String delete_url =basic_url+"/user/{username}";

}
