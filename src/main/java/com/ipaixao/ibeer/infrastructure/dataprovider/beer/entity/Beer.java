package com.ipaixao.ibeer.infrastructure.dataprovider.beer.entity;

import com.ipaixao.ibeer.infrastructure.dataprovider.common.AuditMetadata;
import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.entity.Brewery;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.CascadeType.PERSIST;
import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "beer")
@AllArgsConstructor(access = PRIVATE)
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer ibu;

    @Column(nullable = false)
    private BigDecimal abv;

    @Column(nullable = false)
    private String style;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer milliliter;

    @ToString.Exclude
    @JoinColumn(name = "brewery_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST})
    private Brewery brewery;

    @Embedded
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AuditMetadata auditMetadata = new AuditMetadata();
}
