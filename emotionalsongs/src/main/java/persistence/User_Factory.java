package persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import object.User;

public class User_Factory implements IGeneric_Factory<User, Long>{
	private static final String FILEPATH = System.getProperty("user.dir") + "\\data\\user_data.csv";
    private static User_Factory istance = null;
    
    private Map<Long, User> UserList;
    private List<String> lines;
    
    private User_Factory() throws Exception {
    	this.UserList = new HashMap<>();
        this.lines = new ArrayList<>();
        
        fillUserList();
    }
    
    public static User_Factory getIstance() throws Exception {
    	if(istance == null){
            return new User_Factory();
        } else return istance;
    }

	@Override
	public void create(User user) throws Exception, IOException {
		user.setUserId(Long.valueOf(UserList.size()+1));
		UserList.putIfAbsent(user.getUserId(), user);
		save();
	}

	@Override
	public User getById(Long id) throws Exception {
		if(UserList.containsKey(id))
			return UserList.get(id);
		else return null;
	}

	@Override
	public void update(User user) throws Exception, IOException {
		UserList.remove(user.getUserId());
		UserList.put(user.getUserId(), user);
		save();
	}

	@Override
	public void delete(User user) throws Exception {
		if(UserList.containsKey(user.getUserId())) {
			UserList.remove(user.getUserId());
			save();
		}	
	}

	@Override
	public Map<Long, User> listAll() {
		return UserList;
	}
	
	public User getByUsername(String username) {
		for(User user : UserList.values()) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
	public boolean existUser(User user) {
		for(User u : UserList.values()) {
			if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		
		return false;
	}
	
	private Boolean save() throws Exception{
        prepareDataForWriting();
        
        try{
            Files.deleteIfExists(new File(FILEPATH).toPath());
            Path file = Files.createFile(new File(FILEPATH).toPath());
            Files.write(file, lines);            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        UserList.clear();
        fillUserList();
        
        return true;
    }
    
    private void prepareDataForWriting() {
        lines.clear();
        String line = new String();
        
        for(User user : UserList.values()) {
            
            line = user.getUserId() + ";"
                    + user.getUsername() + ";" 
                    + user.getPassword() + ";"
                    + user.getEmail() + ";"
                    + user.getFirstName() + ";"
                    + user.getLastName() + ";"
                    + user.getBirthday().getDate() + "/" + user.getBirthday().getMonth() + "/" + user.getBirthday().getYear();
            
            lines.add(line);
        }        
    }
    
    private void fillUserList() throws Exception{
        lines.clear();
        
        try {
            lines = Files.readAllLines(new File(FILEPATH).toPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        User user;
        
        for(String line : lines) {
            user = new User(); //do this for cache problems
            
            String[] strs = line.split(";");
            if(strs.length > 0){
                user.setUserId(Long.valueOf(strs[0]));
                user.setUsername(strs[1]);
                user.setPassword(strs[3]);
                user.setEmail(strs[4]);
                user.setFirstName(strs[5]);
                user.setLastName(strs[6]);
                String[] data = strs[7].split("/");
                user.setBirthday(new Date(Integer.valueOf(data[2]), Integer.valueOf(data[1]), Integer.valueOf(data[0])));

                UserList.putIfAbsent(user.getUserId(), user);
            }
            user = null; //do this for cache problems
        }    
    }

}
