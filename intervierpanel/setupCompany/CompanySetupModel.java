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

	public void startSetup() {
		if (company == null || company.getCompanyId() == 0) {
			companySetupView.initiateSetup();
		}
	}


	public void setupLibrary(String companyName, String emailId, String address, long phoneNo, int companyId) {
		if (new Validation().isEmailvalidator(emailId)  && String.valueOf(phoneNo).length()==10 && companyId>0 ) {
			Company company= new Company();
			company.setCompanyName(companyName);
			company.setEmailId(emailId);
			company.setAddress(address);
			company.setCompanyId(companyId);
			company.setPhoneNo(phoneNo);
			createLibrary(company);
		} else {
			companySetupView.showAlert("Invalid library details");
			companySetupView.initiateSetup();

		}
	}


	public void createLibrary(Company company) {
		this.company=InterviewPanelDatabase.getInstance().insertCompany(company);
		companySetupView.onSetupComplete(company);
	}

}
