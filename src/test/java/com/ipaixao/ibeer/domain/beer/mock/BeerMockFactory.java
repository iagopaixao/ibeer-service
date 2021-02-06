package com.ipaixao.ibeer.domain.beer.mock;

import com.ipaixao.ibeer.domain.beer.Beer;
import com.ipaixao.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.ipaixao.ibeer.interfaces.incomming.beer.response.BeerResponse;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Stream;

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

    static Beer beer() {
        return Beer.builder().id(10L).name("Heineken").build();
    }

    static BeerDTO beerDTO() {
        return new BeerDTO(10L, "Heineken", null, null, null, null, null, null);
    }

    static BeerResponse beerResponse() {
        return new BeerResponse("Heineken", null, null, null, null, null, null);
    }
}