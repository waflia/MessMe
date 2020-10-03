
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Picture {

    @Json(name = "large")
    private String large;
    @Json(name = "medium")
    private String medium;
    @Json(name = "thumbnail")
    private String thumbnail;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
