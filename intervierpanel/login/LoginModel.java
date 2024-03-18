package com.zsgs.intervierpanel.login;

public class LoginModel {
	private LoginView loginView;

	LoginModel(LoginView loginView) {
		this.loginView = loginView;

	}

	public void validateUser(String userName, String password) {
		if (validateUserName(userName)) {
			if (validatePassword(password)) {
				loginView.onSuccess();

			} else {
				loginView.showAlert("Invalid password");
			}
		} else {
			if (validatePassword(password))
				loginView.showAlert("Invalid username");
			else
				loginView.showAlert("Invalid user name and password");
		}

	}

	private boolean validateUserName(String userName) {
		return userName.equals("zsgs");
	}

	private boolean validatePassword(String password) {
		return password.equals("admin");
	}

}
