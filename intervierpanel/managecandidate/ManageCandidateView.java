package com.zsgs.intervierpanel.managecandidate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageCandidateView {
	Scanner scanner=new Scanner(System.in);
	private ManageCandidateModel manageCandidateModel;
	public ManageCandidateView() {
		this.manageCandidateModel=new ManageCandidateModel(this);
	}
	public void init() {
		System.out.println("Enter the Candidate name");
		String candidateName=scanner.nextLine();
		System.out.println("Enter the email id");
		String emailId=scanner.nextLine();
		System.out.println("Enter candidate skills");
		String skills=scanner.nextLine();
		System.out.println("Enter candidate experience in years");
		int experience=0;
		try {
			experience=scanner.nextInt();
		} catch (InputMismatchException e) {
			showAlert("Enter type mismatch,Enter valid experience");
			checkForAgain();
		}
		System.out.println("Enter candidate phone no");
		long phoneNo=0;
		try{
			phoneNo=scanner.nextLong();
		}catch (InputMismatchException e) {
			showAlert("Type mismatch,Enter valid phone No");
			checkForAgain();
		}
		scanner.nextLine();
		manageCandidateModel.setupInterview(candidateName,emailId,skills,experience,phoneNo);
		
	}
	public void checkForAgain() {
		System.out.println("Do you want to try again?\nYes\nNo");
		String choice=scanner.nextLine();
		if(choice.equalsIgnoreCase("yes"))
			init();
		else if(choice.equalsIgnoreCase("no")) {
			return;
		}else {
			showAlert("Invalid choice ,Please enter valid choice");
			checkForAgain();
		}
		
	}
	public void showAlert(String alert) {
		System.out.println(alert);
		
	}

}
