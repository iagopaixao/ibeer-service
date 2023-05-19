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
class BeerServiceTest {

    @Mock
    private BeerRepository repository;
    @Mock
    private BeerMapper mapper;

    @InjectMocks
    private BeerService service;

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerStub")
    void shouldSuccessfullyCreateABeer_whenCreateIsCalled(Beer beer, BeerDTO beerDTO, BeerResponse beerResponse) {
        when(repository.getIdByName(beer.getName())).thenReturn(Optional.empty());
        when(mapper.toEntity(beerDTO)).thenReturn(beer);
        when(repository.save(beer)).thenReturn(beer);
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.create(beerDTO);

        assertSame(response, beerResponse);
        verify(repository).getIdByName(beer.getName());
        verify(mapper).toEntity(beerDTO);
        verify(repository).save(beer);
        verify(mapper).toResponse(beer);
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerUnPopuledStub")
    void shouldThrowsBeerNameDuplicatedException_whenCreateIsCalled(BeerDTO beerDTO) {
        final var beerNameCaptor = ArgumentCaptor.forClass(String.class);

        when(repository.getIdByName(anyString())).thenReturn(Optional.of(1L));

        final var expectedException = assertThrows(
                NameDuplicatedException.class,
                () -> service.create(beerDTO)
        );

        assertThat(expectedException)
                .isInstanceOf(NameDuplicatedException.class);
        assertTrue(expectedException.getMessage().contains("Name Duplicated!"));
        verify(repository).getIdByName(beerNameCaptor.capture());
        assertSame(beerDTO.name(), beerNameCaptor.getValue());
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beersStub")
    void shouldReturnAllBeersSuccessfully_whenGetAllIsCalled(
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
    void shouldReturnABeerSuccessfully_whenGetByIdIsCalled(long id, Beer beer, BeerResponse beerResponse) {
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findById(anyLong())).thenReturn(Optional.of(beer));
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.getById(id);

        assertSame(response, beerResponse);
        verify(repository).findById((long) idCaptor.capture());
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
        verify(repository).findById((long) idCaptor.capture());
        assertSame(id, idCaptor.getValue());
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerStub")
    void shouldSuccessfullyUpdateABeer_whenUpdateIsCalled(Beer beer, BeerDTO beerDTO, BeerResponse beerResponse) {
        final var beerNameCaptor = ArgumentCaptor.forClass(String.class);
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.getIdByNameAndIdNot(anyString(), anyLong())).thenReturn(Optional.empty());
        when(repository.getIdByName(anyString())).thenReturn(Optional.empty());
        when(mapper.toEntity(beerDTO)).thenReturn(beer);
        when(repository.save(beer)).thenReturn(beer);
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.update(beerDTO);

        assertSame(response, beerResponse);
        verify(repository).getIdByNameAndIdNot(beerNameCaptor.capture(), idCaptor.capture());
        verify(repository).getIdByName(beerNameCaptor.capture());
        assertSame(beerDTO.name(), beerNameCaptor.getValue());
        assertSame(beerDTO.id(), idCaptor.getValue());
        verify(mapper).toEntity(beerDTO);
        verify(repository).save(beer);
        verify(mapper).toResponse(beer);
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.beer.mock.BeerMockFactory#beerUnPopuledStub")
    void shouldThrowsBeerNameDuplicatedException_whenUpdateIsCalled(BeerDTO beerDTO) {
        final var nameCaptor = ArgumentCaptor.forClass(String.class);
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.getIdByNameAndIdNot(anyString(), anyLong())).thenReturn(Optional.of(1L));

        final var expectedException = assertThrows(
                NameDuplicatedException.class,
                () -> service.update(beerDTO)
        );

        assertThat(expectedException)
                .hasMessage("Name Duplicated!")
                .isInstanceOf(NameDuplicatedException.class);
        verify(repository).getIdByNameAndIdNot(nameCaptor.capture(), idCaptor.capture());
        assertSame(beerDTO.name(), nameCaptor.getValue());
        assertSame(beerDTO.id(), idCaptor.getValue());
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
