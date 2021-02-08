package com.ipaixao.ibeer.interfaces.incomming.beer.mapper;

import com.ipaixao.ibeer.domain.beer.Beer;
import com.ipaixao.ibeer.interfaces.incomming.beer.response.BeerResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory.beer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BeerMapperTest {
    @Mock
    private BeerMapper beerMapper;

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerMapStub")
    public void shouldMapBeerResponse_whenToResponseIsCalled(Beer beer, BeerResponse expected) {
        when(beerMapper.toResponse(beer())).thenReturn(expected);

        final var beerResponse = beerMapper.toResponse(beer);

        assertThat(beerResponse).isEqualTo(expected);
    }
}
