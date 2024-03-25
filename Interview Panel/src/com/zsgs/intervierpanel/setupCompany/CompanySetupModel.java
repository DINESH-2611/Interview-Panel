package com.zsgs.intervierpanel.setupCompany;

import com.zsgs.intervierpanel.datalayer.InterviewPanelDatabase;
import com.zsgs.intervierpanel.model.Company;
import com.zsgs.intervierpanel.model.Validation;

public class CompanySetupModel {
	private CompanySetupView companySetupView;
	private Company company;


	public CompanySetupModel(CompanySetupView companySetupView) {
		this.companySetupView = companySetupView;
		company = InterviewPanelDatabase.getInstance().getCompany();

	}

	public void startSetup(String userName) {
		if (InterviewPanelDatabase.getInstance().getCompany()==null) {
			companySetupView.initiateSetup(userName);
		}else {
			companySetupView.onSetupComplete(company,userName);
		}
		
	}


	public void setupLibrary(String companyName, String emailId, String userName) {
		if (new Validation().isEmailvalidator(emailId)  && new Validation().nameChecker(companyName) ) {
			Company company= new Company();
			company.setCompanyName(companyName);
			company.setEmailId(emailId);
//			company.setAddress(address);
//			company.setCompanyId(companyId);
//			company.setPhoneNo(phoneNo);
			this.company=company;
			createLibrary(company,userName);
		} else {
			companySetupView.showAlert("Invalid library details");
			companySetupView.initiateSetup(userName);

		}
	}


	public void createLibrary(Company company, String userName) {
		this.company=InterviewPanelDatabase.getInstance().insertCompany(company);
		companySetupView.onSetupComplete(company, userName);
	}



	public void saveCompany() {
		InterviewPanelDatabase.getInstance().saveCompany();

	}

	public void interviewerFile() {
		InterviewPanelDatabase.getInstance().interviewerFromFile();
	}

	public void candidateFile() {
		InterviewPanelDatabase.getInstance().candidateFromFile();
	}

	public void interviewFile() {
		InterviewPanelDatabase.getInstance().interviewFromFile();
	}

	public void saveAll() {
		InterviewPanelDatabase.getInstance().saveAll();
	}
}
