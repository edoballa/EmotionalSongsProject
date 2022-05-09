package services;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import object.User;

/**
 * @author Diana Cantaluppi
 *  Matricola 744457
 *  Sede di Como
 */


public class LoginService {
    private static final Logger logger = Logger.getLogger(String.valueOf(LoginService.class));
    private static LoginService istance = null;
    
    private LoginService(){
        
    }
    
    public static LoginService getIstance() {
    	if(istance == null)
    		return new LoginService();
    	else return istance;
    }

    public User run() {
        Scanner cmdInput = new Scanner(System.in);

        System.out.print("Username: ");
        String inpUser = cmdInput.nextLine();
        System.out.print("Password: ");
        String inpPass = cmdInput.nextLine();
        
        cmdInput.close();

        return new User(inpUser, inpPass);
    }

    public boolean loginAttempt(User user, String file) {
        Map<String, String> mapUser = new HashMap<>();
        getLinesFromFile(file).forEach(l -> {
            String[] strs = l.split(";");
            mapUser.put(strs[0], strs[1]);
        });
        
        if (mapUser.containsKey(user.getUsername())) {
            if(mapUser.get(user.getUsername()).trim().equals(user.getPassword())) {
                return true;
            } 
        }
        
        logger.info("user not found");
        return false;
        
    }

    private List<String> getLinesFromFile(String path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(new File(path).toPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.info(lines.size() + " lines were read");
        return lines;
    }

}