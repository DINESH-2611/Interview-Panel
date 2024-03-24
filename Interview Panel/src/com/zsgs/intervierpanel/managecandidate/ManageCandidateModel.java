package com.zsgs.intervierpanel.managecandidate;

import com.zsgs.intervierpanel.datalayer.InterviewPanelDatabase;
import com.zsgs.intervierpanel.model.Candidate;
import com.zsgs.intervierpanel.model.Credentials;
import com.zsgs.intervierpanel.model.Interview;
import com.zsgs.intervierpanel.model.Validation;

import java.util.List;

class ManageCandidateModel {
	private ManageCandidateView manageCandidateView;
	public ManageCandidateModel(ManageCandidateView manageCandidateView) {
		this.manageCandidateView=manageCandidateView;
	}
	public void setupInterview(String candidateName, String emailId,long phoneNo,String password) {
		if(new Validation().isEmailvalidator(emailId) &&  String.valueOf(phoneNo).length()==10 && InterviewPanelDatabase.getInstance().getCandidate(emailId)==null && new Validation().nameChecker(candidateName)) {
			Candidate candidate=new Candidate();
			candidate.setCandidateName(candidateName);
			candidate.setEmailId(emailId);
//            candidate.setResult("Not yet started");
			candidate.setPhoneNo(phoneNo);
//			candidate.setExperience(experience);
//			candidate.setSkills(skills);
			InterviewPanelDatabase.getInstance().insertCandidate(candidate);
			manageCandidateView.showAlert("Candidate added successfully");
			Credentials credentials=new Credentials();
			credentials.setUserName(emailId);
			credentials.setPassword(password);
			credentials.setRole(2);
			InterviewPanelDatabase.getInstance().addCredentials(credentials);
		}else {
			manageCandidateView.showAlert("Invalid candidate details");
			manageCandidateView.checkForAgain();
		}
		
	}

	public void setResult(String cEmailId) {
		if(InterviewPanelDatabase.getInstance().getAttendedCandidate(cEmailId)){
			Candidate candidate=InterviewPanelDatabase.getInstance().getCandidate(cEmailId);
			if(candidate.getResult().equalsIgnoreCase("pending")){
				if(candidate.getRating()==0){
					manageCandidateView.showAlert("Interview not yet finished");
				}else if(candidate.getRating()<8){
					candidate.setResult("Rejected");
					manageCandidateView.showAlert("Result set");
				}else{
					candidate.setResult("Selected");
					manageCandidateView.showAlert("Result set");
				}
			}else{
				manageCandidateView.showAlert("Already result set");
			}
		}else{
			manageCandidateView.showAlert("Invalid email id");
			manageCandidateView.checkForAgain();
		}

	}

	public void getCandidateList() {
		List<Interview> candidateList= InterviewPanelDatabase.getInstance().getAllInterview();
		if(candidateList.size()==0){
			manageCandidateView.showAlert("Interview is yet to start");
		}else{
			manageCandidateView.showCandidate(candidateList);
		}
	}
}
