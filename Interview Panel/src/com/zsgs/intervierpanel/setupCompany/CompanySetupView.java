package com.zsgs.intervierpanel.setupCompany;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.zsgs.intervierpanel.login.LoginView;
import com.zsgs.intervierpanel.managecandidate.ManageCandidateView;
import com.zsgs.intervierpanel.manageinterview.ManageInterviewView;
import com.zsgs.intervierpanel.model.Company;
import com.zsgs.intervierpanel.manageinterviewer.ManageInterviewerView;

public class CompanySetupView {
    Scanner scanner = new Scanner(System.in);
    private CompanySetupModel companySetupModel;

    public CompanySetupView() {
        companySetupModel = new CompanySetupModel(this);
    }

    public void init(String userName) {
        companySetupModel.startSetup(userName);
    }

    public void onSetupComplete(Company company, String userName) {
        companySetupModel.saveCompany();
        try{
        System.out.println("\nCompany setup  completed");
        showCompanyName(company.getCompanyName());
        while (true) {
            System.out.println(
                    "1.Add Interviewers\n2.Add Candidates\n3.Schedule Interview\n4.Set Result\n5.Logout\nEnter the choice");
            char choice = scanner.next().charAt(0);
//            if(Integer.parseInt(choice))
//            int c=(int)choice;
//            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case '1': {
                    companySetupModel.interviewerFile();
                    new ManageInterviewerView().init(userName);
                    break;
                }
                case '2': {
                    companySetupModel.candidateFile();
                    new ManageCandidateView().init(userName);
                    break;
                }
                case '3': {
                    companySetupModel.interviewFile();
                    new ManageInterviewView().init();
                    break;
                }
                case '4': {
                    companySetupModel.candidateFile();
                   new ManageCandidateView().setResult();
                    break;
                }
                case '5': {
                    companySetupModel.saveAll();
                    System.out.println("\t\t\tYou are logged out successfully");
                    new LoginView().init();
                    return;
                }
//                case '0': {
//                    System.out.println("\t\t\t---Thanks for using " + InterviewPanel.getInstance().getAppName() + "---");
//                    return;
//                }
                default: {
                    System.out.println("Invalid choice,Please Enter valid choice");
                    break;
                }
            }
        }
        }catch (InputMismatchException e){
            showAlert("Input mismatch,Enter valid input");
            onSetupComplete(company, userName);
        }

    }

    private void showCompanyName(String companyName) {
        System.out.println("Current Company Name-" + companyName);

    }

    public void showAlert(String alert) {
        System.out.println(alert);
    }

    public void initiateSetup(String userName) {
//	    	System.out.println("\n\nGet Library Details From Here.");

        System.out.println("Enter Company name");
        String companyName = scanner.nextLine();
//			scanner.nextLine();
        System.out.println("Enter Company email");
        String emailId = scanner.nextLine();
//        System.out.println("Enter Company address");
//        String address = scanner.nextLine();
//        System.out.println("Enter Company phone no");
//        long phoneNo = 0;
//        int companyId = 0;
//        try {
//            phoneNo = scanner.nextLong();
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid phone number,Enter valid ph no");
//            initiateSetup();
//        }
//        System.out.println("Enter Company id");
//        try {
//            companyId = scanner.nextInt();
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid library id,Enter valid library id");
//            initiateSetup();
//        }

        companySetupModel.setupLibrary(companyName, emailId,userName);

    }
//		public void initiateSetup() {
//			// TODO Auto-generated method stub
//			
//		}

}
