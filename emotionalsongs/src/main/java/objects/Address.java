package objects;


public class Address {
    private String address;
    private String addressNumber;
    private String cap;
    private String city;
    private String province;
    private String region;
    private String country;
    private boolean valid = false;

    public Address() {
    }

    public Address(String address, String addressNumber, String cap, String city, String province, String region, String country) {
        this.address = address;
        this.addressNumber = addressNumber;
        this.cap = cap;
        this.city = city;
        this.province = province;
        this.region = region;
        this.country = country;
        this.valid = checkAddressData();
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the addressNumber
     */
    public String getAddressNumber() {
        return addressNumber;
    }

    /**
     * @param addressNumber the addressNumber to set
     */
    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    /**
     * @return the cap
     */
    public String getCap() {
        return cap;
    }

    /**
     * @param cap the cap to set
     */
    public void setCap(String cap) {
        this.cap = cap;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public boolean getValid() {
        return valid;
    }

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
