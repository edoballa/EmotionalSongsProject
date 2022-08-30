/**
* This package contains the classes that instantiate the objects.
*
* @author Diana Cantaluppi Matr. 744457.
* @author Edoardo Ballabio Matr. 745115.
*/
package objects;

import java.util.ArrayList;
import java.util.List;

public class User{
	/**
     * A Long to keep track of the user's id.
     */
    private Long userId;
    /**
	 * A String to store the user's username.
	 */
    private String username;
    /**
	 * A String to store the user's password.
	 */
    private String password;
    /**
	 * A String to store the user's email.
	 */
    private String email;
    /**
	 * A String to store the user's first name.
	 */
    private String firstName;
    /**
	 * A String to store the user's last name.
	 */
    private String lastName;
    /**
	 * A String to store the user's fiscal code.
	 */
    private String fiscalCode;
    /**
	 * An Address object to store the address data.
	 */
    private Address address;
    private List<Playlist> playlists;
    private List<EmotionFelt> emotionsFelt;

    /**
     * User default constructor.
     */
    public User() {
    	this.playlists = new ArrayList<>();
        this.emotionsFelt = new ArrayList<>();
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
     */
    public User(Long userId, String username, String password, String email, String firstName, String lastName,
            String fiscalCode, Address address, List<Playlist> playlists, List<EmotionFelt> emotionsFelt) {
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
     * @return String the user's address.
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

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<EmotionFelt> getEmotionsFelt() {
		return emotionsFelt;
	}

	public void setEmotionsFelt(List<EmotionFelt> emotionsFelt) {
		this.emotionsFelt = emotionsFelt;
	}

}

