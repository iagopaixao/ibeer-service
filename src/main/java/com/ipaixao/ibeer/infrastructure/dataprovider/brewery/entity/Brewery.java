package com.ipaixao.ibeer.infrastructure.dataprovider.brewery.entity;

import com.ipaixao.ibeer.infrastructure.dataprovider.beer.entity.Beer;
import com.ipaixao.ibeer.infrastructure.dataprovider.common.AuditMetadata;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.CascadeType.REFRESH;
import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "brewery")
@AllArgsConstructor(access = PRIVATE)
public class Brewery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthplace;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "brewery", orphanRemoval = true, cascade = {REFRESH, DETACH})
    private List<Beer> beers = new ArrayList<>();

    @Embedded
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AuditMetadata auditMetadata = new AuditMetadata();
}
