package com.ipaixao.ibeer.api.broker;

import com.ipaixao.ibeer.domain.beer.BeerDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class CreatedBeerEvent extends ApplicationEvent {
    private String status;

    public CreatedBeerEvent(BeerDomain source, String status) {
        super(source);
        this.status = status;
    }
}
