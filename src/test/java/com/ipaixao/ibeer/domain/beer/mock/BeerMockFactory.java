package com.ipaixao.ibeer.domain.beer.mock;

import com.ipaixao.ibeer.domain.beer.Beer;
import com.ipaixao.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.ipaixao.ibeer.interfaces.incomming.beer.response.BeerResponse;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public record BeerMockFactory() {
    public static Stream<Arguments> beerStub() {
        return Stream.of(
                arguments(
                        beer(),
                        beerDTO(),
                        beerResponse()
                )
        );
    }

    public static Stream<Arguments> beersStub() {
        return Stream.of(
                arguments(
                        beer(),
                        new PageImpl<>(List.of(beer())),
                        beerResponse(),
                        PageRequest.of(1, 1)
                )
        );
    }

    public static Stream<Arguments> beerByIdStub() {
        return Stream.of(
                arguments(
                        10L,
                        beer(),
                        beerResponse()
                )
        );
    }

    public static Stream<Arguments> beerDTOStub() {
        return Stream.of(
                arguments(
                        newBeerDTO(),
                        beerResponse()
                )
        );
    }

    public static Stream<Arguments> beerMapStub() {
        return Stream.of(
                arguments(
                        beer(),
                        beerResponse()
                )
        );
    }

    public static Beer newBeer() {
        return Beer.builder()
                .name(beer().getName())
                .style(beer().getStyle())
                .abv(beer().getAbv())
                .ibu(beer().getIbu())
                .milliliter(beer().getMilliliter())
                .price(beer().getPrice())
                .manufacturer(newManufacturer())
                .build();
    }

    public static Beer beer() {
        return Beer.builder()
                .id(10L)
                .name("Heineken")
                .style("Pale Lager")
                .abv(5.0)
                .ibu(19)
                .milliliter(600)
                .price(BigDecimal.valueOf(9.90))
                .manufacturer(manufacturer())
                .build();
    }

    public static BeerDTO beerDTO() {
        return new BeerDTO(
                beer().getId(),
                beer().getName(),
                beer().getIbu(),
                beer().getAbv(),
                beer().getStyle(),
                beer().getPrice(),
                beer().getMilliliter(),
                manufacturerDTO()
        );
    }

    public static BeerDTO newBeerDTO() {
        return new BeerDTO(
                null,
                beer().getName(),
                beer().getIbu(),
                beer().getAbv(),
                beer().getStyle(),
                beer().getPrice(),
                beer().getMilliliter(),
                newManufacturerDTO()
        );
    }

    public static BeerResponse beerResponse() {
        return new BeerResponse(
                beer().getName(),
                beer().getStyle(),
                beer().getIbu(),
                beer().getAbv(),
                beer().getMilliliter(),
                beer().getPrice(),
                beer().getManufacturer().getName()
        );
    }
}