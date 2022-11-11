package com.ipaixao.ibeer.domain.manufacturer;

import com.ipaixao.ibeer.domain.common.DuplicationValidator;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.mapper.ManufacturerMapper;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.response.ManufacturerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Service
@RequiredArgsConstructor(access = PROTECTED)
public class ManufacturerService {

    private final ManufacturerDataProvider dataProvider;
    private final ManufacturerMapper mapper;

    public ManufacturerResponse create(ManufacturerDTO dto) {
        applyValidations(dto);
        return mapper.toResponse(dataProvider.create(mapper.toEntity(dto)));
    }

    public void applyValidations(@NonNull ManufacturerDTO dto) {
        final var duplicatedManufacturer = Optional.of(dto)
                .filter(d -> nonNull(d.id()))
                .flatMap(d -> dataProvider.getByNameAndIdNot(d.name(), d.id()).map(mapper::toDTO))
                .orElseGet(() -> dataProvider.getByName(dto.name()).map(mapper::toDTO).orElse(null));

        new DuplicationValidator()
                .accept(duplicatedManufacturer);
    }

    public Page<ManufacturerResponse> getAll(Pageable pageable) {
        return dataProvider.getAll(pageable).map(mapper::toResponse);
    }

    public ManufacturerResponse getById(long id) {
        return dataProvider.getById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> {
                    final var e = new EntityNotFoundException("Entity not found!");
                    log.error("m=getById, status=error, message={}", e.getMessage(), e);
                    return e;
                });
    }

    public ManufacturerResponse update(ManufacturerDTO dto) {
        return create(dto);
    }

    public void deleteById(long id) {
        dataProvider.deleteById(id);
    }
}
