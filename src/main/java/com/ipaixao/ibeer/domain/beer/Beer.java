package com.ipaixao.ibeer.domain.beer;

import com.ipaixao.ibeer.domain.common.DateAudit;
import com.ipaixao.ibeer.domain.manufacturer.Manufacturer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.CascadeType.PERSIST;
import static lombok.AccessLevel.PRIVATE;

@Data
@Table(name = "beer")
@Entity
@Builder
@NoArgsConstructor
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
    @JoinColumn(name = "manufacturer_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST})
    private Manufacturer manufacturer;

    @Embedded
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DateAudit dateAudit = new DateAudit();
}
