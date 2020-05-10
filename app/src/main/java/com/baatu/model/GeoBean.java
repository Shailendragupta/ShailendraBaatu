package com.baatu.model;

/**
 *
 * @author Shailendra This is model class for geo location
 *
 */

public class GeoBean {
    /**
     * lat : -37.3159
     * lng : 81.1496
     */

    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public GeoBean() {
    }

}
