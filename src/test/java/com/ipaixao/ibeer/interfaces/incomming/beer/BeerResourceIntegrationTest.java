package com.ipaixao.ibeer.interfaces.incomming.beer;

import com.ipaixao.ibeer.base.BaseIntegrationTest;
import com.ipaixao.ibeer.domain.beer.BeerRepository;
import com.ipaixao.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.ipaixao.ibeer.interfaces.incomming.beer.response.BeerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory.beerResponse;
import static com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory.newBeer;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

public class BeerResourceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private BeerRepository repository;

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerDTOStub")
    void shouldSuccessfullyCreateABeer_whenCreateIsCalled(BeerDTO beerDTO, BeerResponse expected) throws Exception {
        mockMvc.perform(
                post("/beers")
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(beerDTO))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(expected.name()))
                .andExpect(jsonPath("$.style").value(expected.style()))
                .andExpect(jsonPath("$.ibu").value(expected.ibu()))
                .andExpect(jsonPath("$.abv").value(expected.abv()))
                .andExpect(jsonPath("$.milliliter").value(expected.milliliter()))
                .andExpect(jsonPath("$.price").value(expected.price()))
                .andExpect(jsonPath("$.manufacturer").value(expected.manufacturer()));
    }

    @Test
    void shouldReturnABeerSuccessfully_whenGetByIdIsCalled() throws Exception {
        final var savedBeer = repository.save(newBeer());

        mockMvc.perform(get(fromUriString("/beers/{id}").buildAndExpand(savedBeer.getId()).toUri())
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(beerResponse().name()))
                .andExpect(jsonPath("$.style").value(beerResponse().style()))
                .andExpect(jsonPath("$.ibu").value(beerResponse().ibu()))
                .andExpect(jsonPath("$.abv").value(beerResponse().abv()))
                .andExpect(jsonPath("$.milliliter").value(beerResponse().milliliter()))
                .andExpect(jsonPath("$.price").value(beerResponse().price()))
                .andExpect(jsonPath("$.manufacturer").value(beerResponse().manufacturer()));
    }
}
