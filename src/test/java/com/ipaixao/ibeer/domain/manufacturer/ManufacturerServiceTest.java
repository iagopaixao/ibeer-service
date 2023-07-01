package com.ipaixao.ibeer.domain.manufacturer;

import com.ipaixao.ibeer.domain.common.NameDuplicatedException;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.mapper.ManufacturerMapper;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.response.ManufacturerResponse;
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

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManufacturerServiceTest {

    @Mock
    private ManufacturerRepository repository;
    @Mock
    private ManufacturerMapper mapper;

    @InjectMocks
    private ManufacturerService service;

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory#manufacturerPopuledStub")
    void shouldSuccessfullyCreateAManufacturer_whenCreateIsCalled(Manufacturer manufacturer, ManufacturerDTO manufacturerDTO, ManufacturerResponse beerResponse) {
        when(repository.findIdByName(manufacturer.getName())).thenReturn(Optional.empty());
        when(mapper.toEntity(manufacturerDTO)).thenReturn(manufacturer);
        when(repository.save(manufacturer)).thenReturn(manufacturer);
        when(mapper.toResponse(manufacturer)).thenReturn(beerResponse);

        final var response = service.create(manufacturerDTO);

        assertSame(response, beerResponse);
        verify(repository).findIdByName(manufacturer.getName());
        verify(mapper).toEntity(manufacturerDTO);
        verify(repository).save(manufacturer);
        verify(mapper).toResponse(manufacturer);
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory#manufacturerUnPopuledStub")
    void shouldThrowsManufacturerNameDuplicatedException_whenCreateIsCalled(ManufacturerDTO manufacturerDTO) {
        final var nameCaptor = ArgumentCaptor.forClass(String.class);

        when(repository.findIdByName(anyString())).thenReturn(Optional.of(10L));

        final var expectedException = assertThrows(
                NameDuplicatedException.class,
                () -> service.create(manufacturerDTO)
        );

        assertThat(expectedException)
                .isInstanceOf(NameDuplicatedException.class);
        assertTrue(expectedException.getMessage().contains("Name Duplicated!"));
        verify(repository).findIdByName(nameCaptor.capture());
        assertSame(manufacturerDTO.name(), nameCaptor.getValue());
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory#manufacturersStub")
    void shouldReturnAllManufacturersSuccessfully_whenGetAllIsCalled(
            Manufacturer manufacturer,
            PageImpl<Manufacturer> beers,
            ManufacturerResponse beerResponse,
            PageRequest pageRequest) {

        when(repository.findAll(pageRequest)).thenReturn(beers);
        when(mapper.toResponse(manufacturer)).thenReturn(beerResponse);

        final var response = service.getAll(pageRequest);

        assertThat(beerResponse).isIn(response.getContent());
        verify(repository).findAll(pageRequest);
        verify(mapper).toResponse(manufacturer);
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory#manufacturerByIdStub")
    void shouldReturnAManufacturerSuccessfully_whenGetByIdIsCalled(long id, Manufacturer manufacturer, ManufacturerResponse beerResponse) {
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findById(anyLong())).thenReturn(Optional.of(manufacturer));
        when(mapper.toResponse(manufacturer)).thenReturn(beerResponse);

        final var response = service.getById(id);

        assertSame(response, beerResponse);
        verify(repository).findById((long) idCaptor.capture());
        assertSame(id, idCaptor.getValue());
        verify(mapper).toResponse(manufacturer);
    }


    @ParameterizedTest
    @ValueSource(longs = {5, 2L, 7L})
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
    @MethodSource("com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory#manufacturerPopuledStub")
    void shouldSuccessfullyUpdateAManufacturer_whenUpdateIsCalled(Manufacturer manufacturer, ManufacturerDTO manufacturerDTO, ManufacturerResponse beerResponse) {
        final var beerNameCaptor = ArgumentCaptor.forClass(String.class);
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findIdByNameAndIdNot(anyString(), anyLong())).thenReturn(Optional.empty());
        when(repository.findIdByName(anyString())).thenReturn(Optional.empty());
        when(mapper.toEntity(manufacturerDTO)).thenReturn(manufacturer);
        when(repository.save(manufacturer)).thenReturn(manufacturer);
        when(mapper.toResponse(manufacturer)).thenReturn(beerResponse);

        final var response = service.update(manufacturerDTO);

        assertSame(response, beerResponse);
        verify(repository).findIdByNameAndIdNot(beerNameCaptor.capture(), idCaptor.capture());
        verify(repository).findIdByName(beerNameCaptor.capture());
        assertSame(manufacturerDTO.name(), beerNameCaptor.getValue());
        assertSame(manufacturerDTO.id(), idCaptor.getValue());
        verify(mapper).toEntity(manufacturerDTO);
        verify(repository).save(manufacturer);
        verify(mapper).toResponse(manufacturer);
    }

    @ParameterizedTest
    @MethodSource("com.ipaixao.ibeer.domain.manufacturer.mock.ManufacturerMockFactory#manufacturerUnPopuledStub")
    void shouldThrowsNameDuplicatedException_whenUpdateIsCalled(ManufacturerDTO manufacturerDTO) {
        final var nameCaptor = ArgumentCaptor.forClass(String.class);
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findIdByNameAndIdNot(anyString(), anyLong())).thenReturn(Optional.of(1L));

        final var expectedException = assertThrows(
                NameDuplicatedException.class,
                () -> service.update(manufacturerDTO)
        );

        assertThat(expectedException)
                .hasMessage("Name Duplicated!")
                .isInstanceOf(NameDuplicatedException.class);
        verify(repository).findIdByNameAndIdNot(nameCaptor.capture(), idCaptor.capture());
        assertSame(manufacturerDTO.name(), nameCaptor.getValue());
        assertSame(manufacturerDTO.id(), idCaptor.getValue());
    }

    @ParameterizedTest
    @ValueSource(longs = {29L, 35L})
    void shouldSuccessfullyDelete_whenDeleteByIdIsCalled(long id) {
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(id);

        verify(repository).deleteById(idCaptor.capture());
        assertSame(id, idCaptor.getValue());
    }

}
