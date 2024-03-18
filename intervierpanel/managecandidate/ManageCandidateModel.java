package com.zsgs.intervierpanel.managecandidate;

import com.zsgs.intervierpanel.datalayer.InterviewPanelDatabase;
import com.zsgs.intervierpanel.model.Candidate;
import com.zsgs.intervierpanel.model.Validation;
import com.zsgs.intervierpanelmanageinterviewer.ManageInterviewerView;

class ManageCandidateModel {
	private ManageCandidateView manageCandidateView;
	public ManageCandidateModel(ManageCandidateView manageCandidateView) {
		this.manageCandidateView=manageCandidateView;
	}
	public void setupInterview(String candidateName, String emailId, String skills, int experience, long phoneNo) {
		if(new Validation().isEmailvalidator(emailId) &&  String.valueOf(phoneNo).length()==10 && InterviewPanelDatabase.getInstance().getCandidate(emailId)==null) {
			Candidate candidate=new Candidate();
			candidate.setCandidateName(candidateName);
			candidate.setEmailId(emailId);
			candidate.setExperience(experience);
			candidate.setPhoneNo(phoneNo);
			candidate.setSkills(skills);
			InterviewPanelDatabase.getInstance().insertCandidate(candidate);
			manageCandidateView.showAlert("Candidate added successfully");
		}else {
			manageCandidateView.showAlert("Invalid candidate details");
			manageCandidateView.checkForAgain();
		}
		
	}

}
