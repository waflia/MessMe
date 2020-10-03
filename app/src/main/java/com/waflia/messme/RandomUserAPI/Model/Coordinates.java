
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Coordinates {

    @Json(name = "latitude")
    private String latitude;
    @Json(name = "longitude")
    private String longitude;

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
