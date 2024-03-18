package com.zsgs.intervierpanel.login;

import java.util.Scanner;

import com.zsgs.intervierpanel.InterviewPanel;
import com.zsgs.intervierpanel.setupCompany.CompanySetupView;

public class LoginView {
	Scanner scanner = new Scanner(System.in);
	private LoginModel loginModel;

	public LoginView() {
		loginModel = new LoginModel(this);

	}

	public void init() {
		System.out.println("--- " + InterviewPanel.getInstance().getAppName() + " --- \nversion "
				+ InterviewPanel.getInstance().getAppVersion());
		getLoginDetials();
	}

	public void getLoginDetials() {
		System.out.println("Enter the username");
		String userName = scanner.nextLine();
		System.out.println("Enter the password");
		String password = scanner.nextLine();
		loginModel.validateUser(userName, password);
	}

	public void onSuccess() {
		System.out.flush();
		System.out.println("\n\nLogin successful...\n\n ---- welcome to " + InterviewPanel.getInstance().getAppName()
				+ " - v" + InterviewPanel.getInstance().getAppVersion() + "----");
		System.out.println("Login Success");
		new CompanySetupView().init();
	}

	public void showAlert(String alert) {
		System.out.println(alert);
		checkForLogin();
	}

	public void checkForLogin() {
		System.out.println("Do you try again \nYes\nNo");
		String choice = scanner.nextLine();
		if (choice.equalsIgnoreCase("yes"))
			getLoginDetials();
		else if (choice.equalsIgnoreCase("no"))
			System.out.println("\t\t\tThank you");
		else {
			System.out.println("Invalid choice\nPlease enter valid choice");
			checkForLogin();
		}
	}

}
