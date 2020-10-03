
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Street {

    @Json(name = "number")
    private Integer number;
    @Json(name = "name")
    private String name;

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
