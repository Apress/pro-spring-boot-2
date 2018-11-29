package com.apress.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SpringBootSimpleApplication {
//Version 11. implementing the CommandLineRunner and the ApplicationRunner.
//public class SpringBootSimpleApplication implements CommandLineRunner, ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringBootSimpleApplication.class);
	
	public static void main(String[] args) throws IOException {
		//Comment and uncomment the versions for testing.
		//Version 1.
		SpringApplication.run(SpringBootSimpleApplication.class, args);

		//Version 2.
		//SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
		//add more features here
		//app.run(args);
		
		
		//Version 3.
		//SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
		//app.setBanner(new Banner() {
		//	@Override
		//	public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
		//		out.print("\n\n\tThis is my own banner!\n\n".toUpperCase());
		//	}
		//});
	    //app.run(args);
	    
		
	    //Version 4.
	  	//SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
	  	//app.setBannerMode(Banner.Mode.OFF);
	  	//app.run(args);
		
		
		//Version 5.
		//new SpringApplicationBuilder()
	    //.bannerMode(Banner.Mode.OFF)
	    //.sources(SpringBootSimpleApplication.class)
	    //.run(args);
		
		//Version 5.1 - There is a class below, you need to uncomment it out.
		//new SpringApplicationBuilder(SpringBootSimpleApplication.class)
		//.child(MyConfig.class)	
	    //.run(args);
		
		
		//Version 6.
		//new SpringApplicationBuilder(SpringBootSimpleApplication.class)
		//.logStartupInfo(false)
		//.run(args);
	
		
		//Version 7. Activate profiles
		//new SpringApplicationBuilder(SpringBootSimpleApplication.class)
		//.profiles("production")
		//.run(args);
		
		
		//Version 8. Add Listeners
		//Logger log = LoggerFactory.getLogger(SpringBootSimpleApplication.class);
		//new SpringApplicationBuilder(SpringBootSimpleApplication.class)
		//.listeners(new ApplicationListener<ApplicationEvent>() {
		//
		//	@Override
		//	public void onApplicationEvent(ApplicationEvent event) {
		//		log.info("#### > " + event.getClass().getCanonicalName());
		//	}
		//	
		//})
		//.run(args);
		
		
		//Version 9. Remove Web
		//new SpringApplicationBuilder(SpringBootSimpleApplication.class)
		//.web(WebApplicationType.NONE)
		//.run(args);
		
		
		//Version 10.
		//SpringApplication.run(SpringBootSimpleApplication.class, args);
		
		
		//Version 11 and 12.
		//SpringApplication.run(SpringBootSimpleApplication.class, args);
		
	}
	

	//Version 11. ApplicationRunner implementation
    /*
	@Bean
	String info(){
		return "Just a simple String bean";
	}
	
	@Autowired
	String info;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("## > ApplicationRunner Implementation...");
		log.info("Accessing the Info bean: " + info);
		args.getNonOptionArgs().forEach(file -> log.info(file));
	}
	@Override
	public void run(String... args) throws Exception {
		log.info("## > CommandLineRunner Implementation...");
		log.info("Accessing the Info bean: " + info);
		for(String arg:args)
			log.info(arg);
	}
    */
	
	
	//Version 12. CommandLineRunner
	/*
	@Bean
	String info(){
		return "Just a simple String bean";
	}
	
	@Autowired
	String info;
	
	@Bean
	CommandLineRunner myMethod(){
		return args -> {
			log.info("## > CommandLineRunner Implementation...");
			log.info("Accessing the Info bean: " + info);
			for(String arg:args)
				log.info(arg);
		};
	}
	*/
}

//Version 5.1
//@Configuration
//class MyConfig{
	
//	@Bean
//	String text(){
//		return "Hi there!";
//	}
//}


//Version 10.

//@Component
//class MyComponent {
//
//	 private static final Logger log = LoggerFactory.getLogger(MyComponent.class);
//
//	 @Autowired
//	 public MyComponent(ApplicationArguments args) {
//		 	//Step. Get if enable argument was passed in the form:  --enable or --enable=true
//		 	boolean enable = args.containsOption("enable");
//	        if(enable)
//	        	log.info("## > You are enable!");
//
//	        //Step. Get argument files in the form:  myfile.txt  or files=["myfile.txt"]  or  enable=true, files=["myfile.txt"]
//	        log.info("## > extra args...");
//	        List<String> _args = args.getNonOptionArgs();
//	        if(!_args.isEmpty())
//	        	_args.forEach(file -> log.info(file));
//	 }
//}


