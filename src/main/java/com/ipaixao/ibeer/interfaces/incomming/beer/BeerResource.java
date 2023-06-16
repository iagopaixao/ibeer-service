package com.ipaixao.ibeer.interfaces.incomming.beer;

import com.ipaixao.ibeer.domain.beer.BeerService;
import com.ipaixao.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.ipaixao.ibeer.interfaces.incomming.beer.response.BeerResponse;
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
@Tag(name = "Beer Resource", description = "Allows you to perform operations on the beer resource")
public record BeerResource(BeerService service) {

    @PostMapping
    @Operation(summary = "Creates a new Beer", method = "POST")
    public ResponseEntity<BeerResponse> create(@RequestBody @Valid BeerDTO dto) {
        return ResponseEntity.created(URI.create("/beers")).body((service.create(dto)));
    }

    @GetMapping
    @Operation(summary = "Gets all Beers", method = "GET")
    public ResponseEntity<Page<BeerResponse>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a Beer by id", method = "GET")
    public ResponseEntity<BeerResponse> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping
    @Operation(summary = "Updates a Beer", method = "PUT")
    public ResponseEntity<BeerResponse> update(@Valid @RequestBody BeerDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclude a Beer by id", method = "DELETE")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }
}