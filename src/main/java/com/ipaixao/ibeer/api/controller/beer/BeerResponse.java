package com.ipaixao.ibeer.api.controller.beer;

import java.math.BigDecimal;

public record BeerResponse(long id,
                           String name,
                           String style,
                           Integer ibu,
                           BigDecimal abv,
                           Integer milliliter,
                           BigDecimal price,
                           String brewery) {}
