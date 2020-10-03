
package com.waflia.messme.RandomUserAPI.Model;

import java.util.List;
import com.squareup.moshi.Json;

public class RandomUserResponse {

    @Json(name = "results")
    private List<Result> results = null;
    @Json(name = "info")
    private Info info;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
