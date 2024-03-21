//package com.ipaixao.ibeer.domain.brewery.mock;
//
//import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.entity.Brewery;
//import com.ipaixao.ibeer.api.response.BreweryResponse;
//import org.junit.jupiter.params.provider.Arguments;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//
//import java.util.List;
//import java.util.stream.Stream;
//
//import static com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory.beerResponse;
//import static org.junit.jupiter.params.provider.Arguments.arguments;
//
//public record BreweryMockFactory() {
//    public static Stream<Arguments> breweryPopuledStub() {
//        return Stream.of(
//                arguments(
//                        brewery(),
//                        breweryDTO(),
//                        breweryResponse()
//                )
//        );
//    }
//
//    public static Stream<Arguments> breweryUnPopuledStub() {
//        return Stream.of(
//                arguments(
//                        breweryDTO(),
//                        breweryResponse()
//                )
//        );
//    }
//
//    public static Stream<Arguments> brewerysStub() {
//        return Stream.of(
//                arguments(
//                        brewery(),
//                        new PageImpl<>(List.of(brewery())),
//                        breweryResponse(),
//                        PageRequest.of(1, 1)
//                )
//        );
//    }
//
//    public static Stream<Arguments> breweryByIdStub() {
//        return Stream.of(
//                arguments(
//                        10L,
//                        brewery(),
//                        breweryResponse()
//                )
//        );
//    }
//
//    public static Stream<Arguments> breweryDTOStub() {
//        return Stream.of(
//                arguments(
//                        newBreweryDTO(),
//                        breweryResponse()
//                )
//        );
//    }
//
//    public static Brewery newBrewery() {
//        return Brewery.builder().name("Heineken").birthplace("Amsterdam, Netherlands").build();
//    }
//
//    public static Brewery brewery() {
//        return Brewery.builder()
//                .id(1L)
//                .name(newBrewery().getName())
//                .birthplace(newBrewery().getBirthplace())
//                .build();
//    }
//
//    public static BreweryDTO newBreweryDTO() {
//        return new BreweryDTO(null, brewery().getName(), brewery().getBirthplace());
//    }
//
//    public static BreweryDTO breweryDTO() {
//        return new BreweryDTO(brewery().getId(), brewery().getName(), brewery().getBirthplace());
//    }
//
//    public static BreweryResponse breweryResponse() {
//        return new BreweryResponse(
//                brewery().getId(),
//                brewery().getName(),
//                brewery().getBirthplace(),
//                List.of(beerResponse())
//        );
//    }
//}