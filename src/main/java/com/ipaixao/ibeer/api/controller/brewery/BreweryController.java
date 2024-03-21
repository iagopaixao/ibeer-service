package com.ipaixao.ibeer.api.controller.brewery;

import com.ipaixao.ibeer.application.usecase.brewery.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/brewerys")
@Tag(
        name = "Brewery Controller",
        description = "Allows you to perform operations on the brewery controller"
)
public record BreweryController(RegisterBreweryUseCase registerBreweryUseCase,
                                UpdateBreweryUseCase updateBreweryUseCase,
                                QueryBreweryByIdUseCase queryBreweryByIdUseCase,
                                QueryAllBreweriesUseCase queryAllBreweriesUseCase,
                                DeleteBreweryUseCase deleteBreweryUseCase) {

    @PostMapping
    @Operation(summary = "Creates a new Brewery", method = "POST")
    public ResponseEntity<BreweryResponse> create(@RequestBody @Valid BreweryRequest request) {
        return ResponseEntity.created(URI.create("/breweries")).body((registerBreweryUseCase.create(request)));
    }

    @GetMapping
    @Operation(summary = "Gets all Breweries", method = "GET")
    public ResponseEntity<Page<BreweryResponse>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(queryAllBreweriesUseCase.getAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a Brewery by id", method = "GET")
    public ResponseEntity<BreweryResponse> getOne(@PathVariable long id) {
        return ResponseEntity.ok(queryBreweryByIdUseCase.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a Brewery", method = "PUT")
    public ResponseEntity<BreweryResponse> update(@PathVariable long id, @Valid @RequestBody BreweryRequest request) {
        return ResponseEntity.ok(updateBreweryUseCase.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclude a Brewery by id", method = "DELETE")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Min(value = 1, message = "{min_field_value}") long id) {
        deleteBreweryUseCase.deleteById(id);
    }
}