package com.zsgs.intervierpanel.login;


import com.zsgs.intervierpanel.InterviewPanel;
import com.zsgs.intervierpanel.datalayer.InterviewPanelDatabase;
import com.zsgs.intervierpanel.managecandidate.ManageCandidateView;
import com.zsgs.intervierpanel.manageinterviewer.ManageInterviewerView;
import com.zsgs.intervierpanel.model.Credentials;
import com.zsgs.intervierpanel.user.UserView;

public class LoginModel {
	private LoginView loginView;

	LoginModel(LoginView loginView) {
		this.loginView = loginView;

	}

	public void validateUser(String userName, String password) {
		if (InterviewPanelDatabase.getInstance().isUSer(userName)) {
			if (InterviewPanelDatabase.getInstance().passwordMatch(userName, password)) {
				int role = InterviewPanelDatabase.getInstance().getRole(userName);
				if (role == 0) {
					loginView.onSuccess(userName);
				} else if (role == 1) {
					new ManageInterviewerView().start(userName);
				} else {
					new UserView().init(userName);
				}
			} else {
				loginView.showAlert("Invalid password");
			}
		} else {
			loginView.showAlert("Invalid username");
		}

	}

	public void getAndSave() {
		InterviewPanelDatabase.getInstance().credentialsFromFile();
		InterviewPanelDatabase.getInstance().createCredentials();
        InterviewPanelDatabase.getInstance().saveCredentials();
	}

	public void companyFile() {
		InterviewPanelDatabase.getInstance().companyFromFile();
	}

	public void saveAll() {
		InterviewPanelDatabase.getInstance().saveAll();
	}
}
