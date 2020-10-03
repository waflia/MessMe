
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Name {

    @Json(name = "title")
    private String title;
    @Json(name = "first")
    private String first;
    @Json(name = "last")
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFullName() {
        return first + " " + last;
    }

}
