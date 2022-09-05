/**
* This package contains the classes that create and manage the various factory.
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package persistence;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import objects.Address;

import objects.User;

public class User_Factory implements IGeneric_Factory<User, Long> {
	/**
	 * <code>FILEPATH</code>
	 * A String to store the path of the file that contains the users.
	 */
    private static final String FILEPATH = System.getProperty("user.dir") + "\\data\\user_data.csv";
    /**
	 * <code>istance</code>
	 */
    private static User_Factory istance = null;
    /**
	 * <code>UserMap</code>
	 * A map of User to store all users who are registered.
	 */
    private Map<Long, User> userMap;
    /**
	 * <code>lines</code>
	 * A List of String that contains the lines of a file.
	 */
    private List<String> lines;
    /**
	 * <code>nextKey</code>
	 * A Long to keep track of the next key of the map.
	 */
    private Long nextKey = null;

    /**
     * User_Factory default constructor.
     * 
     * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
     */
    private User_Factory() throws Exception {
        this.userMap = new HashMap<>();
        this.lines = new ArrayList<>();
        
        fillUserList();
        
        if(nextKey == null) {
        	nextKey = Long.valueOf(userMap.size());
        	while(userMap.containsKey(nextKey)) {
        		nextKey++;
        	}
        }
    }

    /**
     * This method return the istance of the User_Factory object.
     * 
     * @return The object User_Factory.
     * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
     */
    public static User_Factory getIstance() throws Exception {
        if (istance == null) {
            return new User_Factory();
        } else {
            return istance;
        }
    }

    /**
     * This method create an user and add to the map.
     * 
     * @param <user> The object that represents the user.
     * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
     * @throws <IOException> This exception is thrown if there is any input/output error.
     */
    @Override
    public void create(User user) throws Exception, IOException {
        user.setUserId(getNextKey());
        userMap.putIfAbsent(user.getUserId(), user);
        save();
    }

    /**
     * This method return a User based on its id.
     * 
     * @param <id> The user's id.
     * @return The object User.
     * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
     */
    @Override
    public User getById(Long id) throws Exception {
        if (userMap.containsKey(id)) {
            return userMap.get(id);
        } else {
            return null;
        }
    }

    /**
	 * This method replace in the map an User object with the one passed as a parameter.
	 * 
	 * @param <user> The object that represents the user.
     * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
     * @throws <IOException> This exception is thrown if there is any input/output error.
	 */
    @Override
    public void update(User user) throws Exception, IOException {
        userMap.remove(user.getUserId());
        userMap.put(user.getUserId(), user);
        save();
    }

    /**
	 * This method remove an User from the map.
	 * 
	 * @param <user> The object that represents the user.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
    @Override
    public void delete(User user) throws Exception {
        if (userMap.containsKey(user.getUserId())) {
            userMap.remove(user.getUserId());
            save();
        }
    }

    /**
	 * This method return a Map with all the User.
	 * 
	 * @return <UserMap> A Map of User.
	 */
    @Override
    public Map<Long, User> listAll() {
        return userMap;
    }
    
    /**
	 * This method return a Long that is the next key to save on file.
	 * 
	 * @return <nextKey> A Long that indicate the next key of the map.
	 */
    public Long getNextKey() {
        while(userMap.containsKey(nextKey)) {
            nextKey++;
        }
        return nextKey;
    }

    /**
     * This method return a user based on the user's username.
     * 
     * @param <username> The username of the user.
     * @return A User object.
     */
    public User getByUsername(String username) {
        for (User user : userMap.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    /**
     * This method returns a user based on the user's email.
     * 
     * @param <email> The user's email.
     * @return A User object.
     */
    public User getByEmail(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
    }

    /**
     * This method checks if a user exists or doesn't exist.
     * 
     * @param <user> The user object to check if it exists.
     * @return True if the user exists, false if the user doesn't exists.
     */
    public boolean existUser(User user) throws Exception {
    	userMap.clear(); //refresh user map to get new user
        fillUserList();
        
        for (User u : userMap.values()) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }

    /**
	 * This method creates a new file in which it saves the users.
	 * If a file with that name already exists, it deletes it and creates a new one.
	 * 
	 * @return <strong>True</strong>, if the program successfully saves the file, <strong>False</strong>, if the program throws the exception.
	 * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
	 */
    private Boolean save() throws Exception {
        prepareDataForWriting();

        try {
            Files.deleteIfExists(new File(FILEPATH).toPath());
            Path file = Files.createFile(new File(FILEPATH).toPath());
            Files.write(file, lines);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        userMap.clear();
        fillUserList();

        return true;
    }

    /**
	 * This method adds the users to a list of strings.
	 * The data in the list are divided by a separator.
	 */
    private void prepareDataForWriting() {
        lines.clear();
        String line = new String();

        for (User user : userMap.values()) {

            line = user.getUserId() + ";"
                    + user.getUsername() + ";"
                    + user.getPassword() + ";"
                    + user.getEmail() + ";"
                    + user.getFirstName() + ";"
                    + user.getLastName() + ";"
                    + user.getAddress().getAddress() + ";"
                    + user.getAddress().getAddressNumber()+ ";"
                    + user.getAddress().getCity()+ ";"
                    + user.getAddress().getCap()+ ";"
                    + user.getAddress().getProvince(); //+ ";"
                    //+ user.getAddress().getRegion()+ ";"
                    //+ user.getAddress().getCountry();

            lines.add(line);
        }
    }

    /**
     * This method after the every update refresh the object Map.
     * 
     * @throws <Exception> This class indicate conditions that a reasonable application might want to catch.
     */
    private void fillUserList() throws Exception {
        lines.clear();

        try {
            lines = Files.readAllLines(new File(FILEPATH).toPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (String line : lines) {
        	User user = new User();

            String[] strs = line.split(";");
            if (strs.length > 0) {
                user.setUserId(Long.valueOf(strs[0]));
                user.setUsername(strs[1]);
                user.setPassword(strs[2]);
                user.setEmail(strs[3]);
                user.setFirstName(strs[4]);
                user.setLastName(strs[5]);
                user.setAddress(new Address());
                user.getAddress().setAddress(strs[6]);
                user.getAddress().setAddressNumber(strs[7]);
                user.getAddress().setCity(strs[8]);
                user.getAddress().setCap(strs[9]);
                user.getAddress().setProvince(strs[10]);
                
                /*if(strs.length > 10){
                    user.getAddress().setRegion(strs[11]);
                }
                if(strs.length > 11){
                    user.getAddress().setCountry(strs[12]);
                }*/
                
                userMap.putIfAbsent(user.getUserId(), user);
            }
        }
    }

}
