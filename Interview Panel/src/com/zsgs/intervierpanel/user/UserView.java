package com.zsgs.intervierpanel.user;

import com.zsgs.intervierpanel.login.LoginView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserView {
    private UserModel userModel;
    public UserView(){
        this.userModel=new UserModel(this);
    }

    public void init(String emailId) {
        userModel.candidateFromFile();
        Scanner scanner=new Scanner(System.in);
        try {
            while (true){
                System.out.println("1.See Result\n2.Logout\nEnter the choice");
                int choice=scanner.nextInt();
                switch (choice){
                    case 1:userModel.isInterviewFinish(emailId);
                    break;
                    case 2:
                        userModel.saveCandidate();
                        new LoginView().init();
                    return;
                    default:showAlert("Invalid input,Enter valid input");
                    init(emailId);
                }
            }
        }catch (InputMismatchException e){
            showAlert("Input mismatch,Enter valid input");
        }
    }

     void showAlert(String alert) {
         System.out.println(alert);
    }
}
