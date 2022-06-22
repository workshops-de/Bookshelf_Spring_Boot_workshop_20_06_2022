package de.workshops.bookshelf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties({CoursePropertiesConfiguration.WorkshopProperties.class})
@PropertySource("classpath:global.properties")
public class CoursePropertiesConfiguration {
    @ConfigurationProperties("course")
    public static class WorkshopProperties {
        private String name;
        private int duration;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }
}
