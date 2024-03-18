package com.zsgs.intervierpanel.datalayer;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import com.zsgs.intervierpanel.model.Candidate;
import com.zsgs.intervierpanel.model.Company;
import com.zsgs.intervierpanel.model.Interview;
import com.zsgs.intervierpanel.model.Interviewer;

public class InterviewPanelDatabase {
	private static InterviewPanelDatabase interviewPanelDatabase;
	private Company company;
	private List<Interview> interviewList=new ArrayList<Interview>(); 
	private List<Interviewer> interviewerList = new ArrayList<>();
	private List<Candidate> candidateList = new ArrayList<>();
//	    private List<Credentials> credentialsList=new ArrayList<>();

	public static InterviewPanelDatabase getInstance() {
		if (interviewPanelDatabase == null) {
			interviewPanelDatabase = new InterviewPanelDatabase();
		}
		return interviewPanelDatabase;
	}

	public Company getCompany() {
		return company;// sql query and its result
	}

	public  Company insertCompany( Company company) {
		this.company = company;// insert sql query here
		return company;
	}

	public List<Interview> getAllInterview() {
		return interviewList;
	}

	public Interview getInterview(int interviewId) {
		for (Interview interview:interviewList) {
			if (interview.getInterviewId() == interviewId) {
				return interview;
			}
		} // select query with condition
		return null;
	}

	public void insertInterview(Interview interview) {
		interviewList.add(interview);
	}
	
	//-----------------------------------
	public Interviewer getInterviewer(String emailId) {
		for (Interviewer interviewer:interviewerList) {
			if (interviewer.getEmailId() ==emailId) {
				return interviewer;
			}
		} // select query with condition
		return null;
	}
	public List<Interviewer> getAllInterviewer() {
		return interviewerList;
	}

	public List<Interviewer> searchInterviewer(String emailId) {
		List<Interviewer> searchResult = new ArrayList<>();
		for (Interviewer interviewer : interviewerList) {
			if (interviewer.getEmailId().equals(emailId)) {
				searchResult.add(interviewer);
			}
		}
		return searchResult;// query
	}

	public void insertInterviewer(Interviewer interviewer) {
		interviewerList.add(interviewer);
	}
	
//---------------------------------------
	public Candidate getCandidate(String emailId) {
		for (Candidate candidate:candidateList) {
			if (candidate.getEmailId() ==emailId) {
				return candidate;
			}
		} // select query with condition
		return null;
	}
	public List<Candidate> getAllCandidate() {
		return candidateList;
	}

	public List<Candidate> searchCandidate(String emailId) {
		List<Candidate> searchResult = new ArrayList<>();
		for (Candidate candidate : candidateList) {
			if (candidate.getEmailId().equals(emailId)) {
				searchResult.add(candidate);
			}
		}
		return searchResult;// query
	}
	public void insertCandidate(Candidate candidate) {
		candidateList.add(candidate);
	}

//	public Company insertCompany(Company company2) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	    public void addCredentials(Credentials credentials) {
//	        credentialsList.add(credentials);
//	    }

}
