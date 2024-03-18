package com.zsgs.intervierpanel.manageinterview;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageInterviewView {
	Scanner scanner=new Scanner(System.in);
	private ManageInterviewModel manageInterviewModel;
	public ManageInterviewView() {
		this.manageInterviewModel=new ManageInterviewModel(this);
		}
	public void init() {
		System.out.println("Enter the Role");
		String role=scanner.nextLine();
		System.out.println("Enter the qualification");
		String qualification=scanner.nextLine();
		System.out.println("Enter the interview date");
		String interviewDate=scanner.nextLine();
		System.out.println("Enter the interview time");
		String interviewTime=scanner.nextLine();
		System.out.println("Enter interview id");
		int interviewId=0;
		try{
			interviewId=scanner.nextInt();
		}catch (InputMismatchException e) {
			showAlert("Type mismatch,Enter valid id");
			checkForAgain();
		}
		System.out.println("Enter no of requirement");
		int noOfRequirement=0;
		try{
			noOfRequirement=scanner.nextInt();
		}catch (InputMismatchException e) {
			showAlert("Type mismatch,Enter valid no of requirements");
			checkForAgain();
		}
		scanner.nextLine();
		manageInterviewModel.setupInterview(role,qualification,interviewDate,interviewTime,interviewId,noOfRequirement);
		
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
