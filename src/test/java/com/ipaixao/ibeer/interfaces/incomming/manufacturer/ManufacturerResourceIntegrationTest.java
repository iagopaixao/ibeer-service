package com.ipaixao.ibeer.interfaces.incomming.manufacturer;

import com.ipaixao.ibeer.base.BaseIntegrationTest;
import com.ipaixao.ibeer.domain.manufacturer.ManufacturerRepository;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.response.ManufacturerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory.manufacturerResponse;
import static com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory.newManufacturer;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

public class ManufacturerResourceIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private ManufacturerRepository repository;

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory#manufacturerDTOStub")
    void shouldSuccessfullyCreateAManufacturer_whenCreateIsCalled(ManufacturerDTO manufacturerDTO, ManufacturerResponse expected) throws Exception {
        mockMvc.perform(
                post("/manufacturers")
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(manufacturerDTO))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(expected.name()));
    }

    @Test
    void shouldReturnABeerSuccessfully_whenGetByIdIsCalled() throws Exception {
        final var savedManufacturer = repository.save(newManufacturer());

        mockMvc.perform(get(fromUriString("/manufacturers/{id}").buildAndExpand(savedManufacturer.getId()).toUri())
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(manufacturerResponse().name()));
    }
}
