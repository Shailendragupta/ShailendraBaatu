package com.baatu.model;

/**
 *
 * @author Shailendra This is model class for User address and geo location
 *
 */

public class AddressBean {
    /**
     * street : Kulas Light
     * suite : Apt. 556
     * city : Gwenborough
     * zipcode : 92998-3874
     * geo : {"lat":"-37.3159","lng":"81.1496"}
     */

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoBean geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public GeoBean getGeo() {
        return geo;
    }

    public void setGeo(GeoBean geo) {
        this.geo = geo;
    }

    public AddressBean() {
    }

}
