package com.ipaixao.ibeer.interfaces.incomming.manufacturer.dto;

import javax.validation.constraints.NotBlank;

public record ManufacturerDTO(Long id, @NotBlank(message = "required_field")String name, String birthplace) {}
