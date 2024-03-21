//package com.ipaixao.ibeer.api.resource;
//
//import com.ipaixao.ibeer.api.resource.brewery.BreweryController;
//import com.ipaixao.ibeer.infrastructure.dataprovider.service.BreweryService;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static com.ipaixao.ibeer.domain.brewery.mock.BreweryMockFactory.breweryResponse;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.web.util.UriComponentsBuilder.fromUriString;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(
//        controllers = BreweryController.class,
//        excludeAutoConfiguration = SecurityAutoConfiguration.class
//)
//class BreweryResourceComponentTest {
//    @Autowired private MockMvc mockMvc;
//    @MockBean  private BreweryService service;
//
//    @ParameterizedTest
//    @ValueSource(longs = 10L)
//    void shouldReturnABeerSuccessfully_whenGetByIdIsCalled(long id) throws Exception {
//        given(service.getById(id)).willReturn(breweryResponse());
//
//        mockMvc.perform(get(fromUriString("/brewerys/{id}").buildAndExpand(id).toUri())
//                .accept(APPLICATION_JSON))
//                .andExpect(status().isOk())
//        .andExpect(jsonPath("$.name").value(breweryResponse().name()));
//    }
//}
