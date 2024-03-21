//package com.ipaixao.ibeer.api.resource;
//
//import com.ipaixao.ibeer.base.BaseIntegrationTest;
//import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.repository.JpaBreweryRepository;
//import com.ipaixao.ibeer.api.response.BreweryResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static com.ipaixao.ibeer.domain.brewery.mock.BreweryMockFactory.breweryResponse;
//import static com.ipaixao.ibeer.domain.brewery.mock.BreweryMockFactory.newBrewery;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.web.util.UriComponentsBuilder.fromUriString;
//
//class BreweryResourceIntegrationTest extends BaseIntegrationTest {
//    @Autowired
//    private JpaBreweryRepository repository;
//
//    @BeforeEach
//    void setup() {
//        if (!repository.findAll().isEmpty())
//            repository.deleteAll();
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.ipaixao.ibeer.domain.brewery.mock.BreweryMockFactory#breweryDTOStub")
//    void shouldSuccessfullyCreateABrewery_whenCreateIsCalled(BreweryDTO breweryDTO, BreweryResponse expected) throws Exception {
//        mockMvc.perform(
//                post("/brewerys")
//                        .accept(APPLICATION_JSON)
//                        .contentType(APPLICATION_JSON)
//                        .content(OBJECT_MAPPER.writeValueAsString(breweryDTO))
//        )
//                .andExpect(status().isCreated())
//                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
//                .andExpect(jsonPath("$.name").value(expected.name()))
//                .andExpect(jsonPath("$.birthplace").value(expected.birthplace()));
//    }
//
//    @Test
//    void shouldReturnABeerSuccessfully_whenGetByIdIsCalled() throws Exception {
//        final var savedBrewery = repository.save(newBrewery());
//
//        mockMvc.perform(get(fromUriString("/brewerys/{id}").buildAndExpand(savedBrewery.getId()).toUri())
//                .accept(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(savedBrewery.getId()))
//                .andExpect(jsonPath("$.name").value(breweryResponse().name()));
//    }
//}
