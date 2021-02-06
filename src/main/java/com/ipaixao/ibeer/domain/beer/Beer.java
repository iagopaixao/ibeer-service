package com.ipaixao.ibeer.domain.beer;

import com.ipaixao.ibeer.domain.common.DateAudit;
import com.ipaixao.ibeer.domain.manufacturer.Manufacturer;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Beer {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "beer_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "beer_seq", sequenceName = "beer_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer ibu;

    @Column(nullable = false)
    private Double abv;

    @Column(nullable = false)
    private String style;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer milliliter;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Embedded
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DateAudit dateAudit = new DateAudit();
}
