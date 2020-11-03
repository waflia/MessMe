
package com.waflia.messme.RandomUserAPI.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Id implements Parcelable {

    @Json(name = "name")
    private String name;
    @Json(name = "value")
    private String value;

    protected Id(Parcel in) {
        name = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Id> CREATOR = new Creator<Id>() {
        @Override
        public Id createFromParcel(Parcel in) {
            return new Id(in);
        }

        @Override
        public Id[] newArray(int size) {
            return new Id[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
