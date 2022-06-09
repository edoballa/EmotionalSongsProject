package object;

public class Address {
	private String address;
    private String addressNumber;
    private String cap;
    private String city;
    private String province;
    private String region;
    private String country;

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
    public String getCivico() {
        return addressNumber;
    }

    /**
     * @param addressNumber the addressNumber to set
     */
    public void setCivico(String addressNumber) {
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
    public String getComune() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setComune(String city) {
        this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvincia() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvincia(String province) {
        this.province = province;
    }

    /**
     * @return the region
     */
    public String getRegione() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegione(String region) {
        this.region = region;
    }

    /**
     * @return the country
     */
    public String getStato() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setStato(String country) {
        this.country = country;
    }
}
