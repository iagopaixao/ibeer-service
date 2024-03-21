//package com.ipaixao.ibeer.domain.brewery;
//
//import com.ipaixao.ibeer.application.usecase.brewery.mapper.BreweryRequestMapper;
//import com.ipaixao.ibeer.infrastructure.dataprovider.service.BreweryService;
//import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.entity.Brewery;
//import com.ipaixao.ibeer.domain.exception.NameDuplicatedException;
//import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.repository.JpaBreweryRepository;
//import com.ipaixao.ibeer.api.response.BreweryResponse;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//
//import jakarta.persistence.EntityNotFoundException;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class BreweryServiceTest {
//
//    @Mock
//    private JpaBreweryRepository repository;
//    @Mock
//    private BreweryRequestMapper mapper;
//
//    @InjectMocks
//    private BreweryService service;
//
//    @ParameterizedTest
//    @MethodSource("com.ipaixao.ibeer.domain.brewery.mock.BreweryMockFactory#breweryPopuledStub")
//    void shouldSuccessfullyCreateABrewery_whenCreateIsCalled(Brewery brewery, BreweryDTO breweryDTO, BreweryResponse beerResponse) {
//        when(repository.findIDByName(brewery.getName())).thenReturn(Optional.empty());
//        when(mapper.toEntity(breweryDTO)).thenReturn(brewery);
//        when(repository.save(brewery)).thenReturn(brewery);
//        when(mapper.toResponse(brewery)).thenReturn(beerResponse);
//
//        final var response = service.create(breweryDTO);
//
//        assertSame(response, beerResponse);
//        verify(repository).findIDByName(brewery.getName());
//        verify(mapper).toEntity(breweryDTO);
//        verify(repository).save(brewery);
//        verify(mapper).toResponse(brewery);
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.ipaixao.ibeer.domain.brewery.mock.BreweryMockFactory#breweryUnPopuledStub")
//    void shouldThrowsBreweryNameDuplicatedException_whenCreateIsCalled(BreweryDTO breweryDTO) {
//        final var nameCaptor = ArgumentCaptor.forClass(String.class);
//
//        when(repository.findIDByName(anyString())).thenReturn(Optional.of(10L));
//
//        final var expectedException = assertThrows(
//                NameDuplicatedException.class,
//                () -> service.create(breweryDTO)
//        );
//
//        assertThat(expectedException)
//                .isInstanceOf(NameDuplicatedException.class);
//        assertTrue(expectedException.getMessage().contains("Name Duplicated!"));
//        verify(repository).findIDByName(nameCaptor.capture());
//        assertSame(breweryDTO.name(), nameCaptor.getValue());
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.ipaixao.ibeer.domain.brewery.mock.BreweryMockFactory#brewerysStub")
//    void shouldReturnAllBrewerysSuccessfully_whenGetAllIsCalled(
//            Brewery brewery,
//            PageImpl<Brewery> beers,
//            BreweryResponse beerResponse,
//            PageRequest pageRequest) {
//
//        when(repository.findAll(pageRequest)).thenReturn(beers);
//        when(mapper.toResponse(brewery)).thenReturn(beerResponse);
//
//        final var response = service.getAll(pageRequest);
//
//        assertThat(beerResponse).isIn(response.getContent());
//        verify(repository).findAll(pageRequest);
//        verify(mapper).toResponse(brewery);
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.ipaixao.ibeer.domain.brewery.mock.BreweryMockFactory#breweryByIdStub")
//    void shouldReturnABrewerySuccessfully_whenGetByIdIsCalled(long id, Brewery brewery, BreweryResponse beerResponse) {
//        final var idCaptor = ArgumentCaptor.forClass(Long.class);
//
//        when(repository.findById(anyLong())).thenReturn(Optional.of(brewery));
//        when(mapper.toResponse(brewery)).thenReturn(beerResponse);
//
//        final var response = service.getById(id);
//
//        assertSame(response, beerResponse);
//        verify(repository).findById((long) idCaptor.capture());
//        assertSame(id, idCaptor.getValue());
//        verify(mapper).toResponse(brewery);
//    }
//
//
//    @ParameterizedTest
//    @ValueSource(longs = {5, 2L, 7L})
//    void shouldThrowsEntityNotFoundException_whenGetByIdIsCalled(long id) {
//        final var idCaptor = ArgumentCaptor.forClass(Long.class);
//
//        when(repository.findById(anyLong())).thenReturn(Optional.empty());
//
//        final var actualException = assertThrows(
//                EntityNotFoundException.class,
//                () -> service.getById(id)
//        );
//
//        assertThat(actualException)
//                .isInstanceOf(EntityNotFoundException.class)
//                .hasMessage("Entity not found!");
//        verify(repository).findById((long) idCaptor.capture());
//        assertSame(id, idCaptor.getValue());
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.ipaixao.ibeer.domain.brewery.mock.BreweryMockFactory#breweryPopuledStub")
//    void shouldSuccessfullyUpdateABrewery_whenUpdateIsCalled(Brewery brewery, BreweryDTO breweryDTO, BreweryResponse beerResponse) {
//        final var beerNameCaptor = ArgumentCaptor.forClass(String.class);
//        final var idCaptor = ArgumentCaptor.forClass(Long.class);
//
//        when(repository.findIDByNameAndIdNot(anyString(), anyLong())).thenReturn(Optional.empty());
//        when(repository.findIDByName(anyString())).thenReturn(Optional.empty());
//        when(mapper.toEntity(breweryDTO)).thenReturn(brewery);
//        when(repository.save(brewery)).thenReturn(brewery);
//        when(mapper.toResponse(brewery)).thenReturn(beerResponse);
//
//        final var response = service.update(breweryDTO);
//
//        assertSame(response, beerResponse);
//        verify(repository).findIDByNameAndIdNot(beerNameCaptor.capture(), idCaptor.capture());
//        verify(repository).findIDByName(beerNameCaptor.capture());
//        assertSame(breweryDTO.name(), beerNameCaptor.getValue());
//        assertSame(breweryDTO.id(), idCaptor.getValue());
//        verify(mapper).toEntity(breweryDTO);
//        verify(repository).save(brewery);
//        verify(mapper).toResponse(brewery);
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.ipaixao.ibeer.domain.brewery.mock.BreweryMockFactory#breweryUnPopuledStub")
//    void shouldThrowsNameDuplicatedException_whenUpdateIsCalled(BreweryDTO breweryDTO) {
//        final var nameCaptor = ArgumentCaptor.forClass(String.class);
//        final var idCaptor = ArgumentCaptor.forClass(Long.class);
//
//        when(repository.findIDByNameAndIdNot(anyString(), anyLong())).thenReturn(Optional.of(1L));
//
//        final var expectedException = assertThrows(
//                NameDuplicatedException.class,
//                () -> service.update(breweryDTO)
//        );
//
//        assertThat(expectedException)
//                .hasMessage("Name Duplicated!")
//                .isInstanceOf(NameDuplicatedException.class);
//        verify(repository).findIDByNameAndIdNot(nameCaptor.capture(), idCaptor.capture());
//        assertSame(breweryDTO.name(), nameCaptor.getValue());
//        assertSame(breweryDTO.id(), idCaptor.getValue());
//    }
//
//    @ParameterizedTest
//    @ValueSource(longs = {29L, 35L})
//    void shouldSuccessfullyDelete_whenDeleteByIdIsCalled(long id) {
//        final var idCaptor = ArgumentCaptor.forClass(Long.class);
//
//        doNothing().when(repository).deleteById(anyLong());
//
//        service.deleteById(id);
//
//        verify(repository).deleteById(idCaptor.capture());
//        assertSame(id, idCaptor.getValue());
//    }
//
//}
