
package com.waflia.messme.RandomUserAPI.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Info implements Parcelable {

    @Json(name = "seed")
    private String seed;
    @Json(name = "results")
    private Integer results;
    @Json(name = "page")
    private Integer page;
    @Json(name = "version")
    private String version;

    protected Info(Parcel in) {
        seed = in.readString();
        if (in.readByte() == 0) {
            results = null;
        } else {
            results = in.readInt();
        }
        if (in.readByte() == 0) {
            page = null;
        } else {
            page = in.readInt();
        }
        version = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(seed);
        if (results == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(results);
        }
        if (page == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(page);
        }
        dest.writeString(version);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
