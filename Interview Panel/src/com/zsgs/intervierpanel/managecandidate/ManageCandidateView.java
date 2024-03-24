package com.zsgs.intervierpanel.managecandidate;

import com.zsgs.intervierpanel.model.Interview;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManageCandidateView {
	Scanner scanner=new Scanner(System.in);
	private ManageCandidateModel manageCandidateModel;
	public ManageCandidateView() {
		this.manageCandidateModel=new ManageCandidateModel(this);
	}
	public void init(String userName) {
		try {
			System.out.println("Enter the Candidate name");
			String candidateName = scanner.nextLine();
			System.out.println("Enter the email id");
			String emailId = scanner.nextLine();
			System.out.println("Enter password");
			String password=scanner.nextLine();
			System.out.println("Enter phone no");
			long phoneNo = scanner.nextLong();
			scanner.nextLine();
//		System.out.println("Enter candidate skills");
//		String skills=scanner.nextLine();
//		System.out.println("Enter candidate experience in years");
//		int experience=0;
//		try {
//			experience=scanner.nextInt();
//		} catch (InputMismatchException e) {
//			showAlert("Enter type mismatch,Enter valid experience");
//			checkForAgain();
//		}
//		System.out.println("Enter candidate phone no");
//		long phoneNo=0;
//		try{
//			phoneNo=scanner.nextLong();
//		}catch (InputMismatchException e) {
//			showAlert("Type mismatch,Enter valid phone No");
//			checkForAgain();
//		}
//		scanner.nextLine();
			manageCandidateModel.setupInterview(candidateName, emailId,phoneNo,password);
		}catch (InputMismatchException e){
			showAlert("Invalid input ,Enter valid input");

		}
		
	}
	public void checkForAgain() {
		System.out.println("Do you want to try again?\nYes\nNo");
		String choice=scanner.nextLine();
		if(choice.equalsIgnoreCase("yes"))
			getCandidateEmailID();
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
	public void setResult(){
		manageCandidateModel.getCandidateList();
	}

	public void showCandidate(List<Interview> candidateList) {
		System.out.println("\t\t\tCandidate List");
		System.out.printf("%-15s %-25s %-10s","Name","Email id","Result\n\n");
		for(Interview interview:candidateList){
			System.out.printf("%-15s %-25s %-10s",interview.getCandidate().getCandidateName(),interview.getCandidate().getEmailId(),interview.getCandidate().getResult()+"\n");
		}

		getCandidateEmailID();
	}

	private void getCandidateEmailID() {
		System.out.println("Enter candidate emailid you want to set result");
		String cEmailId=scanner.nextLine();
		manageCandidateModel.setResult(cEmailId);
	}
}
