package com.ipaixao.ibeer.application.listener;

import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.domain.beer.CreatedBeerEvent;
import com.ipaixao.ibeer.domain.beer.gateway.CreatedBeerKafkaDispatcherGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatedBeerListener {
    private final CreatedBeerKafkaDispatcherGateway kafkaDispatcherGateway;

    @Async
    @TransactionalEventListener(classes = CreatedBeerEvent.class, condition = "#event != null")
    public void handlerCreatedBeerEvent(CreatedBeerEvent event) {
        if (event.getSource() instanceof BeerDomain beer) {
            kafkaDispatcherGateway.dispatcher(beer);
            log.info("Event handled successfully. ID={}", beer.id());
        }
    }
}
