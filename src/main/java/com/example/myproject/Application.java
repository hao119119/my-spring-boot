package com.example.myproject;

import com.example.myproject.domain.Customer;
import com.example.myproject.domain.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@RestController
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository){
        return (args) -> {
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            log.info("Customer found with findAll():");
            log.info("------------------------------");
            for (Customer customer: repository.findAll()){
                log.info(customer.toString());
            }
            log.info("");

            //fetch an individual customer by ID
            Customer customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("------------------------------");
            log.info(customer.toString());
            log.info("");

            //fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("------------------------------");
            for (Customer bauer: repository.findByLastName("Bauer")){
                log.info(bauer.toString());
            }
            log.info("");

            log.info("Customer countByLastName(('Bauer'):");
            log.info("------------------------------");
            Long number = repository.countByLastName("Bauer");
            log.info(""+ number);
            log.info("");


        };
    }



    public static void main(String[] args) {
        //System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
//        SpringApplication app = new SpringApplication(Application.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);

    }

}
