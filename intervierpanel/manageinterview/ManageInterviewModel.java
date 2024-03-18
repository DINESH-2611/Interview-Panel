package com.zsgs.intervierpanel.manageinterview;

import com.zsgs.intervierpanel.datalayer.InterviewPanelDatabase;
import com.zsgs.intervierpanel.model.Interview;

class ManageInterviewModel {
	private ManageInterviewView manageInterviewView;
	public ManageInterviewModel(ManageInterviewView manageInterviewView) {
		this.manageInterviewView=manageInterviewView;
	}
	public void setupInterview(String role, String qualification, String interviewDate, String interviewTime,
			int interviewId, int noOfRequirement) {
		if(InterviewPanelDatabase.getInstance().getInterview(interviewId)==null) {
			Interview interview=new Interview();
			interview.setDate(interviewDate);
			interview.setInterviewId(interviewId);
			interview.setNoOfRequirement(noOfRequirement);
			interview.setQualification(qualification);
			interview.setRole(role);
			interview.setTime(interviewTime);
			InterviewPanelDatabase.getInstance().insertInterview(interview);
			manageInterviewView.showAlert("Interview details added successfully");
		}else {
			manageInterviewView.showAlert("Interview Id already exist");
			manageInterviewView.checkForAgain();
		}
		
	}

}
