package com.ipaixao.ibeer.domain.manufacturer;

import com.ipaixao.ibeer.domain.beer.Beer;
import com.ipaixao.ibeer.domain.common.DateAudit;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Manufacturer {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "manufacturer_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "manufacturer_seq", sequenceName = "manufacturer_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthplace;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Beer> beers;

    @Embedded
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DateAudit dateAudit = new DateAudit();
}
