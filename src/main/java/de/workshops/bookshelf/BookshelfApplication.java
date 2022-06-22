package de.workshops.bookshelf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class BookshelfApplication {

	@Autowired
	CoursePropertiesConfiguration.WorkshopProperties workshopProperties;

	@Autowired
	CourseProperties courseProperties;

	public static void main(String[] args) {
		SpringApplication.run(BookshelfApplication.class, args);
	}

	@PostConstruct
	public void init() {
		log.info("Your are in a {} days Course for {}",
				courseProperties.getDuration(),
				courseProperties.getName());

		log.info("Du bis in einem {} Tage Workshop für {}",
				workshopProperties.getDuration(),
				workshopProperties.getName());
	}
}
