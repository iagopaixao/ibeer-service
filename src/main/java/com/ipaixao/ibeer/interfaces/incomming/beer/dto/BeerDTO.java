package com.ipaixao.ibeer.interfaces.incomming.beer.dto;

import com.ipaixao.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;
import lombok.Builder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
public record BeerDTO(
        Long id,
        @NotBlank(message = "required_field")
        String name,
        @NotNull(message = "required_field")
        @Min(value = 1, groups = int.class)
        @Max(value = 120, groups = int.class)
        Integer ibu,
        @NotNull(message = "required_field") Double abv,
        @NotNull(message = "required_field") String style,
        @NotNull(message = "required_field") @Min(value = 1) BigDecimal price,
        @NotNull(message = "required_field") Integer milliliter,
        @NotNull(message = "required_field") ManufacturerDTO manufacturer) {

    public BeerDTO() {
        this(null, null, null, null, null, null, null, null);
    }
}
