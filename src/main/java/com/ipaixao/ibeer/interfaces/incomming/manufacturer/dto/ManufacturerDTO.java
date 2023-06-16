package com.ipaixao.ibeer.interfaces.incomming.manufacturer.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "Manufacturer Request",
        description = "Request object used to create or update a new Manufacturer record"
)
public record ManufacturerDTO(@Hidden Long id,
                              @NotBlank(message = "{required_field}") String name,
                              @NotBlank(message = "{required_field}") String birthplace) {}
