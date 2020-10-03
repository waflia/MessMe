
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Id {

    @Json(name = "name")
    private String name;
    @Json(name = "value")
    private String value;

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
