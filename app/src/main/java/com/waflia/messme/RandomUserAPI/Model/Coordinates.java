
package com.waflia.messme.RandomUserAPI.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Coordinates implements Parcelable {

    @Json(name = "latitude")
    private String latitude;
    @Json(name = "longitude")
    private String longitude;

    protected Coordinates(Parcel in) {
        latitude = in.readString();
        longitude = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(latitude);
        dest.writeString(longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Coordinates> CREATOR = new Creator<Coordinates>() {
        @Override
        public Coordinates createFromParcel(Parcel in) {
            return new Coordinates(in);
        }

        @Override
        public Coordinates[] newArray(int size) {
            return new Coordinates[size];
        }
    };

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
