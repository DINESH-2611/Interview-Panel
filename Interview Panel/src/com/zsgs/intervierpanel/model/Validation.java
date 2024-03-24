package com.zsgs.intervierpanel.model;

public class Validation {
	public boolean isEmailvalidator(String email) {
		String[] s3=email.split("@");
		if(s3.length!=2)
			return false;
		String s1=s3[0];
		if(s1.isEmpty() || s1.charAt(0)=='.' || s1.charAt(s1.length()-1)=='.')
			return false;
		
		for (int i = 0; i < s1.length()-1; i++) {
			if(i<email.length()-1 && s1.charAt(i)=='.' && email.charAt(i+1)=='.')
				return false;
			if(!(Character.isDigit(email.charAt(i)) || Character.isLetter(s1.charAt(i)) ||email.charAt(i)=='.' ))
				return false;
				
		}
		String s2=s3[1];
		if(s2.charAt(0)=='.' || s2.charAt(s2.length()-1)=='.')
			return false;
		int count=0;
		for(int i=0;i<s2.length();i++) {
			if(i<s2.length()-1 && s2.charAt(i)=='.' && s2.charAt(i+1)=='.')
				return false;
			if(s2.charAt(i)=='.')
				count++;
			if(!(Character.isLowerCase(s2.charAt(i) )|| s2.charAt(i)=='.'))
				return false;
		}
		if(!(count==1 || count==2))
			return false;

		
		return true;
	}
	public boolean nameChecker(String name) {
		if(name.length()<3 || name.length()>30)
			return false;
		for(int i=0;i<name.length();i++) {
			if(!(name.charAt(i)>='A' && name.charAt(i)<='Z' || name.charAt(i)>='a' && name.charAt(i)<='z'))
				return false;
		}
		return true;
	}

}
