package com.ipaixao.ibeer.interfaces.incomming.beer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record BeerResponse(@JsonProperty("name") String name,
                           @JsonProperty("abv") Double abv,
                           @JsonProperty("ibu") Integer ibu,
                           @JsonProperty("style") String style,
                           @JsonProperty("price") BigDecimal price,
                           @JsonProperty("milliliter") Integer milliliter,
                           @JsonProperty("manufacturer") String manufacturer) {

    public BeerResponse() {
        this(null, null, null, null, null, null, null);
    }
}
