package com.ipaixao.ibeer.domain.manufacturer;

import com.ipaixao.ibeer.domain.beer.Beer;
import com.ipaixao.ibeer.domain.common.DateAudit;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.CascadeType.REFRESH;
import static lombok.AccessLevel.PRIVATE;

@Data
@Table(name = "manufacturer")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthplace;

    @ToString.Exclude
    @OneToMany(mappedBy = "manufacturer", orphanRemoval = true, cascade = {REFRESH, DETACH})
    private List<Beer> beers = new ArrayList<>();

    @Embedded
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DateAudit dateAudit = new DateAudit();
}
