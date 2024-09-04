package com.ipaixao.ibeer.infrastructure.dataproducer;

import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.domain.beer.gateway.BeerDispatcherKafkaGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaBeerDataProducer implements BeerDispatcherKafkaGateway {
    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private final KafkaTemplate<String, BeerResponse> kafkaTemplate;
    private final BeerResponseMapper mapper;

    @Override
    public void dispatch(BeerDomain beer) {
        final var beerResponse = mapper.toResponse(beer);
        final var beerProducerRecord = new ProducerRecord<String, BeerResponse>(topic, beerResponse);
        log.info("Publishing persisted beer ID={} on the kafka topic={}", beer.id(), topic);

        kafkaTemplate.send(beerProducerRecord)
                     .whenCompleteAsync((result, exception) -> {
                         if (exception == null && result.getProducerRecord() != null) {
                             final var publishedBeer = result.getProducerRecord().value();

                             log.info("Event beer ID={} published successfuly!", publishedBeer.id());
                         } else {
                             log.error("Error to publishing event beer ID={}.", beerResponse.id(), exception);
                         }
                     });
    }
}
