package com.zsgs.intervierpanel.user;

import com.zsgs.intervierpanel.InterviewPanel;
import com.zsgs.intervierpanel.datalayer.InterviewPanelDatabase;
import com.zsgs.intervierpanel.model.Candidate;

class UserModel {
    private UserView userView;
    public UserModel(UserView userView){
        this.userView=userView;
    }

    public void isInterviewFinish(String emailId) {
        Candidate candidate= InterviewPanelDatabase.getInstance().getCandidate(emailId);
        if(InterviewPanelDatabase.getInstance().getAttendedCandidate(emailId)){
            if(candidate.getResult()==null){
                userView.showAlert("Interview not yet started");
            }else{
                if(candidate.getResult().equalsIgnoreCase("selected")){
                    userView.showAlert("Congratulations!,You are selected");
                }else if(candidate.getResult().equalsIgnoreCase("rejected")){
                    userView.showAlert("Sorry,You are rejected for this time,Better luck next time");
                }else{
                    userView.showAlert("Result is pending...");
                }
            }
        }
    }

    public void candidateFromFile() {
        InterviewPanelDatabase.getInstance().candidateFromFile();
    }

    public void saveCandidate() {
        InterviewPanelDatabase.getInstance().saveCandidtes();
    }
}
