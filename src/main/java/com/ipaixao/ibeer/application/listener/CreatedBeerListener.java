package com.ipaixao.ibeer.application.listener;

import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.domain.beer.BeerEvent;
import com.ipaixao.ibeer.domain.beer.gateway.BeerDispatcherKafkaGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatedBeerListener {
    private final BeerDispatcherKafkaGateway kafkaGateway;

    @Async
    @TransactionalEventListener(classes = BeerEvent.class, condition = "#event != null")
    public void handlerCreatedBeerEvent(BeerEvent event) {
        if (event.getSource() instanceof BeerDomain beer) {
            kafkaGateway.dispatch(beer);
            log.info("Event handled successfully. ID={}", beer.id());
        }
    }
}
