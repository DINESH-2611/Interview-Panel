package com.zsgs.intervierpanel.manageinterview;

import java.util.List;
import java.util.Scanner;

import com.zsgs.intervierpanel.model.Candidate;
import com.zsgs.intervierpanel.model.Interviewer;

public class ManageInterviewView {
	Scanner scanner=new Scanner(System.in);
	private ManageInterviewModel manageInterviewModel;
	public ManageInterviewView() {
		this.manageInterviewModel=new ManageInterviewModel(this);
		}
	public void init() {
		System.out.println("Enter candidate emailid");
		String emailId=scanner.nextLine();
		manageInterviewModel.isUserExist(emailId);
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
	public void showUser(List<Interviewer> allInterviewer, Candidate candidate) {
		System.out.println("\t\t\tAvailable Interviewers details");
		System.out.printf("%-20s %-25s","Name","Email id");
		System.out.println();
		for(Interviewer interviewer:allInterviewer) {
				System.out.printf("%-20s %-25s",interviewer.getInterviewerName(),interviewer.getEmailId());
				System.out.println();
		}
		selectinterviewer(allInterviewer,candidate);
		
	}
	private void selectinterviewer(List<Interviewer> allInterviewer, Candidate candidate) {
		System.out.println("Select the interviewer and Enter the selected Interviewer emailid");
		String iEmailId=scanner.nextLine();
		manageInterviewModel.check(allInterviewer,iEmailId,candidate);
		
	}

}
