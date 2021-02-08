package com.ipaixao.ibeer.interfaces.incomming.beer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record BeerResponse(@JsonProperty("name") String name,
                           @JsonProperty("style") String style,
                           @JsonProperty("ibu") Integer ibu,
                           @JsonProperty("abv") Double abv,
                           @JsonProperty("milliliter") Integer milliliter,
                           @JsonProperty("price") BigDecimal price,
                           @JsonProperty("manufacturer") String manufacturer) {

    public BeerResponse() {
        this(null, null, null, null, null, null, null);
    }
}
