package com.example.demo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.consumingwebservice.wsdl.GetCountryResponse;

@SpringBootApplication
public class ConsumingWebServiceApplication {
	public static boolean bool;
	public static String posted;

	@Bean
	CommandLineRunner lookup(CountryClient quoteClient) {
		return args -> {
			bool = false;
			while(!bool){
				System.out.println("waiting");
			}
			String country = posted;
			if (args.length > 0) {
				country = args[0];
			}
			GetCountryResponse response = quoteClient.getCountry(country);
			System.err.println(response.getCountry().getCurrency());
			if(response.getCountry().getCurrency().name() == "PLN") {
				Producer producer = new Producer();
				Consumer consumer1 = new Consumer("Thread-1",producer);
				Consumer consumer2 = new Consumer("Thread-2",producer);
				consumer1.start();
				consumer2.start();
				producer.produce();
            }
			System.err.println(response.getCountry().getCurrency());
		};
	}

}