package com.tech3s.ffm.model;

public class SecurityContextHolder {
	
	private static User loggedUser;

	public static User getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(User user) {
		loggedUser = user;
	}

	@Override
	public String toString() {
		return "SecurityContextHolder [loggedUser=" + loggedUser + "]";
	}
		
}
