package com.ipaixao.ibeer.domain.beer;

import com.ipaixao.ibeer.domain.common.DuplicationValidator;
import com.ipaixao.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.ipaixao.ibeer.interfaces.incomming.beer.mapper.BeerMapper;
import com.ipaixao.ibeer.interfaces.incomming.beer.response.BeerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import static java.util.Objects.nonNull;
import static java.util.Optional.of;
import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Service
@RequiredArgsConstructor(access = PROTECTED)
public class BeerService {

    private final BeerRepository repository;
    private final BeerMapper mapper;

    @Transactional
    public BeerResponse create(BeerDTO dto) {
        applyValidations(dto);
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    private void applyValidations(@NonNull BeerDTO dto) {
        final var duplicatedBeer = of(dto)
                .filter(d -> nonNull(d.id()))
                .flatMap(d -> repository.getIdByNameAndIdNot(d.name(), d.id()))
                .orElseGet(() -> repository.getIdByName(dto.name()).orElse(null));

        new DuplicationValidator()
                .accept(duplicatedBeer);
    }

    @Transactional(readOnly = true)
    public Page<BeerResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public BeerResponse getById(long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> {
                    final var e = new EntityNotFoundException("Entity not found!");
                    log.error("m=getById, status=error, message={}", e.getMessage(), e);
                    return e;
                });
    }

    @Transactional
    public BeerResponse update(BeerDTO dto) {
        return create(dto);
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
