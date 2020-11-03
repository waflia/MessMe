
package com.waflia.messme.RandomUserAPI.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Location implements Parcelable {

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

    protected Location(Parcel in) {
        street = in.readParcelable(Street.class.getClassLoader());
        city = in.readString();
        state = in.readString();
        country = in.readString();
        postcode = in.readString();
        coordinates = in.readParcelable(Coordinates.class.getClassLoader());
        timezone = in.readParcelable(Timezone.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(street, flags);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(postcode);
        dest.writeParcelable(coordinates, flags);
        dest.writeParcelable(timezone, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

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
