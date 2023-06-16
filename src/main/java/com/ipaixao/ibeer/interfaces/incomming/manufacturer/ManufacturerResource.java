package com.ipaixao.ibeer.interfaces.incomming.manufacturer;

import com.ipaixao.ibeer.domain.manufacturer.ManufacturerService;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.response.ManufacturerResponse;
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
@RequestMapping("/manufacturers")
@Tag(
        name = "Manufacturer Resource",
        description = "Allows you to perform operations on the manufacturer resource"
)
public record ManufacturerResource(ManufacturerService service) {

    @PostMapping
    @Operation(summary = "Creates a new Manufacturer", method = "POST")
    public ResponseEntity<ManufacturerResponse> create(@RequestBody @Valid ManufacturerDTO dto) {
        return ResponseEntity.created(URI.create("/manufactures")).body((service.create(dto)));
    }

    @GetMapping
    @Operation(summary ="Gets all Manufacturers", method = "GET")
    public ResponseEntity<Page<ManufacturerResponse>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a Manufacturer by id", method = "GET")
    public ResponseEntity<ManufacturerResponse> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping
    @Operation(summary = "Updates a Manufacturer", method = "PUT")
    public ResponseEntity<ManufacturerResponse> update(@Valid @RequestBody ManufacturerDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclude a Manufacturer by id", method = "DELETE")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Min(value = 1, message = "{min_field_value}") long id) {
        service.deleteById(id);
    }
}