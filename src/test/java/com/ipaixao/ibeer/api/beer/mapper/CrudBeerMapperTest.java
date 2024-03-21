//package com.ipaixao.ibeer.api.beer.mapper;
//
//import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
//import com.ipaixao.ibeer.domain.beer.BeerDomain;
//import com.ipaixao.ibeer.infrastructure.dataprovider.beer.entity.Beer;
//import com.ipaixao.ibeer.api.response.BeerResponse;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.Mock;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory.beer;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.instancio.Select.field;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//class CrudBeerMapperTest {
//    @Mock
//    private BeerResponseMapper beerResponseMapper;
//
//    @ParameterizedTest
//    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerMapStub")
//    void shouldMapBeerResponse_whenToResponseIsCalled(BeerDomain beer, BeerResponse expected) {
//        when(beerResponseMapper.toResponse(beer())).thenReturn(expected);
//
//        final var beerResponse = beerResponseMapper.toResponse(beer);
//
//        assertThat(beerResponse).isEqualTo(expected);
//    }
//}
