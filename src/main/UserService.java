package main;

import java.util.ArrayList;

public class UserService {
	private static ArrayList<User> userList = new ArrayList<User>();
	
	public UserService() {
	}
	
	public static ArrayList<User> getInstance() {
		return userList;
	}
	
	public static void addUser(User user) {
		userList.add(user);
	}
}
