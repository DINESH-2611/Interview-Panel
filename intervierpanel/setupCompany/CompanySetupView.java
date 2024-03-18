package com.zsgs.intervierpanel.setupCompany;

import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;

import com.zsgs.intervierpanel.InterviewPanel;
import com.zsgs.intervierpanel.login.LoginView;
import com.zsgs.intervierpanel.managecandidate.ManageCandidateView;
import com.zsgs.intervierpanel.manageinterview.ManageInterviewView;
import com.zsgs.intervierpanel.model.Company;
import com.zsgs.intervierpanelmanageinterviewer.ManageInterviewerView;

public class CompanySetupView {
	Scanner scanner = new Scanner(System.in);
	private CompanySetupModel companySetupModel;

	public CompanySetupView() {
		companySetupModel = new CompanySetupModel(this);
	}

	public void init() {
		companySetupModel.startSetup();
	}

	public void onSetupComplete(Company company) {
		System.out.println("\nCompany setup  completed");
		showCompanyName(company.getCompanyName());
		while (true) {
			System.out.println(
					"1.Add Interviews\n2.Add Interviewers\n3.Add Candidates\n4.Logout\n0.Exit\nEnter the choice");
			int choice = 0;
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				showAlert("Type mismatch,Enter valid choice");
				onSetupComplete(company);
			}
			scanner.nextLine();
			switch (choice) {
			case 1: {
				new ManageInterviewView().init();
				break;
			}
			case 2: {
				new ManageInterviewerView().init();
				break;
			}
			case 3: {
				new ManageCandidateView().init();
				break;
			}
			case 4: {
				System.out.println("\t\t\tYou are logged out successfully");
				new LoginView().init();
				return;
			}
			case 0: {
				System.out.println("\t\t\t---Thanks for using " + InterviewPanel.getInstance().getAppName() + "---");
				return;
			}
			default: {
				System.out.println("Invalid choice,Please Enter valid choice");
			}
			}
		}

	}

	private void showCompanyName(String companyName) {
		System.out.println("Current Company Name-" + companyName);

	}

	public void showAlert(String alert) {
		System.out.println(alert);
	}

	public void initiateSetup() {
//	    	System.out.println("\n\nGet Library Details From Here.");

		System.out.println("Enter Company name");
		String companyName = scanner.nextLine();
//			scanner.nextLine();
		System.out.println("Enter Company email");
		String emailId = scanner.nextLine();
		System.out.println("Enter Company address");
		String address = scanner.nextLine();
		System.out.println("Enter Company phone no");
		long phoneNo = 0;
		int companyId = 0;
		try {
			phoneNo = scanner.nextLong();
		} catch (InputMismatchException e) {
			System.out.println("Invalid phone number,Enter valid ph no");
			initiateSetup();
		}
		System.out.println("Enter Company id");
		try {
			companyId = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid library id,Enter valid library id");
			initiateSetup();
		}

		companySetupModel.setupLibrary(companyName, emailId, address, phoneNo, companyId);

	}
//		public void initiateSetup() {
//			// TODO Auto-generated method stub
//			
//		}

}
