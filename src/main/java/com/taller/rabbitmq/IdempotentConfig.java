package com.taller.rabbitmq;

import org.apache.camel.spi.IdempotentRepository;
import org.apache.camel.support.processor.idempotent.FileIdempotentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class IdempotentConfig {

    @Bean("fileRepo")
    public IdempotentRepository fileIdempotentRepository() {
        return FileIdempotentRepository
                .fileIdempotentRepository(new File(".filestore.dat"));
    }
}