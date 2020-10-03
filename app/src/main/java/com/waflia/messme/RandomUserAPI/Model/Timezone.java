
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Timezone {

    @Json(name = "offset")
    private String offset;
    @Json(name = "description")
    private String description;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
