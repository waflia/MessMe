
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Login {

    @Json(name = "uuid")
    private String uuid;
    @Json(name = "username")
    private String username;
    @Json(name = "password")
    private String password;
    @Json(name = "salt")
    private String salt;
    @Json(name = "md5")
    private String md5;
    @Json(name = "sha1")
    private String sha1;
    @Json(name = "sha256")
    private String sha256;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

}
