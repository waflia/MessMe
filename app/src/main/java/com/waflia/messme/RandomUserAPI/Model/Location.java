
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Location {

    @Json(name = "street")
    private Street street;
    @Json(name = "city")
    private String city;
    @Json(name = "state")
    private String state;
    @Json(name = "country")
    private String country;
    @Json(name = "postcode")
    private String postcode;
    @Json(name = "coordinates")
    private Coordinates coordinates;
    @Json(name = "timezone")
    private Timezone timezone;

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

}
