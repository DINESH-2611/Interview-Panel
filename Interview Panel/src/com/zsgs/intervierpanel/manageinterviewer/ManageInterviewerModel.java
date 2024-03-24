package com.zsgs.intervierpanel.manageinterviewer;

import com.zsgs.intervierpanel.datalayer.InterviewPanelDatabase;
import com.zsgs.intervierpanel.model.Credentials;
import com.zsgs.intervierpanel.model.Interview;
import com.zsgs.intervierpanel.model.Interviewer;
import com.zsgs.intervierpanel.model.Validation;

import java.util.List;

class ManageInterviewerModel {
	private ManageInterviewerView manageInterviewerView;

	public ManageInterviewerModel(ManageInterviewerView manageInterviewerView) {
		this.manageInterviewerView=manageInterviewerView;
	}

	public void setupInterviewer(String interviewerName, String emailId, String password) {
		if(new Validation().isEmailvalidator(emailId) && new Validation().nameChecker(interviewerName)) {
			Interviewer interviewer=new Interviewer();
			interviewer.setEmailId(emailId);
			interviewer.setInterviewerName(interviewerName);
			interviewer.setPassword(password);
			interviewer.setAvailable(true);
//			interviewer.setExpertise(expertise);
//			interviewer.setPhoneNo(phoneNo);
//			interviewer.setRole(role);
			InterviewPanelDatabase.getInstance().insertInterviewer(interviewer);
			manageInterviewerView.showAlert("Interviewer added successfully");
			Credentials credentials=new Credentials();
			credentials.setUserName(emailId);
			credentials.setPassword(password);
			credentials.setRole(1);
			InterviewPanelDatabase.getInstance().addCredentials(credentials);
		}else {
			manageInterviewerView.showAlert("Invalid interviewer details");
			manageInterviewerView.checkForAgain(emailId);
		}
		
	}


	public void getList(String emailId) {
		List<Interview> interviewList=InterviewPanelDatabase.getInstance().getList(emailId);
//		System.out.println(interviewList.size());
		if(interviewList.size()==0){
			manageInterviewerView.showAlert("This Interviewer is not taking any interviews yet");
		}else{
			manageInterviewerView.showInterviews(interviewList);
		}
	}

	public void setRating(String cEmailId, double rating) {
		InterviewPanelDatabase.getInstance().getCandidate(cEmailId).setRating(rating);
		manageInterviewerView.showAlert("Rating set");
	}

	public void interviewerFromFile() {
		InterviewPanelDatabase.getInstance().interviewerFromFile();
		InterviewPanelDatabase.getInstance().candidateFromFile();
	}


	public void saveCandidateInterviewer() {
		InterviewPanelDatabase.getInstance().saveCandidtes();
		InterviewPanelDatabase.getInstance().saveInterviewer();
	}
}
