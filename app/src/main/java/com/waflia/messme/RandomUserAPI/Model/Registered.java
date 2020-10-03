
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Registered {

    @Json(name = "date")
    private String date;
    @Json(name = "age")
    private Integer age;

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
