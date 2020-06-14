package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.consumingwebservice.wsdl.GetCountryResponse;

import java.util.Scanner;

@SpringBootApplication
public class ConsumingWebServiceApplication {
    Producer p = new Producer();
    Consumer R1 = new Consumer("Thread-1",p );
    Consumer R2 = new Consumer("Thread-2",p);
	Scanner scan=new Scanner(System.in);
	public static void main(String[] args) {
		SpringApplication.run(ConsumingWebServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(CountryClient quoteClient) {
		return args -> {
			System.out.println("Enter Country name");
			String country = scan.next();
			//String country = "Spain";

			if (args.length > 0) {
				country = args[0];
			}
			GetCountryResponse response = quoteClient.getCountry(country);
			if(response.getCountry().getCurrency().name() == "EUR") {
                R1.start();
                R2.start();
                p.produce();
            }
			System.err.println(response.getCountry().getCurrency());
		};
	}

}