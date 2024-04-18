package com.ipaixao.ibeer.domain.beer;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreatedBeerEvent extends ApplicationEvent {
    private final String status;

    public CreatedBeerEvent(BeerDomain source) {
        super(source);
        this.status = "CREATED";
    }
}
