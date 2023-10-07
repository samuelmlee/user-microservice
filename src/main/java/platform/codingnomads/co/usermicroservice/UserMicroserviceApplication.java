package platform.codingnomads.co.usermicroservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import platform.codingnomads.co.usermicroservice.service.InitDataService;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
public class UserMicroserviceApplication implements CommandLineRunner {

    private final InitDataService initDataService;

    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initDataService.initAppData();
    }
}
