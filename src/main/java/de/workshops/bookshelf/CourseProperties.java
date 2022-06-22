package de.workshops.bookshelf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
@ConfigurationProperties("course")
@Data
public class CourseProperties {
    private String name;
    private int duration;
}

