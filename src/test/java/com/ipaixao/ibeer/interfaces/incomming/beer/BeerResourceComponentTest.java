package com.ipaixao.ibeer.interfaces.incomming.beer;

import com.ipaixao.ibeer.domain.beer.BeerService;
import com.ipaixao.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.ipaixao.ibeer.interfaces.incomming.beer.response.BeerResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.ipaixao.ibeer.base.BaseIntegrationTest.OBJECT_MAPPER;
import static com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory.beerResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BeerResource.class)
class BeerResourceComponentTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BeerService service;

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerDTOStub")
    void shouldSuccessfullyCreateABeer_whenCreateIsCalled(BeerDTO beerDTO, BeerResponse beerResponse) throws Exception {
        given(service.create(beerDTO)).willReturn(beerResponse);

        mockMvc.perform(
                post("/beers")
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(beerDTO))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(beerResponse.name()))
                .andExpect(jsonPath("$.style").value(beerResponse.style()))
                .andExpect(jsonPath("$.ibu").value(beerResponse.ibu()))
                .andExpect(jsonPath("$.abv").value(beerResponse.abv()))
                .andExpect(jsonPath("$.milliliter").value(beerResponse.milliliter()))
                .andExpect(jsonPath("$.price").value(beerResponse.price()))
                .andExpect(jsonPath("$.manufacturer").value(beerResponse.manufacturer()));
    }

    @ParameterizedTest
    @ValueSource(longs = 10L)
    void shouldReturnABeerSuccessfully_whenGetByIdIsCalled(long id) throws Exception {
        given(service.getById(id)).willReturn(beerResponse());

        mockMvc.perform(get(fromUriString("/beers/{id}").buildAndExpand(id).toUri())
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
