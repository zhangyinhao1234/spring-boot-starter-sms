package com.github.zhangyinhao1234.plugin.sms_springboot_example;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@ComponentScan()
public class WebMain {
	public static void main(String[] args) {
		SpringApplication.run(WebMain.class, args);
	}
}
