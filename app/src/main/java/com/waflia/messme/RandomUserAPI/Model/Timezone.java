
package com.waflia.messme.RandomUserAPI.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Timezone implements Parcelable {

    @Json(name = "offset")
    private String offset;
    @Json(name = "description")
    private String description;

    protected Timezone(Parcel in) {
        offset = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(offset);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Timezone> CREATOR = new Creator<Timezone>() {
        @Override
        public Timezone createFromParcel(Parcel in) {
            return new Timezone(in);
        }

        @Override
        public Timezone[] newArray(int size) {
            return new Timezone[size];
        }
    };

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
