package com.ipaixao.ibeer.domain.beer;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class BeerEvent extends ApplicationEvent {
    private final BeerStatus status;

    public BeerEvent(BeerDomain source, BeerStatus status) {
        super(source);
        this.status = status;
    }

    @Getter
    public enum BeerStatus {
        CREATED,
        UPDATED
    }
}
