package services;

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
		else return istance;
	}
	
	public User insertdata(Scanner cmdInput) {
		boolean isValid = true;
		
		String inpUser = "";
		do{
			System.out.print("Scegli un Username: ");
	        inpUser = cmdInput.nextLine();

	        if(inpUser == null || inpUser.isBlank()) {
				isValid = false;
			} else if(userFactory.getByUsername(inpUser) != null) {
				System.out.println("Username già esistente, inserirne uno diverso :");
				inpUser = cmdInput.nextLine();
				isValid = false;
			} else isValid = true;
					
		}while(!isValid);
		
		String inpPass = "";
		do{
			System.out.print("Scegli una Password: ");
	        inpPass = cmdInput.nextLine();

	        if(inpPass == null || inpPass.isBlank()) {
				isValid = false;
			} else isValid = true;
	        
		}while(!isValid);
        
		String inpEmail = "";
        do {
	        System.out.print("Inserisci il tuo indirizzo Email: ");
	        inpEmail = cmdInput.nextLine();
	        
	        if(inpEmail == null || inpEmail.isBlank() || inpEmail.startsWith("@") || inpEmail.endsWith("@") 
	        		|| !inpEmail.contains(".")) {
				isValid = false;
			} else if(userFactory.getByEmail(inpEmail) != null) {
				System.out.println("Email già esistente, inserirne un'altra: ");
				inpEmail = cmdInput.nextLine();
				isValid = false;
			} else isValid = true;
	        
        } while(!isValid);
        
        String inpFName = "";
        do{
        	System.out.print("Inserisci il tuo Nome: ");
            inpFName = cmdInput.nextLine();

	        if(inpFName == null || inpFName.isBlank()) {
				isValid = false;
			} else isValid = true;
	        
		}while(!isValid);
        
        String inpLName = "";
        do{
        	System.out.print("Inserisci il tuo Cognome: ");
            inpLName = cmdInput.nextLine();

	        if(inpLName == null || inpLName.isBlank()) {
				isValid = false;
			} else isValid = true;
	        
		}while(!isValid);
        
        String inpCF = "";
        do{
        	System.out.print("Inserisci il tuo Codice fiscale: ");
            inpCF = cmdInput.nextLine();

	        if(inpCF == null || inpCF.isEmpty()) {
				isValid = false;
			} else isValid = true;
	        
		}while(!isValid);
        
        String inpAddr = "";
        do{
        	System.out.print("Inserisci il tuo Indirizzo: ");
            inpAddr = cmdInput.nextLine();

	        if(inpAddr == null || inpAddr.isBlank()) {
				isValid = false;
			} else isValid = true;
	        
		}while(!isValid);
        
        String inpAddrNum = "";
        do{
        	System.out.print("Inserisci il tuo Numero civico: ");
            inpAddrNum = cmdInput.nextLine();

	        if(inpAddrNum == null || inpAddrNum.isBlank()) {
				isValid = false;
			} else isValid = true;
	        
		}while(!isValid);
        
        String inpCap = "";
        do{
        	System.out.print("Inserisci il tuo CAP: ");
            inpCap = cmdInput.nextLine();

	        if(inpCap == null || inpCap.isBlank()) {
				isValid = false;
			} else isValid = true;
	        
		}while(!isValid);
        
        String inpCity = "";
        do{
        	System.out.print("Inserisci la tua Citta : ");
            inpCity = cmdInput.nextLine();

	        if(inpCity == null || inpCity.isBlank()) {
				isValid = false;
			} else isValid = true;
	        
		}while(!isValid);
        
        String inpProv = "";
        do{
        	System.out.print("Inserisci la tua Provincia: ");
            inpProv = cmdInput.nextLine();

	        if(inpProv == null || inpProv.isBlank()) {
				isValid = false;
			} else isValid = true;
	        
		}while(!isValid);
        
        Address inpAddress = new Address(inpAddr, inpAddrNum, inpCap, inpCity, inpProv);
        User paramsUser = new User(null, inpUser, inpPass, inpEmail, inpFName, inpLName, inpCF, inpAddress, null, null);
        
		return paramsUser;
	}
	
	public User insertNewUser(User paramUser) throws  Exception {
		userFactory.create(paramUser);
		return userFactory.getByUsername(paramUser.getUsername());
	}
}
