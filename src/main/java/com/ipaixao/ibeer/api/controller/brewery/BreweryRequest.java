package com.ipaixao.ibeer.api.controller.brewery;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "Brewery Request",
        description = "Request object used to create or update a new Brewery record"
)
public record BreweryRequest(@NotBlank(message = "{required_field}") String name,
                             @NotBlank(message = "{required_field}") String birthplace) {}
