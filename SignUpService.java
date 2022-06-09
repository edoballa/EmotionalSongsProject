package services;

import java.io.IOException;

import object.User;
import persistence.User_Factory;

public class SignUpService {
	private static SignUpService istance = null;
	private User_Factory userFactory;
	
	private SignUpService() throws Exception {
		this.userFactory = User_Factory.getIstance();
	}
	
	public static SignUpService getIstance() throws Exception {
		if(istance == null) {
			return new SignUpService();
		}
		else
			return istance;
	}
	
	public User insertdata() {
		return new User();
	}
	
	public boolean checkUserDataInsert(User paramUser) {
		if(paramUser.getFirstName().isEmpty() || paramUser.getFirstName() == null) {
			return false;
		}
		if(paramUser.getLastName().isEmpty() || paramUser.getLastName() == null) {
			return false;
		}
		if(paramUser.getFiscalCode().length() > 16 || paramUser.getFiscalCode() == null || paramUser.getFiscalCode().isEmpty()) {
			return false;
		}
		//TODO adding checkAddress
		if(paramUser.getEmail() == null || paramUser.getEmail().isEmpty() || paramUser.getEmail().startsWith("@") || paramUser.getEmail().endsWith("@") || paramUser.getEmail().contains(".")) {
			return false;
		}
		if(paramUser.getUsername() == null || paramUser.getUsername().isEmpty()) {
			return false;
		}
		if(paramUser.getPassword() == null || paramUser.getPassword().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public String checkUniqueData(User paramUser) {
		if(userFactory.getByUsername(paramUser.getUsername()) != null) {
			return "Username già esistente";
		}
		if(userFactory.getByEmail(paramUser.getEmail()) != null) {
			return "Email già esistente";
		}
		if(userFactory.getByFiscalCode(paramUser.getFiscalCode()) != null) {
			return "Codice fiscale già esistente";
		}
		return null;
	}
	
	public User insertNewUser(User paramUser) throws  Exception {
		userFactory.create(paramUser);
		return userFactory.getByUsername(paramUser.getUsername());
	}
}
