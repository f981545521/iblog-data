package cn.acyou.iblogdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)//如需要将服务注册到Eureka Server，需要设置为true
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
