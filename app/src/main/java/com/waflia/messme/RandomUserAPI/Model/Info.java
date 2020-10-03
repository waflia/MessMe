
package com.waflia.messme.RandomUserAPI.Model;

import com.squareup.moshi.Json;

public class Info {

    @Json(name = "seed")
    private String seed;
    @Json(name = "results")
    private Integer results;
    @Json(name = "page")
    private Integer page;
    @Json(name = "version")
    private String version;

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
