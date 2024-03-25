package com.zsgs.intervierpanel;

import com.zsgs.intervierpanel.datalayer.InterviewPanelDatabase;
import com.zsgs.intervierpanel.login.LoginView;
import com.zsgs.intervierpanel.model.Credentials;

public class InterviewPanel {
	private static InterviewPanel interviewPanel;
	
	private String appName = "Interview Panel System";

	private String appVersion = "0.0.1";
	
	private InterviewPanel() {
		
	}
	
	public static InterviewPanel getInstance() {
		if(interviewPanel == null) {
			interviewPanel = new InterviewPanel();
		}
		return interviewPanel;
	}
	private void create() {
		InterviewPanelDatabase.getInstance().createCredentials();
		LoginView loginView = new LoginView();
		loginView.init();
	}
	
	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public static void main(String[] args) {
		InterviewPanel.getInstance().create();

	}



}
