package com.ipaixao.ibeer.domain.manufacturer;

import com.ipaixao.ibeer.domain.beer.Beer;
import com.ipaixao.ibeer.domain.common.DateAudit;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
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
public class Manufacturer {

    @Id
    @Column(name = "id", unique = true, nullable = false)
//    @GeneratedValue(generator = "manufacturer_id_seq", strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "manufacturer_seq", sequenceName = "manufacturer_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthplace;

    @ToString.Exclude
    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Beer> beers;

    @Embedded
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DateAudit dateAudit = new DateAudit();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Manufacturer that = (Manufacturer) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
