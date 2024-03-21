package com.ipaixao.ibeer.api.broker;

import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
import com.ipaixao.ibeer.domain.beer.BeerDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatedBeerProducer {
    @Value("${spring.kafka.producer.topic}")
    private String topic;
    private final KafkaTemplate<String, BeerResponse> kafkaTemplate;
    private final BeerResponseMapper mapper;

    @EventListener(CreatedBeerEvent.class)
    void sendCreatedBeer(CreatedBeerEvent event) {
        if (event.getSource() instanceof BeerDomain beer) {
            final var beerResponse = mapper.toResponse(beer);
            final var beerProducerRecord = new ProducerRecord<String, BeerResponse>(topic, beerResponse);
            log.info("Publishing beer event status={}", event.getStatus());

            kafkaTemplate.send(beerProducerRecord).whenComplete((result, exception) -> {
                if (exception == null) {
                    log.info("Event beer ID={} published successfuly!", result.getProducerRecord().value().id());
                } else {
                    log.error("Error to publishing event.", exception);
                }
            });
        }
    }
}
