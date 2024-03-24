package com.zsgs.intervierpanel.manageinterview;

import java.util.List;

import com.zsgs.intervierpanel.datalayer.InterviewPanelDatabase;
import com.zsgs.intervierpanel.model.Candidate;
import com.zsgs.intervierpanel.model.Interview;
import com.zsgs.intervierpanel.model.Interviewer;

class ManageInterviewModel {
	private ManageInterviewView manageInterviewView;
	public ManageInterviewModel(ManageInterviewView manageInterviewView) {
		this.manageInterviewView=manageInterviewView;
	}
	
	public void isUserExist(String emailId) {
		Candidate candidate=InterviewPanelDatabase.getInstance().getCandidate(emailId);
//		System.out.println(candidate);
		if(candidate==null) {
			manageInterviewView.showAlert("Candidate not found");
		}else {
			List<Interviewer> interviewers=InterviewPanelDatabase.getInstance().getAvailableInterviewer();
			if(interviewers.size()==0) {
				manageInterviewView.showAlert("No Interviewers are available,Wait for some time");
			}else {
			manageInterviewView.showUser(interviewers,candidate);
		}}
		
	}
	public void check(List<Interviewer> allInterviewer, String iEmailId, Candidate candidate) {
		if(allInterviewer.stream().anyMatch(interview->interview.getEmailId().equals(iEmailId))) {
			Interview interview=new Interview();
			interview.setCandidate(candidate);
			interview.setiEmailId(iEmailId);
			InterviewPanelDatabase.getInstance().insertInterview(interview);
			InterviewPanelDatabase.getInstance().getCandidate(candidate.getEmailId()).setResult("Pending");
			InterviewPanelDatabase.getInstance().getInterviewer(iEmailId).setAvailable(false);
			manageInterviewView.showAlert("Interview is going on");
		}
		
	}

}
