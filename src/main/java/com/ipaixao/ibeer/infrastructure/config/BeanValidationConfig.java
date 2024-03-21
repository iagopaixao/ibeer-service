package com.ipaixao.ibeer.infrastructure.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class BeanValidationConfig {

    @Bean
    @DependsOn("messageSource")
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        final var validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);

        return validator;
    }

    @Bean
    public MessageSource messageSource() {
        final var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:config.i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}
