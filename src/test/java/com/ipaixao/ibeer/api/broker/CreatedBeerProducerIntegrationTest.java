package com.ipaixao.ibeer.api.broker;

import com.ipaixao.ibeer.api.controller.beer.BeerRequest;
import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.api.controller.brewery.BreweryRequest;
import com.ipaixao.ibeer.application.usecase.beer.RegisterBeerUseCase;
import com.ipaixao.ibeer.base.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CreatedBeerProducerIntegrationTest extends BaseIntegrationTest {
    @Autowired
    RegisterBeerUseCase registerBeerUseCase;
    @Value("${spring.kafka.producer.topic}")
    private String topic;

    @BeforeEach
    void setup() {
        final var request = new BeerRequest(
                "Heinenken",
                5,
                new BigDecimal("3.2"),
                "Larger Premium",
                new BigDecimal("4.5"),
                330,
                new BreweryRequest("Heinen", "Amsterdam")
        );
        registerBeerUseCase.create(request);
    }

    @Test
    void shouldSendCreatedBeerEventSucessfuly_whenSendCreatedBeerIsCalled() {
        final var consumer = new EmbeddedKafkaUtils().createEmbeddedConsumer(topic, BeerResponse.class);
        this.embeddedKafkaBroker.consumeFromEmbeddedTopics(consumer, topic);

        final var consumedRecord = KafkaTestUtils.getSingleRecord(consumer, topic);
        assertThat(consumedRecord)
                .satisfies(record ->
                        assertThat(record.value())
                                .isNotNull()
                                .isExactlyInstanceOf(BeerResponse.class)
                                .hasFieldOrPropertyWithValue("id", 1L)
                );
    }
}