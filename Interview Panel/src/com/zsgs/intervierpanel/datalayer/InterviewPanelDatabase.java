package com.zsgs.intervierpanel.datalayer;

import java.awt.print.Book;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zsgs.intervierpanel.model.*;

public class InterviewPanelDatabase {
	private static InterviewPanelDatabase interviewPanelDatabase;
	private List<Company> companyList;
	private List<Interview> interviewList=new ArrayList<Interview>(); 
	private List<Interviewer> interviewerList = new ArrayList<>();
	private List<Candidate> candidateList = new ArrayList<>();
	private List<Credentials> credentialsList=new ArrayList<>();
//	    private List<Credentials> credentialsList=new ArrayList<>();

	public static InterviewPanelDatabase getInstance() {

		if (interviewPanelDatabase == null) {
			interviewPanelDatabase = new InterviewPanelDatabase();
		}
		return interviewPanelDatabase;
	}

	public Company getCompany() {
		if(companyList.size()==0){
			return null;
		}else{
			return companyList.get(0);
		}
	}

	public  Company insertCompany( Company company) {
		companyList.add(company);
		return company;
	}



	

	public void insertInterview(Interview interview) {
		interviewList.add(interview);
	}
	
	//-----------------------------------
	public Interviewer getInterviewer(String emailId) {
		for (Interviewer interviewer:interviewerList) {
			if (interviewer.getEmailId().equals(emailId)) {
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
			if (candidate.getEmailId().equals(emailId)) {
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

    public void createCredentials() {
		Credentials credentials=new Credentials();
		credentials.setUserName("zsgs");
		credentials.setPassword("admin");
		credentialsList.add(credentials);
    }

	public Credentials getCredentialsList(String userName) {
		for(Credentials credentials:credentialsList){
			if(credentials.getUserName().equals(userName))
				return credentials;
		}
	return null;
	}

	public void addCredentials(Credentials credentials) {
		credentialsList.add(credentials);
	}

	public List<Interviewer> getAvailableInterviewer() {
		return interviewerList.stream().filter(interviewer->interviewer.isAvailable()==true).toList();
		
	}



	public List<Interview> getList(String emailId) {
//		return interviewList.stream().filter(interview->interview.getiEmailId().equals(emailId)).toList();
		List<Interview> interviewList1=new ArrayList<>();
		for(Interview interview:interviewList){
			if(interview.getiEmailId().equals(emailId)){
				interviewList1.add(interview);
			}
		}
		return interviewList1;
	}

	public List<Interview> getAllInterview() {
		return interviewList;
	}

	public boolean getAttendedCandidate(String cEmailId) {
		for(Interview interview:interviewList){
			if(interview.getCandidate().getEmailId().equals(cEmailId)){
				return true;
			}
		}return false;
	}

	public boolean isUSer(String userName) {
		for (Credentials credentials : credentialsList) {
			if (credentials.getUserName().equals(userName)) {
				return true;
			}
		}
		return false;
	}

	public boolean passwordMatch(String userName, String password) {
		if(getPassword(userName)!=null){
			return getPassword(userName).equals(password);
		}return false;
	}

	private String getPassword(String userName) {
		for (Credentials credentials : credentialsList) {
			if (credentials.getUserName().equals(userName))
				return credentials.getPassword();
		}
		return null;
	}

	public int getRole(String userName) {
		for (Credentials credentials : credentialsList) {
			if (credentials.getUserName().equals(userName)) {
				return credentials.getRole();
			}
		}
		return 0;
	}
	public void saveCandidtes() {
		Gson gson = new Gson();
		String jsonString = gson.toJson(candidateList);

		try (FileWriter writer = new FileWriter("D:\\Java\\interview\\Candidate.json")) {
			writer.write(jsonString);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveInterviewer() {
		Gson gson = new Gson();
		String jsonString = gson.toJson(interviewerList);

		try (FileWriter writer = new FileWriter("D:\\Java\\interview\\Interviewer.json")) {
			writer.write(jsonString);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveInterview() {
		Gson gson = new Gson();
		String jsonString = gson.toJson(interviewList);

		try (FileWriter writer = new FileWriter("D:\\Java\\interview\\Interview.json")) {
			writer.write(jsonString);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveCompany() {
		Gson gson = new Gson();
		String jsonString = gson.toJson(companyList);

		try (FileWriter writer = new FileWriter("D:\\Java\\interview\\Company.json")) {
			writer.write(jsonString);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveCredentials() {
		Gson gson = new Gson();
		String jsonString = gson.toJson(credentialsList);

		try (FileWriter writer = new FileWriter("D:\\Java\\interview\\Credentails.json")) {
			writer.write(jsonString);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private List<Candidate> loadCandidates()  {
		Gson gson = new Gson();
		List<Candidate> loadedList = new ArrayList<>();
		try {
			File file=new File("D:\\Java\\interview\\Candidate.json");
			if(!file.exists()){
				file.createNewFile();
			}
		}catch (IOException e){
			e.printStackTrace();
		}


		try (
				FileReader reader = new FileReader("D:\\Java\\interview\\Candidate.json")) {
			StringBuilder sb = new StringBuilder();
			int c;
			while ((c = reader.read()) != -1) {
				sb.append((char) c);
			}

			String jsonString = sb.toString();
			if (jsonString.isEmpty()) {
				return loadedList;
			}

			Type type = new TypeToken<List<Candidate>>() {
			}.getType();
			loadedList = gson.fromJson(jsonString, type);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return loadedList;


	}
	private List<Interviewer> loadInterviewer()  {
		Gson gson = new Gson();
		List<Interviewer> loadedList = new ArrayList<>();
		try {
			File file=new File("D:\\Java\\interview\\Interviewer.json");
			if(!file.exists()){
				file.createNewFile();
			}
		}catch (IOException e){
			e.printStackTrace();
		}


		try (
				FileReader reader = new FileReader("D:\\Java\\interview\\Interviewer.json")) {
			StringBuilder sb = new StringBuilder();
			int c;
			while ((c = reader.read()) != -1) {
				sb.append((char) c);
			}

			String jsonString = sb.toString();
			if (jsonString.isEmpty()) {
				return loadedList;
			}

			Type type = new TypeToken<List<Interviewer>>() {
			}.getType();
			loadedList = gson.fromJson(jsonString, type);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return loadedList;


	}
	private List<Interview> loadInterview()  {
		Gson gson = new Gson();
		List<Interview> loadedList = new ArrayList<>();
		try {
			File file=new File("D:\\Java\\interview\\Interview.json");
			if(!file.exists()){
				file.createNewFile();
			}
		}catch (IOException e){
			e.printStackTrace();
		}


		try (
				FileReader reader = new FileReader("D:\\Java\\interview\\Interview.json")) {
			StringBuilder sb = new StringBuilder();
			int c;
			while ((c = reader.read()) != -1) {
				sb.append((char) c);
			}

			String jsonString = sb.toString();
			if (jsonString.isEmpty()) {
				return loadedList;
			}

			Type type = new TypeToken<List<Interview>>() {
			}.getType();
			loadedList = gson.fromJson(jsonString, type);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return loadedList;


	}
	private List<Company> loadCompany()  {
		Gson gson = new Gson();
		List<Company> loadedList = new ArrayList<>();
		try {
			File file=new File("D:\\Java\\interview\\Company.json");
			if(!file.exists()){
				file.createNewFile();
			}
		}catch (IOException e){
			e.printStackTrace();
		}


		try (
				FileReader reader = new FileReader("D:\\Java\\interview\\Company.json")) {
			StringBuilder sb = new StringBuilder();
			int c;
			while ((c = reader.read()) != -1) {
				sb.append((char) c);
			}

			String jsonString = sb.toString();
			if (jsonString.isEmpty()) {
				return loadedList;
			}

			Type type = new TypeToken<List<Company>>() {
			}.getType();
			loadedList = gson.fromJson(jsonString, type);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return loadedList;


	}
	private List<Credentials> loadCredentials()  {
		Gson gson = new Gson();
		List<Credentials> loadedList = new ArrayList<>();
		try {
			File file=new File("D:\\Java\\interview\\Credentials.json");
			if(!file.exists()){
				file.createNewFile();
			}
		}catch (IOException e){
			e.printStackTrace();
		}


		try (
				FileReader reader = new FileReader("D:\\Java\\interview\\Credentails.json")) {
			StringBuilder sb = new StringBuilder();
			int c;
			while ((c = reader.read()) != -1) {
				sb.append((char) c);
			}

			String jsonString = sb.toString();
			if (jsonString.isEmpty()) {
				return loadedList;
			}

			Type type = new TypeToken<List<Credentials>>() {
			}.getType();
			loadedList = gson.fromJson(jsonString, type);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return loadedList;


	}


	public void credentialsFromFile() {
		this.credentialsList=loadCredentials();
	}
	public void companyFromFile() {
		this.companyList=loadCompany();
	}
	public void interviewFromFile() {
		this.interviewList=loadInterview();
	}
	public void interviewerFromFile() {
		this.interviewerList=loadInterviewer();
	}
	public void candidateFromFile() {
		this.candidateList=loadCandidates();
	}
	public void saveAll(){
		saveCredentials();
		saveCandidtes();
		saveCompany();
		saveInterview();
		saveInterviewer();
	}
}
