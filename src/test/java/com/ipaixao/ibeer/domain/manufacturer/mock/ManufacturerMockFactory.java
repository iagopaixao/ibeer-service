package com.ipaixao.ibeer.domain.manufacturer.mock;

import com.ipaixao.ibeer.domain.manufacturer.Manufacturer;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.response.ManufacturerResponse;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public record ManufacturerMockFactory() {
    public static Stream<Arguments> manufacturerStub() {
        return Stream.of(
                arguments(
                        manufacturer(),
                        manufacturerDTO(),
                        manufacturerResponse()
                )
        );
    }

    public static Stream<Arguments> manufacturersStub() {
        return Stream.of(
                arguments(
                        manufacturer(),
                        new PageImpl<>(List.of(manufacturer())),
                        manufacturerResponse(),
                        PageRequest.of(1, 1)
                )
        );
    }

    public static Stream<Arguments> manufacturerByIdStub() {
        return Stream.of(
                arguments(
                        10L,
                        manufacturer(),
                        manufacturerResponse()
                )
        );
    }

    public static Stream<Arguments> manufacturerDTOStub() {
        return Stream.of(
                arguments(
                        newManufacturerDTO(),
                        manufacturerResponse()
                )
        );
    }

    public static Manufacturer newManufacturer() {
        return Manufacturer.builder().name("Heineken").birthplace("Amsterdam, Netherlands").build();
    }

    public static Manufacturer manufacturer() {
        return Manufacturer.builder()
                .id(10L)
                .name(newManufacturer().getName())
                .birthplace(newManufacturer().getBirthplace())
                .build();
    }

    public static ManufacturerDTO newManufacturerDTO() {
        return new ManufacturerDTO(null, manufacturer().getName(), manufacturer().getBirthplace());
    }

    public static ManufacturerDTO manufacturerDTO() {
        return new ManufacturerDTO(manufacturer().getId(), manufacturer().getName(), manufacturer().getBirthplace());
    }

    public static ManufacturerResponse manufacturerResponse() {
        return new ManufacturerResponse(manufacturer().getName());
    }
}