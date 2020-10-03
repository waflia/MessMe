
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Result {

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
