
package com.waflia.messme.RandomUserAPI.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Street implements Parcelable {

    @Json(name = "number")
    private Integer number;
    @Json(name = "name")
    private String name;

    protected Street(Parcel in) {
        if (in.readByte() == 0) {
            number = null;
        } else {
            number = in.readInt();
        }
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (number == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(number);
        }
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Street> CREATOR = new Creator<Street>() {
        @Override
        public Street createFromParcel(Parcel in) {
            return new Street(in);
        }

        @Override
        public Street[] newArray(int size) {
            return new Street[size];
        }
    };

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
