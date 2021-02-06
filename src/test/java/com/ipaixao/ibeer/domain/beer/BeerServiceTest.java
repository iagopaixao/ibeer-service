package com.ipaixao.ibeer.domain.beer;

import com.ipaixao.ibeer.domain.common.NameDuplicatedException;
import com.ipaixao.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.ipaixao.ibeer.interfaces.incomming.beer.mapper.BeerMapper;
import com.ipaixao.ibeer.interfaces.incomming.beer.response.BeerResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    @Mock
    private BeerRepository repository;
    @Mock
    private BeerMapper mapper;

    @InjectMocks
    private BeerService service;

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerStub")
    void shouldSuccessfullyCreateABeer_whenCreateIsCalled(Beer beer, BeerDTO beerDTO, BeerResponse beerResponse) {
        when(repository.findByName(beer.getName())).thenReturn(Optional.empty());
        when(mapper.toEntity(beerDTO)).thenReturn(beer);
        when(repository.save(beer)).thenReturn(beer);
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.create(beerDTO);

        assertSame(response, beerResponse);
        verify(repository).findByName(beer.getName());
        verify(mapper).toEntity(beerDTO);
        verify(repository).save(beer);
        verify(mapper).toResponse(beer);
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerStub")
    void shouldThrowsBeerNameDuplicatedException_whenCreateIsCalled(Beer beer, BeerDTO beerDTO) {
        final var beerNameCaptor = ArgumentCaptor.forClass(String.class);
        final var beerCaptor = ArgumentCaptor.forClass(Beer.class);

        when(repository.findByName(anyString())).thenReturn(Optional.of(beer));
        when(mapper.toDTO(any(Beer.class))).thenReturn(beerDTO);

        final var expectedException = assertThrows(
                NameDuplicatedException.class,
                () -> service.create(beerDTO)
        );

        assertThat(expectedException)
                .isInstanceOf(NameDuplicatedException.class);
        assertTrue(expectedException.getMessage().contains("Name Duplicated!"));
        verify(repository).findByName(beerNameCaptor.capture());
        verify(mapper).toDTO(beerCaptor.capture());
        assertSame(beer, beerCaptor.getValue());
        assertSame(beerDTO.name(), beerNameCaptor.getValue());
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beersStub")
    public void shouldReturnAllBeersSuccessfully_whenGetAllIsCalled(
            Beer beer,
            PageImpl<Beer> beers,
            BeerResponse beerResponse,
            PageRequest pageRequest) {

        when(repository.findAll(pageRequest)).thenReturn(beers);
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.getAll(pageRequest);

        assertThat(beerResponse).isIn(response.getContent());
        verify(repository).findAll(pageRequest);
        verify(mapper).toResponse(beer);
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerByIdStub")
    public void shouldReturnABeerSuccessfully_whenGetByIdIsCalled(long id, Beer beer, BeerResponse beerResponse) {
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findById(anyLong())).thenReturn(Optional.of(beer));
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.getById(id);

        assertSame(response, beerResponse);
        verify(repository).findById(idCaptor.capture());
        assertSame(id, idCaptor.getValue());
        verify(mapper).toResponse(beer);
    }


    @ParameterizedTest
    @ValueSource(longs = {10L, 9L, 23L})
    void shouldThrowsEntityNotFoundException_whenGetByIdIsCalled(long id) {
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        final var actualException = assertThrows(
                EntityNotFoundException.class,
                () -> service.getById(id)
        );

        assertThat(actualException)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Entity not found!");
        verify(repository).findById(idCaptor.capture());
        assertSame(id, idCaptor.getValue());
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerStub")
    void shouldSuccessfullyUpdateABeer_whenUpdateIsCalled(Beer beer, BeerDTO beerDTO, BeerResponse beerResponse) {
        final var beerNameCaptor = ArgumentCaptor.forClass(String.class);
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findByNameAndIdNot(anyString(), anyLong())).thenReturn(Optional.empty());
        when(repository.findByName(anyString())).thenReturn(Optional.empty());
        when(mapper.toEntity(beerDTO)).thenReturn(beer);
        when(repository.save(beer)).thenReturn(beer);
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.update(beerDTO);

        assertSame(response, beerResponse);
        verify(repository).findByNameAndIdNot(beerNameCaptor.capture(), idCaptor.capture());
        verify(repository).findByName(beerNameCaptor.capture());
        assertSame(beerDTO.name(), beerNameCaptor.getValue());
        assertSame(beerDTO.id(), idCaptor.getValue());
        verify(mapper).toEntity(beerDTO);
        verify(repository).save(beer);
        verify(mapper).toResponse(beer);
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerStub")
    void shouldThrowsBeerNameDuplicatedException_whenUpdateIsCalled(Beer beer, BeerDTO beerDTO) {
        final var beerNameCaptor = ArgumentCaptor.forClass(String.class);
        final var idCaptor = ArgumentCaptor.forClass(Long.class);
        final var beerCaptor = ArgumentCaptor.forClass(Beer.class);

        when(repository.findByNameAndIdNot(anyString(), anyLong())).thenReturn(Optional.of(beer));
        when(mapper.toDTO(any(Beer.class))).thenReturn(beerDTO);

        final var expectedException = assertThrows(
                NameDuplicatedException.class,
                () -> service.update(beerDTO)
        );

        assertThat(expectedException)
                .hasMessage("Name Duplicated!")
                .isInstanceOf(NameDuplicatedException.class);
        verify(repository).findByNameAndIdNot(beerNameCaptor.capture(), idCaptor.capture());
        verify(mapper).toDTO(beerCaptor.capture());
        assertSame(beerDTO.name(), beerNameCaptor.getValue());
        assertSame(beerDTO.id(), idCaptor.getValue());
        assertSame(beer, beerCaptor.getValue());
    }

    @ParameterizedTest
    @ValueSource(longs = {11L, 5L})
    void shouldSuccessfullyDelete_whenDeleteByIdIsCalled(long id) {
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(id);

        verify(repository).deleteById(idCaptor.capture());
        assertSame(id, idCaptor.getValue());
    }

}
