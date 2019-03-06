package cvr.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;

@Configuration
@PropertySource("classpath:file-path.properties")
public class PrintServiceConfig {
    @Value("${language}")
    private String language;


    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setBasename("/i18n/bundle");
        return ms;
    }

    @Bean
    public Iterator<String> scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Locale locale() {
        String[] split = language.split(",");
        return new Locale(split[0], split[1]);
    }

    @Bean
    public Consumer<String> print() {
        return System.out::print;
    }

//    @Bean
//    public Consumer<String> println() {
//        return System.out::println;
//    }
}