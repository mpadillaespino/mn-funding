package com.mfpe;

import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class DummyDataProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DummyDataProvider.class);
    private final TransactionsRepository transactionsRepository;

    public DummyDataProvider(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @Scheduled(fixedDelay = "1s")
    void generate(){
        transactionsRepository.save(new Transaction(
                UUID.randomUUID().toString(),
                "SYMBOL",
                randomValue()));
        LOG.info("Content {}", transactionsRepository.findAll().size());
    }

    private BigDecimal randomValue() {
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1,100));
    }

}
