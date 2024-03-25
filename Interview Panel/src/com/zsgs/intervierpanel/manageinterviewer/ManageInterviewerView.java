package com.zsgs.intervierpanel.manageinterviewer;

import com.zsgs.intervierpanel.login.LoginView;
import com.zsgs.intervierpanel.model.Interview;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManageInterviewerView {
	Scanner scanner=new Scanner(System.in);
	private ManageInterviewerModel manageInterviewerModel;
	public ManageInterviewerView() {
		this.manageInterviewerModel=new ManageInterviewerModel(this);
	}
	public void init(String emailID) {
		System.out.println("Enter the Interviewer name");
		String interviewerName=scanner.nextLine();
		System.out.println("Enter the email id");
		String emailId=scanner.nextLine();
		System.out.println("Enter the password");
		String password=scanner.nextLine();
//		System.out.println("Enter the interviewr expertise");
//		String expertise=scanner.nextLine();
//		System.out.println("Enter interviewer phone no");
//		long phoneNo=0;
//		try{
//			phoneNo=scanner.nextLong();
//		}catch (InputMismatchException e) {
//			showAlert("Type mismatch,Enter valid phone No");
//			checkForAgain();
//		}
//		scanner.nextLine();
		manageInterviewerModel.setupInterviewer(interviewerName,emailId,password);
		
	}
	public void checkForAgain(String emailId) {
		System.out.println("Do you want to try again?\nYes\nNo");
		String choice=scanner.nextLine();
		if(choice.equalsIgnoreCase("yes"))
			init(emailId);
		else if(choice.equalsIgnoreCase("no")) {
			return;
		}else {
			showAlert("Invalid choice ,Please enter valid choice");
			checkForAgain(emailId);
		}
		
	}
	public void showAlert(String alert) {
		System.out.println(alert);
		
	}

	public void start(String userName) {
		manageInterviewerModel.interviewerFromFile();
		try {
			while (true){
				System.out.println("1.Show List\n2.Give Rating\n3.Logout\nEnter the choice");
				int choice=scanner.nextInt();
				scanner.nextLine();
				switch (choice){
					case 1:manageInterviewerModel.getList(userName);
					break;
					case 2:giveRating();
					break;
					case 3:
						manageInterviewerModel.saveCandidateInterviewer();
						new LoginView().init();
						return;
					default:showAlert("Invalid choice,Enter valid choice");
					start(userName);

				}
			}
		}catch (InputMismatchException e){
			showAlert("Invalid choice,Enter valid choice");
			start(userName);
		}
	}

	private void giveRating() {
		System.out.println("Enter candidate emailid");
		String cEmailId=scanner.nextLine();
		System.out.println("Enter rating");
		double rating=scanner.nextDouble();
		manageInterviewerModel.setRating(cEmailId,rating);
	}

	public void showInterviews(List<Interview> interviewList) {
		System.out.printf("%-20s %-25s %-10s","Name","Email id","'Rating\n\n");
		for(Interview interview:interviewList){
			System.out.printf("%-20s %-25s %-10s",interview.getCandidate().getCandidateName(),interview.getCandidate().getEmailId(),interview.getCandidate().getRating());
			System.out.println();
		}
	}
}
