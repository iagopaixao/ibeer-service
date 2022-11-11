package com.ipaixao.ibeer.domain.beer;

import com.ipaixao.ibeer.domain.common.DateAudit;
import com.ipaixao.ibeer.domain.manufacturer.Manufacturer;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Table
@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Beer {

    @Id
    @Column(name = "id", unique = true, nullable = false)
//    @GeneratedValue(generator = "beer_id_seq", strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ToString.Exclude
    private Manufacturer manufacturer;

    @Embedded
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DateAudit dateAudit = new DateAudit();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Beer beer = (Beer) o;
        return id != null && Objects.equals(id, beer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
