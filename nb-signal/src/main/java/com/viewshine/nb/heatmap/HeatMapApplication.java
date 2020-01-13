package com.viewshine.nb.heatmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@EnableDiscoveryClient
@EnableAsync
public class HeatMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeatMapApplication.class, args);
	}

}
