package com.ipaixao.ibeer.api.controller.beer;

import com.ipaixao.ibeer.api.controller.brewery.BreweryRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(
        name = "Beer Request",
        description = "Request object used to create or update a new Beer record"
)
public record BeerRequest(@NotBlank(message = "{required_field}") String name,
                          @NotNull(message = "{required_field}")
                          @Min(value = 1, groups = int.class, message = "{min_field_value}")
                          @Max(value = 120, groups = int.class, message = "{max_field_value}")
                          Integer ibu,
                          @NotNull(message = "{required_field}") BigDecimal abv,
                          @NotNull(message = "{required_field}") String style,
                          @NotNull(message = "{required_field}") @Min(value = 1) BigDecimal price,
                          @NotNull(message = "{required_field}") Integer milliliter,
                          @NotNull(message = "{required_field}") BreweryRequest brewery) {
}
