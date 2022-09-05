/**
* <p>This package contains the classes that instantiate the objects.</p>
*/
package objects;

import java.util.HashMap;
import java.util.Map;

/**
* <p>This class define what is an user</p>
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
public class User{
	/**
	 * <code>userId</code>
     * A Long to keep track of the user's id.
     */
    private Long userId;
    /**
     * <code>username</code>
	 * A String to store the user's username.
	 */
    private String username;
    /**
     * <code>password</code>
	 * A String to store the user's password.
	 */
    private String password;
    /**
     * <code>email</code>
	 * A String to store the user's email.
	 */
    private String email;
    /**
     * <code>firstName</code>
	 * A String to store the user's first name.
	 */
    private String firstName;
    /**
     * <code>lastName</code>
	 * A String to store the user's last name.
	 */
    private String lastName;
    /**
     * <code>fiscalCode</code>
	 * A String to store the user's fiscal code.
	 */
    private String fiscalCode;
    /**
     * <code>address</code>
	 * An Address object to store the address data.
	 */
    private Address address;
    /**
     * <code>playlists</code>
     * A Map of Playlist to store all the playlists create by a user.
     */
    private Map<Long, Playlist> playlists;
    /**
     * <code>emotionsFelt</code>
     * A Map of EmotionFelt to store all the emotion felt by a user.
     */
    private Map<String, EmotionFelt> emotionsFelt;

    /**
     * User default constructor.
     */
    public User() {
    	this.playlists = new HashMap<>();
        this.emotionsFelt = new HashMap<>();
    }

    /**
     * User constructor with all fields as parameters.
     * 
     * @param userId The user's id.
     * @param username The user's username.
     * @param password The user's password.
     * @param email The user's email.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param fiscalCode The user's fiscal code.
     * @param address The user's address.
     * @param playlists A Map of playlist.
     * @param emotionsFelt A map of emotions felt.
     */
    public User(Long userId, String username, String password, String email, String firstName, String lastName,
            String fiscalCode, Address address, Map<Long, Playlist> playlists, Map<String, EmotionFelt> emotionsFelt) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fiscalCode = fiscalCode;
        this.address = address;
        this.playlists = playlists;
        this.emotionsFelt = emotionsFelt;
    }

    /**
     * User constructor with all fields as parameters.
     * 
     * @param username The user's username.
     * @param password The user's password.
     */
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    /**
     * This method return the userId field.
     * @return Long the userId.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * userId setter method.
     * @param userId The user id to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method return the username field.
     * @return String the user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * username setter method.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method return the password field.
     * @return String the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * password setter method.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method return the email field.
     * @return String the user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * email setter method.
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method return the first name field.
     * @return String the user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * firstName setter method.
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method return the last name field.
     * @return String the user's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * lastName setter method.
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method return the fiscal code field.
     * @return String the user's fiscal code name.
     */
    public String getFiscalCode() {
        return fiscalCode;
    }

    /**
     * fiscalCode setter method.
     * @param fiscalCode The fiscal code to set.
     */
    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    /**
     * This method return the address field.
     * @return Address the object address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * address setter method.
     * @param address The address to set.
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    
    /**
     * This method return a Map of Playlist.
     * @return The Map of playlists create by a user.
     */
    public Map<Long, Playlist> getPlaylists() {
		return playlists;
	}

    /**
     * playlists setter method.
     * @param playlists The Map of playlists to set.
     */
	public void setPlaylists(Map<Long, Playlist> playlists) {
		this.playlists = playlists;
	}

	/**
     * This method return a Map of EmotionsFelt.
     * @return The Map of emotions felt.
     */
	public Map<String, EmotionFelt> getEmotionsFelt() {
		return emotionsFelt;
	}

	/**
     * emotionsFelt setter method.
     * @param emotionsFelt The map of emotions felt to set.
     */
	public void setEmotionsFelt(Map<String, EmotionFelt> emotionsFelt) {
		this.emotionsFelt = emotionsFelt;
	}

}
