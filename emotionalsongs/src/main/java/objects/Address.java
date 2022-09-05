/**
* This package contains the classes that instantiate the objects.
*
* @author Diana Cantaluppi, Matr. 744457 Sede Como.
* @author Edoardo Ballabio, Matr. 745115 Sede Como.
*/
package objects;


public class Address {
	/**
	 * <code>address</code>
	 * A String to store the user's address.
	 */
    private String address;
    /**
     * <code>addressNumber</code>
	 * A String to store the user's addressNumber.
	 */
    private String addressNumber;
    /**
     * <code>cap</code>
	 * A String to store the cap of the user's city.
	 */
    private String cap;
    /**
     * <code>city</code>
	 * A String to store the user's city.
	 */
    private String city;
    /**
     * <code>province</code>
	 * A String to store the user's province.
	 */
    private String province;
    /**
     * <code>region</code>
	 * A String to store the user's region.
	 * 
    private String region;

     * <code>country</code>
	 * A String to store the user's country.

    private String country;
    */
    
    /**
     * <code>valid</code>
	 * A boolean to check the validity of the user's address data.
	 */
    private boolean valid = false;

    /**
    * Address default constructor.
    */
    public Address() {
    }

    /**
    * Address constructor with all fields as parameters.
    * 
    * @param <address> The user's address.
    * @param <addressNumber> The user's number of the address.
    * @param <cap> The cap of the user's city.
    * @param <city> The user's city.
    * @param <province> The user's province.
    * @param <region> The user's province.
    * @param <country> The user's country.
    */
    public Address(String address, String addressNumber, String cap, String city, String province) {
        this.address = address;
        this.addressNumber = addressNumber;
        this.cap = cap;
        this.city = city;
        this.province = province;
        //this.region = region;
        //this.country = country;
        this.valid = checkAddressData();
    }

    /**
     * This method return the address field.
     * @return String the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * address setter method.
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method return the addressNumber field.
     * @return String the addressNumber.
     */
    public String getAddressNumber() {
        return addressNumber;
    }

    /**
     * addressNumber setter method.
     * @param addressNumber The addressNumber to set.
     */
    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    /**
     * This method return the cap field.
     * @return String the cap.
     */
    public String getCap() {
        return cap;
    }

    /**
     * cap setter method.
     * @param cap The cap to set.
     */
    public void setCap(String cap) {
        this.cap = cap;
    }

    /**
     * This method return the city field.
     * @return String the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * city setter method.
     * @param city The city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * This method return the province field.
     * @return String the province.
     */
    public String getProvince() {
        return province;
    }

    /**
     * province setter method.
     * @param province The province to set.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * This method return the region field.
     * @return String the region.

    public String getRegion() {
        return region;
    }

     * region setter method.
     * @param region The region to set.

    public void setRegion(String region) {
        this.region = region;
    }

     * This method return the country field.
     * @return String the country.

    public String getCountry() {
        return country;
    }

     * country setter method.
     * @param country The country to set.

    public void setCountry(String country) {
        this.country = country;
    }
    */

    /**
     * This method return the valid field.
     * @return boolean the value of valid.
     */
    public boolean getValid() {
        return valid;
    }

    /**
     * This method checks the validity of the fields that compose the user's address.
     * @return boolean a boolean value that depends on the correctness of the user's address data.
     */
    public boolean checkAddressData() {
        if (this.address.isEmpty() || this.address == null) {
            return false;
        }

        if (this.addressNumber.isEmpty() || this.addressNumber == null) {
            return false;
        }

        if (this.cap.isEmpty() || this.cap == null || this.cap.length() > 5) {
            return false;
        }

        if (this.city.isEmpty() || this.city == null) {
            return false;
        }

        if (this.province.isEmpty() || this.province == null || this.province.length() > 2) {
            return false;
        }

        return true;
    }
}
