
package com.waflia.messme.RandomUserAPI.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Registered implements Parcelable {

    @Json(name = "date")
    private String date;
    @Json(name = "age")
    private Integer age;

    protected Registered(Parcel in) {
        date = in.readString();
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        if (age == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(age);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Registered> CREATOR = new Creator<Registered>() {
        @Override
        public Registered createFromParcel(Parcel in) {
            return new Registered(in);
        }

        @Override
        public Registered[] newArray(int size) {
            return new Registered[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
