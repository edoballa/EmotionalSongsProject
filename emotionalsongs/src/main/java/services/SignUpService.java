package services;

import java.io.IOException;
import java.util.Scanner;

import objects.Address;
import objects.User;
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
		Scanner cmdInput = new Scanner(System.in);
		
		System.out.print("Scegli un Username: ");
        String inpUser = cmdInput.nextLine();
        System.out.print("Scegli una Password: ");
        String inpPass = cmdInput.nextLine();
        System.out.print("Inserisci il tuo indirizzo Email: ");
        String inpEmail = cmdInput.nextLine();
        System.out.print("Inserisci il tuo Nome: ");
        String inpFName = cmdInput.nextLine();
        System.out.print("Inserisci il tuo Cognome: ");
        String inpLName = cmdInput.nextLine();
        System.out.print("Inserisci il tuo Codice fiscale: ");
        String inpCF = cmdInput.nextLine();
        System.out.print("Inserisci il tuo Indirizzo: ");
        String inpAddr = cmdInput.nextLine();
        System.out.print("Inserisci il tuo Numero civico: ");
        String inpAddrNum = cmdInput.nextLine();
        System.out.print("Inserisci il tuo CAP: ");
        String inpCap = cmdInput.nextLine();
        System.out.print("Inserisci la tua Città: ");
        String inpCity = cmdInput.nextLine();
        System.out.print("Inserisci la tua Provincia: ");
        String inpProv = cmdInput.nextLine();
        System.out.print("Inserisci la tua Regione: ");
        String inpReg = cmdInput.nextLine();
        System.out.print("Inserisci il tuo Stato: ");
        String inpCountry = cmdInput.nextLine();
        
        Address inpAddress = new Address(inpAddr, inpAddrNum, inpCap, inpCity, inpProv, inpReg, inpCountry);
        
        cmdInput.close();
		return new User(null, inpUser, inpPass, inpEmail, inpFName, inpLName, inpCF, inpAddress);
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

		if(!paramUser.getAddress().getValid()) {
			return false;
		}
		
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
