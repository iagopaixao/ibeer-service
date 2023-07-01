package com.ipaixao.ibeer.interfaces.incomming.manufacturer;

import com.ipaixao.ibeer.domain.manufacturer.ManufacturerService;
import com.ipaixao.ibeer.interfaces.incomming.beer.BeerResource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory.manufacturerResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@ExtendWith(SpringExtension.class)
@WebMvcTest(
        controllers = ManufacturerResource.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
class ManufacturerResourceComponentTest {
    @Autowired private MockMvc mockMvc;
    @MockBean  private ManufacturerService service;

    @ParameterizedTest
    @ValueSource(longs = 10L)
    void shouldReturnABeerSuccessfully_whenGetByIdIsCalled(long id) throws Exception {
        given(service.getById(id)).willReturn(manufacturerResponse());

        mockMvc.perform(get(fromUriString("/manufacturers/{id}").buildAndExpand(id).toUri())
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(manufacturerResponse().name()));
    }
}
