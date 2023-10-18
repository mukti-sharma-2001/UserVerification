package com.ass.mini2.config;


import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import com.ass.mini2.util.AppConstants;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfiguration {
	@Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .baseUrl(AppConstants.url1)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
                        .compress(true)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000) 
                        .responseTimeout(Duration.ofMillis(2000)) 
                        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(2))
                        				.addHandlerLast(new WriteTimeoutHandler(2))))); 
    }
	@Bean
    public WebClient.Builder webClientBuilder1() {
        return WebClient.builder()
                .baseUrl(AppConstants.url2)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
                        .compress(true)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000) 
                        .responseTimeout(Duration.ofMillis(1000)) 
                        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(1))
                        				.addHandlerLast(new WriteTimeoutHandler(1))))); 
    }
	@Bean
    public WebClient.Builder webClientBuilder2() {
        return WebClient.builder()
                .baseUrl(AppConstants.url3)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
                        .compress(true)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000) 
                        .responseTimeout(Duration.ofMillis(1000)) 
                        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(1))
                        				.addHandlerLast(new WriteTimeoutHandler(1))))); 
    }
	

	
}
