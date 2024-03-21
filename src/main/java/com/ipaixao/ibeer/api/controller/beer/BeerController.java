package com.ipaixao.ibeer.api.controller.beer;

import com.ipaixao.ibeer.application.usecase.beer.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/beers")
@Tag(
    name = "Beer Controller",
    description = "Allows you to perform operations on the beer controller"
)
public record BeerController(RegisterBeerUseCase registerBeerUseCase,
                             UpdateBeerUseCase updateBeerUseCase,
                             QueryBeerByIdUseCase queryBeerByIdUseCase,
                             QueryAllBeersUseCase queryAllBeersUseCase,
                             DeleteBeerUseCase deleteBeerUseCase) {

    @PostMapping
    @Operation(summary = "Creates a new Beer", method = "POST")
    public ResponseEntity<BeerResponse> create(@RequestBody @Valid BeerRequest request) {
        return ResponseEntity.created(URI.create("/beers")).body((registerBeerUseCase.create(request)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a Beer by id", method = "GET")
    public ResponseEntity<BeerResponse> getOne(@PathVariable long id) {
        return ResponseEntity.ok(queryBeerByIdUseCase.getById(id));
    }

    @GetMapping
    @Operation(summary = "Gets all Beers", method = "GET")
    public ResponseEntity<Page<BeerResponse>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(queryAllBeersUseCase.getAll(PageRequest.of(page, size)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a Beer", method = "PUT")
    public ResponseEntity<BeerResponse> update(@PathVariable long id, @Valid @RequestBody BeerRequest request) {
        return ResponseEntity.ok(updateBeerUseCase.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclude a Beer by id", method = "DELETE")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        deleteBeerUseCase.deleteById(id);
    }
}