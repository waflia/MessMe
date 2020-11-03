
package com.waflia.messme.RandomUserAPI.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Result implements Parcelable {

    @Json(name = "gender")
    private String gender;
    @Json(name = "name")
    private Name name;
    @Json(name = "location")
    private Location location;
    @Json(name = "email")
    private String email;
    @Json(name = "login")
    private Login login;
    @Json(name = "dob")
    private Dob dob;
    @Json(name = "registered")
    private Registered registered;
    @Json(name = "phone")
    private String phone;
    @Json(name = "cell")
    private String cell;
    @Json(name = "id")
    private Id id;
    @Json(name = "picture")
    private Picture picture;
    @Json(name = "nat")
    private String nat;

    protected Result(Parcel in) {
        gender = in.readString();
        name = in.readParcelable(Name.class.getClassLoader());
        location = in.readParcelable(Location.class.getClassLoader());
        email = in.readString();
        login = in.readParcelable(Login.class.getClassLoader());
        dob = in.readParcelable(Dob.class.getClassLoader());
        registered = in.readParcelable(Registered.class.getClassLoader());
        phone = in.readString();
        cell = in.readString();
        id = in.readParcelable(Id.class.getClassLoader());
        picture = in.readParcelable(Picture.class.getClassLoader());
        nat = in.readString();
    }
    public Result(){

    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gender);
        dest.writeParcelable(name, flags);
        dest.writeParcelable(location, flags);
        dest.writeString(email);
        dest.writeParcelable(login, flags);
        dest.writeParcelable(dob, flags);
        dest.writeParcelable(registered, flags);
        dest.writeString(phone);
        dest.writeString(cell);
        dest.writeParcelable(id, flags);
        dest.writeParcelable(picture, flags);
        dest.writeString(nat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Dob getDob() {
        return dob;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }


}
