package com.familyreunificationbackend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.scalars.ExtendedScalars;
@Configuration
public class GraphQlConfiguration {
@Bean
public RuntimeWiringConfigurer configurer(){
    return builder -> builder
    .scalar(ExtendedScalars.DateTime)
    .scalar(ExtendedScalars.GraphQLLong)
    .scalar(ExtendedScalars.GraphQLBigDecimal)
    ;
}
}
