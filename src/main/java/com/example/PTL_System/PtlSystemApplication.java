package com.example.PTL_System;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class PtlSystemApplication implements ApplicationRunner{

	private static Logger LOG = LoggerFactory.getLogger(PtlSystemApplication.class);

	@Bean
	public WebClient.Builder getWebClientBuilder()
	{
		return WebClient.builder();
	}


	public static void main(String[] args) {
		SpringApplication.run(PtlSystemApplication.class, args);
		LOG.info("START : SpringBoot Application");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOG.info("EXECUTING: Application Runner");

		
	}
}


